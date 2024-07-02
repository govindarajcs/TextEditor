package com.fortify.texteditor.command;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author govin
 * 
 * Class file to handle the command list on Text editor.
 */

public class ListCommand implements TextEditorCommand {

	Lock readLock;
	File file;
	
	public ListCommand(File file) {
		this.file = file;
		this.readLock = new ReentrantReadWriteLock().readLock();
	}
	
	@Override
	public void execute() {
		try {
			readLock.lock();
			// write the code to read the file
			System.out.println("Please find the file content");
		} catch(Exception err) {
			System.out.println("Error in reading the file: "+file.getName());
		} finally {
			readLock.unlock();
		}
	}

}
