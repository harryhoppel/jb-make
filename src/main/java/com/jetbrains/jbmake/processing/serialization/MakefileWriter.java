package com.jetbrains.jbmake.processing.serialization;

import com.jetbrains.jbmake.parser.ast.Location;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public interface MakefileWriter {
    void write(String s);
    void write(char c);
    void writeNewLine();
    void addSpaces(Location untilLocation);

    void close();
}
