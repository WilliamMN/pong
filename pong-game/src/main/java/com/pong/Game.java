package com.pong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Game extends Canvas implements Runnable, KeyListener {

    public static final int WIDTH = 960;
    public static final int HEIGHT = 480;
    public static final int SCALE = 3;
    public static String PLACAR = "0 x 0";

    private List<Entity> entities = new ArrayList<>();

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        entities.add(new Player());
        entities.add(new Enemy());
        entities.add(new Ball());
    }

    public static Boolean colisao(boolean b) {
        return b;
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", Font.BOLD, 10 * SCALE));
        g.drawString(PLACAR, 200, 50);

        for (Entity e : entities) {
            e.render(g);
        }

        bs.show();
    }

    private void tick() {
        ((Player) entities.get(0)).tick();
        ((Enemy) entities.get(1)).tick((Ball) entities.get(2));
        ((Ball) entities.get(2)).tick((Enemy) entities.get(1), (Player) entities.get(0));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ((Player) entities.get(0)).up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ((Player) entities.get(0)).down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && ((Ball) entities.get(2)).stoped) {
            ((Ball) entities.get(2)).stoped = false;
            switch (ThreadLocalRandom.current().nextInt(1, 4)) {
                case 1 -> {
                    ((Ball) entities.get(2)).right = true;
                    ((Ball) entities.get(2)).up = true;
                }
                case 2 -> {
                    ((Ball) entities.get(2)).left = true;
                    ((Ball) entities.get(2)).up = true;
                }
                case 3 -> {
                    ((Ball) entities.get(2)).right = true;
                    ((Ball) entities.get(2)).down = true;
                }
                case 4 -> {
                    ((Ball) entities.get(2)).left = true;
                    ((Ball) entities.get(2)).down = true;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            ((Player) entities.get(0)).up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            ((Player) entities.get(0)).down = false;
        }
    }

    @Override
    public void run() {
        while (true) {
            tick();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame frame = new JFrame();

        frame.add(game);
        frame.setTitle("Pong");
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

        new Thread(game).start();
    }
}
