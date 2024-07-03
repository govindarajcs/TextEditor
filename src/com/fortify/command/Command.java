package com.fortify.command;

import com.fortify.editor.exception.EditorException;

/**
 * @author govin
 * 
 * Command interface for editor with execute method which defines the business logic for their corresponding command operation.
 */

public interface Command {
	public void execute() throws EditorException;
}
