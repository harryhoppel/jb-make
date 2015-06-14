package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public interface SkippingNodesVisitor {
    boolean preVisit(Makefile makefile);
    void postVisit(Makefile makefile);

    boolean preVisit(Rule rule);
    void postVisit(Rule rule);

    boolean preVisit(Target target);
    void postVisit(Target target);

    void visit(TargetId targetId);

    void visit(Dependency dependency);

    void visit(Command command);
}
