import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;



public class FileUploader
{
    // Cretion date: 15.10.2023

    // initial variables
    private JFrame window;
    private JTextField textBoxToEnterName;
    private JButton button;
    private Container container;
    FileUploader()
    {
        // identifying the objects that will be used in the object
        window = new JFrame("File uploader");
        container = window.getContentPane();
        container.setLayout(null);

        // creating the context menu
        JMenuBar menuBar1 = new JMenuBar();
        menuBar1.setLayout(new BoxLayout(menuBar1, BoxLayout.PAGE_AXIS));
        menuBar1.add(createContextMenu("Find file",""));
        menuBar1.setBounds(100,150,200,250);

        // creating the buttons
        JMenuBar butt = new JMenuBar();
        butt.add(createButton("Search",1));
        butt.add(createButton("Already created",2));
        butt.setBounds(100,250,400,250);
        menuBar1.add(butt);

        // adding to the container
        container.add(menuBar1);

        // making the object to appear on frame
        window.setSize(500,500);
        window.setLocation(101, 101);
        window.setVisible(true);
    }

    // method to create the button
    private JPanel createButton(String title, int operation)
    {
        button = new JButton ();
        JPanel buttonsPanel = new JPanel();

        button.setPreferredSize(new Dimension(200, 50));
        button.setText(title);
        if (operation ==2)
        {
            button.setBackground(Color.gray);
        }

        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(operation ==1)
                {
                    try {
                        // method to find the file
                        ReadFile();
                    } catch (IOException e) {
                        // Error prevention
                        TextAppear("Not Found");
                    }
                }
                else
                {
                    // closing the frame
                    window.setVisible(false);
                }
            }
        });

        buttonsPanel.add(button);
        return buttonsPanel;
    }
    // method to create the context menu
    private JPanel createContextMenu(String str, String recommended)
    {
        JLabel Str = new JLabel(str);
        textBoxToEnterName = new JTextField(recommended,10);
        JPanel panelTop = new JPanel();
        panelTop.add(Str);
        panelTop.add(textBoxToEnterName);
        return panelTop;
    }
    // method to find the file
    private void ReadFile() throws IOException {
        // obtaining the text from context menu
        String str = textBoxToEnterName.getText();
        // error prevention
        if(!str.equals(""))
        {
            // creating the file on the location given
            File file = new File(str);
            // error prevention
            if (file.exists()) {
                // creating the empty file in the project forlder
                File f = new File("");
                // obtaining the path to the project folder and tranferring to image folder
                String path = f.getAbsolutePath() + "/Images/face4.png";
                // initial file
                File source = new File(str);
                // replace file
                File dest = new File(path);
                // method for file transfer
                copyFileUsingChannel(source, dest);
                // notification appear
                TextAppear("Reload the program");
            }
        }
            else
            {
                // notification if file was not found
                TextAppear("Not Found");
            }

        }
    // method for the notification on the screen
    private void TextAppear(String str)
    {
        container.removeAll();
        JLabel label = new JLabel(str);
        JPanel pan = new JPanel();
        pan.add(label);
        pan.setBounds(100,150,200,250);
        container.add(pan);
        SwingUtilities.updateComponentTreeUI(window);
    }

    // method for copying the file using the open channel
    private static void copyFileUsingChannel(File source, File dest) throws IOException
    {
        // creating the entering channel point
        FileChannel sourceChannel = null;
        // creating the output channel point
        FileChannel destChannel = null;
        // error prevention
        try {
            // creating the input stream
            sourceChannel = new FileInputStream(source).getChannel();
            // creating the output stream
            destChannel = new FileOutputStream(dest).getChannel();
            // error prevention
            if ((destChannel != null) && (sourceChannel != null)) {
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            }
        }finally{
            // closing the channel in case transfer corruption
            sourceChannel.close();
            destChannel.close();
        }
    }
}
