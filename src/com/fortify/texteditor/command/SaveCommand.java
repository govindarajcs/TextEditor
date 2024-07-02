/**
 * @author govin
 *
 * Class to handle save operation on a text editor
 */

package com.fortify.texteditor.command;

import java.io.File;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class SaveCommand implements TextEditorCommand {

	Lock writeLock;
	File file;
	
	public SaveCommand(File file) {
		this.file = file;
		this.writeLock = new ReentrantReadWriteLock().writeLock();
	}
	
	@Override
	public void execute() {
		
		try {
			writeLock.lock();
			// code to save the line 
			System.out.println("Saving the file "+ file.getName());
			
		} catch(Exception err) {
			System.out.println("Error in saving the file "+this.file.getName());
		} finally {
			writeLock.unlock();
		}
	}

}
