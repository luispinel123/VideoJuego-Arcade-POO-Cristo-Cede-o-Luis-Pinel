package com.chaospong.view;

import com.chaospong.util.Constants;
import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    public MenuPanel(GameFrame frame) {
        setBackground(Constants.BG_COLOR);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.gridx = 0;
        
        JLabel title = new JLabel("CHAOS PONG");
        title.setFont(Constants.TITLE_FONT);
        title.setForeground(Constants.ACCENT_COLOR);
        gbc.gridy = 0;
        add(title, gbc);

        JButton btnStart = createButton("INICIAR JUEGO");
        btnStart.addActionListener(e -> frame.startGame());
        gbc.gridy = 1;
        add(btnStart, gbc);

        JButton btnScores = createButton("MEJORES PUNTAJES");
        btnScores.addActionListener(e -> frame.showScores());
        gbc.gridy = 2;
        add(btnScores, gbc);

        JButton btnExit = createButton("SALIR");
        btnExit.addActionListener(e -> System.exit(0));
        gbc.gridy = 3;
        add(btnExit, gbc);
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(Constants.MAIN_FONT);
        btn.setBackground(Color.DARK_GRAY);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        return btn;
    }
}