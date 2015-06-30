package com.jetbrains.jbmake.core;

import com.jetbrains.jbmake.parser.exceptions.ParserException;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author daywalker
 * @since 30/06/15.
 */
public interface Editor {
    File getFile();
    void openFile(File file) throws FileNotFoundException, ParserException;
    void updateMakefile(String text);
    void save(File file);
    String getFileContent();

    void exportMakefileAsTree(DefaultMutableTreeNode root);

    void renameLeafNode(int caretPosition, String newName);

    void addListener(EditorListener listener);
    void removeListener(EditorListener listener);
}
