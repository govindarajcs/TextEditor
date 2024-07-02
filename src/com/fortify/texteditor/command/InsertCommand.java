package com.fortify.texteditor.command;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author govin
 *
 * Class to handle insert operation on a text editor
 */

public class InsertCommand implements TextEditorCommand {

	Lock writeLock;
	File file;
	int lineNo;
	String input;
	
	public InsertCommand(File file, int lineNo, String input) {
		this.file = file;
		this.writeLock = new ReentrantReadWriteLock().writeLock();
		this.lineNo = lineNo;
		this.input = input;
	}
	
	@Override
	public void execute() {
		
		try {
			writeLock.lock();
			// code to insert the line 
			System.out.println("Insert the line "+input+" in the file "+file.getName()+" at line "+lineNo);
			
		} catch(Exception err) {
			System.out.println("Error in inserting the line "+this.input+" in the file "+this.file.getName()+ "at line Numner "+lineNo);
		} finally {
			writeLock.unlock();
		}
	}


}
