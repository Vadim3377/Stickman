import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jcodec.api.awt.AWTSequenceEncoder;
// Creation date: 10.11.2023

// the class was created to IA based on work, whose author presnted below, using their tutorial,
// source is stated in Criterion B and C
/**
 *
 * @author enyason
 */
public class ScreenRecorderTask extends TimerTask {
    // objects that will be used in code
    // object encoding the list of screenshots to the coherent movies
    private AWTSequenceEncoder encoder;
    // object making the screenshots
    private Robot robot;
    // region for the screenshots
    private Rectangle screenDimension;
    // creating the image file
    private BufferedImage image;

    public ScreenRecorderTask(AWTSequenceEncoder sequenceEncoder, Rectangle rectangle) {
        // intialising the variables
        encoder = sequenceEncoder;
        screenDimension = rectangle;

        try {
            robot = new Robot();
        } catch (AWTException ex) {

            Logger.getLogger(ScreenRecorderTask.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // method which starts the recording process
    @Override
    public void run() {

        // making the screenshot
        image = robot.createScreenCapture(screenDimension);
        // encoding the image
        try {
            encoder.encodeImage(image);
        } catch (Exception ignore) {
            }
    }

}