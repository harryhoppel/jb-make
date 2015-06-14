package com.jetbrains.jbmake.core;

import com.jetbrains.jbmake.lexer.MakefileLexer;
import com.jetbrains.jbmake.lexer.MakefileSymbolFactory;
import com.jetbrains.jbmake.parser.MakefileParser;
import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.parser.exceptions.ParserException;
import com.jetbrains.jbmake.util.FileUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class MakefileLoaderImpl implements MakefileLoader {
    public static final MakefileLoader INSTANCE = new MakefileLoaderImpl();

    private MakefileLoaderImpl() {}

    public static MakefileLoader getInstance() {
        return INSTANCE;
    }

    public Makefile loadMakefile(String pathToFile) throws FileNotFoundException, ParserException {
        BufferedInputStream makefileInputStream
                = new BufferedInputStream(new FileInputStream("src/test/resources/parser/original-example.txt"));
        MakefileParser makefileParser
                = new MakefileParser(new MakefileLexer(makefileInputStream), new MakefileSymbolFactory());
        try {
            return ((Makefile) makefileParser.parse().value);
        } catch (Exception e) {
            throw new ParserException("Can't parse file " + pathToFile, e);
        } finally {
            FileUtils.close(makefileInputStream);
        }
    }
}
