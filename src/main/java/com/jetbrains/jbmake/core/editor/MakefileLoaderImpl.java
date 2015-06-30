package com.jetbrains.jbmake.core.editor;

import com.jetbrains.jbmake.core.MakefileLoader;
import com.jetbrains.jbmake.lexer.MakefileLexer;
import com.jetbrains.jbmake.lexer.MakefileSymbolFactory;
import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.parser.exceptions.ParserException;
import java_cup.runtime.MakefileParser;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class MakefileLoaderImpl implements MakefileLoader {
    private static final MakefileLoader INSTANCE = new MakefileLoaderImpl();

    private MakefileLoaderImpl() {}

    public static MakefileLoader getInstance() {
        return INSTANCE;
    }

    public Makefile loadMakefile(String pathToFile) throws FileNotFoundException, ParserException {
        try (BufferedInputStream makefileInputStream = new BufferedInputStream(new FileInputStream(pathToFile))) {
            MakefileParser makefileParser
                    = new MakefileParser(new MakefileLexer(makefileInputStream), new MakefileSymbolFactory());
            return ((Makefile) makefileParser.parse().value);
        } catch (Exception e) {
            throw new ParserException("Can't parse file " + pathToFile, e);
        }
    }

    @Override
    public Makefile parseMakefile(String fileContent) throws FileNotFoundException, ParserException {
        ByteArrayInputStream bytes = new ByteArrayInputStream(fileContent.getBytes());
        MakefileParser makefileParser = new MakefileParser(new MakefileLexer(bytes), new MakefileSymbolFactory());
        try {
            return ((Makefile) makefileParser.parse().value);
        } catch (Exception e) {
            throw new ParserException("Can't parse file from fileContent: " + fileContent , e);
        }
    }
}
