import javax.swing.*;
import java.awt.*;

public class Hint
{
    // Creation date: 13.01.2024

    // initial variables
    private int num;
    private Container container;
    private String s;
    private JFrame frame = new JFrame();
    Hint(int num)
    {
        // defining the frame charachteristics
        this.num = num;
        frame.setSize(new Dimension(500,500));
        container = frame.getContentPane();
        container.add(CreateImage(num));
        frame.setVisible(true);
    }
    private JLabel CreateImage(int num)
    {
        JLabel lbIntro = new JLabel();
        //adding the image with tooltips depending on the frame number
        if (num ==1)
        {
            s = "images/text1.png";
        }
        if (num ==2)
        {
            s = "images/text2.png";
        }
        lbIntro.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource(s)).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        lbIntro.setBounds(500,80,500,500);

        return lbIntro;
    }


}
