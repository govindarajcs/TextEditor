package com.fortify.editor.exception;

@SuppressWarnings("serial")
public class EditorException extends Exception {
	
	String message;
	
	public EditorException(Exception err) {
		super(err);
	}

	public EditorException(String message) {
		super(message);
		this.message = message;
	}

}
