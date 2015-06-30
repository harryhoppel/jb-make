package com.jetbrains.jbmake.core.config;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public interface Configuration {
    String getValue(String key);
    void putValue(String key, String value);

    void save();
}