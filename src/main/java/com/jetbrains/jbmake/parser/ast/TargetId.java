package com.jetbrains.jbmake.parser.ast;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class TargetId {
    private String targetName;

    public TargetId(String targetName) {
        this.targetName = targetName;
    }

    public String getTargetName() {
        return targetName;
    }

    public void setTargetName(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetId targetId = (TargetId) o;

        return !(targetName != null ? !targetName.equals(targetId.targetName) : targetId.targetName != null);

    }

    @Override
    public int hashCode() {
        return targetName != null ? targetName.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "TargetId{" +
                "targetName='" + targetName + '\'' +
                '}';
    }
}
