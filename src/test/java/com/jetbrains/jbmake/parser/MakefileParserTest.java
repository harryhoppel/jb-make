package com.jetbrains.jbmake.parser;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.parser.exceptions.ParserException;
import com.jetbrains.jbmake.processing.MakefileCreatingUtils;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class MakefileParserTest {
    @Test
    public void testEmptyFileParsing() throws Exception {
        try {
            MakefileCreatingUtils.createEmptyMakefile();
            fail();
        } catch (ParserException ignore) {}
    }

    @Ignore
    @Test
    public void testRecipeContinuationSupport() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMakefileWithRecipeContinuation();
        final List<Rule> ruleList = nodeTree.getRuleList();
        System.out.println(ruleList);
        assertEquals(1, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("hello"), "main.o"),
                new Command("cc -c utils.c\\\n            test")), ruleList.get(0));
    }

    @Test
    public void testRulesWithEmptyLinesBetweenThem() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMakefileWithEmptyLinesBetweenRules();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("hello"), "main.o"), new Command("g++ main.o -o hello")), ruleList.get(0));
        assertEquals(new Rule(new Target(new TargetId("test.o"), "test.cpp"), new Command("g++ -c test.cpp")), ruleList.get(1));
    }

    @Test
    public void testMalformedTarget() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMalformedRuleMakefile();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("core.o"), "core.c"), new Command("cc -c core.c")), ruleList.get(0));
        assertEquals(new Rule(new Target(new TargetId("utils.o"), "utils.c"), new Command("cc -c utils.c")), ruleList.get(1));
    }

    @Test
    public void testOriginalExample() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createOriginalMakefile();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(4, ruleList.size());

        assertEquals(new Rule(new Target(new TargetId("all"), "hello")), ruleList.get(0));

        assertEquals(new Rule(new Target(new TargetId("hello"),
                "main.o"), new Command("g++ main.o -o hello")), ruleList.get(1));

        assertEquals(new Rule(new Target(new TargetId("main.o"),
                "main.cpp"), new Command("g++ -c main.cpp")), ruleList.get(2));

        assertEquals(new Rule(new Target(new TargetId("clean")), new Command("rm *o hello")), ruleList.get(3));
    }
}
