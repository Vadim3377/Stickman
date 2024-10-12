import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Checkbox
{
    // Creation date: 08.07.2023

    // objects that will be used in the program
    private JMenuBar menuBar = new JMenuBar();
    private JTextField textBoxToEnterName;
    private Color color;
    private String [] names = new String [4];
    private String [] faces = new String [5];
    private String [] backgrounds = new String [5];
    // initial values for using charachteristics
    private int face = -1;
    private int back = -1;
    
    public Checkbox()
    {
        // creating the menu for the stickman charachteristics
        menuBar.setLayout(new BoxLayout(menuBar, BoxLayout.PAGE_AXIS));
        // creating the height Text block
        menuBar.add(createContextMenu("Height: ","100"));
        // creating the options for expanding list in Colors section
        names[0] = "Black";
        names[1] = "Blue";
        names[2] = "Orange";
        names[3] = "Yellow";
        menuBar.add(createViewMenu("        Color          ", 4, names));

        // creating the options for expanding list in Face section
        faces[0] = "No face";
        faces[1] = "Funny";
        faces[2] = "Sweet";
        faces[3] = "Sad";
        faces[4] = "Add face";
        menuBar.add(createViewMenu("        Face          ", 5, faces));

        // creating the options for expanding list in Background section
        backgrounds[0] = "Canvas";
        backgrounds[1] = "Home";
        backgrounds[2] = "Desert";
        backgrounds[3] = "Winter";
        backgrounds[4] = "Add background";
        
        menuBar.add(createViewMenu("Background", 5, backgrounds));
        menuBar.setBounds(800,150,200,250);
    }

    // method to create the text box for height characteristic of stickman
    private JPanel createContextMenu(String str, String recommended)
    {
        JLabel Str = new JLabel(str);
        textBoxToEnterName = new JTextField(recommended,10);
        JPanel panelTop = new JPanel();
        panelTop.add(Str);
        panelTop.add(textBoxToEnterName);
        return panelTop;
    }

    // method to combine the options to expanding list
    private JMenu createViewMenu(String name, int positions, String [] arr)
    {
        // creating the pop-up menu
        JMenu viewMenu = new JMenu(name);
        // radio buttons for menu in cycle
        ButtonGroup bg = new ButtonGroup();
        for(int i = 0; i < positions; i++)
        {
            JRadioButtonMenuItem black = new JRadioButtonMenuItem(arr[i]);
            String str = arr[i];
            int num = i;
            black.addActionListener(new ActionListener()
            {
                // identifying the option chosen
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    if(name.equals("        Color          "))
                    {
                        // method, identifying the color chosen
                        GetColor(str);
                    }
                    
                    if(name.equals("        Face          "))
                    {

                        if(num == 4 )
                        {
                            // calling the method for pop-up window for hint
                            UploadFile();
                        }
                        face = num;
                    }
                    
                    if(name.equals("Background"))
                    {
                        back = num;
                        if(back == 4 )
                        {
                            UploadFile();
                        }
                    }
                }
            });
            bg.add(black);
            viewMenu.add( new JSeparator());
            viewMenu.add(black);
        }
        
        return viewMenu;
    }

    // converting options to charachteristics
    private Color GetColor(String str)
    {
        if (str.equals("Black"))
        {
            color = Color.black;
        }
        
        if (str.equals("Blue"))
        {
            color = Color.blue;
        }
        
        if (str.equals("Orange"))
        {
            color = Color.orange;
        }
        
        if (str.equals("Yellow"))
        {
            color = Color.yellow;
        }
        
        return color;
    }

    // method to create object for pop-up window for file uploader
    private void UploadFile()
    {
        FileUploader file = new FileUploader();
    }

    // method, converting entered value to characteristic
    public int getHeight()
    {
        // extracting entered vale
        String str = textBoxToEnterName.getText();
        // identifying the value
        if(str.equals(""))
        {
            // null value error prevention
            str = "100";
        }
        int number;
        try {
            // converting string to number
             number = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            // abnormal data error prevention
            number = 100;
        }

        return number;
    }
    // accessors
    public JMenuBar getMenu(){return menuBar;}
    public Color getColor(){return color;}
    public int getFace(){return face;}
    public int getBack(){return back;}
}
