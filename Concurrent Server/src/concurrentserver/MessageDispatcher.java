package concurrentserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static concurrentserver.ConcurrentServer.*; 
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class MessageDispatcher extends Thread {
    PrintWriter fos;
    public MessageDispatcher(String path){
        try{
        fos=new PrintWriter(new BufferedWriter(new FileWriter(path,true)),true);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Logging file Not Opened Successfully.");
        }
    }
    @Override
    public void run(){
        while(true){
            String msg=(String)mq.dequeue();
            for(PrintWriter i:al)
                i.println(msg);
            fos.println(new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(new Date()) + " " +msg);
        }
    }
}