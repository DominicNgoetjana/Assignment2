
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JPanel;

public class WordPanel extends JPanel implements Runnable {

    public static volatile boolean done;
    private WordRecord[] words;
    private int noWords;
    private int maxY, hi, lo;

    public void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.clearRect(0, 0, width, height);
        g.setColor(Color.red);
        g.fillRect(0, maxY - 10, width, height);

        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", Font.PLAIN, 26));
        //draw the words
        //animation must be added 
        for (int i = 0; i < noWords; i++) {
            //g.drawString(words[i].getWord(),words[i].getX(),words[i].getY());	
            g.drawString(words[i].getWord(), words[i].getX(), words[i].getY() + 20);  //y-offset for skeleton so that you can see the words	
        }

    }

    WordPanel(WordRecord[] words, int maxY) {
        this.words = words; //will this work?
        noWords = words.length;
        done = false;
        this.maxY = maxY;
    }

    public void run() {
        //add in code to animate this
        Word[] w = new Word[words.length];
        CountDownLatch latch = new CountDownLatch(words.length);
        while (!done) {
            for (int i = 0; i < words.length; i++) {
                w[i] = new Word(words[i], latch);
                w[i].start();
            }
            try {
                Thread.sleep(200);
                latch.await();
            } catch (Exception e) {
            }
            repaint();
        }
    }

}
