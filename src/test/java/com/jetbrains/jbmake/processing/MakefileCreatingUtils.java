package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.core.MakefileLoaderImpl;
import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.parser.exceptions.ParserException;

import java.io.FileNotFoundException;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class MakefileCreatingUtils {
    public static Makefile createOriginalMakefile() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/parser/original-example.txt");
    }
}
