package com.jetbrains.jbmake.core.editor;

import com.jetbrains.jbmake.core.Editor;
import com.jetbrains.jbmake.core.EditorListener;
import com.jetbrains.jbmake.core.MakefileLoader;
import com.jetbrains.jbmake.parser.ast.Makefile;
import com.jetbrains.jbmake.parser.exceptions.ParserException;
import com.jetbrains.jbmake.processing.SkippingNodesVisitor;
import com.jetbrains.jbmake.processing.TreeExportVisitor;
import com.jetbrains.jbmake.processing.renaming.RenamingFileVisitor;
import com.jetbrains.jbmake.processing.serialization.SerializingVisitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author daywalker
 * @since 30/06/15.
 */
public class EditorImpl implements Editor {
    private static final Logger LOG = LoggerFactory.getLogger(EditorImpl.class);

    private final List<EditorListener> editorListeners = new CopyOnWriteArrayList<>();

    private final MakefileLoader makefileLoader = MakefileLoaderImpl.getInstance();

    private File currentFile;
    private Makefile currentMakefile;

    @Override
    public void openFile(File file) throws FileNotFoundException, ParserException {
        currentFile = file;
        currentMakefile = makefileLoader.loadMakefile(file.getAbsolutePath());
        editorListeners.forEach(com.jetbrains.jbmake.core.EditorListener::makefileChanged);
    }

    @Override
    public void updateMakefile(String text) {
        try {
            currentMakefile = makefileLoader.parseMakefile(text);
        } catch (FileNotFoundException | ParserException ignore) {
            // ignore for now: should probably handle in the UI, but not with its current version
        }
        editorListeners.forEach(com.jetbrains.jbmake.core.EditorListener::makefileChanged);
    }

    @Override
    public File getFile() {
        return currentFile;
    }

    @Override
    public void save(File file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            SkippingNodesVisitor serializingVisitor = new SerializingVisitor(writer, System.lineSeparator());
            currentMakefile.accept(serializingVisitor);
        } catch (IOException e) {
            LOG.error("Can't save file: " + currentFile.getAbsolutePath(), e);
        }
    }

    @Override
    public void renameLeafNode(int caretPosition, String newName) {
        String oldName = new VariableFinder().getVariableName(getFileContent(), caretPosition);
        RenamingFileVisitor visitor = new RenamingFileVisitor(oldName, newName);
        currentMakefile.accept(visitor);
        editorListeners.forEach(com.jetbrains.jbmake.core.EditorListener::makefileChanged);
    }

    @Override
    public String getFileContent() {
        StringWriter stringWriter = new StringWriter();
        SerializingVisitor visitor = new SerializingVisitor(stringWriter, System.lineSeparator());
        currentMakefile.accept(visitor);
        return stringWriter.toString();
    }

    @Override
    public void exportMakefileAsTree(DefaultMutableTreeNode root) {
        TreeExportVisitor visitor = new TreeExportVisitor(root);
        currentMakefile.accept(visitor);
    }

    @Override
    public void addListener(EditorListener listener) {
        editorListeners.add(listener);
    }

    @Override
    public void removeListener(EditorListener listener) {
        editorListeners.remove(listener);
    }
}
