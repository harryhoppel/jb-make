package com.jetbrains.jbmake.processing.renaming;

import com.jetbrains.jbmake.parser.ast.*;
import com.jetbrains.jbmake.processing.DefaultMakefileVisitor;

/**
 * @author daywalker
 * @since 14/06/15.
 */
public class RenamingFileVisitor extends DefaultMakefileVisitor {
    private final FileDescription oldFileName;
    private final FileDescription newFileName;

    private boolean changedLocation;
    private int diff;

    public RenamingFileVisitor(String oldFileName, String newFileName) {
        this.oldFileName = new FileDescription(oldFileName);
        this.newFileName = new FileDescription(newFileName);
    }

    @Override
    public boolean preVisit(Rule rule) {
        changedLocation = false;
        diff = 0;
        return super.preVisit(rule);
    }

    public void visit(TargetId targetId) {
        String newTargetName = RenamingUtils.getNewTargetName(targetId, oldFileName, newFileName);
        String oldTargetName = targetId.getTargetName();
        updateLocation(targetId, oldTargetName, newTargetName);
        targetId.setTargetName(newTargetName);
    }

    public void visit(Dependency dependency) {
        if (changedLocation) {
            Location locationLeft = dependency.getLocationLeft();
            Location locationRight = dependency.getLocationRight();
            dependency.setLocationLeft(new Location(locationLeft.getLine(), locationLeft.getColumn() + diff));
            dependency.setLocationRight(new Location(locationRight.getLine(), locationRight.getColumn() + diff));
        }
        String newDependencyName = RenamingUtils.getNewDependencyName(dependency, oldFileName, newFileName);
        String oldDependencyName = dependency.getDependencyName();
        updateLocation(dependency, oldDependencyName, newDependencyName);
        dependency.setDependencyName(newDependencyName);
    }

    public void visit(Command command) {
        String newCommand = RenamingUtils.getCommandWithRenamedFile(command, oldFileName, newFileName);
        String oldCommand = command.getCommandLine();
        updateLocation(command, oldCommand, newCommand);
        command.setCommandLine(newCommand);
    }

    @Override
    public void postVisitTargetId(Target target) {
        if (changedLocation) {
            Location oldColonLocation = target.getColonLocation();
            target.setColonLocation(new Location(oldColonLocation.getLine(), oldColonLocation.getColumn() + diff));
        }
    }

    private void updateLocation(Locatable targetId, String oldTargetName, String newTargetName) {
        diff = newTargetName.length() - oldTargetName.length();
        Location oldLocationRight = targetId.getLocationRight();
        targetId.setLocationRight(new Location(oldLocationRight.getLine(), oldLocationRight.getColumn() + diff));
        changedLocation = true;
    }
}
