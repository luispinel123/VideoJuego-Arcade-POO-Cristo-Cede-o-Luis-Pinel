package com.chaospong.model;

import com.chaospong.util.Constants;

public class GameModel {
    private Paddle paddleLeft, paddleRight;
    private Ball ball;
    private PowerUp powerUp;
    private boolean gameOver;
    private String winnerMsg;

    public GameModel() {
        paddleLeft = new Paddle(30, Constants.HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2);
        paddleRight = new Paddle(Constants.WIDTH - 45, Constants.HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2);
        ball = new Ball();
        powerUp = new PowerUp();
        gameOver = false;
    }

    public void resetGame() {
        paddleLeft.resetScore();
        paddleRight.resetScore();
        paddleLeft.resetPosition(Constants.HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2);
        paddleRight.resetPosition(Constants.HEIGHT / 2 - Constants.PADDLE_HEIGHT / 2);
        ball.reset();
        powerUp.consume();
        gameOver = false;
    }

    public void update() {
        if (gameOver) return;

        paddleLeft.update();
        paddleRight.update();
        ball.update();

        ball.checkCollision(paddleLeft);
        ball.checkCollision(paddleRight);

        // Generar Power-Up
        if (!powerUp.isActive() && Math.random() < 0.002) {
            powerUp.spawn();
        }

        // Consumir Power-Up
        if (powerUp.isActive() && ball.getBounds().intersects(powerUp.getBounds())) {
            powerUp.consume();
        }

        // Puntuación
        if (ball.getX() < 0) {
            paddleRight.addPoint();
            checkWin();
            if(!gameOver) ball.reset();
        } else if (ball.getX() > Constants.WIDTH) {
            paddleLeft.addPoint();
            checkWin();
            if(!gameOver) ball.reset();
        }
    }

    private void checkWin() {
        if (paddleLeft.getScore() >= Constants.WINNING_SCORE) {
            winnerMsg = "¡Jugador Izquierda Gana!";
            gameOver = true;
        } else if (paddleRight.getScore() >= Constants.WINNING_SCORE) {
            winnerMsg = "¡Jugador Derecha Gana!";
            gameOver = true;
        }
    }

    public int getMaxScore() { return Math.max(paddleLeft.getScore(), paddleRight.getScore()) * 100; }
    public String getWinnerMsg() { return winnerMsg; }
    public Paddle getPaddleLeft() { return paddleLeft; }
    public Paddle getPaddleRight() { return paddleRight; }
    public Ball getBall() { return ball; }
    public PowerUp getPowerUp() { return powerUp; }
    public boolean isGameOver() { return gameOver; }
}