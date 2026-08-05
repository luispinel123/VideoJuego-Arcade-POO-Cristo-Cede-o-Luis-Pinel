package com.chaospong.model;

import java.io.Serializable;
import java.time.LocalDate;

public class ScoreEntry implements Serializable, Comparable<ScoreEntry> {
    private static final long serialVersionUID = 1L;
    
    private String playerName;
    private int score;
    private String date;

    public ScoreEntry(String playerName, int score) {
        this.playerName = playerName;
        this.score = score;
        this.date = LocalDate.now().toString();
    }

    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public String getDate() { return date; }

    @Override
    public int compareTo(ScoreEntry o) {
        return Integer.compare(o.score, this.score); // Orden descendente
    }
}