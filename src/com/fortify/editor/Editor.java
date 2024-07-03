package com.fortify.editor;

import java.io.File;
import java.util.List;

import com.fortify.editor.exception.EditorException;

public interface Editor {
	
	public void addAtPosition(String inputLine, int position) throws EditorException;
	
	public void delAtPosition(int position) throws EditorException;
	
	public List<String> readFile() throws EditorException;
	
	public void save() throws EditorException;
	
	public File getFile();
	
}
