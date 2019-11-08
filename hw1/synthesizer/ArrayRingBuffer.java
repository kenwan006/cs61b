
package synthesizer;

import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /** Create a new ArrayRingBuffer with the given capacity.*/
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];
    }

    /** when index equals capacity, make it wrap around by the index to 0 */
    private int indexTrue(int index) {
        if (index == capacity) {
            index = 0;
        }
        return index;
    }

    /** Adds x to the end of the ring buffer. If there is no room, then
     *  throw new RuntimeException("Ring buffer overflow"). Exceptions
     *  covered Monday */
    @Override
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (this.isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        rb[last] = x;
        last += 1;
        last = indexTrue(last);
        fillCount += 1;
    }

    /** Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday. */
    @Override
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (this.isEmpty()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }

        T itemDeleted = rb[first];
        rb[first] = null;
        first += 1;
        first = indexTrue(first);
        fillCount -= 1;
        return itemDeleted;
    }

    /** Return oldest item, but don't remove it. */
    @Override
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (this.isEmpty()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            return rb[first];
        }

    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new KeyIterator();
    }
    private class KeyIterator implements Iterator<T> {
        private int ptr;
        public KeyIterator() {
            ptr = 0;
        }
        public boolean hasNext() {
            return (ptr != fillCount);
        }
        public T next() {
            T returnItem = rb[ptr];
            ptr += 1;
            return returnItem;
        }
    }

}
