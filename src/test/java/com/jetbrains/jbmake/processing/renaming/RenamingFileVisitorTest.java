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
    public void testOneRuleMakefile() throws Exception {
        final Makefile oneRuleMakefile = MakefileCreatingUtils.createOneRuleMakefile();
        final List<Rule> ruleList = getRulesAfterRename(oneRuleMakefile, "utils.c", "core.cpp");
        assertEquals(1, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("core.o"), "core.cpp"), new Command("cc -c core.cpp")), ruleList.get(0));
    }

    public void testTwoRulesMakefile() throws Exception {
        final Makefile twoRulesMakefile = MakefileCreatingUtils.createTwoRulesMakefile();
        final List<Rule> ruleList = getRulesAfterRename(twoRulesMakefile, "utils.c", "generic.cpp");
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("core.o"), "core.c"), new Command("cc -c core.c")), ruleList.get(0));
        assertEquals(new Rule(new Target(new TargetId("generic.o"), "generic.cpp"), new Command("cc -c generic.cpp")), ruleList.get(1));
    }

    public void testTwoDependentRulesMakefile() throws Exception {
        final Makefile twoDependentRulesMakefile = MakefileCreatingUtils.createTwoDependentRulesMakefile();
        final List<Rule> ruleList = getRulesAfterRename(twoDependentRulesMakefile, "utils.c", "generic.cpp");
        assertEquals(2, ruleList.size());
        assertEquals(new Rule(new Target(new TargetId("all"), "generic.o")), ruleList.get(0));
        assertEquals(new Rule(new Target(new TargetId("generic.o"), "generic.cpp"), new Command("cc -c generic.cpp")), ruleList.get(1));
    }

    public void testOriginalExample() throws Exception {
        final Makefile originalMakefile = MakefileCreatingUtils.createOriginalMakefile();
        final List<Rule> ruleList = getRulesAfterRename(originalMakefile, "main.cpp", "general.cxx");
        assertEquals(4, ruleList.size());

        assertEquals(new Rule(new Target(new TargetId("all"), "hello")), ruleList.get(0));

        assertEquals(new Rule(new Target(new TargetId("hello"), "general.o"),
                new Command("g++ general.o -o hello")), ruleList.get(1));

        assertEquals(new Rule(new Target(new TargetId("general.o"), "general.cxx"),
                new Command("g++ -c general.cxx")), ruleList.get(2));

        assertEquals(new Rule(new Target(new TargetId("clean")), new Command("rm *o hello")), ruleList.get(3));

    }

    private List<Rule> getRulesAfterRename(Makefile oneRuleMakefile, String oldFileName, String newFileName) {
        oneRuleMakefile.accept(new RenamingFileVisitor(oldFileName, newFileName));
        return oneRuleMakefile.getRuleList();
    }
}