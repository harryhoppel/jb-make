package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.Dependency;
import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.parser.ast.Rule;
import com.jetbrains.jbmake.parser.ast.TargetId;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class RootRulesFindingVisitor extends DefaultMakefileVisitor {
    private final Set<String> dependencyNames = new HashSet<String>();
    private final Map<String, Rule> targetIdToRule = new HashMap<String, Rule>();

    private Set<Rule> rootRules;

    private String currentlyVisitedTargetName;

    @Override
    public void postVisit(Rule rule) {
        targetIdToRule.put(currentlyVisitedTargetName, rule);
    }

    @Override
    public void visit(TargetId targetId) {
        currentlyVisitedTargetName = targetId.getTargetName();
    }

    @Override
    public void visit(Dependency dependency) {
        dependencyNames.add(dependency.getDependencyName());
    }

    @Override
    public void postVisit(Makefile makefile) {
        for (String dependencyName : dependencyNames) {
            targetIdToRule.remove(dependencyName);
        }
        rootRules = new HashSet<Rule>();
        rootRules.addAll(targetIdToRule.values());
    }

    public Set<Rule> getRootRules() {
        if (rootRules == null) {
            throw new IllegalStateException("Didn't visit Makefile yet");
        }
        return rootRules;
    }
}
