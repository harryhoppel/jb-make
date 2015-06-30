package com.jetbrains.jbmake.parser.ast;

import com.google.common.annotations.VisibleForTesting;

/**
 * @author daywalker
 * @since 28/06/15.
 */
public class Location {
    private final int line;
    private final int column;

    @VisibleForTesting
    public static Location loc(int line, int column) {
        return new Location(line, column);
    }

    public Location(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Location location = (Location) o;

        //noinspection SimplifiableIfStatement
        if (line != location.line) return false;
        return column == location.column;

    }

    @Override
    public int hashCode() {
        int result = line;
        result = 31 * result + column;
        return result;
    }

    @Override
    public String toString() {
        return "Location{" +
                "line=" + line +
                ", column=" + column +
                '}';
    }
}
