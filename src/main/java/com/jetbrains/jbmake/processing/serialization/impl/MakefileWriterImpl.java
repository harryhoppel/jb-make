package com.jetbrains.jbmake.processing.serialization.impl;

import com.jetbrains.jbmake.parser.ast.Location;
import com.jetbrains.jbmake.processing.serialization.MakefileWriter;
import com.jetbrains.jbmake.util.FileUtils;

import java.io.Writer;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public class MakefileWriterImpl implements MakefileWriter {
    private final Writer writer;
    private final String lineSeparator;

    private int column = 1; // 1-based, not zero-based

    public MakefileWriterImpl(Writer writer, String lineSeparator) {
        this.writer = writer;
        this.lineSeparator = lineSeparator;
    }

    @Override
    public void write(String s) {
        int indexOfSeparator = s.lastIndexOf(lineSeparator);
        if (indexOfSeparator != -1) {
            setColumn(s.length() - indexOfSeparator);
        } else {
            incrementColumn(s.length());
        }
        FileUtils.writeWoException(writer, s);
    }

    @Override
    public void write(char c) {
        if (lineSeparator.length() == 1 && lineSeparator.charAt(0) == c) {
            resetColumn();
        }
        FileUtils.writeWoException(writer, c);
    }


    @Override
    public void writeNewLine() {
        resetColumn();
        FileUtils.writeWoException(writer, lineSeparator);
    }

    @Override
    public void addSpaces(Location untilLocation) {
        while (column++ < untilLocation.getColumn()) {
            write(' ');
        }
    }

    private void incrementColumn(int length) {
        column += length;
    }

    private void setColumn(int lineOffset) {
        column = lineOffset;
    }

    private void resetColumn() {
        column = 1;
    }

    @Override
    public void close() {
        FileUtils.close(writer);
    }
}
