package com.jetbrains.jbmake.lexer;

import static java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import static java_cup.runtime.ComplexSymbolFactory.Location;

/**
 * @author daywalker
 * @since 10/06/15.
 */
public class MakefileSymbol extends ComplexSymbol {
    public MakefileSymbol(String plainName, int terminalCode, Location left, Location right) {
        super(plainName, terminalCode, left, right);
    }

    public MakefileSymbol(String plainName, int terminalCode, Location left, Location right, Object value) {
        super(plainName, terminalCode, left, right, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MakefileSymbol that = (MakefileSymbol) o;

        return equalLocations(getLeft(), that.getLeft())
                && equalLocations(getRight(), that.getRight())
                && (value == null ? that.value == null : value.equals(that.value));
    }

    private boolean equalLocations(Location location1, Location location2) {
        return location1.getLine() == location2.getLine()
                && location1.getColumn() == location2.getColumn()
                && location1.getUnit().equals(location2.getUnit());
    }

    @Override
    public int hashCode() {
        final Location left = getLeft();
        final Location right = getRight();
        int result = left.getLine();
        result = 31 * result + left.getColumn();
        result = 31 * result + right.getLine();
        result = 31 * result + right.getColumn();
        return result;
    }

    @Override
    public String toString() {
        return "MakefileSymbol{"
                + "left: " + getLeft().getLine() + "/" + getLeft().getColumn()
                + ", right: " + getRight().getLine() + "/" + getRight().getColumn()
                + ", value: " + value
                + "}";
    }
}
