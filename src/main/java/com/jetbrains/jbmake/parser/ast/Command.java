package com.jetbrains.jbmake.parser.ast;

import com.jetbrains.jbmake.processing.SkippingNodesVisitor;

/**
 * @author daywalker
 * @since 13/06/15.
 */
public class Command extends Locatable {
    private String commandLine;

    public Command(Location locationLeft, String commandLine, Location locationRight) {
        super(locationLeft, locationRight);
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

        if (locationLeft != null ? !locationLeft.equals(command1.locationLeft) : command1.locationLeft != null)
            return false;
        //noinspection SimplifiableIfStatement
        if (locationRight != null ? !locationRight.equals(command1.locationRight) : command1.locationRight != null)
            return false;

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
                ", locationLeft='" + locationLeft + '\'' +
                ", locationRight='" + locationRight + '\'' +
                '}';
    }
}
