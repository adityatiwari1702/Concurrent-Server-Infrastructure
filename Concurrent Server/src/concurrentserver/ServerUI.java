package concurrentserver;

import java.awt.BorderLayout;
import javax.swing.*;


public class ServerUI extends JFrame {
    private JTextArea ta;
    public ServerUI(){
        this.setTitle("GUI Server");
        ta=new JTextArea(20,20);
        this.add(BorderLayout.CENTER,ta);
        ta.setEditable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
    public void appendTA(String msg){
        ta.append(msg+"\n");
    }
}
