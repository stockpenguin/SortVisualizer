import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private Renderer r;

    public Window(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Background bg = new Background(width, height, Color.BLACK);
        r = new Renderer(bg);
        add(r);
        setVisible(true);
    }

    public Renderer getR() {
        return r;
    }
}
