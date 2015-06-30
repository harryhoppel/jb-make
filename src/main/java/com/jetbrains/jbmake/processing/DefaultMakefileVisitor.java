package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class DefaultMakefileVisitor implements SkippingNodesVisitor {
    public boolean preVisit(Makefile makefile) {
        return true;
    }

    public void postVisit(Makefile makefile) {

    }

    public boolean preVisit(Rule rule) {
        return true;
    }

    public void postVisit(Rule rule) {

    }

    public boolean preVisit(Target target) {
        return true;
    }

    public void postVisitTargetId(Target target) {}

    public void postVisit(Target target) {

    }

    public void visit(TargetId targetId) {

    }

    public void visit(Dependency dependency) {

    }

    public void visit(Command command) {

    }
}
