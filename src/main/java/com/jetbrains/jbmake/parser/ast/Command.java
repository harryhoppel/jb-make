package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class Command {
    private String commandLine;

    public Command(String commandLine) {
        this.commandLine = commandLine;
    }

    public String getCommandLine() {
        return commandLine;
    }

    public void setCommandLine(String commandLine) {
        this.commandLine = commandLine;
    }

    public void accept(SkippingNodesVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Command command1 = (Command) o;

        return !(commandLine != null ? !commandLine.equals(command1.commandLine) : command1.commandLine != null);
    }

    @Override
    public int hashCode() {
        return commandLine != null ? commandLine.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Command{" +
                "commandLine='" + commandLine + '\'' +
                '}';
    }
}
