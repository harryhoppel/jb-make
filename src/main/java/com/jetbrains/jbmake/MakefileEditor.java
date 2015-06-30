package com.jetbrains.jbmake;

import com.jetbrains.jbmake.core.Editor;
import com.jetbrains.jbmake.core.config.Configuration;
import com.jetbrains.jbmake.core.editor.EditorImpl;
import com.jetbrains.jbmake.ui.swing.MainWindow;

import javax.swing.*;

/**
 * @author daywalker
 * @since 29/06/15.
 */
public class MakefileEditor implements Application {
    private Configuration configuration;
    private Editor editor;

    @Override
    public void start(Configuration configuration) {
        this.configuration = configuration;
        this.editor = new EditorImpl();
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(this, configuration, editor);
            mainWindow.pack();
            mainWindow.setVisible(true);
        });
    }

    @Override
    public void exit(ExitCode exitCode) {
        configuration.save();
        System.exit(exitCode.getExitCodeInt());
    }
}
