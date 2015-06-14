package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.Makefile;
import junit.framework.TestCase;

import java.io.StringWriter;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class SerializingVisitorTest extends TestCase {
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        final StringWriter writer = new StringWriter();
        originalMakefile.accept(new SerializingVisitor(writer));
        assertEquals(
                "all: hello\n" +
                "hello: main.o\n" +
                "\tg++ main.o -o hello\n" +
                "main.o: main.cpp\n" +
                "\tg++ -c main.cpp\n" +
                "clean:\n" +
                "\trm *o hello\n", writer.toString());
    }
}