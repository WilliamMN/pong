package com.pong;

import java.awt.*;

public class Enemy extends Entity {
    public static int WIDTH = 16;
    public static int HEIGHT = 100;
    public int pontos = 0;

    public int spd = 6;


    public Enemy() {
        super(Game.WIDTH - WIDTH, 0, WIDTH, HEIGHT);
    }

    public void tick(Ball b) {
        if (!b.stoped) {
            if (Game.colisao(Game.HEIGHT - y != Game.HEIGHT) && (y + (HEIGHT / 2)) >= b.y) {
                y -= spd;
            } else if (Game.colisao(Game.HEIGHT - (y + HEIGHT) >= 1) && (y + (HEIGHT / 2)) <= b.y) {
                y += spd;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
