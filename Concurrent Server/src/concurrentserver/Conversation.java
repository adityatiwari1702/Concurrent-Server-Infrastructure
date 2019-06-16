package concurrentserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import static concurrentserver.ConcurrentServer.*; 
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class Conversation extends Thread {
    private final Socket soc;
    private PrintWriter nos;
    private BufferedReader nis;
    private String Username;
    public Conversation(Socket soc,PrintWriter nos,BufferedReader nis,String Username){
        this.soc=soc;
        this.nis=nis;
        this.nos=nos;
        this.Username=Username;
    }
    @Override
    public void run(){
        try {
            String msg=nis.readLine();
            while(!msg.equalsIgnoreCase("end")){
                mq.enqueue(Username+" : "+msg);
                ui.appendTA(Username+"\n"+msg);
                msg=nis.readLine();
            }
            nos.println("end");
            al.remove(nos);
        } catch (IOException ex) {
            System.out.println("Reading not Done");
        }
    }
}
