import java.awt.*;
import java.awt.geom.Rectangle2D;

class Blocks implements Shape {
    private int x, y, width, height;
    private String text;
    private Color color;

    public Blocks(int x, int y, int width, int height, String text, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.color = color;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D.Double r = new Rectangle2D.Double(x, y, width, height);
        g2d.setColor(color);
        g2d.fill(r);

        // Calculate the position to center the text horizontally and vertically on top of the rectangle
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = (int) (x + (width - textWidth) / 2);
        int textY = (int) (y + (height - textHeight) / 2 + fm.getAscent());

        // Set the color of the text to black
        g2d.setColor(Color.BLACK);

        // Draw the text centered both horizontally and vertically on top of the rectangle
        g2d.drawString(text, textX, textY);
    }

    @Override
    public boolean contains(Point point) {
        return x <= point.x && point.x <= x + width && y <= point.y && point.y <= y + height;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
