import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class DragAndDrop extends JPanel {
    private Shape currentShape;
    private int offsetX, offsetY;
    boolean isDragging;

    public DragAndDrop() {
        // Add mouse listener
        addMouseListener(new MyMouseListener());
        addMouseMotionListener(new MyMouseMotionListener());
    }

    public void setShape(Shape shape) {
        this.currentShape = shape;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //sample paintComponent that draws the panel where the blocks are located
        super.paintComponent(g);
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int blockWidth = 450;
        int blockHeight = 550;
        int blockX = panelWidth - blockWidth - 200; // Adjust the values as needed
        int blockY = panelHeight / 2 - blockHeight / 2;
        g.setColor(Color.WHITE);
        g.fillRect(blockX, blockY, blockWidth, blockHeight);
        g.setColor(Color.GRAY);
        g.drawRect(blockX, blockY, blockWidth, blockHeight);
        if (currentShape != null) {
            currentShape.draw(g);
        }
    }

    private class MyMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            if (currentShape != null && currentShape.contains(e.getPoint())) {
                offsetX = e.getX() - currentShape.getX();
                offsetY = e.getY() - currentShape.getY();
                isDragging = true;
            }
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if (isDragging) {
                isDragging = false;
            }
        }
    }

    private class MyMouseMotionListener extends MouseAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (isDragging) {
                currentShape.setLocation(e.getX() - offsetX, e.getY() - offsetY);
                repaint();
            }
        }
    }
}
