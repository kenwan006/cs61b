public class LinkedListDeque<T> implements Deque<T> {
    private class IntNode{
        public IntNode prev;
        public T item;
        public IntNode next;

        public IntNode(IntNode p, T i, IntNode n){
            item = i;
            prev = p;
            next = n;
        }
    }
    private IntNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new IntNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item){
        IntNode firstNode = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.prev = firstNode;
        sentinel.next = firstNode;
        size += 1;
    }

    @Override
    public void addLast(T item){
        IntNode lastNode = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.next =lastNode;
        sentinel.prev = lastNode;
        size += 1;
    }

    @Override
    public boolean isEmpty(){
        return sentinel.next.item == null;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void printDeque(){
        IntNode p = sentinel.next;
        int i = 0;
        while(i< size){
            System.out.print(p.item + " ");
            i += 1;
            p = p.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst(){
        T itemRemoved = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return itemRemoved;
    }

    @Override
    public T removeLast(){
        T itemRemoved = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return itemRemoved;
    }

    @Override
    public T get(int index){
        int i = 0;
        IntNode p = sentinel;
        while(i <= index){
            p = p.next;
            i+= 1;
        }
        return p.item;
    }

    /**the helper function of the getRecursion; need to keep track of sentinel */
    public T getHelper(int index, IntNode p){
        if(index ==0){
            return p.next.item;
        }
        return getHelper(index -1, p.next);
    }
    public T getRecursion(int index){
        return getHelper(index, sentinel);
    }

    public static void main(String[] args){
        LinkedListDeque<Integer> list = new LinkedListDeque<>();
        System.out.println(list.isEmpty());
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        list.addLast(0);
        list.printDeque();
        System.out.println(list.size());

    }

}