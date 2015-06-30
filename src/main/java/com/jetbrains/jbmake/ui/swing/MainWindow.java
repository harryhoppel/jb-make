package com.jetbrains.jbmake.ui.swing;

import com.jetbrains.jbmake.Application;
import com.jetbrains.jbmake.ExitCode;
import com.jetbrains.jbmake.core.Editor;
import com.jetbrains.jbmake.core.config.Configuration;
import com.jetbrains.jbmake.parser.exceptions.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author daywalker
 * @since 25/06/15.
 */
public class MainWindow extends JFrame {
    private static final Logger LOG = LoggerFactory.getLogger(MainWindow.class);

    private static final String WINDOW_WIDTH = "window-width";
    private static final String WINDOW_HEIGHT = "window-height";
    private static final String DEFAULT_MAKEFILE = "default-makefile";

    private final Application application;
    private final Configuration configuration;
    private final Editor editor;
    private MainPanel mainPanel;

    public MainWindow(Application application, Configuration configuration, Editor editor) {
        this.application = application;
        this.configuration = configuration;
        this.editor = editor;
        createGUI();
    }

    private void createGUI() {
        setLayout(new BorderLayout());

        int windowWidth = Integer.parseInt(configuration.getValue(WINDOW_WIDTH));
        int windowHeight = Integer.parseInt(configuration.getValue(WINDOW_HEIGHT));

        setPreferredSize(new Dimension(windowWidth, windowHeight));
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addResizeListener();
        addCloseListener();

        openDefaultMakefile();

        mainPanel = new MainPanel(editor);
        editor.addListener(mainPanel);
        add(mainPanel, BorderLayout.CENTER);

        JMenuBar jMenuBar = createMenuBar();
        setJMenuBar(jMenuBar);
    }

    private void openDefaultMakefile() {
        String defaultMakefile = configuration.getValue(DEFAULT_MAKEFILE);
        try {
            editor.openFile(new File(defaultMakefile));
        } catch (FileNotFoundException | ParserException e) {
            LOG.error("Default file doesn't open: " + defaultMakefile, e);
            application.exit(ExitCode.GENERIC_ERROR);
        }
    }

    private void addCloseListener() {
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                int result = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit the application?",
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    application.exit(ExitCode.OK);
                }
            }
        });
    }

    private void addResizeListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                configuration.putValue(WINDOW_WIDTH, Integer.toString(getWidth()));
                configuration.putValue(WINDOW_HEIGHT, Integer.toString(getHeight()));
            }
        });
    }

    private JMenuBar createMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu fileMenu = createFileMenu();
        jMenuBar.add(fileMenu);

        JMenu refactoringMenu = createRefactoringMenu();
        jMenuBar.add(refactoringMenu);

        JMenu aboutMenu = createAboutMenu();
        jMenuBar.add(aboutMenu);

        return jMenuBar;
    }

    private JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");

        JMenuItem openFileMenuItem = new JMenuItem("Open file...", KeyEvent.VK_O);
        openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.META_MASK));
        openFileMenuItem.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                final File selectedFile = fc.getSelectedFile();
                LOG.debug("Opening file: " + selectedFile.getAbsolutePath());
                try {
                    editor.openFile(selectedFile);
                } catch (FileNotFoundException | ParserException e1) {
                    LOG.warn("Can't open a file: " + selectedFile.getAbsolutePath(), e);
                }
            }
        });

        JMenuItem saveFileMenuItem = new JMenuItem("Save", KeyEvent.VK_S);
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.META_MASK));
        saveFileMenuItem.addActionListener(e -> {
            final JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showSaveDialog(this);
            editor.save(jFileChooser.getSelectedFile());
        });

        fileMenu.add(openFileMenuItem);
        fileMenu.add(saveFileMenuItem);

        fileMenu.addSeparator();

        JMenuItem exitMenuItem = new JMenuItem("Quit", KeyEvent.VK_Q);
        exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.META_MASK));
        exitMenuItem.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit the application?",
                    "Exit Application",
                    JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                application.exit(ExitCode.OK);
            }
        });

        fileMenu.add(exitMenuItem);
        return fileMenu;
    }

    private JMenu createAboutMenu() {
        final JMenu aboutMenu = new JMenu("About");
        aboutMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                SwingUtilities.invokeLater(() -> {
                    aboutMenu.setSelected(false);
                    final AboutWindow aboutWindow = new AboutWindow(getWidth() / 2, getHeight() / 2);
                    aboutWindow.pack();
                    aboutWindow.setVisible(true);
                });
            }

            @Override
            public void menuDeselected(MenuEvent ignore) {}

            @Override
            public void menuCanceled(MenuEvent ignore) {}
        });
        return aboutMenu;
    }

    private JMenu createRefactoringMenu() {
        final JMenu refactoringMenu = new JMenu("Refactor");
        JMenuItem renameMenuItem = new JMenuItem("Rename...", KeyEvent.VK_O);
        renameMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.META_MASK));
        renameMenuItem.addActionListener(e -> {
            String newValue = JOptionPane.showInputDialog(
                    this,
                    "Please rename node",
                    "Rename node",
                    JOptionPane.QUESTION_MESSAGE);
            int caretPosition = mainPanel.getCaretPosition();
            if (newValue != null) {
                editor.renameLeafNode(caretPosition, newValue);
            }
        });
        refactoringMenu.add(renameMenuItem);
        return refactoringMenu;
    }
}
