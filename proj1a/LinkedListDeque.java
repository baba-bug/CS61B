import javax.management.NotificationEmitter;

/* LinkedListDeque. */
public class LinkedListDeque<T>{

    /* defined class node with previous and next pointer. */
    public class Node<T>{
        public  Node prev;
        public  Node next;
        public T item;
        public Node(Node p, Node n, T i){
            prev = p;
            next = n;
            item = i;
        }
    }
    /* sentinel.next是链表第一项，.pre是最后一项 */
    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        sentinel = new Node(null, null, 1);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T i){
        sentinel = new Node(null, null, 1);
        size = 1;
        sentinel.next = new Node(sentinel, sentinel, i);
        sentinel.prev = sentinel.next;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new Node(null, null, 1);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        Node pointerOther = other.sentinel.next;
        while (size < other.size){
            addLast((T) pointerOther.item);
            pointerOther = pointerOther.next;
        }
    }

    public void addFirst(T item){
        Node N = new Node(sentinel, sentinel.next, item);
        sentinel.next.prev = N;
        sentinel.next = N;
        size += 1;
    }

    public void addLast(T item){
        Node N = new Node(sentinel.prev, sentinel, item);
        sentinel.prev.next = N;
        sentinel.prev = N;
        size += 1;
    }

    public boolean isEmpty(){
        if(size == 0) return true;
        else return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int i = 0;
        Node pointer = sentinel;
        while(i < size){
            i += 1;
            System.out.print(pointer.next.item);
            System.out.print(' ');
            pointer = pointer.next;
        }
        System.out.println();
    }
    public T removeFirst(){
        T i;
        if (sentinel.next == null) return null;
        else {
            i = (T) sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return i;
        }
    }

    public T removeLast(){
        T i;
        if (sentinel.prev == null) return null;
        else{
            i = (T) sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return i;
        }
    }

    public T get(int index){
        int i = 0;
        Node p = sentinel.next;
        if(index > size-1) return null;
        else {
            while(i < index){
                i += 1;
                p = p.next;
            }
        }
        return (T) p.item;
    }

    Node p = sentinel;
    public T getRecursive(int index){
        if (index > size-1) return null;
        Node p = sentinel.next;
        return (T) getRecursiveHelper(p, index);
    }
    public T getRecursiveHelper(Node p, int index){
        if (index == 0) return (T) p.item;
        return (T) getRecursiveHelper(p.next, index-1);
    }
}