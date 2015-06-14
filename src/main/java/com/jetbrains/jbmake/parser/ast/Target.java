package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

import java.util.LinkedList;
import java.util.List;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class Target implements Visitable {
    private final TargetId targetId;

    private List<Dependency> dependencyList;

    public Target(TargetId targetId) {
        this.targetId = targetId;
        this.dependencyList = new LinkedList<Dependency>();
    }

    public Target(TargetId targetId, List<Dependency> dependencyList) {
        this.targetId = targetId;
        this.dependencyList = dependencyList;
    }

    public Target(TargetId targetId, String ... dependencyNames) {
        this.targetId = targetId;
        this.dependencyList = new LinkedList<Dependency>();
        for (String dependencyName : dependencyNames) {
            this.dependencyList.add(new Dependency(dependencyName));
        }
    }

    public TargetId getTargetId() {
        return targetId;
    }

    public List<Dependency> getDependencyList() {
        return dependencyList;
    }

    public void accept(SkippingNodesVisitor visitor) {
        if (!visitor.preVisit(this)) {
            return;
        }
        visitor.visit(targetId);
        for (Dependency dependency : dependencyList) {
            dependency.accept(visitor);
        }
        visitor.postVisit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Target target = (Target) o;

        //noinspection SimplifiableIfStatement
        if (!targetId.equals(target.targetId)) return false;
        return !(dependencyList != null ? !dependencyList.equals(target.dependencyList) : target.dependencyList != null);

    }

    @Override
    public int hashCode() {
        int result = targetId.hashCode();
        result = 31 * result + (dependencyList != null ? dependencyList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Target{" +
                "targetId=" + targetId +
                ", dependencyList=" + dependencyList +
                '}';
    }
}
