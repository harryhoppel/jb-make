package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class RootRulesFindingVisitorTest {
    @Test
    public void testOneRuleMakefile() throws Exception {
        final Makefile oneRuleMakefile = MakefileCreatingUtils.createOneRuleMakefile();
        final Set<Rule> rootRules = getRootRules(oneRuleMakefile);
        assertEquals(1, rootRules.size());
        assertTrue(rootRules.contains(
                new Rule(new Target(new TargetId("utils.o"), "utils.c"), new Command("cc -c utils.c"))));
    }

    @Test
    public void testTwoRulesMakefile() throws Exception {
        final Makefile twoRulesMakefile = MakefileCreatingUtils.createTwoRulesMakefile();
        final Set<Rule> rootRules = getRootRules(twoRulesMakefile);
        assertEquals(2, rootRules.size());
        assertTrue(rootRules.contains(
                new Rule(new Target(new TargetId("utils.o"), "utils.c"), new Command("cc -c utils.c"))));
        assertTrue(rootRules.contains(
                new Rule(new Target(new TargetId("core.o"), "core.c"), new Command("cc -c core.c"))));
    }

    @Test
    public void testTwoDependentRulesMakefile() throws Exception {
        final Makefile twoRulesMakefile = MakefileCreatingUtils.createTwoDependentRulesMakefile();
        final Set<Rule> rootRules = getRootRules(twoRulesMakefile);
        assertEquals(1, rootRules.size());
        assertTrue(rootRules.contains(
                new Rule(new Target(new TargetId("all"), "utils.o"))));
    }

    @Test
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        final Set<Rule> rootRules = getRootRules(originalMakefile);
        assertEquals(2, rootRules.size());

        assertTrue(rootRules.contains(new Rule(new Target(new TargetId("clean")), new Command("rm *o hello"))));
        assertTrue(rootRules.contains(new Rule(new Target(new TargetId("all"), "hello"))));
    }

    private Set<Rule> getRootRules(Makefile makefile) {
        final RootRulesFindingVisitor rootRulesFindingVisitor = new RootRulesFindingVisitor();
        makefile.accept(rootRulesFindingVisitor);
        return rootRulesFindingVisitor.getRootRules();
    }
}