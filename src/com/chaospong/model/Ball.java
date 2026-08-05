package com.chaospong.model;

import com.chaospong.util.Constants;
import java.awt.Rectangle;

public class Ball {
    private int x, y, vx, vy;

    public Ball() { reset(); }

    public void reset() {
        x = Constants.WIDTH / 2 - Constants.BALL_SIZE / 2;
        y = Constants.HEIGHT / 2 - Constants.BALL_SIZE / 2;
        vx = (Math.random() > 0.5 ? 1 : -1) * Constants.BALL_INITIAL_SPEED;
        vy = (Math.random() > 0.5 ? 1 : -1) * Constants.BALL_INITIAL_SPEED;
    }

    public void update() {
        x += vx;
        y += vy;
        // Rebote en paredes superior/inferior
        if (y <= 0 || y >= Constants.HEIGHT - Constants.BALL_SIZE) {
            vy = -vy;
        }
    }

    public void checkCollision(Paddle p) {
        if (getBounds().intersects(p.getBounds())) {
            vx = -vx;
            // Incrementar velocidad levemente por rebote
            vx += (vx > 0) ? 1 : -1; 
            // Variar el ángulo dependiendo de la zona de impacto en la raqueta
            int hitPoint = (y + Constants.BALL_SIZE / 2) - (p.getY() + Constants.PADDLE_HEIGHT / 2);
            vy = hitPoint / 10; 
        }
    }

    public Rectangle getBounds() { return new Rectangle(x, y, Constants.BALL_SIZE, Constants.BALL_SIZE); }
    public int getX() { return x; }
    public int getY() { return y; }
}