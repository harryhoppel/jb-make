package com.jetbrains.jbmake.processing;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.util.FileUtils;

import java.io.Writer;

/**
 * @author daywalker
 * @since 14/06/15.
 *
 * todo line separator!
 * todo replace syntax like spaces or colons with OOP
 */
public class SerializingVisitor implements SkippingNodesVisitor {
    private final Writer writer;

    public SerializingVisitor(Writer writer) {
        this.writer = writer;
    }

    public boolean preVisit(Makefile makefile) {
        return true;
    }

    public void postVisit(Makefile makefile) {
        // maybe close writer somewhere else? those who created it are responsible for it's fate
        FileUtils.close(writer);
    }

    public boolean preVisit(Rule rule) {
        return true;
    }

    public void postVisit(Rule rule) {
        if (rule.hasCommand()) {
            FileUtils.writeWoException(writer, "\n");
        }
    }

    public boolean preVisit(Target target) {
        return true;
    }

    public void postVisit(Target target) {
        FileUtils.writeWoException(writer, "\n");
    }

    public void visit(TargetId targetId) {
        FileUtils.writeWoException(writer, targetId.getTargetName());
        FileUtils.writeWoException(writer, ":");
    }

    public void visit(Dependency dependency) {
        FileUtils.writeWoException(writer, " ");
        FileUtils.writeWoException(writer, dependency.getDependencyName());
    }

    public void visit(Command command) {
        FileUtils.writeWoException(writer, "\t");
        FileUtils.writeWoException(writer, command.getCommandLine());
    }
}
