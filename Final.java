import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Final {
    // Creation date: 15.01.2024

    // initialising objects that will be used in class
    private JFrame window;
    private JTextField textBoxToEnterName;
    private JButton button;
    private Container container;
    private JLabel welcome;
    JPanel buttonsPanel = new JPanel();
    JPanel buttonsPanel1 = new JPanel();
    Final()
    {
        //creating thr window
        window = new JFrame("Final frame");
        window.setSize((int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth()-50), (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-150));
        // creating the container
        container = window.getContentPane();
        container.setLayout(null);

        // adding the GUI elements on the frame
        welcome = new JLabel("Thank you for using",SwingConstants.CENTER);
        welcome.setFont(new Font("Serif",Font.PLAIN,40));
        welcome.setBounds(400,-70,700,200);
        container.add(welcome);

        buttonsPanel.add(CreateButton("Restart",1));
        buttonsPanel.setBounds(550,560,400,40);

        buttonsPanel1.add(CreateButton("Close",2));
        buttonsPanel1.setBounds(550,600,400,40);

        // combining elements in the container
        container.add(buttonsPanel);
        container.add(buttonsPanel1);
        container.add(CreateImage());
        // adding objects on the frame
        window.setContentPane(container);
        window.setVisible(true);
    }
    // button creating method
    private JButton CreateButton(String str, int num)
    {
        JButton start = new JButton (str);
        start.setPreferredSize(new Dimension(400, 40));
        start.setText(str);
        start.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (arg0.getSource()  == start)
                {
                    // restarting the program
                    if (num ==1) {
                        Intro i = new Intro();
                        window.setVisible(false);
                    }
                    // closing the program
                    if (num ==2) {
                        window.setVisible(false);
                    }
                }
            }
        });
        return start;
    }

    // method to add the image on the frame
    private JLabel CreateImage()
    {
        JLabel lbIntro = new JLabel();
        //added background image
        lbIntro.setIcon(new ImageIcon(new javax.swing.ImageIcon(getClass().getResource("images/final.jpg")).getImage().getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
        lbIntro.setBounds(500,80,500,500);

        return lbIntro;
    }
}
