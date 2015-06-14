package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class Rule implements Visitable {
    private final Target target;

    private Command command;

    public Rule(Target target) {
        this.target = target;
    }

    public Rule(Target target, Command command) {
        this.target = target;
        this.command = command;
    }

    public Target getTarget() {
        return target;
    }

    public Command getCommand() {
        return command;
    }

    public boolean hasCommand() {
        return command != null;
    }

    public void accept(SkippingNodesVisitor visitor) {
        if (!visitor.preVisit(this)) {
            return;
        }
        target.accept(visitor);
        if (command != null) {
            command.accept(visitor);
        }
        visitor.postVisit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        //noinspection SimplifiableIfStatement
        if (!target.equals(rule.target)) return false;
        return !(command != null ? !command.equals(rule.command) : rule.command != null);

    }

    @Override
    public int hashCode() {
        int result = target.hashCode();
        result = 31 * result + (command != null ? command.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rule{" +
                "target=" + target +
                ", command=" + command +
                '}';
    }
}
