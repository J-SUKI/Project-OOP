package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setTitle("Let’s go meow meow");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);

        window.pack();
        window.setResizable(false); 
        window.setVisible(true);

        // หน้าต่างอยู่กลางจอ
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - window.getWidth()) / 2;
        int y = (screenSize.height - window.getHeight()) / 2;
        window.setLocation(x, y);

        gamepanel.startGameThread();
    }
}
