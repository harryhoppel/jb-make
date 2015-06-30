package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class Dependency extends Locatable implements Visitable {
    private String dependencyName;

    public static Dependency dep(Location locationLeft, String dependencyName, Location locationRight) {
        return new Dependency(locationLeft, dependencyName, locationRight);
    }

    public Dependency(Location locationLeft, String dependencyName, Location locationRight) {
        super(locationLeft, locationRight);
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

        if (locationLeft != null ? !locationLeft.equals(that.locationLeft) : that.locationLeft != null)
            return false;
        //noinspection SimplifiableIfStatement
        if (locationRight != null ? !locationRight.equals(that.locationRight) : that.locationRight != null)
            return false;
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
                ", locationLeft='" + locationLeft + '\'' +
                ", locationRight='" + locationRight + '\'' +
                '}';
    }
}
