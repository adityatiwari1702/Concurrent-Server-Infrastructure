package concurrentserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ConcurrentServer {
    static ArrayList<PrintWriter> al;
    static MessageQueue<String> mq;
    static String logfile="D:\\Server\\Log3.txt";
    static MessageDispatcher md;
    static ServerUI ui;
    static{
        al=new ArrayList<PrintWriter>();
        mq=new MessageQueue<String>();
        md=new MessageDispatcher(logfile);
        md.setDaemon(true);
        md.start();
    }
    public static void main(String[] args) {
        int portno=Integer.parseInt(JOptionPane.showInputDialog("Enter Port No for Server:-"));
        ui=new ServerUI();
        try {
            ServerSocket ss=new ServerSocket(portno);
            for(int i=0;i<5;i++){
                Socket soc=ss.accept();
                Login l=new Login(soc);
                l.start();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Server Socket Not Created");
        }
        
    }
    
}
