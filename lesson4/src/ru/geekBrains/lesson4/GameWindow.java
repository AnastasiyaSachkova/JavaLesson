package ru.geekBrains.lesson4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class GameWindow extends JFrame {
    private static GameWindow game_window;
    private static Image background;
    private static Image drop;
    private static Image gameOver;
    private static float dropLeft = 200;
    private static float dropTop = -100;
    private static long lastFrameTime;
    private static float dropSpeed = 200;
    private static int score = 0;


    public static void main(String[] args) throws IOException {
       // int a = 5;
       // int b = 20;
       // int x = 3 ;
//        String a = "my test";
//        String x = "e";
//        switch (a) {
//            case "MY TEST":
//                System.out.println("MY TEST");
//                break;
//            case "My test":
//                    System.out.println("My test");
//                    break;
//            case "my_test":
//                        System.out.println("my_test");
//                        break;
//        }
int i = 0;
        System.out.println(++i);
        System.out.println(i++);
        System.out.println(i);


 //       boolean res = !((a >= b) && (a <= 100 || b>4) && !(a == 5) && a != b);
//        System.out.println(s == s2);
//        System.out.println( s.equals(s2));

       // System.out.println(new int [][] {{1,2,7}, {3,4,5}}[1][2]);
        game_window = new GameWindow();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 100);
        game_window.setSize(900, 500);
        game_window.setResizable(false);

        lastFrameTime = System.nanoTime();

        GameField game_field = new GameField();
        game_window.add(game_field);
        game_window.setVisible(true);

        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
        //setupGameWindow();
        //setupImage();
    }

    private static void setupImage() throws IOException {
        background = ImageIO.read(GameWindow.class.getResourceAsStream("background.png"));
        drop = ImageIO.read(GameWindow.class.getResourceAsStream("drop.png"));
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
    }

    private static void setupGameWindow() {
        game_window = new GameWindow();
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(200, 100);
        game_window.setSize(900, 500);
        game_window.setResizable(false);
        GameField game_field = new GameField();
        game_field.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                int x = e.getX();
                int y = e.getY();
                float dropRight = dropLeft + drop.getWidth(null);
                float dropButton = dropTop + drop.getHeight(null);
                boolean isDrop = x>= dropLeft && x <= dropRight && y >= dropTop && y <= dropButton;
                if (isDrop) {
                    dropTop = -100;
                    dropLeft = (int) (Math.random() * (game_field.getWidth() - drop.getWidth(null)));
                    dropSpeed = dropSpeed + 20;
                    score ++;
                    game_window.setTitle("Score" + score);
                }



            }
        });

        game_window.add(game_field);
        game_window.setVisible(true);
    }

    private static void onRepaint(Graphics g) {
        //g.fillOval(10,10,100,100);
        //g.drawLine(100,150,200,300);


        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        dropTop = dropTop + dropSpeed * deltaTime;

        g.drawImage(background, 0,0,null);
        g.drawImage(drop, (int) dropLeft, (int) dropTop,null);
        if (dropTop > game_window.getHeight()) {
            g.drawImage(gameOver, 280,120,null);
        }


    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            onRepaint(g);
            repaint();

        }

    }
}
