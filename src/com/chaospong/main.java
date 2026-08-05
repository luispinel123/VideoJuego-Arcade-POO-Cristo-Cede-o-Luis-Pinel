package com.chaospong;

import com.chaospong.view.GameFrame;
import javax.swing.SwingUtilities;

public class main {
    public static void main(String[] args) {
        // Ejecutar en el hilo correcto de Swing
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }
}