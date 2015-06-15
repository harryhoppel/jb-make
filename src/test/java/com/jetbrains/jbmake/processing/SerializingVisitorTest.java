package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.Makefile;
import org.junit.Test;

import java.io.StringWriter;

import static org.junit.Assert.assertEquals;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class SerializingVisitorTest {
    @Test
    public void testOneRuleMakefile() throws Exception {
        final Makefile oneRuleMakefile = MakefileCreatingUtils.createOneRuleMakefile();
        testSerialization("utils.o: utils.c\n" +
                "\tcc -c utils.c\n", oneRuleMakefile);
    }

    @Test
    public void testTwoRulesMakefile() throws Exception {
        final Makefile oneRuleMakefile = MakefileCreatingUtils.createTwoRulesMakefile();
        testSerialization("core.o: core.c\n" +
                "\tcc -c core.c\n" +
                "utils.o: utils.c\n" +
                "\tcc -c utils.c\n", oneRuleMakefile);
    }

    @Test
    public void testTwoDependentRulesMakefile() throws Exception {
        final Makefile oneRuleMakefile = MakefileCreatingUtils.createTwoDependentRulesMakefile();
        testSerialization("all: utils.o\n" +
                "utils.o: utils.c\n" +
                "\tcc -c utils.c\n", oneRuleMakefile);
    }

    @Test
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        testSerialization(
                "all: hello\n" +
                        "hello: main.o\n" +
                        "\tg++ main.o -o hello\n" +
                        "main.o: main.cpp\n" +
                        "\tg++ -c main.cpp\n" +
                "clean:\n" +
                "\trm *o hello\n", originalMakefile);
    }

    private void testSerialization(String expectedOutput, Makefile oneRuleMakefile) {
        final StringWriter writer = new StringWriter();
        final SerializingVisitor serializingVisitor = new SerializingVisitor(writer);
        oneRuleMakefile.accept(serializingVisitor);
        assertEquals(expectedOutput, writer.toString());
    }
}