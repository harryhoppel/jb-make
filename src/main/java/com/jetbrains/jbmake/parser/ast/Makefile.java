package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

import java.util.List;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class Makefile implements Visitable {
    private final List<Rule> ruleList;

    public Makefile(List<Rule> ruleList) {
        this.ruleList = ruleList;
    }

    public List<Rule> getRuleList() {
        return ruleList;
    }

    public void accept(SkippingNodesVisitor visitor) {
        if (!visitor.preVisit(this)) {
            return;
        }
        for (Rule rule : ruleList) {
            rule.accept(visitor);
        }
        visitor.postVisit(this);
    }

    @Override
    public String toString() {
        return "Makefile{" +
                "ruleList=" + ruleList +
                '}';
    }
}
