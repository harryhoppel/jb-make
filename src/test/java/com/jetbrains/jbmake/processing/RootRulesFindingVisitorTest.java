package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;
import org.junit.Test;

import java.util.Set;

import static com.jetbrains.jbmake.parser.ast.Dependency.dep;
import static com.jetbrains.jbmake.parser.ast.Location.loc;
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
                new Rule(
                        new Target(new TargetId(loc(1, 1), "utils.o", loc(1, 7)), loc(1, 8), dep(loc(1, 10), "utils.c", loc(1, 16))),
                        new Command(loc(2, 2), "cc -c utils.c", loc(2, 14)))));
    }

    @Test
    public void testTwoRulesMakefile() throws Exception {
        final Makefile twoRulesMakefile = MakefileCreatingUtils.createTwoRulesMakefile();
        final Set<Rule> rootRules = getRootRules(twoRulesMakefile);
        assertEquals(2, rootRules.size());
        assertTrue(rootRules.contains(
                new Rule(
                        new Target(new TargetId(loc(3, 1), "utils.o", loc(3, 7)), loc(3, 8), dep(loc(3, 10), "utils.c", loc(3, 16))),
                        new Command(loc(4, 2), "cc -c utils.c", loc(4, 14)))));
        assertTrue(rootRules.contains(
                new Rule(
                        new Target(new TargetId(loc(1, 1), "core.o", loc(1, 6)), loc(1, 7), dep(loc(1, 9), "core.c", loc(1, 14))),
                        new Command(loc(2, 2), "cc -c core.c", loc(2, 13)))));
    }

    @Test
    public void testTwoDependentRulesMakefile() throws Exception {
        final Makefile twoRulesMakefile = MakefileCreatingUtils.createTwoDependentRulesMakefile();
        final Set<Rule> rootRules = getRootRules(twoRulesMakefile);
        assertEquals(1, rootRules.size());
        assertTrue(rootRules.contains(
                new Rule(new Target(new TargetId(loc(1, 1), "all", loc(1, 3)), loc(1, 4), dep(loc(1, 6), "utils.o", loc(1, 12))))));
    }

    @Test
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        final Set<Rule> rootRules = getRootRules(originalMakefile);
        assertEquals(2, rootRules.size());

        assertTrue(rootRules.contains(new Rule(new Target(new TargetId(loc(6, 1), "clean", loc(6, 5)), loc(6, 6)),
                new Command(loc(7, 2), "rm *o hello", loc(7, 12)))));
        assertTrue(rootRules.contains(new Rule(
                new Target(new TargetId(loc(1, 1), "all", loc(1, 3)), loc(1, 4), dep(loc(1, 6), "hello", loc(1, 10))))));
    }

    private Set<Rule> getRootRules(Makefile makefile) {
        final RootRulesFindingVisitor rootRulesFindingVisitor = new RootRulesFindingVisitor();
        makefile.accept(rootRulesFindingVisitor);
        return rootRulesFindingVisitor.getRootRules();
    }
}