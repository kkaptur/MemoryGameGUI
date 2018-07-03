package MemoryGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimerTask;

public class MemoryGame extends MemoryFrame {
    int boardSize = MemoryNumber.getBoardSize();
    long elapsedTime;
    int score = 0;
    long startTime;
    JLabel timeLabel;
    JPanel cardsPanel;
    MemoryButton backButton;
    ArrayList<MemoryCard> cardList;
    int rowsNumber;

    public MemoryGame() throws IOException {
        super();
        if (boardSize>70)
            this.setPreferredSize(new Dimension(800,900));

        startTime = System.currentTimeMillis();

        // adding timer
        timeLabel = new JLabel("");
        Timer SimpleTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long timePassed = ((System.currentTimeMillis() - startTime) / 1000);
                timeLabel.setText("TIME : " + String.format("%02d:%02d", timePassed / 60, timePassed % 60));
            }
        });
        SimpleTimer.start();

        //cards panel
        cardsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcCards = new GridBagConstraints();
        gbcCards.ipady = 70;
        gbcCards.ipadx = 70;

        //create Card Board
        cardList = makeCardList(boardSize);


        for (int j = 0; j < cardList.size(); j++) {
            gbcCards.gridx = j % countGridRows();
            cardsPanel.add(cardList.get(j), gbcCards);
            cardList.get(j).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    mouseClickedHandle(e);
                }
            });
        }

        //main panel
        GridBagConstraints gbcGame = new GridBagConstraints();
        gbcGame.ipady = 20;
        gbcGame.gridwidth = GridBagConstraints.REMAINDER;
        gbcGame.fill = GridBagConstraints.NONE;
        gbcGame.insets.top=18;
        JPanel jp = new JPanel(new GridBagLayout());
        backButton = new MemoryButton("STOP GAME");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MemoryMenu();
                    dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        jp.add(cardsPanel, gbcGame);
        jp.add(backButton, gbcGame);
        jp.add(timeLabel, gbcGame);
        setSmallFont(timeLabel);

        cardsPanel.setOpaque(false);
        jp.setOpaque(false);

        this.getContentPane().add(jp, BorderLayout.CENTER);
        pack();
    }

    MemoryCard clickedFirst = null;
    MemoryCard clickedStore = null;
    int counter = 0;
    int delay = 750;
    static boolean readyToClick = true;

    private ArrayList makeRandomNumberList() {
        ArrayList<Integer> randomNumberList = new ArrayList<>();
        for (int i = 0; i < 50; i++)
            randomNumberList.add(i);
        Collections.shuffle(randomNumberList);
        return randomNumberList;
    }

    private ArrayList makeCardList(int boardSize) {
        cardList = new ArrayList<>();
        ArrayList<Integer> randomNumberList = makeRandomNumberList();
        for (int i = 0; i < (boardSize / 2); i++) {
            try {
                cardList.add(new MemoryCard(randomNumberList.get(i)));
                cardList.add(new MemoryCard(randomNumberList.get(i)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Collections.shuffle(cardList);
        return cardList;
    }

    private int countGridRows(){
        rowsNumber = 0;
        int cols = 0;
        for (int i = 11; i > 1; i--) {
            if (boardSize % i == 0) {
                rowsNumber = i;
                cols = boardSize/rowsNumber;
                i = 1;
                System.out.println("rows" + rowsNumber);
                System.out.println("cols" + cols);
            }
            if (rowsNumber > 11 || cols > 11){
                rowsNumber=10;
                i=1;
            }
        }
        return rowsNumber;
    }

    public void compareCards(MemoryCard c1, MemoryCard c2) {
        new java.util.Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        if (c1.getNumber() == c2.getNumber()) {
                            c1.setMatched();
                            c2.setMatched();
                            score++;
                            if (score == boardSize / 2) {
                                win();
                            }
                        } else {
                            c1.turnDown();
                            c2.turnDown();
                        }
                        readyToClick = true;
                    }
                },
                delay
        );
    }

    protected void mouseClickedHandle(MouseEvent e) {
        if (readyToClick) {
            clickedFirst = (MemoryCard) e.getSource();
            clickedFirst.turnUp();
            if (clickedFirst.equals(clickedStore))
                return;
            if (counter > 0) {
                readyToClick = false;
                compareCards(clickedFirst, clickedStore);
                counter = -1;
            }
            if (counter == 0) {
                clickedStore = clickedFirst;
            }
            counter++;
        }
    }

    public void win() {
        elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println(elapsedTime);

    }


}

