package com.chaospong.model;

import com.chaospong.util.Constants;
import java.awt.Rectangle;

public class Paddle {
    private int x, y, vy, score;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
        this.score = 0;
    }

    public void update() {
        y += vy;
        if (y < 0) y = 0;
        if (y > Constants.HEIGHT - Constants.PADDLE_HEIGHT) {
            y = Constants.HEIGHT - Constants.PADDLE_HEIGHT;
        }
    }

    public void resetScore() { this.score = 0; }
    public void resetPosition(int y) { this.y = y; this.vy = 0; }
    public void addPoint() { this.score++; }

    public Rectangle getBounds() { return new Rectangle(x, y, Constants.PADDLE_WIDTH, Constants.PADDLE_HEIGHT); }
    public void setVy(int vy) { this.vy = vy; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getScore() { return score; }
}