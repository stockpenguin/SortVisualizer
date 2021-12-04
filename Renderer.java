import javax.swing.*;
import java.awt.*;

public class Renderer extends JPanel {
    private Background bg;
    private PenguinList<SortRectangle> rectangles;

    public Renderer(Background bg) {
        this.bg = bg;
        /* custom array list with listener that is set to repaint() */
        this.rectangles = new PenguinList<>(this::repaint);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        /* paint background */
        g.setColor(bg.getColor());
        g.fillRect(0, 0, bg.getWidth(), bg.getHeight());

        /* paint sort rectangles */
        for (int i = 0; i < rectangles.size(); i++) {
            SortRectangle r = rectangles.get(i);
            g.setColor(r.getColor());
            g.fillRect(r.getX(), r.getY(), r.getWidth(), r.getHeight());
        }
    }

    public PenguinList<SortRectangle> getRectangles() {
        return rectangles;
    }
}
