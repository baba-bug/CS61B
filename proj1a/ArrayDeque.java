

public class ArrayDeque<T> { 
    private T[] items;
    private int size;
    private int nextFirst, nextLast;

    public ArrayDeque() { 
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

//    public ArrayDeque(T[] ar) { 
//        items = ar;
//        size = ar.length;
//        nextFirst = 0;
//        nextLast = ar.length - 1;
//    }

    private void resize(String ls) { 
        T[] a = null;
        int P = (nextFirst + 1) % items.length;
        int newP = 0;
        int i = 0;
        int lengthCache = 0;

        if (ls.equals("larger") ) { 
            a = (T[]) new Object[items.length * 2];
            lengthCache = items.length;
        }
        if (ls.equals("smaller") ) { 
            a = (T[]) new Object[(items.length / 2) ];
            lengthCache = size;
        }

        while (i != lengthCache ) { 
            a[newP] = items[P];
            newP += 1;
            i += 1;
            P = (P + 1) % items.length;
        }

        nextFirst = a.length - 1;
        nextLast = newP % a.length;
        items = a;

    }

    public void addFirst(T p) { 
        items[nextFirst] = p;
        nextFirst -= 1;
        if (nextFirst < 0) { 
            nextFirst += items.length;
        }
        size += 1;
        if (size >= items.length) { 
            resize("larger") ;
        }
    }

    public void addLast(T p) { 
        items[nextLast] = p;
        nextLast += 1;
        if (nextLast >= items.length) { 
            nextLast = nextLast - items.length;
        }
        size += 1;
        if (size >= items.length) { 
            resize("larger") ;
        }
    }

    public boolean isEmpty() { 
        if (size == 0) { 
            return true;
        }
        else { 
            return false;
        }
    }

    public int size() { 
        return size;
    }

    public void printDeque() { 
        int pointer = (nextFirst + 1) % items.length;
        int i = 0;
        while(i != size) { 
            System.out.print(items[pointer]) ;
            System.out.print(" ") ;
            pointer = (pointer + 1) % items.length;
            i += 1;
        }
    }

    public T removeFirst() { 
        if (size == 0) { 
            return null;
        }
        nextFirst = (nextFirst+1) % items.length;
        T t = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if((double) size / items.length < 0.25) {
            resize("smaller");
        }
        return t;
    }

    public T removeLast() {  
        if (size == 0) { 
            return null;
        }
        nextLast -= 1;
        if (nextLast < 0) nextLast += items.length;
        T t = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if((double) size / items.length < 0.25) {
            resize("smaller");
        }
        return t;
    }

    public T get(int index) { 
        if (index >= size) {
            return null;
        }
        return items[(index + 1 + nextFirst) % items.length];
    }




}
