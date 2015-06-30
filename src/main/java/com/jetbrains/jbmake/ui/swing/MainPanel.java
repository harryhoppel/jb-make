package com.jetbrains.jbmake.ui.swing;

import com.jetbrains.jbmake.core.Editor;
import com.jetbrains.jbmake.core.EditorListener;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author daywalker
 * @since 30/06/15.
 */
public class MainPanel extends JPanel implements EditorListener {
    private static final int PARSE_DELAY_AFTER_EDITING = 250;

    private final Editor editor;

    private final JTextArea textArea;
    private JPanel makefileTreePanel;
    private JScrollPane treeView;

    public MainPanel(Editor editor) {
        this.editor = editor;

        setLayout(new BorderLayout());
        JTabbedPane makefileTabs = new JTabbedPane();
        makefileTabs.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        textArea = new JTextArea();
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setEditable(true);
        setText(editor, textArea);
        setupEditing();

        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createEtchedBorder()));
        leftPanel.add(areaScrollPane);

        JPanel makefileTreePanel = getMakefileTreePanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
        splitPane.setResizeWeight(1.0);
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(makefileTreePanel);
        makefileTabs.add(splitPane, BorderLayout.CENTER);

        add(makefileTabs);
    }

    private void setupEditing() {
        Timer timer = new Timer(PARSE_DELAY_AFTER_EDITING, e -> {
            editor.updateMakefile(textArea.getText());
        });
        timer.setRepeats(false);
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                timer.restart();
            }
        });
    }

    private void setText(Editor editor, JTextArea textArea) {
        textArea.setText(editor.getFileContent());
    }

    private JPanel getMakefileTreePanel() {
        makefileTreePanel = new JPanel(new BorderLayout());
        makefileTreePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        treeView = createJTree();
        makefileTreePanel.add(treeView);
        return makefileTreePanel;
    }

    private JScrollPane createJTree() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(editor.getFile().getAbsolutePath());
        editor.exportMakefileAsTree(rootNode);
        JTree tree = new JTree(rootNode);
        JScrollPane treeView = new JScrollPane(tree);
        treeView.setBorder(BorderFactory.createEtchedBorder());
        return treeView;
    }

    public int getCaretPosition() {
        return textArea.getCaretPosition();
    }

    @Override
    public void makefileChanged() {
        int caretPosition = textArea.getCaretPosition();
        setText(editor, textArea);
        textArea.setCaretPosition(caretPosition);
        updateTree();
    }

    private void updateTree() {
        makefileTreePanel.remove(treeView);
        treeView = createJTree();
        makefileTreePanel.add(treeView);
        makefileTreePanel.getRootPane().repaint();
    }
}
