package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;
import junit.framework.TestCase;

import java.util.Set;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class RootRulesFindingVisitorTest extends TestCase {
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        RootRulesFindingVisitor visitor = new RootRulesFindingVisitor();
        originalMakefile.accept(visitor);
        final Set<Rule> rootRules = visitor.getRootRules();
        assertEquals(2, rootRules.size());

        assertTrue(rootRules.contains(new Rule(new Target(new TargetId("clean")), new Command("rm *o hello"))));
        assertTrue(rootRules.contains(new Rule(new Target(new TargetId("all"), "hello"))));
    }
}