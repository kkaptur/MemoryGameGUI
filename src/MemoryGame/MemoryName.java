package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MemoryName extends MemoryFrame {

    long gameTime;
    int boardSize;
    String userName;
    JTextField nameField;
    MemoryButton okButton;
    JLabel nameLabel;

    MemoryName(long gameTime, int boardSize) {
        super();
        this.gameTime = gameTime;
        this.boardSize = boardSize;

        JPanel jp = new JPanel(new GridBagLayout());
        nameLabel = new JLabel(" YOUR NAME");
        nameField = new JTextField("Player");
        nameField.setPreferredSize(new Dimension(200, 20));
        okButton = new MemoryButton("OK");
        gbc.ipady = 20;
        gbc.ipadx = 30;
        gbc.fill = GridBagConstraints.VERTICAL;
        setBigFont(nameLabel);
        setMediumFont(nameField);
        jp.add(nameLabel, gbc);
        jp.add(nameField, gbc);
        jp.add(okButton, gbc);
        jp.setOpaque(false);
        this.getContentPane().add(jp, BorderLayout.CENTER);
        pack();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userName = nameField.getText();
                try {
                    new MemoryMenu();
                    new Result(userName, gameTime, boardSize);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
    }
}