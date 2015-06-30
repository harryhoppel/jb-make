package com.jetbrains.jbmake.lexer;

import java_cup.runtime.MakefileSymbols;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.assertEquals;

/**
 * @author daywalker
 * @since 10/06/15.
 */
public class MakefileLexerTest {
    @Test
    public void testSimpleRule() throws Exception {
        MakefileLexer makefileLexer = createMakefileLexer("src/test/resources/lexer/lexer-simple-rule.txt");
        MakefileSymbolFactory symbolFactory = new MakefileSymbolFactory();
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.OTHER, 1, 1, 1, 3, "all"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.COLON, 1, 4, 1, 4), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.SPACE, 1, 5, 1, 5, " "), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.OTHER, 1, 6, 1, 10, "hello"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.LINE_TERMINATOR, 1, 11, 1, 11, "\n"), makefileLexer.next_token());
    }

    @Test
    public void testRuleWithOneLineCommand() throws Exception {
        MakefileLexer makefileLexer = createMakefileLexer("src/test/resources/lexer/lexer-rule-with-command.txt");
        MakefileSymbolFactory symbolFactory = new MakefileSymbolFactory();
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.OTHER, 1, 1, 1, 5, "hello"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.COLON, 1, 6, 1, 6), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.SPACE, 1, 7, 1, 7, " "), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.OTHER, 1, 8, 1, 13, "main.o"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.LINE_TERMINATOR, 1, 14, 1, 14, "\n"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.RECIPE_PREFIX, 2, 1, 2, 1, "\t"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.OTHER, 2, 2, 2, 20, "g++ main.o -o hello"), makefileLexer.next_token());
        assertEquals(symbolFactory.newSymbol(MakefileSymbols.LINE_TERMINATOR, 2, 21, 2, 21, "\n"), makefileLexer.next_token());
    }

    private MakefileLexer createMakefileLexer(String filePath) throws FileNotFoundException {
        BufferedInputStream makefileInputStream = new BufferedInputStream(new FileInputStream(filePath));
        return new MakefileLexer(makefileInputStream);
    }
}