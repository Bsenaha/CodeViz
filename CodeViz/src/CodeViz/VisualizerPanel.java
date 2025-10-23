package CodeViz;

import javax.swing.*;
import java.awt.*;

public class VisualizerPanel extends JPanel {

    private int lines = 0;
    private int complexity = 0;
    private String face = "";

    public void updateData(int lines, int complexity, String face) {
        this.lines = lines;
        this.complexity = complexity;
        this.face = face;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        int panelHeight = getHeight() - 50;
        int panelWidth = getWidth();

        if (lines == 0 && complexity == 0 && face.isEmpty()) {
            g.drawString("Open a Folder with a valid File to analyze", panelWidth/2 - 130, (panelHeight + 50)/2);
            return;
        }

        int sizeHeight = Math.min(panelHeight - 100, (panelHeight - 100) * lines/200);
        int complexityHeight = Math.min(panelHeight - 100, (panelHeight - 100) * complexity/200);

        g.setColor(Color.darkGray);
        g.drawRect(150, 100, 100, panelHeight - 100);
        g.setColor(Color.RED);
        g.fillRect(150, panelHeight - sizeHeight, 100, sizeHeight);
        g.setColor(Color.BLACK);
        g.drawString("Size (200 Max): " + lines, 135, panelHeight +20);

        g.setColor(Color.darkGray);
        g.drawRect(400, 100, 100, panelHeight - 100);
        g.setColor(Color.BLUE);
        g.fillRect(400, panelHeight - complexityHeight, 100, complexityHeight);
        g.setColor(Color.BLACK);
        g.drawString("Complexity (200 Max): " + complexity, 370, panelHeight +20);

        g.setColor(Color.CYAN);
        int x = 680;
        int y = panelHeight/2;
        g.fillOval(x, y, 150, 150);
        g.setColor(Color.MAGENTA);
        g.fillOval(x + 40, y + 45, 10, 10);
        g.fillOval(x + 95, y + 45, 10, 10);
        if ("Happy".equalsIgnoreCase(face)) {
            g.drawArc(x + 45, y + 80, 60, 30, 180, 180);
        } else if ("Neutral".equalsIgnoreCase(face)) {
            g.drawLine(x + 40, y + 90, x + 110, y + 90);
        } else if ("Sad".equalsIgnoreCase(face)) {
            g.drawArc(x + 45, y + 80, 60, 30, 0, 180);
        }
        g.setColor(Color.BLACK);
        g.drawString("Overall", 730, panelHeight +20);

    }
}
