package com.jetbrains.jbmake.parser;

import com.jetbrains.jbmake.core.MakefileLoaderImpl;
import com.jetbrains.jbmake.parser.ast.*;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class MakefileParserTest {
    @Test
    public void testOriginalExample() throws Exception {
        Makefile nodeTree = MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/parser/original-example.txt");
        final List<Rule> ruleList = nodeTree.getRuleList();
        assertEquals(4, ruleList.size());

        assertEquals(new Rule(new Target(new TargetId("all"), createDependencies("hello"))), ruleList.get(0));

        assertEquals(new Rule(new Target(new TargetId("hello"),
                createDependencies("main.o")), new Command("g++ main.o -o hello")), ruleList.get(1));

        assertEquals(new Rule(new Target(new TargetId("main.o"),
                createDependencies("main.cpp")), new Command("g++ -c main.cpp")), ruleList.get(2));

        assertEquals(new Rule(new Target(new TargetId("clean")), new Command("rm *o hello")), ruleList.get(3));
    }

    private LinkedList<Dependency> createDependencies(String dependencyName) {
        final LinkedList<Dependency> dependencies = new LinkedList<Dependency>();
        dependencies.add(new Dependency(dependencyName));
        return dependencies;
    }
}
