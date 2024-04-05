import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Monkey {
    int x, dx, y, left, dy;
    Image still, jump;

    ImageIcon s = new ImageIcon("still.png");
    ImageIcon j = new ImageIcon("jump.png");

    static ArrayList<Banana> bananas = new ArrayList<>();

    public Monkey() {
        x = 75;
        left = 150;
        y = 210;
        still = s.getImage();
    }

    public void move() {
        x += dx;
        left += dx;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            dx = -1;
            still = s.getImage();
        }
        if (key == KeyEvent.VK_RIGHT) {
            dx = 1;
            still = s.getImage();
        }
        if (key == KeyEvent.VK_SPACE) {
            throwBanana();
        }
        if (key == KeyEvent.VK_UP) {
            dy = 1;
            still = j.getImage();
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
        if (key == KeyEvent.VK_UP) {
            dy = 0;
            still = s.getImage();
        }
    }

    public void throwBanana() {
        if (storedBananas > 0) {
            storedBananas--;
            Banana z = new Banana((left + 60), (Board.v + 90 / 2));
            bananas.add(z);
        }
    }

    public static ArrayList<Banana> getBananas() {
        return bananas;
    }
}
