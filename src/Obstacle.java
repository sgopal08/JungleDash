import java.awt.*;
import javax.swing.ImageIcon;

public class Obstacle {
    Image img;
    int x, y;
    boolean isAlive = true;

    public Obstacle(int startX, int startY, String location) {
        x = startX;
        y = startY;
        ImageIcon l = new ImageIcon(location);
        img = l.getImage();
    }

    public void move(int dx, int left) {
        if (dx == 1 && ((left + dx) > 150)) // Corrected condition for crocodile movement
            x -= dx;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 122, 54);
    }
}
