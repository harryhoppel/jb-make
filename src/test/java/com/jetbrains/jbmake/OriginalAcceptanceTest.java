package com.jetbrains.jbmake;

import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.processing.MakefileCreatingUtils;
import com.jetbrains.jbmake.processing.SerializingVisitor;
import com.jetbrains.jbmake.processing.renaming.RenamingFileVisitor;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class OriginalAcceptanceTest {
    @Test
    public void testOriginalCase() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        originalMakefile.accept(new RenamingFileVisitor("main.cpp", "general.cxx"));

        final StringWriter writer = new StringWriter();
        originalMakefile.accept(new SerializingVisitor(writer));

        assertEquals("all: hello\n" +
                "hello: general.o\n" +
                "\tg++ general.o -o hello\n" +
                "general.o: general.cxx\n" +
                "\tg++ -c general.cxx\n" +
                "clean:\n" +
                "\trm *o hello\n", writer.toString());
    }
}
