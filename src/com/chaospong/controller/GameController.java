package com.chaospong.controller;

import com.chaospong.model.GameModel;
import com.chaospong.model.ScoreEntry;
import com.chaospong.view.GameFrame;
import com.chaospong.view.GamePanel;
import com.chaospong.util.Constants;

import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController extends KeyAdapter implements ActionListener {
    private GameModel model;
    private GamePanel view;
    private GameFrame mainFrame;
    private Timer gameTimer;

    public GameController(GameModel model, GamePanel view, GameFrame mainFrame) {
        this.model = model;
        this.view = view;
        this.mainFrame = mainFrame;
        this.gameTimer = new Timer(1000 / 60, this); // 60 FPS
    }

    public void startGame() {
        model.resetGame();
        view.requestFocusInWindow();
        gameTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!model.isGameOver()) {
            model.update();
            view.repaint();
        } else {
            gameTimer.stop();
            view.repaint();
            handleGameOver();
        }
    }

    private void handleGameOver() {
        String name = JOptionPane.showInputDialog(mainFrame, 
                model.getWinnerMsg() + "\nIngresa tu nombre:", 
                "Fin de la Partida", JOptionPane.PLAIN_MESSAGE);
                
        if (name != null && !name.trim().isEmpty()) {
            ScoreManager.saveScore(new ScoreEntry(name, model.getMaxScore()));
        }
        mainFrame.showScores(); // Cambiar al panel de scores tras jugar
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) model.getPaddleLeft().setVy(-Constants.PADDLE_SPEED);
        if (key == KeyEvent.VK_S) model.getPaddleLeft().setVy(Constants.PADDLE_SPEED);
        if (key == KeyEvent.VK_UP) model.getPaddleRight().setVy(-Constants.PADDLE_SPEED);
        if (key == KeyEvent.VK_DOWN) model.getPaddleRight().setVy(Constants.PADDLE_SPEED);
        
        // Atajo de emergencia para volver al menú
        if (key == KeyEvent.VK_ESCAPE) {
            gameTimer.stop();
            mainFrame.showMenu();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) model.getPaddleLeft().setVy(0);
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) model.getPaddleRight().setVy(0);
    }
}