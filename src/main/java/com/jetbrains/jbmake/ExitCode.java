package com.jetbrains.jbmake;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public enum ExitCode {
    OK(0),
    GENERIC_ERROR(1),
    ;

    private final int exitCode;

    ExitCode(int exitCode) {
        this.exitCode = exitCode;
    }

    public int getExitCodeInt() {
        return exitCode;
    }
}
