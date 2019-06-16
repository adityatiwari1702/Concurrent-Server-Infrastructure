package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import static client.Client.*;
/**
 *
 * @author Aditya Tiwari
 */
public class ClientUI extends JFrame {
    JTextArea ta;
    JButton b1;
    JTextField tf;
    public ClientUI(){
        this.setTitle("Client");
        ta=new JTextArea(20,20);
        JPanel p=new JPanel();
        p.setLayout(new FlowLayout());
        b1=new JButton("Submit");
        tf=new JTextField(20);
        ta.setEditable(false);
        this.add(BorderLayout.CENTER,ta);
        p.add(tf);
        p.add(b1);
        this.add(BorderLayout.SOUTH,p);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        b1.addActionListener((e)->{
            String msg=tf.getText();
            nos.println(msg);
            tf.setText("");
        });
    }
    public void appendTA(String msg){
        ta.append(msg+"\n");
    }
    
}
