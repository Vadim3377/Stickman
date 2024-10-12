import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Parser
{
    // Creation date: 01.08.2023

    // creating the identifier
    private int i;
    // creating the variable for plot conversion to commands
    private String plot;
    private String [] arr = new String[1000];
    // Creating the objects that will be used in class
    private Starter key;
    private Actor actor;
    private Actor friend;
    private Actor enemy;
    // creating the thread timer, required for delay between command execution
    private Timer timer = new Timer();
    
    public Parser(String str, Actor actor, Actor friend,Actor enemy, Starter start)
    {
        // intialising the instance variables
        this.plot = str;
        key = start;
        this.actor = actor;
        this.friend = friend;
        this.enemy = enemy;

        // converting the plot to the array of commands
        arr = Command(str);
        // starting the commands execution
        ReadTheLine(actor,friend,enemy, arr[i]);
    }

    // method converting the plot to array of commands
    private String [] Command(String str)
    {
        // separating commands by extracts
        int newLine = str.indexOf('\n');
        // nulling the fisr point
        arr[1] = null;
        // initialising the index
        int i =0;
        // case when there is only one command
        if (newLine == -1)
        {
            // the first command to execute will be the only entered
            arr[0] = str;
        }
        // if there are more commands the loop is started
        while (newLine > 0) {
            // separating each command by the paragraph
            arr[i] = str.substring(0, newLine);
            // increasing the index
            i++;
            // continuing the sepparation to next element
            str = str.substring(newLine + 1, str.length());
            // finding the end of next command
            newLine = str.indexOf('\n');
        }
        return arr;
    }

    // the command execution method
    private void ReadTheLine(Actor stickman, Actor friend, Actor enemy,  String str)
      {
        // increasing the index
        i++;
        // initialising the command executor
        Actor art = null;
        // case when there are no additional charachters
        if (friend == null && enemy == null )
        {
            // the executor is the stickman itself
            art = stickman;
        }
        else
        {
            // otherwise the executor is stated before the command
            int space = str.indexOf(' ');
            String person = str.substring(0,space);
            // defining who is the executor
            if (person.equals("STICKMAN"))
            {
                art = stickman;
            }
            if (person.equals("FRIEND"))
            {
                art = friend;
            }
            if (person.equals("ENEMY"))
            {
                art = enemy;
            }
            // separating the executor statement from the command itself
            str = str.substring(space+1,str.length());
        }
        // method, defining the duration of the command
        int time = getTime(str);
        // error prevention
        if (time != 0)
        {
            // extracting the command from the plot
            int bracketOpen = str.indexOf('(');
            str = str.substring(0,bracketOpen);
        }
        // identifying the say command
        int space = str.indexOf(' ');
        if (space>2)
        {
           String talk = str.substring(0,space);
           if(talk.equals("SAY:"))
           {
               // extracting the message
               String message = str.substring(space,str.length());
               art.setMessage(message);
               // starting the thread
               key.start();
               // setting the duration
               time = 5000;
               // determining the executor
               Actor finalArt = art;
               // ssetting the delay until the command changes to the next one
               timer.schedule(new TimerTask()
               {
                   public void run()
                   {
                       NextAction(finalArt);
                   }
               }, time);
           }
           
        }

        // identifying the command
        if (str.equals("FIGHT"))
        {
            // nulling all the parameters
            art.setI(0);
            // setting the command to execute for execution class
            art.setCommand(2);
            // starting the thread
            key.start();
            // setting the fixed time required to implement the static action
            time = 1000;
            // defining the executor
            Actor finalArt = art;
            // starting the action
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    NextAction(finalArt);
                }
            }, time);
        }
        
        if (str.equals("DANCE"))
        {
            art.setI(0);
            art.setCommand(1);
            key.start();
            
            Actor finalArt = art;
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    NextAction(finalArt);
                }
            }, time);
            
        }

        if (str.equals("GO RIGHT"))
        {
            //System.out.print("Зашло");
            art.setI(0);
            art.setJ(0);
            art.setCommand(3);
            // setting the step required to direction motion
            art.setStep(1);
            key.start();
            
            Actor finalArt = art;
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    NextAction(finalArt);
                }
            }, time);

        }
        
        if (str.equals("GO LEFT"))
        {
            art.setI(0);
            art.setCommand(3);
            // setting the step required to direction motion
            art.setStep(-1);
            key.start();
            
            Actor finalArt = art;
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    NextAction(finalArt);
                }
            }, time);
        }
        
        if (str.equals("JUMP"))
        {
            // nulling the index
            art.setJ(0);
            art.setCommand(4);
            key.start();
            time = 1000;
            
            Actor finalArt = art;
            timer.schedule(new TimerTask()
            {
                public void run()
                {
                    NextAction(finalArt);
                }
            }, time);
        }
        
        if (str.equals("SIT"))
        {
            art.setCommand(5);
            key.start();
            time = 1000;
            
            Actor finalArt = art;
            timer.schedule(new TimerTask()
            {
                public void run() 
                {
                   NextAction(finalArt);
                }
            }, time);
        }

      }

    // method defining the duration of the command
    private int getTime(String str)
      {
        // extracting time from the brackets
        int bracketOpen = str.indexOf('(');
        int bracketClose = str.indexOf(')');
        // local variable for the duration
        int num;
        try {
            // obtaining the time in string format
            String time = str.substring(bracketOpen+1,bracketClose);
            // converting string to integer
            num = Integer.parseInt(time);
            return num;
        } catch (Exception e) {
            num = 0;
            return num;
        }
        
    }

    //the method setting the next action
    private void NextAction(Actor hero)
    {
        // stopping the animation for executor
        hero.setCommand(0);
        key.start();
        // case when it is not the last command
        if (arr[i] != null)
        {
            // case  when there is only one executor
            if (friend == null && enemy == null )
            {
                ReadTheLine(hero, friend, enemy, arr[i]);

            }
            else
            // other cases
            {
                ReadTheLine(actor, friend, enemy, arr[i]);
            }
        }
        // case if there are no more commands left in the array
        else
        {
            // case when there are more than ine character
            if (friend != null || enemy != null )
            {
                // stopping the animation for each character
                friend.setCommand(0);
                enemy.setCommand(0);
                // nulling the messages
                friend.setMessage(null);
                enemy.setMessage(null);
            }
            actor.setCommand(0);
            actor.setMessage(null);
            // fixing the position using thread
            key.start();
            // calling the method to terminate all threads
            StopTimer();
        }
    }
    // method terminating all threads
    public void StopTimer()
      {
         key.stop();
         timer.cancel();
         timer.purge();
      }
}
