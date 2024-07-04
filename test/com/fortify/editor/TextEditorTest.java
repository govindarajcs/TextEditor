/**
 * 
 */
package com.fortify.editor;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fortify.editor.exception.EditorException;

/**
 * @author govin
 *
 */
class TextEditorTest {

	
	TextEditor editor;
	static String filePath;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		filePath = "D:\\Workspace-STS\\TextEditor\\test\\com\\fortify\\editor\\resource\\input.txt";
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		filePath = null;
		System.gc();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		editor = new TextEditor(filePath);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
		editor = null;
	}

	@Test
	void testAddLine() throws EditorException {
		int pos = 6;
		String line = "Sixth Line";
		editor.addAtPosition(line, pos);
		List<String> inputFileString = editor.getInputFileString();
		assertTrue(inputFileString.get(pos-1).equals(line));
		inputFileString = null;
	}
	
	@Test
	void testAddOutOfIndex() {
		int pos = 8;
		String line = "Sixth Line";
		String expectedString = "Index position of the file is not valid. Try between 1 and last line of the file";
		try {
			editor.addAtPosition(line, pos);
		} catch(EditorException e) {
			assertTrue(expectedString.equals(e.getMessage()));
		} finally {
			expectedString = null;
			line = null;
		}
	}
	
	@Test
	void testDeleteLine() {
		int pos = 3;
		List<String> modifiedInputFileString;
		try {
			String delString = editor.getInputFileString().get(pos-1);
			editor.delAtPosition(pos);
			modifiedInputFileString = editor.getInputFileString();
			assertTrue(!modifiedInputFileString.contains(delString));
		} catch(EditorException e) {
			fail();
		} finally {
			modifiedInputFileString = null;
		}
	}
	
	@Test
	void testDeleteLineAtInvalidIndex() {
		int pos = 8;
		String expectedOutput = "index position of the file is not valid. Try between 1 & last line of the file";
		try {
			editor.delAtPosition(pos);
			fail("Test case deletion index invalid scenario fails.");
		} catch(EditorException e) {
			assertTrue(e.getMessage().equals(expectedOutput));
		}
	}
	
	@Test
	void testReadFile() {
		List<String> actualStringList;
		List<String> expectedStringList;
		try {
			actualStringList = editor.readFile();
			expectedStringList = Files.readAllLines(new File(filePath).toPath());
			for(String actualString: actualStringList) {
				if(!expectedStringList.contains(actualString)) {
					fail("Test Scenario list file failed");
				}
			}
			assertTrue(true);
		} catch (IOException e) {
			fail("Test Scenario list file failed");
		} catch (EditorException e) {
			fail("Test Scenario list file failed");
		} finally {
			expectedStringList = null;
			actualStringList = null;
		}
	}
	
	@Test
	void testSave() {
		List<String> originalInputFileString;
		List<String> actualInputFileString;
		String deletedString;
		try {
			originalInputFileString = editor.getInputFileString();
			deletedString = originalInputFileString.get(2-1);
			editor.delAtPosition(2);
			editor.save();
			actualInputFileString = editor.getInputFileString();
			assertTrue(!actualInputFileString.contains(deletedString));
			editor.addAtPosition(deletedString, 2);
			editor.save();
			actualInputFileString = editor.getInputFileString();
			assertTrue(actualInputFileString.contains(deletedString));
		} catch (EditorException e) {
			fail("Test Scenario save failed");
		} finally {
			originalInputFileString = null;
			actualInputFileString = null;
			deletedString = null;
		}
	}
	

}
