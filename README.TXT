try {
            //Temp file for the Recorder
            File file = new File("D:\\Users\\USER\\Desktop", "screen.cap");
            OutputStream out = new FileOutputStream(file);
            ScreenRecorder screenRecorder = new DesktopScreenRecorder(out,this);
            screenRecorder.startRecording();
            try {
                Thread.sleep(5000);
                screenRecorder.stopRecording();
                //We reformat the video to .mov file
                RecordingConverter.main(new String[]{file.getAbsolutePath()});
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }