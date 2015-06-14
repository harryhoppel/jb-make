package com.jetbrains.jbmake.processing.renaming;

import com.jetbrains.jbmake.parser.ast.Command;
import com.jetbrains.jbmake.parser.ast.Dependency;
import com.jetbrains.jbmake.parser.ast.TargetId;
import com.jetbrains.jbmake.processing.DefaultMakefileVisitor;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class RenamingFileVisitor extends DefaultMakefileVisitor {
    private final FileDescription oldFileName;
    private final FileDescription newFileName;

    public RenamingFileVisitor(String oldFileName, String newFileName) {
        this.oldFileName = new FileDescription(oldFileName);
        this.newFileName = new FileDescription(newFileName);
    }

    public void visit(TargetId targetId) {
        targetId.setTargetName(RenamingUtils.getNewTargetName(targetId, oldFileName, newFileName));
    }

    public void visit(Dependency dependency) {
        dependency.setDependencyName(RenamingUtils.getNewDependencyName(dependency, oldFileName, newFileName));
    }

    public void visit(Command command) {
        command.setCommandLine(RenamingUtils.getCommandWithRenamedFile(command, oldFileName, newFileName));
    }
}
