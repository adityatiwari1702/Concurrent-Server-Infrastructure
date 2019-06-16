package client;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.*;
import static client.Client.*;
import java.io.IOException;

/**
 *
 * @author Aditya Tiwari
 */
public class LoginUI extends JFrame {
    public LoginUI() {
        this.setTitle("Login Frame");
        JLabel l1 = new JLabel("Username : ");
        JLabel l2 = new JLabel("Password : ");
        JTextField tf = new JTextField(20);
        JPasswordField pf = new JPasswordField(20);
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        p1.setLayout(new FlowLayout());
        p2.setLayout(new FlowLayout());
        p1.add(l1);
        p1.add(tf);
        p2.add(l2);
        p2.add(pf);
        JButton b0 = new JButton("Submit");
        add(BorderLayout.NORTH, p1);
        add(BorderLayout.CENTER, p2);
        add(BorderLayout.SOUTH, b0);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        b0.addActionListener((e) -> {
            nos.println(tf.getText());
            nos.println(pf.getText());
            tf.setText("");
            pf.setText("");
        });
    }

    public boolean authenticate() {
        int i = 0;
        while (i < 3) {
            try {
                if (nis.readLine().equalsIgnoreCase("true")) {
                    this.dispose();
                    return true;
                } else {
                    i++;
                }
            } catch (IOException e) {
                System.out.println("Server Sending Message Trouble.");
            }
        }
        this.dispose();
        return false;
    }
}
