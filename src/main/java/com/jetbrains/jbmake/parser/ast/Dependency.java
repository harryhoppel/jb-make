package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class Dependency implements Visitable {
    private String dependencyName;

    public Dependency(String dependencyName) {
        this.dependencyName = dependencyName;
    }

    public String getDependencyName() {
        return dependencyName;
    }

    public void setDependencyName(String dependencyName) {
        this.dependencyName = dependencyName;
    }

    public void accept(SkippingNodesVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dependency that = (Dependency) o;

        return !(dependencyName != null ? !dependencyName.equals(that.dependencyName) : that.dependencyName != null);

    }

    @Override
    public int hashCode() {
        return dependencyName != null ? dependencyName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Dependency{" +
                "dependencyName='" + dependencyName + '\'' +
                '}';
    }
}
