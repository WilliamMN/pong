package com.pong;

import java.awt.*;

public class Player extends Entity {

    public static int WIDTH = 16;
    public static int HEIGHT = 100;
    public int pontos = 0;

    public int spd = 6;
    public boolean up;
    public boolean down;


    public Player() {
        super(0, 0, WIDTH, HEIGHT);
    }

    public void tick() {
        if (up && Game.colisao(Game.HEIGHT - y <= Game.HEIGHT)) {
            y -= spd;
        } else if (down && Game.colisao(Game.HEIGHT - (y + HEIGHT) >= 1)) {
            y += spd;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}
