import java.awt.*;
import javax.swing.*;

public class Rect extends JComponent
{
    // Creation date: 12.06.2023

    // initial variables that will be used in data operation
    private int x;
    private int y;
    private int length;
    private int width;
    
    public Rect(int x, int y,int length, int width)
    {
        // setting the dimensions for the box
        setVisible(true);
        setSize(1000,1000);
        setBackground(Color.white);
        setForeground(Color.black);

        this.x = x;
        this.y = y;
        this.length = length;
        this.width = width;
    }

    // method to draw the box where stickman is created
    public void paint(Graphics page)
    {
        Graphics2D g = (Graphics2D) page.create();
        g.drawRect(x, y, width, length);
        g.dispose();
    }
}
