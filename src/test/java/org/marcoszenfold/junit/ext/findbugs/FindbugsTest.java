package org.marcoszenfold.junit.ext.findbugs;

import static org.junit.Assert.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class FindbugsTest {

	@Test
	public void test() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, ClassNotFoundException, NoSuchMethodException, IOException {
		int resultsLength = new FindbugsWrapper().execute("D:\\Dev\\Workspaces\\Java_WS_Cassowary\\findbugsWrapper");
		assertEquals(-1, resultsLength);
	}

}
