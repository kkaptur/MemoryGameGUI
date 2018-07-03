package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MemoryButton extends JButton {
    public MemoryButton(String msg) {
        super(msg);
        this.setBackground(new Color(221, 206, 165));
        this.setFont(new Font("Helvetica", 1, 18));
        this.setForeground(Color.BLACK);

        //take off focus ring
        this.setFocusPainted(false);
        setMemoryBorder(this);
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(230, 230, 220));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(221, 206, 165));
            }
        });
    }

    public void setMemoryBorder(JButton button) {
        button.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
    }
}
