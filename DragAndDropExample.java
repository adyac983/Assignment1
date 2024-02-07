import javax.swing.*;
import java.awt.*;

public class DragAndDropExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Drag and Drop Shapes Example");
            frame.setSize(400, 400);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            DragAndDrop d = new DragAndDrop();
            d.setShape(new Blocks(250, 100, 50, 50, "step", Color.RED));

            frame.add(d);
            frame.setVisible(true);
        });
    }
}
