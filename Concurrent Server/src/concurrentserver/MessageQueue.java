package concurrentserver;

import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Aditya Tiwari
 */
public class MessageQueue<T> {

    ArrayList<T> al = new ArrayList<T>();

    public synchronized void enqueue(T content) {
        al.add(content);
        notify();
    }

    public synchronized T dequeue() {
        if (al.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                JOptionPane.showMessageDialog(null, "Thread Stopped in Message Queue.");
            }
        }
        return al.remove(0);
    }
}
