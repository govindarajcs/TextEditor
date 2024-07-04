/**
 * @author govin
 *
 * Class to handle save operation on a text editor
 */

package com.fortify.command.texteditor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.fortify.command.Command;
import com.fortify.editor.Editor;
import com.fortify.editor.exception.EditorException;
import com.fortify.editor.exception.TextEditorException;

public class TextEditorSaveCommand implements Command {

	Lock writeLock;
	Editor editor;
	
	public TextEditorSaveCommand(Editor editor) {
		this.editor = editor;
		this.writeLock = new ReentrantReadWriteLock().writeLock();
	}
	
	@Override
	public void execute() throws EditorException {
		
		try {
			writeLock.lock();
			editor.save();
			System.out.println("File "+editor.getFile()+" saved successfully");
			
		} catch(Exception err) {
			throw new TextEditorException("Error in saving the file "+editor.getFile().getName());
		} finally {
			writeLock.unlock();
		}
	}

}
