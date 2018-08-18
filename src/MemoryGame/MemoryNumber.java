package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MemoryNumber extends MemoryFrame {
       static int boardSize=4;
    public MemoryNumber(){
        super();

        //general vertical layout
        JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        gbc.fill=GridBagConstraints.VERTICAL;

        //element 1
        JLabel sizeLabel = new JLabel("BOARD SIZE");
        //element 2
        JPanel jpTextField = new JPanel(new GridBagLayout());
        JTextField textField = new JTextField(Integer.toString(boardSize));
        textField.setPreferredSize(new Dimension(40,40));
        JButton sizeIncr = new MemoryButton("+");
        JButton sizeDecr = new MemoryButton("-");
        //element 3
        JPanel jpButtons = new JPanel(new GridBagLayout());
        MemoryButton backButton = new MemoryButton("BACK");
        MemoryButton playButton = new MemoryButton("LET'S PLAY");
        //element 4
        JLabel infoLabel = new JLabel("");

        //adding vertical
        jp.add(sizeLabel,gbc);
        jp.add(jpTextField,gbc);
        jp.add(jpButtons,gbc);
        jp.add(infoLabel,gbc);

        //adding horizontal
        gbc.gridwidth=GridBagConstraints.HORIZONTAL;
        gbc.insets.right=23;
        jpTextField.add(textField,gbc);
        jpTextField.add(sizeDecr,gbc);
        jpTextField.add(sizeIncr,gbc);
        jpButtons.add(backButton,gbc);
        jpButtons.add(playButton,gbc);

        //front
        textField.setBorder(BorderFactory.createMatteBorder(3,3,3,3,Color.BLACK));
        textField.setHorizontalAlignment(JTextField.CENTER);
        setMediumFont(textField);
        setSmallFont(infoLabel);
        setBigFont(sizeLabel);

        jp.setOpaque(false);
        jpTextField.setOpaque(false);
        jpButtons.setOpaque(false);

        //actions
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
        sizeIncr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardSize%2==0 && boardSize<99)
                    boardSize+=2;
                else if (boardSize<100)
                    boardSize++;
                textField.setText(Integer.toString(boardSize));
            }
        });
        sizeDecr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardSize%2==0 && boardSize>2)
                    boardSize-=2;
                else if (boardSize>2)
                    boardSize--;
                textField.setText(Integer.toString(boardSize));
            }
        });
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                boardSize = Integer.parseInt(textField.getText());
            }
            public void removeUpdate(DocumentEvent e) {

            }
            public void changedUpdate(DocumentEvent e) {
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (boardSize>100 || boardSize<2)
                    infoLabel.setText("WRONG BOARD SIZE! IT MUST BE BETWEEN 2 AND 100");
                else if (boardSize%2==0) {
                    try {
                        MemoryGame newGame = new MemoryGame();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    dispose();
                }
                else
                    infoLabel.setText("WRONG BOARD SIZE! IT MUST BE EVEN");
            }
        });
        this.getContentPane().add(jp, BorderLayout.CENTER);
        pack();
    }

    public static int getBoardSize(){
        return boardSize;
    }
}
