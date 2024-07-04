package com.fortify.editor.exception;

@SuppressWarnings("serial")
public class TextEditorException extends EditorException {
	
	String message;

	public TextEditorException(String message) {
		super(message);
		this.message = message;
	}

	public TextEditorException(Exception e) {
		super(e);
	}

}
