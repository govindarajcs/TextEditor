package com.fortify.command.texteditor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.fortify.command.Command;
import com.fortify.editor.Editor;
import com.fortify.editor.exception.EditorException;
import com.fortify.editor.exception.TextEditorException;

/**
 * @author govin
 * 
 * Class file to handle the command list on Text editor.
 */

public class TextEditorListCommand implements Command {

	Lock readLock;
	Editor editor;
	
	public TextEditorListCommand(Editor editor) {
		this.readLock = new ReentrantReadWriteLock().readLock();
		this.editor = editor;
	}
	
	@Override
	public void execute() throws EditorException {
		try {
			readLock.lock();
			int count = 1;
			for(String input: editor.readFile()) {
				System.out.println(count+" "+input);
				count++;
			}
		} catch(Exception err) {
			throw new TextEditorException("Error in reading the file: "+editor.getFile().getName()+ "due to "+err.getLocalizedMessage());
		} finally {
			readLock.unlock();
		}
	}

}
