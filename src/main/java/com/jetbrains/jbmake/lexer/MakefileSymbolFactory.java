package com.jetbrains.jbmake.lexer;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.MakefileSymbols;
import java_cup.runtime.Symbol;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class MakefileSymbolFactory extends ComplexSymbolFactory {
    public Symbol newSymbol(String name, int terminalCode,
                            Location left, Location right) {
        return new MakefileSymbol(name, terminalCode, left, right);
    }

    public Symbol newSymbol(int terminalCode,
                            Location left, Location right) {
        return newSymbol(getTerminalName(terminalCode), terminalCode, left, right);
    }

    public Symbol newSymbol(String name, int terminalCode,
                            Location left, Location right, Object value) {
        return new MakefileSymbol(name, terminalCode, left, right, value);
    }

    public Symbol newSymbol(int terminalCode,
                            Location left, Location right, Object value) {
        return newSymbol(getTerminalName(terminalCode), terminalCode, left, right, value);
    }

    public Symbol newSymbol(String name, int id, Symbol left, Symbol right, Object value) {
        return newSymbol(name, id, new Location(left.left, left.right), new Location(right.left, right.right), value);
    }

    public Symbol newSymbol(int terminalCode, int leftLine, int leftColumn, int rightLine, int rightColumn, Object value) {
        return newSymbol(
                terminalCode,
                new Location(leftLine, leftColumn),
                new Location(rightLine, rightColumn),
                value);
    }

    public Symbol newSymbol(int terminalCode, int leftLine, int leftColumn, int rightLine, int rightColumn) {
        return newSymbol(terminalCode,
                new Location(leftLine, leftColumn),
                new Location(rightLine, rightColumn));
    }

    private String getTerminalName(int terminalCode) {
        return MakefileSymbols.terminalNames[terminalCode];
    }
}
