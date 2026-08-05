package com.chaospong.view;

import com.chaospong.model.GameModel;
import com.chaospong.util.Constants;
import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel {
    private GameModel model;

    public GamePanel(GameModel model) {
        this.model = model;
        setBackground(Constants.BG_COLOR);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Línea central
        g2d.setColor(Color.DARK_GRAY);
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0);
        g2d.setStroke(dashed);
        g2d.drawLine(Constants.WIDTH / 2, 0, Constants.WIDTH / 2, Constants.HEIGHT);

        // Entidades
        g2d.setColor(Color.WHITE);
        g2d.fill(model.getPaddleLeft().getBounds());
        g2d.fill(model.getPaddleRight().getBounds());

        g2d.setColor(Constants.ACCENT_COLOR);
        g2d.fillOval(model.getBall().getX(), model.getBall().getY(), Constants.BALL_SIZE, Constants.BALL_SIZE);

        if (model.getPowerUp().isActive()) {
            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(model.getPowerUp().getX(), model.getPowerUp().getY(), 
                         model.getPowerUp().getSize(), model.getPowerUp().getSize());
        }

        // Puntuaciones
        g2d.setColor(Color.WHITE);
        g2d.setFont(Constants.TITLE_FONT);
        g2d.drawString(String.valueOf(model.getPaddleLeft().getScore()), Constants.WIDTH / 4, 50);
        g2d.drawString(String.valueOf(model.getPaddleRight().getScore()), 3 * Constants.WIDTH / 4, 50);
        
        // Letrero de fin de juego
        if (model.isGameOver()) {
            g2d.setColor(new Color(0, 0, 0, 180));
            g2d.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
            g2d.setColor(Constants.ACCENT_COLOR);
            String msg = model.getWinnerMsg();
            FontMetrics fm = g2d.getFontMetrics();
            g2d.drawString(msg, (Constants.WIDTH - fm.stringWidth(msg)) / 2, Constants.HEIGHT / 2);
        }
    }
}