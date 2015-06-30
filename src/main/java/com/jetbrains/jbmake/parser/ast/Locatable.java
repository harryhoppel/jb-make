package com.jetbrains.jbmake.parser.ast;

/**
 * @author daywalker
 * @since 30/06/15.
 */
public abstract class Locatable {
    protected Location locationLeft;
    protected Location locationRight;

    public Locatable(Location locationLeft, Location locationRight) {
        this.locationLeft = locationLeft;
        this.locationRight = locationRight;
    }

    public Location getLocationLeft() {
        return locationLeft;
    }

    public void setLocationLeft(Location locationLeft) {
        this.locationLeft = locationLeft;
    }

    public Location getLocationRight() {
        return locationRight;
    }

    public void setLocationRight(Location locationRight) {
        this.locationRight = locationRight;
    }
}
