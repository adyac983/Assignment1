import java.awt.*;

public interface Shape {
    void draw(Graphics g);

    boolean contains(Point point);

    int getX();

    int getY();

    void setLocation(int x, int y);
}
