
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author dominic
 */
public class Word extends Thread {
    
    private WordRecord wr;
    private CountDownLatch latch;
    
    public Word(WordRecord wr, CountDownLatch latch) {
        this.wr = wr;
        this.latch = latch;
    }
    
    public void run() {
        if (!wr.dropped()) {
            wr.drop( wr.getSpeed() );
        }
        try {
            //Thread.sleep(100);
        } catch (Exception e) {
        }
        latch.countDown();
    }
    
}
