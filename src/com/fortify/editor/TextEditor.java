package com.fortify.editor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.fortify.editor.exception.EditorException;
import com.fortify.editor.exception.TextEditorException;

public class TextEditor implements Editor {
	
	private List<String> inputFileString;
	private File inputFile;
	
	

	public List<String> getInputFileString() {
		return inputFileString;
	}

	public File getFile() {
		return inputFile;
	}

	public TextEditor(String filePath) throws EditorException {
		this.inputFile = new File(filePath);
		if(inputFile.exists()) {
			try {
				inputFileString = Files.readAllLines(inputFile.toPath());
			} catch (IOException e) {
				throw new TextEditorException("Text Editor Crashed due to "+e.getLocalizedMessage());
			}
		} else {
			throw new TextEditorException("File doesn't exist in the path "+filePath);
		}	
	}
	

	@Override
	public void addAtPosition(String inputLine, int position) throws EditorException {
		/*
		 * If the user wants to enter the string in the last line
		 */
		try {
			if(position == 0|| position>inputFileString.size()+1) {
				throw new TextEditorException("Index position of the file is not valid. Try between 1 and last line of the file");
			}
			inputFileString.add(position-1, inputLine);
		} catch(TextEditorException e) {
			throw new TextEditorException(e.getMessage());
		} catch(Exception e) {
			throw new TextEditorException("Error in inserting the line "+position+" in the file "+inputFile.getName()+" due to "+e.getLocalizedMessage());
		}
		
	}

	@Override
	public void delAtPosition(int position) throws EditorException {
		try {
			if(position == 0 || position>inputFileString.size()) {
				throw new TextEditorException("index position of the file is not valid. Try between 1 & last line of the file");
			} else {
				inputFileString.remove(position-1);
			}
		} catch(TextEditorException e) {
			throw new TextEditorException(e.getLocalizedMessage());
		} catch(Exception e) {
			throw new TextEditorException("Error in deleting the line "+position+" in the file "+inputFile.getName()+" due to "+e.getLocalizedMessage());
		}
	}

	@Override
	public List<String> readFile() throws EditorException {
		
		try {
			List<String> outputString = Files.readAllLines(inputFile.toPath());
			return outputString;
		} catch (IOException e) {
			throw new TextEditorException("Error in reading the input text file "+inputFile.getName());
		}
		
	}

	@Override
	public void save() throws EditorException {
		try {
			Files.deleteIfExists(inputFile.toPath());
			Files.write(inputFile.toPath(), inputFileString);
			inputFileString = Files.readAllLines(inputFile.toPath());
		} catch (IOException e) {
			throw new TextEditorException("Error in saving the file due to "+e.getLocalizedMessage());
		}
		
	}


}
