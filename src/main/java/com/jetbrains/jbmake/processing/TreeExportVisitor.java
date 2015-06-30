package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author daywalker
 * @since 30/06/15.
 */
public class TreeExportVisitor extends DefaultMakefileVisitor {
    private final DefaultMutableTreeNode root;

    private DefaultMutableTreeNode currentRule;
    private DefaultMutableTreeNode currentTarget;
    private DefaultMutableTreeNode currentDependencies;

    public TreeExportVisitor(DefaultMutableTreeNode root) {
        this.root = root;
    }

    @Override
    public boolean preVisit(Rule rule) {
        currentRule = new DefaultMutableTreeNode(rule.getTarget().getTargetId().getTargetName());
        return true;
    }

    @Override
    public boolean preVisit(Target target) {
        currentTarget = new DefaultMutableTreeNode(target.getTargetId());
        return true;
    }

    @Override
    public void visit(TargetId targetId) {
        currentTarget.add(new DefaultMutableTreeNode(targetId.getTargetName()));
    }

    @Override
    public void postVisitTargetId(Target target) {
        currentTarget.add(new DefaultMutableTreeNode("':' -> " + target.getColonLocation()));
        currentDependencies = new DefaultMutableTreeNode("dependencies");
    }

    @Override
    public void visit(Dependency dependency) {
        currentDependencies.add(new DefaultMutableTreeNode(dependency.getDependencyName()));
    }

    @Override
    public void postVisit(Target target) {
        currentRule.add(currentTarget);
        currentRule.add(currentDependencies);
    }

    @Override
    public void visit(Command command) {
        currentRule.add(new DefaultMutableTreeNode(command.getCommandLine()));
    }

    @Override
    public void postVisit(Rule rule) {
        root.add(currentRule);
    }
}
