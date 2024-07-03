package com.fortify.editor.resource;

import com.fortify.command.Command;
import com.fortify.editor.exception.EditorException;

public class Notepad {
	
	Command command;
	
	public void setCommand(Command command) {
		this.command = command;
	}



	public void execute() {
		try {
			command.execute();
		} catch (EditorException e) {
			System.out.println(e.getMessage());
		}
	}
}
