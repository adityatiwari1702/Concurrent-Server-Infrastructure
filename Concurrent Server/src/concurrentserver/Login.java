package concurrentserver;

import static concurrentserver.ConcurrentServer.al;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import static concurrentserver.Authenticator.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class Login extends Thread {

    private final Socket soc;
    private PrintWriter nos;
    private BufferedReader nis;

    public Login(Socket soc) {
        this.soc = soc;
        try {
            this.nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
            this.nos = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()), true);
            al.add(nos);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "NIS and NOS not Created Successfully.");
        }
    }

    @Override
    public void run() {
        try {
            int i = 0;
            while (i < 3) {
                String Username = nis.readLine();
                String Password = nis.readLine();
                if (authenticate(Username, Password)) {
                    nos.println("true");
                    Conversation conv = new Conversation(soc, nos, nis,Username);
                    conv.start();
                    break;
                }else{
                    i++;
                    nos.println("false");
                }
            }
        } catch (IOException ex) {
            System.out.println("Error in Reading From Client");
        }
    }
}
