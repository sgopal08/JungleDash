import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Board extends JPanel implements ActionListener, Runnable {

    @Override
public Dimension getPreferredSize() {
    return new Dimension(1200, 400); // Replace width and height with appropriate values
}

    
    Monkey m;
    Image background;
    Image bananaImage;
    Image crocImage;
    Image jumpImage;
    Image stillImage;
    Timer time;
    static int v = 210;
    Thread animator;
    Obstacle croc;
    Obstacle croc2;
    boolean lost = false;

    boolean a = false;
    boolean done2 = false;

    static Font font = new Font("Serif", Font.BOLD, 18);

    public Board() {
        m = new Monkey();
        addKeyListener(new AL());
        setFocusable(true);

        // Load images
        background = new ImageIcon(getClass().getResource("/images/jungle.png")).getImage();
        bananaImage = new ImageIcon(getClass().getResource("/images/banana.png")).getImage();
        crocImage = new ImageIcon(getClass().getResource("/images/croc.png")).getImage();
        jumpImage = new ImageIcon(getClass().getResource("/images/jump.png")).getImage();
        stillImage = new ImageIcon(getClass().getResource("/images/still.png")).getImage();

        time = new Timer(3, this);
        time.start();
        croc = new Obstacle(700, 270, "croc.png");
        croc2 = new Obstacle(1500, 270, "croc.png");
    }

    public void actionPerformed(ActionEvent e) {
        checkCollisions();
        for (int w = 0; w < Monkey.bananas.size(); w++) {
            Banana b = Monkey.bananas.get(w);
            if (b.getVisible())
                b.move();
            else
                Monkey.bananas.remove(w);
        }
        m.move();
        if (m.x > 400)
            croc.move(m.dx, m.left);
        if (m.x > 500)
            croc2.move(m.dx, m.left);
        repaint();
    }

    public void paint(Graphics g) {
        super.paint(g);

        if (lost) {
            System.out.println("You Lost!");
        }

        if (m != null) {
            Graphics2D g2d = (Graphics2D) g;

            // Draw the background image
            g2d.drawImage(background, 0, 0, getWidth(), getHeight(), this);

            // Draw monkey
            g2d.drawImage(m.getImage(), m.left, v, this);

            // Draw bananas
            ArrayList<Banana> bananas = Monkey.getBananas();
            for (Banana b : bananas) {
                if (b.getVisible()) {
                    g2d.drawImage(bananaImage, b.getX(), b.getY(), this);
                }
            }

            // Draw crocodiles
            if (m.x > 400 && croc.Alive()) {
                g2d.drawImage(crocImage, croc.getX(), croc.getY(), this);
            }
            if (m.x > 500 && croc2.Alive()) {
                g2d.drawImage(crocImage, croc2.getX(), croc2.getY(), this);
            }

            // Draw other game elements...

            g2d.setFont(font);
            g2d.setColor(Color.YELLOW);
            g2d.drawString("Bananas left: " + m.storedBananas, 500, 20);
            g2d.drawString("Score: " + (m.getX() - 75), 500, 40);
        }
    }



    public void checkCollisions() {
        Rectangle r1 = croc.getBounds();
        Rectangle r2 = croc2.getBounds();
        for (int w = 0; w < Monkey.bananas.size(); w++) {
            Banana b = Monkey.bananas.get(w);
            Rectangle b1 = b.getBounds();
            if (r1.intersects(b1) && croc.Alive()) {
                croc.isAlive = false;
                b.visible = false;
            } else if (r2.intersects(b1) && croc2.Alive()) {
                croc2.isAlive = false;
                b.visible = false;
            }
        }

        Rectangle d = m.getBounds();
        if ((d.intersects(r1) && croc.isAlive) || (d.intersects(r2) && croc2.isAlive))
            lost = true;
    }

    private class AL extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
            m.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            m.keyPressed(e);
        }
    }

    boolean h = false;
    boolean done = false;

    public void cycle() {
        if (h == false)
            v--;
        if (v == 100)
            h = true;
        if (h == true && v <= 210) {
            v++;
            if (v == 210) {
                done = true;
            }
        }
    }

    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        while (done == false) {
            cycle();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 10 - timeDiff;
            if (sleep < 0)
                sleep = 2;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
            }
            beforeTime = System.currentTimeMillis();
        }
        done = false;
        h = false;
        done2 = false;
    }
}
