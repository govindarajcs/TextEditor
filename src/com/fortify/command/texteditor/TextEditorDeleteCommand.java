package com.fortify.command.texteditor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.fortify.command.Command;
import com.fortify.editor.Editor;
import com.fortify.editor.exception.EditorException;
import com.fortify.editor.exception.TextEditorException;

/**
 * @author govind
 *
 * Class to handle delete operation on the text editor
 */

public class TextEditorDeleteCommand implements Command {
	
	Editor editor;
	int position;
	Lock lock;
	
	public TextEditorDeleteCommand(Editor editor, int position) {
		lock = new ReentrantReadWriteLock().writeLock();
		this.editor = editor;
		this.position = position;
	}
	
	@Override
	public void execute() throws EditorException {
		
		try {
			lock.lock();
			this.editor.delAtPosition(position);
		} catch(TextEditorException e) {
			throw new EditorException(e.getLocalizedMessage());
		} finally {
			lock.unlock();
		}
	}

}
