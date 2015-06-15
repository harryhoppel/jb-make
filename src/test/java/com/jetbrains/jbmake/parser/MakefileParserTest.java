package com.jetbrains.jbmake.parser;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.processing.MakefileCreatingUtils;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class MakefileParserTest {
    @Test
    public void testRulesWithEmptyLinesBetweenThem() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMakefileWithEmptyLinesBetweenRules();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("hello"), "main.o"), new Command("g++ main.o -o hello")), ruleList.get(0));
        assertEquals(new Rule(new Target(new TargetId("test.o"), "test.cpp"), new Command("g++ -c test.cpp")), ruleList.get(1));
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
