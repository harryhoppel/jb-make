package com.jetbrains.jbmake.parser.ast;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class TargetId extends Locatable {
    private String targetName;

    public TargetId(Location locationLeft, String targetName, Location locationRight) {
        super(locationLeft, locationRight);
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

        if (locationLeft != null ? !locationLeft.equals(targetId.locationLeft) : targetId.locationLeft != null)
            return false;
        //noinspection SimplifiableIfStatement
        if (targetName != null ? !targetName.equals(targetId.targetName) : targetId.targetName != null) return false;
        return !(locationRight != null ? !locationRight.equals(targetId.locationRight) : targetId.locationRight != null);

    }

    @Override
    public int hashCode() {
        int result = locationLeft != null ? locationLeft.hashCode() : 0;
        result = 31 * result + (targetName != null ? targetName.hashCode() : 0);
        result = 31 * result + (locationRight != null ? locationRight.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TargetId{" +
                "targetName='" + targetName + '\'' +
                ", locationLeft='" + locationLeft + '\'' +
                ", locationRight='" + locationRight + '\'' +
                '}';
    }
}
