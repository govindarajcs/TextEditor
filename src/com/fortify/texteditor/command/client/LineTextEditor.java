package com.fortify.texteditor.command.client;

import java.io.File;
import java.util.Scanner;

import com.fortify.texteditor.command.DeleteCommand;
import com.fortify.texteditor.command.EditorOperations;
import com.fortify.texteditor.command.InsertCommand;
import com.fortify.texteditor.command.ListCommand;
import com.fortify.texteditor.command.SaveCommand;
import com.fortify.texteditor.command.TextEditorCommand;

/** 
 * @author govin
 *
 * Client/Driver class for the Text editor
 */

public class LineTextEditor {
	
	public static void main(String[] args) {
		File file = new File(args[0]);
		TextEditorCommand editor = null;
		Boolean canExecute;
		String operationInput;
		try(Scanner consoleInput = new Scanner(System.in)) {
			do {
				System.out.print("editor>>");
				operationInput = consoleInput.next();
				canExecute = true;
				if(operationInput.equals(EditorOperations.ins.name())) {
					int line = consoleInput.nextInt();
					System.out.print("Enter the input text:");
					String input = consoleInput.nextLine();
					editor = new InsertCommand(file, line, input);
				} else if(operationInput.equals(EditorOperations.del.name())) {
					int line = consoleInput.nextInt();
					editor = new DeleteCommand(file, line);
				} else if(operationInput.equals(EditorOperations.save.name())) {
					editor = new SaveCommand(file);
				} else if(operationInput.equals(EditorOperations.list.name())) {
					editor = new ListCommand(file);
				} else {
					canExecute = false;
				}
				if(canExecute) {
					editor.execute();
				} else {
					System.out.println("Command not supported.Please enter any one of the following commands \n1. ins\n2. del\n3.list\n4.save\n5.quit");
				}
				System.out.println();
			} while(!operationInput.equals(EditorOperations.quit.name()));
		}
		
	}

}
