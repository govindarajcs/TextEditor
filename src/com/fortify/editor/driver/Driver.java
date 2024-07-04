package com.fortify.editor.driver;

import java.util.Scanner;

import com.fortify.command.texteditor.TextEditorDeleteCommand;
import com.fortify.command.texteditor.TextEditorInsertCommand;
import com.fortify.command.texteditor.TextEditorListCommand;
import com.fortify.command.texteditor.TextEditorSaveCommand;
import com.fortify.command.utility.EditorOperations;
import com.fortify.editor.Editor;
import com.fortify.editor.TextEditor;
import com.fortify.editor.resource.EditorResource;

/** 
 * @author govin
 *
 * Client/Driver class for the Text editor
 */

public class Driver {
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.println("Please specify the file path in CLI argument");
			System.exit(1);
		}
		
		try(Scanner consoleInput = new Scanner(System.in)) {
			Editor editor = new TextEditor(args[0]);
			EditorResource editorResource = new EditorResource();
			Boolean canExecute;
			String operationInput;
			
			do {
				System.out.print("editor>>");
				operationInput = consoleInput.next();
				canExecute = true;
				if(operationInput.equals(EditorOperations.ins.name())) {
					int line = consoleInput.nextInt();
					System.out.print("Enter the input text:");
					String input = consoleInput.next();
					editorResource.setCommand(new TextEditorInsertCommand(editor, line, input));
				} else if(operationInput.equals(EditorOperations.del.name())) {
					int line = consoleInput.nextInt();
					editorResource.setCommand(new TextEditorDeleteCommand(editor, line));
				} else if(operationInput.equals(EditorOperations.save.name())) {
					editorResource.setCommand(new TextEditorSaveCommand(editor));
				} else if(operationInput.equals(EditorOperations.list.name())) {
					editorResource.setCommand(new TextEditorListCommand(editor));
				} else if(operationInput.equals(EditorOperations.quit.name())) {
					break;
				} else {
					canExecute = false;
				}
				if(canExecute) {
					editorResource.execute();
				} else {
					System.out.println("Command not supported.Please enter any one of the following commands \n1. ins <pos>\n2. del\n3.list\n4.save\n5.quit");
				}
				System.out.println();
			} while(true);
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
	}

}
