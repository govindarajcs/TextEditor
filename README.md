## Text Editor

## Problem Statement 

Write a line-oriented text editor that reads a text file and allows basic editing commands
 
Usage:
lineeditor c:\temp\myfile.txt
(displays a >> prompt)
 
Commands:
list - list each line in n:xxx format, e.g.
1: first line
2: second line
3: last line
del n - delete line at n
ins n - insert a line at n
save - saves to disk
quit - quits the editor and returns to the command line


## How to run the application

Driver.java is the entry point function 

*  Compile the code using the below command 

javac -d <EDITOR-WORKSPACE>\TextEditor\bin -sourcepath <EDITOR-WORKSPACE>\TextEditor\src src\com\fortify\editor\driver\Driver.java

*  Launch the application using the below command 

java -classpath <EDITOR-WORKSPACE>\TextEditor\bin com.fortify.editor.driver.Driver <Path to the input text file>

## Output

editor>> ins 2
Enter the input text:Hello
Line "Hello" at 2 is added to the file input.txt successfully. Please save the changes in the file

editor>> del 3
Line at 3 is removed from the file input.txt successfully. Please save the changes in the file

editor>>list
1 first line
2 Good
3 hello
4 Hello
5  "fifth Line"
6 fourth line

editor>>save
File D:\Workspace-STS\TextEditor\src\com\fortify\texteditor\command\resource\input.txt saved successfully

editor>>in
Command not supported.Please enter any one of the following commands
1. ins <pos>
2. del
3.list
4.save
5.quit

editor>> quit

Exits the editor application

