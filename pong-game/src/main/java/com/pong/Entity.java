package com.pong;

import java.awt.*;

public abstract class Entity extends Rectangle {

    public Entity(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public void render(Graphics g) {
    }
}
