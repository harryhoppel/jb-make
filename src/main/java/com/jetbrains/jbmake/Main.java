package com.jetbrains.jbmake;

import javax.swing.*;

/**
 * Maven generated app
 *
 */
public class Main {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        jFrame.setLayout();
        JButton demoButton = new JButton("Demo");
        jFrame.getContentPane().add(demoButton);
        JMenuBar jMenuBar = new JMenuBar();
        JMenu jMenu = new JMenu();
        jMenuBar.add(jMenu);
        jFrame.add(jMenuBar);

        jFrame.pack();
        jFrame.setVisible(true);

    }
}
