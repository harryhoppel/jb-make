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

    public static Makefile createOneRuleMakefile() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/examples/one-rule-makefile.txt");
    }

    public static Makefile createTwoRulesMakefile() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/examples/two-rules-makefile.txt");
    }

    public static Makefile createTwoDependentRulesMakefile() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/examples/two-dependent-rules-makefile.txt");
    }

    public static Makefile createMakefileWithEmptyLinesBetweenRules() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/parser/rules-with-empty-lines.txt");
    }

    public static Makefile createEmptyMakefile() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/examples/empty-file.txt");
    }

    public static Makefile createMakefileWithRecipeContinuation() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/examples/one-rule-recipe-continuation-makefile.txt");
    }

    public static Makefile createMalformedRuleMakefile() throws FileNotFoundException, ParserException {
        return MakefileLoaderImpl.getInstance()
                .loadMakefile("src/test/resources/examples/malformed-rule.txt");
    }
}
