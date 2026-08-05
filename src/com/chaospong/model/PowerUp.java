package com.chaospong.model;

import com.chaospong.util.Constants;
import java.awt.Rectangle;

public class PowerUp {
    private int x, y, size;
    private boolean active;

    public PowerUp() {
        this.size = 20;
        this.active = false;
    }

    public void spawn() {
        x = Constants.WIDTH / 2 - size / 2;
        y = (int) (Math.random() * (Constants.HEIGHT - size - 100)) + 50;
        active = true;
    }

    public void consume() { active = false; }
    public Rectangle getBounds() { return new Rectangle(x, y, size, size); }
    public boolean isActive() { return active; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getSize() { return size; }
}