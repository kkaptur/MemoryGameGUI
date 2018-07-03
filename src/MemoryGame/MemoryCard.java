package MemoryGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MemoryCard extends JLabel {
    boolean turnedUp;
    boolean matched;
    int imgIndex = 0;


    BufferedImage myPicture = null;
    ImageIcon imageIcon;
    JLabel picLabel;
    String path = null;

    int x = 0;
    int y = 0;
    int width = 60;
    int height = 60;

    MemoryCard(int imgIndex) throws IOException {
        setOpaque(false);
        turnedUp = false;
        matched = false;
        this.imgIndex = imgIndex;
        this.setBounds(x, y, width, height);
        this.setSize(width, height);
        path = "resources/icons/" + Integer.toString(imgIndex) + ".png";
        myPicture = null;
        try {
            myPicture = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageIcon = new ImageIcon(myPicture.getScaledInstance(width - 10, height - 10, Image.SCALE_SMOOTH));
        picLabel = new JLabel(imageIcon);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        picLabel.setBounds(x, y, width, height);
        this.add(picLabel);
        g.fillRoundRect(x, y, width, height, 10, 10);
        if (matched) {
            imgOff(picLabel);
        } else if (turnedUp) {
            picLabel.setVisible(true);
            g.setColor(Color.WHITE);
            g.fillRoundRect(x, y, width, height, 10, 10);
        } else {
            g.setColor(Color.GRAY);
            g.fillRoundRect(x, y, width, height, 10, 10);
            picLabel.setVisible(false);
        }
    }

    public void turnUp() {
        if (!this.turnedUp)
            turnedUp = true;
        repaint();
    }

    public void turnDown() {
        if (this.turnedUp)
            turnedUp = false;
        repaint();
    }

    public void setMatched() {
        matched = true;
        repaint();
    }

    public void imgOff(JLabel label) {
        label.setVisible(false);
    }

    public int getNumber() {
        return this.imgIndex;
    }


}
