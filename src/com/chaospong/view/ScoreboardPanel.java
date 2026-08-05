package com.chaospong.view;

import com.chaospong.controller.ScoreManager;
import com.chaospong.model.ScoreEntry;
import com.chaospong.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ScoreboardPanel extends JPanel {
    private JTextArea scoreArea;

    public ScoreboardPanel(GameFrame frame) {
        setBackground(Constants.BG_COLOR);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("TOP 10 HIGH SCORES", SwingConstants.CENTER);
        title.setFont(Constants.TITLE_FONT);
        title.setForeground(Constants.ACCENT_COLOR);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(title, BorderLayout.NORTH);

        scoreArea = new JTextArea();
        scoreArea.setFont(Constants.MAIN_FONT);
        scoreArea.setBackground(Constants.BG_COLOR);
        scoreArea.setForeground(Color.WHITE);
        scoreArea.setEditable(false);
        scoreArea.setMargin(new Insets(20, 150, 20, 150));
        add(new JScrollPane(scoreArea), BorderLayout.CENTER);

        JButton btnBack = new JButton("VOLVER AL MENÚ");
        btnBack.setFont(Constants.MAIN_FONT);
        btnBack.setBackground(Color.DARK_GRAY);
        btnBack.setForeground(Color.WHITE);
        btnBack.addActionListener(e -> frame.showMenu());
        add(btnBack, BorderLayout.SOUTH);
    }

    public void refreshScores() {
        List<ScoreEntry> scores = ScoreManager.loadScores();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-20s %-10s\n", "POS", "JUGADOR", "PUNTOS"));
        sb.append("------------------------------------------\n");
        
        int pos = 1;
        for (ScoreEntry entry : scores) {
            sb.append(String.format("%-5d %-20s %-10d\n", pos++, entry.getPlayerName(), entry.getScore()));
        }
        scoreArea.setText(sb.toString());
    }
}