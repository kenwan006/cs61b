// TODO: Make sure to make this class a part of the synthesizer package

package synthesizer;
import java.util.Iterator;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int capacity = (int)Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<Double>(capacity);
        for(int i =0; i< capacity; i++) {
            buffer.enqueue(0.0);
        }
    }

    /* Check if the enqueued item already existed in in the buffer */
    private boolean existed(double r, double[] tempCatch) {
        for(double x: tempCatch) {
            if(r ==x) {
                return true;
            }
        }
        return false;
    }
    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //       Make sure that your random numbers are different from each other.
        double r = Math.random() - 0.5;
        // Create a temp array to store the items
        double[] tempCatch = new double[buffer.capacity()];
        while(!buffer.isEmpty()) {
            buffer.dequeue();
        }

        int i =0;
        while(!buffer.isFull()) {
            while(existed(r, tempCatch)) {
                r = Math.random() - 0.5;
            }
            // Create a temp array to store the items
            buffer.enqueue(r);
            tempCatch[i] = buffer.peek();
            i += 1;
        }
    }

    /* Advance the simulation one time step by performing one iteration of the Karplus-Strong algorithm. */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double itemRemoved = buffer.dequeue();
        double itemInserted = buffer.peek();
        double itemInsertedNew = (itemRemoved + itemInserted) /2 *DECAY;
        buffer.enqueue(itemInsertedNew);
    }

    /* Return the double at the front of the buffer. */
    //Iterator<Double> itera = buffer.iterator();
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }

    public static void main(String args[]) {
        GuitarString guitar = new GuitarString(4410);
        guitar.pluck();

    }
}
