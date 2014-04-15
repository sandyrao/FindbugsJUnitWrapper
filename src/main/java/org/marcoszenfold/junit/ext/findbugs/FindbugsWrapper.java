/*
	Copyright (c) 2014 Marcos Zenfold
	
	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:
	
	The above copyright notice and this permission notice shall be included in
	all copies or substantial portions of the Software.
	
	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
	THE SOFTWARE.
*/

package org.marcoszenfold.junit.ext.findbugs;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

public class FindbugsWrapper {

	private PrintStream out;
	private ByteArrayOutputStream byteArrOutputStream;

	public int execute(String sourcePath) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, SecurityException, NoSuchMethodException, IOException {
		PrintStream out2 = System.out;
		setupStreams();
		
		Class<?> cls = Class.forName("edu.umd.cs.findbugs.FindBugs2");
		Method main = cls.getMethod("main", String[].class);
		String[] params = {"-home","D:\\Dev\\util\\findbugs-2.0.3","-excludeBugs","BaselineViolations.xml",sourcePath}; // init params accordingly
	//	String[] params = {"-help"}; // init params accordingly
		main.invoke(null, (Object) params); // static invoke
		
		byte[] byteArray = byteArrOutputStream.toByteArray();
		InputStream inputStream=new ByteArrayInputStream(byteArray);
		int len = inputStream.read(byteArray); 
		if(len>0){
			out2.print(new String(byteArray,Charset.forName("UTF-8")));
		}
		 
		return len;
	}
	
	private void setupStreams() {
		byteArrOutputStream = new ByteArrayOutputStream();
		out = new PrintStream(new BufferedOutputStream(byteArrOutputStream) );		
		System.setOut(out); 
	}
	
	public static void main(String[] args) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, IOException {
		new FindbugsWrapper().execute("D:\\Dev\\Workspaces\\Java_WS_Cassowary\\findbugsWrapper\\target\\classes\\MalitiaActus.class");
	}
}
