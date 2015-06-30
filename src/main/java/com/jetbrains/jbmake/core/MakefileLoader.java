package com.jetbrains.jbmake.core;

import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.parser.exceptions.ParserException;

import java.io.FileNotFoundException;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public interface MakefileLoader {
    Makefile loadMakefile(String pathToFile) throws FileNotFoundException, ParserException;

    Makefile parseMakefile(String fileContent) throws FileNotFoundException, ParserException;
}
