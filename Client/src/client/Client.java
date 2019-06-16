package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class Client {

    static BufferedReader nis;
    static PrintWriter nos;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String ip = JOptionPane.showInputDialog("Enter IP Address of Server:-");
        int portno = Integer.parseInt(JOptionPane.showInputDialog("Enter Port No of Server:-"));
        Socket soc = new Socket(ip, portno);
        nis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        nos = new PrintWriter(new OutputStreamWriter(soc.getOutputStream()), true);
        LoginUI lui = new LoginUI();
        if (lui.authenticate()) {
            ClientUI cui=new ClientUI();
            String check=nis.readLine();
            while(!check.equalsIgnoreCase("end")){
                cui.appendTA(check);
                check=nis.readLine();
            }
            soc.close();
            System.exit(0);
        }else{
            JOptionPane.showMessageDialog(null, "Socket Closed.");
            soc.close();
        }
    }

}
