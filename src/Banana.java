 
import java.awt.*;
 
import javax.swing.ImageIcon;

//code taken from MrJavaHelp https://pastebin.com/HbSVFugN (we changed some stuff and added comments)

 
public class Banana {
 
    int x,y;
    Image img;
    boolean visible;
    
    public Banana(int startX, int startY)
    {
        x = startX;
        y = startY;
        ImageIcon newBanana = new ImageIcon("banana.png");
        img = newBanana.getImage();
        visible = true;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x,y, 50, 41); //size of banana.png image
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public boolean getVisible()
    {
        return visible;
    }
    public Image getImage()
    {
        return img;
    }
    
    public void move()
    {
        x = x + 2;
        if ( x > 700)
            visible = false;
    }
    
    public void setVisible(boolean isVisible) //down
    {
        visible = isVisible;
    }
    
 
}