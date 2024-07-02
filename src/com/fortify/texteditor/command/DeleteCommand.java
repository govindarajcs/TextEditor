package com.fortify.texteditor.command;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author govin
 *
 * Class to handle delete operation on the text editor
 */

public class DeleteCommand implements TextEditorCommand {
	
	Lock writeLock;
	File file;
	int lineNo;
	public DeleteCommand(File file, int lineNo) {
		this.file = file;
		this.writeLock = new ReentrantReadWriteLock().writeLock();
		this.lineNo = lineNo;
	}
	
	@Override
	public void execute() {
		
		try {
			writeLock.lock();
			// code to delete the line 
			System.out.println("Deleted the line "+lineNo+" in the file "+file.getName());
		} catch(Exception err) {
			System.out.println("Error in deleting the line "+this.lineNo+" in the file "+this.file.getName());
		} finally {
			writeLock.unlock();
		}
	}

}
