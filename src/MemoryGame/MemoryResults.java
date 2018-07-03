package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MemoryResults extends MemoryFrame {

    MemoryResults(){
        super();

        JLabel resultsTitle = new JLabel("RESULTS");
        MemoryButton backButton = new MemoryButton("BACK");


        JList jList = new JList((Result.getResultsList()).toArray());
        JScrollPane jsp = new JScrollPane(jList);
        jsp.setPreferredSize(new Dimension(450,200));
        setSmallFont(jList);
        setBigFont(resultsTitle);

        gbc.fill=GridBagConstraints.VERTICAL;
        JPanel jp = new JPanel(new GridBagLayout());
        jp.add(resultsTitle,gbc);
        jp.add(jsp, gbc);
        jp.add(backButton, gbc);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MemoryMenu();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                dispose();
            }
        });
        jp.setOpaque(false);
        this.getContentPane().add(jp,BorderLayout.CENTER);
        pack();
    }
}

