package com.pong;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class Ball extends Entity {
    public static int WIDTH = 16;
    public static int HEIGHT = 16;

    public int spdx = 6;
    public int spdy = 12;
    public boolean up;
    public boolean right;
    public boolean down;
    public boolean left;
    public boolean stoped = true;


    public Ball() {
        super((Game.WIDTH / 2) - WIDTH, (Game.HEIGHT / 2) - HEIGHT, WIDTH, HEIGHT);
    }

    public void tick(Enemy e, Player p) {

        if (right && Game.colisao(Game.WIDTH - (x + WIDTH) >= 1)) {
            x += spdx;

            if (this.intersects(e)) {
                right = false;
                left = true;
                spdx = ThreadLocalRandom.current().nextInt(6, 20);
                spdy = ThreadLocalRandom.current().nextInt(6, 20);
            }

            if (!Game.colisao(Game.WIDTH - (x + WIDTH) >= 1)) {
                x = (Game.WIDTH / 2) - WIDTH;
                y = (Game.HEIGHT / 2) - HEIGHT;
                p.pontos++;
                Game.PLACAR = String.format("%d x %d", p.pontos, e.pontos);
                stoped = true;
                right = false;
                left = false;
                up = false;
                down = false;
            }
        } else if (left && Game.colisao(Game.WIDTH - x <= Game.WIDTH)) {
            x -= spdx;

            if (this.intersects(p)) {
                right = true;
                left = false;
                spdx = ThreadLocalRandom.current().nextInt(6, 20);
                spdy = ThreadLocalRandom.current().nextInt(6, 20);
            }

            if (!Game.colisao(Game.WIDTH - x <= Game.WIDTH)) {
                x = (Game.WIDTH / 2) - WIDTH;
                y = (Game.HEIGHT / 2) - HEIGHT;
                e.pontos++;
                Game.PLACAR = String.format("%d x %d", p.pontos, e.pontos);
                stoped = true;
                right = false;
                left = false;
                up = false;
                down = false;
            }
        }

        if (up && Game.colisao(Game.HEIGHT - y <= Game.HEIGHT)) {
            y -= spdy;
            if (!Game.colisao(Game.HEIGHT - y <= Game.HEIGHT)) {
                up = false;
                down = true;
            }
        } else if (down && Game.colisao(Game.HEIGHT - (y + HEIGHT) >= 1)) {
            y += spdy;
            if (!Game.colisao(Game.HEIGHT - (y + HEIGHT) >= 1)) {
                up = true;
                down = false;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }
}
