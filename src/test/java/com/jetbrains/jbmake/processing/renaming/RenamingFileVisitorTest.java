package com.jetbrains.jbmake.processing.renaming;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.processing.MakefileCreatingUtils;
import org.junit.Test;

import java.util.List;

import static com.jetbrains.jbmake.parser.ast.Dependency.dep;
import static com.jetbrains.jbmake.parser.ast.Location.loc;
import static org.junit.Assert.assertEquals;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class RenamingFileVisitorTest {
    @Test
    public void testOneRuleMakefile() throws Exception {
        final Makefile oneRuleMakefile = MakefileCreatingUtils.createOneRuleMakefile();
        final List<Rule> ruleList = getRulesAfterRename(oneRuleMakefile, "utils.c", "core.cpp");
        assertEquals(1, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId(loc(1, 1), "core.o", loc(1, 6)), loc(1, 7), dep(loc(1, 9), "core.cpp", loc(1, 16))),
                new Command(loc(2, 2), "cc -c core.cpp", loc(2, 15))), ruleList.get(0));
    }

    @Test
    public void testTwoRulesMakefile() throws Exception {
        final Makefile twoRulesMakefile = MakefileCreatingUtils.createTwoRulesMakefile();
        final List<Rule> ruleList = getRulesAfterRename(twoRulesMakefile, "utils.c", "generic.cpp");
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId(loc(1, 1), "core.o", loc(1, 6)), loc(1, 7), dep(loc(1, 9), "core.c", loc(1, 14))),
                new Command(loc(2, 2), "cc -c core.c", loc(2, 13))), ruleList.get(0));
        assertEquals(new Rule(
                new Target(new TargetId(loc(3, 1), "generic.o", loc(3, 9)), loc(3, 10), dep(loc(3, 12), "generic.cpp", loc(3, 22))),
                new Command(loc(4, 2), "cc -c generic.cpp", loc(4, 18))), ruleList.get(1));
    }

    @Test
    public void testTwoDependentRulesMakefile() throws Exception {
        final Makefile twoDependentRulesMakefile = MakefileCreatingUtils.createTwoDependentRulesMakefile();
        final List<Rule> ruleList = getRulesAfterRename(twoDependentRulesMakefile, "utils.c", "generic.cpp");
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId(loc(1, 1), "all", loc(1, 3)), loc(1, 4), dep(loc(1, 6), "generic.o", loc(1, 14)))), ruleList.get(0));
        assertEquals(new Rule(
                new Target(new TargetId(loc(2, 1), "generic.o", loc(2, 9)), loc(2, 10), dep(loc(2, 12), "generic.cpp", loc(2, 22))),
                new Command(loc(3, 2), "cc -c generic.cpp", loc(3, 18))), ruleList.get(1));
    }

    @Test
    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        final List<Rule> ruleList = getRulesAfterRename(originalMakefile, "main.cpp", "general.cxx");
        assertEquals(4, ruleList.size());

        assertEquals(new Rule(
                new Target(new TargetId(loc(1, 1), "all", loc(1, 3)), loc(1, 4), dep(loc(1, 6), "hello", loc(1, 10)))), ruleList.get(0));

        assertEquals(new Rule(new Target(new TargetId(loc(2, 1), "hello", loc(2, 5)), loc(2, 6), dep(loc(2, 8), "general.o", loc(2, 16))),
                new Command(loc(3, 2), "g++ general.o -o hello", loc(3, 23))), ruleList.get(1));

        assertEquals(new Rule(new Target(new TargetId(loc(4, 1), "general.o", loc(4, 9)), loc(4, 10), dep(loc(4, 12), "general.cxx", loc(4, 22))),
                new Command(loc(5, 2), "g++ -c general.cxx", loc(5, 19))), ruleList.get(2));

        assertEquals(new Rule(
                new Target(new TargetId(loc(6, 1), "clean", loc(6, 5)), loc(6, 6)),
                new Command(loc(7, 2), "rm *o hello", loc(7, 12))), ruleList.get(3));
    }

    private List<Rule> getRulesAfterRename(Makefile oneRuleMakefile, String oldFileName, String newFileName) {
        oneRuleMakefile.accept(new RenamingFileVisitor(oldFileName, newFileName));
        return oneRuleMakefile.getRuleList();
    }
}