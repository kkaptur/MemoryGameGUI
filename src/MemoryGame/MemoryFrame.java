package MemoryGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MemoryFrame extends JFrame {
    JLabel myName = new JLabel("s17646  20.05.2018");
    public GridBagConstraints gbc = new GridBagConstraints();
    public MemoryFrame(){

        try{
            setIconImage(ImageIO.read(new File("resources/icons/0.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }

        this.getContentPane().add(myName, BorderLayout.SOUTH);
        setSmallFont(myName);

        //vertical
        gbc.gridwidth=GridBagConstraints.REMAINDER;
        gbc.fill=GridBagConstraints.HORIZONTAL;
        //button y-thickness
        gbc.ipady=12;
        gbc.ipadx=12;
        //button y-margin
        gbc.insets.top=23;

        this.getContentPane().setBackground(new Color(10,100,100));
        setTitle("Memory Game");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreWindow(this);
        setPreferredSize(new Dimension(800,500));
        pack();
        setVisible(true);
    }
    public  void setBigFont(Container container){
        container.setFont(new Font("Helvetica",Font.BOLD,26));
        container.setForeground(Color.BLACK);
    }
    public void setMediumFont(Container container){
        container.setFont(new Font("Helvetica",Font.BOLD,20));
        container.setForeground(Color.BLACK);

    }
    public void setSmallFont(Container container){
        container.setFont(new Font("Helvetica",Font.BOLD,16));
        container.setForeground(Color.BLACK);
    }
    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 5);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 50);
        frame.setLocation(x, y);
    }
}
