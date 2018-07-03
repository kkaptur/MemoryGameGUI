package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MemoryMenu extends MemoryFrame {
    public MemoryMenu() throws IOException {
        super();

        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());

        JLabel gameName = new JLabel("MEMORY GAME");
        MemoryButton newGameButton = new MemoryButton("PLAY A GAME");
        MemoryButton scoresButton = new MemoryButton("HIGH SCORES");
        MemoryButton exitButton = new MemoryButton("EXIT");

        jp.add(gameName, gbc);
        jp.add(newGameButton, gbc);
        jp.add(scoresButton, gbc);
        jp.add(exitButton, gbc);

        setBigFont(gameName);
        setMediumFont(newGameButton);
        setMediumFont(scoresButton);
        setMediumFont(exitButton);
        jp.setOpaque(false);

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemoryNumber();
                dispose();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        scoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MemoryResults();
                dispose();
            }
        });
        this.getContentPane().add(jp, BorderLayout.CENTER);
        pack();
    }

}
