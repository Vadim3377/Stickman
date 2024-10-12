import org.jcodec.api.awt.AWTSequenceEncoder;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
// Creation date: 17.11.2023

// the class was created to IA based on work, whose author presnted below, using their tutorial,
// source is stated in Criterion B and C
/**
 *
 * @author enyason
 */
public class Recorder {

    // initialising the objects that will be used in the class
    private Rectangle rectangle; // region for recording
    private AWTSequenceEncoder encoder; // encoding the sequence
    private ScreenRecorderTask recorderTask; // starting the task
    public static Timer timerRecord; // thread to record the video
    // creating the file
    private File f;
    // initial variables
    private boolean isRecording = false;

    // method to start the recording
    public void StartRecoding()
    {
        initScreenRecorderObjects("mp4_video_output"); // file initialisation method
        scheduleTimerTasks(); // thread which saves frames
    }

    // metod, creating the region, from which the frames were extracted and encoded to the file
    private void initScreenRecorderObjects(String fileName) {

        rectangle = new Rectangle(250,30,1000,500); // recording frame creation
        // creating the file
        f = new File(fileName + ".mp4");

        try {
            // initialize the encoder
            encoder = AWTSequenceEncoder.createSequenceEncoder(f, 8); // combining screenshots in video
        } catch (IOException ex) {
            Logger.getLogger(Canvas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    // method, starting the thread of extracting the frames
    private void scheduleTimerTasks() {

        isRecording = true;
        // fps rate
        int delay = 1000 / 24;
        // creating the new thread
        timerRecord = new Timer("Thread TimerRecord");
        // encoding the frames process
        // starting the terad of obtaining frames
        recorderTask = new ScreenRecorderTask(encoder, rectangle);
        // encoding the frames
        timerRecord.scheduleAtFixedRate(recorderTask, 0, delay);
    }

    // stopping the recording
    public void stopScreenRecording() {
        // terminating the threads
        timerRecord.cancel();
        timerRecord.purge();

        recorderTask.cancel();

        try {

            encoder.finish();

        } catch (IOException ex) {
            Logger.getLogger(Recorder.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
