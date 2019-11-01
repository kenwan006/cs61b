public class ArrayDeque<T> implements Deque<T> {
    private int nextFirst;
    private int nextLast;
    private T[] items;
    private int size;
    private int capacity;
    private double usage = (double) size/capacity;

    /** constructor starting size of the array is 8*/
    public ArrayDeque() {
        nextFirst = 4;
        nextLast = 5;
        items = (T[]) new Object[8];
        size = 0;
        capacity = 8;
    }

    /** increases the given index by one based on the circular structure */
    private int onePlus(int index){
        if(index == capacity -1){
            return 0;
        }
        return index + 1;
    }
    /** decreases the given index by in one based on the circular structure */
    private int oneMinus(int index){
        if(index == 0){
            return capacity -1;
        }
        return index -1;
    }

    /** resize the capacity */
    private void Resize(int newCapacity){
        int currentFirst = onePlus(nextFirst);
        int currentLast = oneMinus(nextLast);
        T[] newItems = (T[]) new Object[newCapacity];

        if (currentFirst <currentLast){
            int arrayLength = currentLast - currentFirst + 1;
            System.arraycopy(items, nextFirst, newItems, 0, arrayLength);
        }
        else{
            int firstsLength = capacity - currentFirst;
            int lastsLength = currentLast +1;
            int newCurrentFirst = newCapacity - firstsLength; //currentFirst has a new position
            System.arraycopy(items, currentFirst, newItems, newCurrentFirst, firstsLength);
            System.arraycopy(items, 0, newItems, 0, lastsLength);
            nextFirst = oneMinus(newCurrentFirst);
        }
        items = newItems;
        capacity = newCapacity;
    }

    /** add the item to the front; once the array is full, must resize */
    @Override
    public void addFirst(T item){
        if(size == capacity ){
            Resize(capacity * 2);
        }
        items[nextFirst] = item;
        nextFirst = oneMinus(nextFirst);
        size += 1;
    }

    /** add the item to the rear; once the array is full, must resize */
    @Override
    public void addLast(T item){
        if(size == capacity){
            Resize(capacity *2);
        }
        items[nextLast] = item;
        nextLast = onePlus(nextLast);
        size += 1;
    }

    /** check if the array is null */
    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    /** return the array size */
    @Override
    public int size(){
        return size;
    }

    /** print out all the items in the array */
    @Override
    public void printDeque(){
        for(T x: items){
                System.out.print(x + " ");
        }
        System.out.println();
    }

    /** remove the front item; if the usage of the array is <0.25, must resize */
    @Override
    public T removeFirst(){
        int currentFist = onePlus(nextFirst);
        T itemRemoved = items[currentFist];
        items[currentFist] = null;
        nextFirst = currentFist;
        size -= 1;
        if(usage < 0.25 && capacity >=16){
            Resize(size * 4); // equals size/0.25
        }
        return itemRemoved;
    }

    /** remove the rear item; if the usage of the array is <0.25, must resize */
    @Override
    public T removeLast(){
        int currentLast = oneMinus(nextLast);
        T itemRemoved = items[currentLast];
        items[currentLast] = null;
        nextLast = currentLast;
        size -= 1;
        if(usage < 0.25 && capacity >=16){
            Resize(size * 4); // equals size/0.25
        }
        return itemRemoved;
    }
    /** get the ith item in the array */
    @Override
    public T get(int index){
        return items[index];
    }


    public static void main(String[] args){
        ArrayDeque<Integer> array = new ArrayDeque<>();
        array.addFirst(0);
        array.addFirst(1);
        array.addLast(2);
        array.addLast(3);
        array.addLast(4);
        array.addLast(5);
        array.addLast(6);
        array.addLast(7);
        array.addFirst(8);
        System.out.println(array.get(1));
        System.out.println(array.get(12));
        System.out.println(array.removeFirst());
        System.out.println(array.removeFirst());
        System.out.println(array.removeFirst());
        System.out.println(array.removeFirst());
        System.out.println(array.removeLast());
        System.out.println(array.removeLast());
        System.out.println(array.removeLast());
        array.printDeque();

    }

}