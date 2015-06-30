package com.jetbrains.jbmake.processing.renaming;

import com.jetbrains.jbmake.parser.ast.Command;
import com.jetbrains.jbmake.parser.ast.Dependency;
import com.jetbrains.jbmake.parser.ast.TargetId;

/**
 * @author daywalker
 * @since 14/06/15.
 *
 */
public class RenamingUtils {
    public static String getCommandWithRenamedFile(Command command,
                                                   FileDescription oldFileName,
                                                   FileDescription newFileName) {
        return replaceOldName(command.getCommandLine(), oldFileName, newFileName);
    }

    public static String getNewDependencyName(Dependency dependency,
                                              FileDescription oldFileName,
                                              FileDescription newFileName) {
        return replaceOldName(dependency.getDependencyName(), oldFileName, newFileName);
    }

    public static String getNewTargetName(TargetId targetId, FileDescription oldFileName, FileDescription newFileName) {
        return replaceOldName(targetId.getTargetName(), oldFileName, newFileName);
    }

    private static String replaceOldName(String oldLine, FileDescription oldFileName, FileDescription newFileName) {
        return oldLine
                .replaceAll(
                        oldFileName.getFileName(),
                        newFileName.getFileName())
                .replaceAll(
                        oldFileName.getFileNameWoExtension(),
                        newFileName.getFileNameWoExtension());
    }
}
