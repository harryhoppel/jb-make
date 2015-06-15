/* --------------------------Usercode Section------------------------ */

package com.jetbrains.jbmake.lexer;

import java_cup.runtime.*;
import jflex.sym;
import java_cup.runtime.ComplexSymbolFactory.Location;
import com.jetbrains.jbmake.lexer.exceptions.*;
import com.jetbrains.jbmake.parser.*;

%%

/* -----------------Options and Declarations Section----------------- */

%public
%class MakefileLexer
%implements MakefileSymbols

/* The current line number can be accessed with the variable yyline
   and the current column number with the variable yycolumn. */
%line
%column

/* CUP compatibility mode */
%cup

%cupdebug

/*
  Declarations
*/
%{
    private static final MakefileSymbolFactory symbolFactory = new MakefileSymbolFactory();

    public Symbol symbol(int terminalCode) {
        return symbolFactory.newSymbol(terminalCode,
                                       new Location(yyline + 1, yycolumn + 1),
                                       new Location(yyline + 1, yycolumn + yylength()));
    }

    private Symbol symbol(int terminalCode, Object value) {
        return symbolFactory.newSymbol(terminalCode,
                                       new Location(yyline + 1, yycolumn + 1),
                                       new Location(yyline + 1, yycolumn + yylength()),
                                       value);
    }
%}


/*
  Macro Declarations
*/

LineTerminator = \r|\n|\r\n

Whitespace = [ \f]+

Space = [ ]+

Colon = :

Other = [^ \f\r\n\t:]+

/* todo it is possible to define recipe prefix in .RECIPEPREFIX environment variable,
   todo so RecipePrefix might be a parameter, not a hardcoded string */
RecipePrefix = \t

%state NEXT_LINE

%state RECIPE_COMMAND

%%

/* ------------------------Lexical Rules Section---------------------- */

<YYINITIAL> {
    {Colon}                 { return symbol(COLON); }
    {Space}                 { return symbol(SPACE, yytext()); }
    {Whitespace}            { return symbol(WHITESPACE, yytext()); }
    {LineTerminator}        { yybegin(NEXT_LINE); return symbol(LINE_TERMINATOR, yytext()); }
    {Other}                 { return symbol(OTHER, yytext()); }
}

<NEXT_LINE> {
    {LineTerminator}        { return symbol(LINE_TERMINATOR, yytext()); }
    {RecipePrefix}+         { yybegin(RECIPE_COMMAND); return symbol(RECIPE_PREFIX, yytext()); }
    {Other}                 { yybegin(YYINITIAL); return symbol(OTHER, yytext()); }
}

<RECIPE_COMMAND> {
    {LineTerminator}+       { yybegin(YYINITIAL); return symbol(LINE_TERMINATOR, yytext()); }
    [^\r\n]+                { return symbol(OTHER, yytext()); }
}
