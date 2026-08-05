package com.chaospong.controller;

import com.chaospong.model.ScoreEntry;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreManager {
    private static final String FILE_PATH = "data/highscores.dat";

    public static synchronized void saveScore(ScoreEntry newEntry) {
        List<ScoreEntry> scores = loadScores();
        scores.add(newEntry);
        Collections.sort(scores);

        if (scores.size() > 10) scores = new ArrayList<>(scores.subList(0, 10)); // Top 10

        File dir = new File("data");
        if (!dir.exists()) dir.mkdirs();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(scores);
        } catch (IOException e) {
            System.err.println("Error al guardar archivo binario: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static synchronized List<ScoreEntry> loadScores() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<ScoreEntry>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}