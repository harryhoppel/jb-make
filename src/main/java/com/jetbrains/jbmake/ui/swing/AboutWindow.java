package com.jetbrains.jbmake.ui.swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * @author daywalker
 * @since 26/06/15.
 */
public class AboutWindow extends JWindow {
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;

    private KeyEventDispatcher closeViaEscapeKey;

    public AboutWindow(int windowCenterX, int windowCenterY) {
        int xPaintFrom = windowCenterX - WIDTH / 2;
        int yPaintFrom = windowCenterY - HEIGHT / 2;
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setBounds(xPaintFrom, yPaintFrom, xPaintFrom + WIDTH, yPaintFrom + HEIGHT);
        addDisposingListeners();
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, Color.WHITE, 0, getHeight(), Color.LIGHT_GRAY);
                g2.setPaint(gradient);
                g2.fill(new Rectangle2D.Double(0, 0, getWidth(), getHeight()));
                g2.setPaint(Color.black);
                g2.drawString("JBMakeEditor®", getWidth() / 3, getHeight() / 2);
            }
        });
    }

    private void addDisposingListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                dispose();
            }
        });
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(closeViaEscapeKey = e -> {
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                dispose();
            }
            return true;
        });
    }

    @Override
    public void dispose() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(closeViaEscapeKey);
        super.dispose();
    }
}
