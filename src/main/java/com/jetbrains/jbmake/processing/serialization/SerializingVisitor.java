package com.jetbrains.jbmake.processing.serialization;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.processing.SkippingNodesVisitor;
import com.jetbrains.jbmake.processing.serialization.impl.MakefileWriterImpl;

import java.io.Writer;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class SerializingVisitor implements SkippingNodesVisitor {
    private final MakefileWriter writer;

    public SerializingVisitor(Writer writer, String lineSeparator) {
        this.writer = new MakefileWriterImpl(writer, lineSeparator);
    }

    public boolean preVisit(Makefile makefile) {
        return true;
    }

    public void postVisit(Makefile makefile) {
        // maybe close writer somewhere else? those who created it are responsible for it's fate
        writer.close();
    }

    public boolean preVisit(Rule rule) {
        return true;
    }

    public void postVisit(Rule rule) {
        if (rule.hasCommand()) {
            writer.writeNewLine();
        }
    }

    public boolean preVisit(Target target) {
        return true;
    }

    @Override
    public void postVisitTargetId(Target target) {
        Location colonLocation = target.getColonLocation();
        writer.addSpaces(colonLocation);
        writer.write(':');
    }

    public void postVisit(Target target) {
        writer.writeNewLine();
    }

    public void visit(TargetId targetId) {
        writer.write(targetId.getTargetName());
    }

    public void visit(Dependency dependency) {
        writer.write(' ');
        writer.write(dependency.getDependencyName());
    }

    public void visit(Command command) {
        writer.write('\t');
        writer.write(command.getCommandLine());
    }
}
