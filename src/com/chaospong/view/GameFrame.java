package com.chaospong.view;

import com.chaospong.controller.GameController;
import com.chaospong.model.GameModel;
import com.chaospong.util.Constants;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Dimension;

public class GameFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainContainer;
    
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private ScoreboardPanel scoreboardPanel;
    
    private GameController controller;

    public GameFrame() {
        setTitle("Chaos Pong Arcade");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Configurar CardLayout para cambiar pantallas
        cardLayout = new CardLayout();
        mainContainer = new JPanel(cardLayout);
        mainContainer.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));

        // Inicializar Componentes MVC
        GameModel model = new GameModel();
        gamePanel = new GamePanel(model);
        menuPanel = new MenuPanel(this);
        scoreboardPanel = new ScoreboardPanel(this);
        
        controller = new GameController(model, gamePanel, this);
        gamePanel.addKeyListener(controller);

        // Añadir pantallas al contenedor
        mainContainer.add(menuPanel, "MENU");
        mainContainer.add(gamePanel, "GAME");
        mainContainer.add(scoreboardPanel, "SCORES");

        add(mainContainer);
        pack();
        setLocationRelativeTo(null); // Centrar en pantalla
        
        showMenu(); // Pantalla inicial
    }

    public void showMenu() {
        cardLayout.show(mainContainer, "MENU");
    }

    public void startGame() {
        cardLayout.show(mainContainer, "GAME");
        controller.startGame(); // Dispara el GameLoop
    }

    public void showScores() {
        scoreboardPanel.refreshScores(); // Lee del archivo binario
        cardLayout.show(mainContainer, "SCORES");
    }
}