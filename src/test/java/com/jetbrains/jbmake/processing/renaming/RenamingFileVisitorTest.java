package com.jetbrains.jbmake.processing.renaming;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.processing.MakefileCreatingUtils;
import junit.framework.TestCase;

import java.util.List;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class RenamingFileVisitorTest extends TestCase {
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        originalMakefile.accept(new RenamingFileVisitor("main.cpp", "general.cxx"));

        final List<Rule> ruleList = originalMakefile.getRuleList();
        assertEquals(4, ruleList.size());

        assertEquals(new Rule(new Target(new TargetId("all"), "hello")), ruleList.get(0));

        assertEquals(new Rule(new Target(new TargetId("hello"), "general.o"),
                new Command("g++ general.o -o hello")), ruleList.get(1));

        assertEquals(new Rule(new Target(new TargetId("general.o"), "general.cxx"),
                new Command("g++ -c general.cxx")), ruleList.get(2));

        assertEquals(new Rule(new Target(new TargetId("clean")), new Command("rm *o hello")), ruleList.get(3));

    }
}