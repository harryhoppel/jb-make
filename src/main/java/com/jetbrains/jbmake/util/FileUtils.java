package com.jetbrains.jbmake.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

/**
 * @author daywalker
 * @since 15/06/15.
 */
public class FileUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static void writeWoException(Writer writer, String s) {
        try {
            writer.write(s);
        } catch (IOException e) {
            LOGGER.warn("Can't write string: " + s, e);
        }
    }

    public static void writeWoException(Writer writer, char c) {
        try {
            writer.write(c);
        } catch (IOException e) {
            LOGGER.warn("Can't write string: " + c, e);
        }
    }

    public static void close(Writer writer) {
        try {
            writer.close();
        } catch (IOException e) {
            LOGGER.warn("Can't close writer", e);
        }
    }

    public static void close(InputStream inputStream) {
        try {
            inputStream.close();
        } catch (IOException e) {
            LOGGER.warn("Can't close input stream", e);
        }
    }
}
