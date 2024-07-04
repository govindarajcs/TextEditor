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
 * Class to handle insert operation on a text editor
 */

public class TextEditorInsertCommand implements Command {

	Editor editor;
	int position;
	Lock lock;
	String input;
	
	public TextEditorInsertCommand(Editor editor, int lineNo, String input) {
		this.editor = editor;
		this.lock = new ReentrantReadWriteLock().writeLock();
		this.position = lineNo;
		this.input = input;
	}
	
	@Override
	public void execute() throws EditorException{
		
		try {
			lock.lock();
			editor.addAtPosition(input, position);
			System.out.println("Line \""+input+"\" at "+position+" is added to the file "+editor.getFile().getName()+" successfully. Please save the changes in the file");
		} catch(Exception err) {
			throw new TextEditorException(err);
		} finally {
			lock.unlock();
		}
	}


}
