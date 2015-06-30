package com.jetbrains.jbmake.parser;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.parser.exceptions.ParserException;
import com.jetbrains.jbmake.processing.MakefileCreatingUtils;
import org.junit.Test;

import java.util.List;

import static com.jetbrains.jbmake.parser.ast.Dependency.dep;
import static com.jetbrains.jbmake.parser.ast.Location.loc;
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

    @Test
    public void testRulesWithEmptyLinesBetweenThem() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMakefileWithEmptyLinesBetweenRules();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(
                new Target(new TargetId(loc(1, 1), "hello", loc(1, 5)), loc(1, 6), dep(loc(1, 8), "main.o", loc(1, 13))),
                new Command(loc(2, 2), "g++ main.o -o hello", loc(2, 20))), ruleList.get(0));
        assertEquals(new Rule(
                new Target(new TargetId(loc(4, 1), "test.o", loc(4, 6)), loc(4, 7), dep(loc(4, 9), "test.cpp", loc(4, 16))),
                new Command(loc(5, 2), "g++ -c test.cpp", loc(5, 16))), ruleList.get(1));
    }

    @Test
    public void testMalformedTarget() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMalformedRuleMakefile();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(2, ruleList.size());
        assertEquals(
                new Rule(new Target(new TargetId(loc(1, 1), "core.o", loc(1, 6)), loc(1, 7), dep(loc(1, 9), "core.c", loc(1, 14))),
                new Command(loc(2, 2), "cc -c core.c", loc(2, 13))), ruleList.get(0));
        assertEquals(
                new Rule(new Target(new TargetId(loc(4, 1), "utils.o", loc(4, 7)), loc(4, 8), dep(loc(4, 10), "utils.c", loc(4, 16))),
                        new Command(loc(5, 2), "cc -c utils.c", loc(5, 14))), ruleList.get(1));
    }

    @Test
    public void testOriginalExample() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createOriginalMakefile();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(4, ruleList.size());

        assertEquals(new Rule(
                new Target(new TargetId(loc(1, 1), "all", loc(1, 3)), loc(1, 4),
                        dep(loc(1, 6), "hello", loc(1, 10)))), ruleList.get(0));

        assertEquals(new Rule(new Target(new TargetId(loc(2, 1), "hello", loc(2, 5)), loc(2, 6),
                dep(loc(2, 8), "main.o", loc(2, 13))), new Command(loc(3, 2), "g++ main.o -o hello", loc(3, 20))), ruleList.get(1));

        assertEquals(new Rule(
                new Target(new TargetId(loc(4, 1), "main.o", loc(4, 6)), loc(4, 7),
                        dep(loc(4, 9), "main.cpp", loc(4, 16))),
                new Command(loc(5, 2), "g++ -c main.cpp", loc(5, 16))), ruleList.get(2));

        assertEquals(new Rule(new Target(new TargetId(loc(6, 1), "clean", loc(6, 5)), loc(6, 6)),
                new Command(loc(7, 2), "rm *o hello", loc(7, 12))), ruleList.get(3));
    }

    @Test
    public void testManualExample() throws Exception {
        Makefile nodeTree = MakefileCreatingUtils.createMakefileFromManual();
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(10, ruleList.size());
    }
}
