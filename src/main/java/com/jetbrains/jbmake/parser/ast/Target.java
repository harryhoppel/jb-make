package com.jetbrains.jbmake.parser.ast;

import com.google.common.annotations.VisibleForTesting;
import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class Target implements Visitable {
    private final TargetId targetId;

    private Location colonLocation;

    private List<Dependency> dependencyList;

    public Target(TargetId targetId, List<Dependency> dependencyList, Location colonLocation) {
        this.targetId = targetId;
        this.dependencyList = dependencyList;
        this.colonLocation = colonLocation;
    }

    public Target(TargetId targetId, Location colonLocation) {
        this(targetId, new LinkedList<>(), colonLocation);
    }

    @VisibleForTesting
    public Target(TargetId targetId, Location colonLocation,  Dependency ... dependencies) {
        this(targetId, colonLocation);
        Collections.addAll(this.dependencyList, dependencies);
    }

    public TargetId getTargetId() {
        return targetId;
    }

    public List<Dependency> getDependencyList() {
        return dependencyList;
    }

    public Location getColonLocation() {
        return colonLocation;
    }

    public void setColonLocation(Location colonLocation) {
        this.colonLocation = colonLocation;
    }

    public void accept(SkippingNodesVisitor visitor) {
        if (!visitor.preVisit(this)) {
            return;
        }
        visitor.visit(targetId);
        visitor.postVisitTargetId(this);
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

        if (colonLocation != null ? !colonLocation.equals(target.colonLocation) : target.colonLocation != null) {
            return false;
        }
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
                ", colonLocation=" + colonLocation +
                '}';
    }
}
