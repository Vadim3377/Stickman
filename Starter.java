import java.awt.*;
import javax.swing.*;

public class Starter
{
    //Creation date: 08.10.2023

    // identifying the trhread
    private Timer timer;
    Starter(Timer timer)
    {
        this.timer = timer;
    }

    // starting the thread
    public void start() 
    {
        timer.start();
    }

    // closing the thread
    public void stop() 
    {
        timer.stop();
    }
}
