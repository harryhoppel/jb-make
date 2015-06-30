package com.jetbrains.jbmake;

import com.jetbrains.jbmake.core.config.Configuration;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public interface Application {
    void start(Configuration configuration);
    void exit(ExitCode exitCode);
}
