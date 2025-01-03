import java.util.Iterator;
import java.util.NoSuchElementException;

public class Circularlinklist<E> implements MyList<E> {

    private Node<E>  tail;
    private int size = 0; // Number of elements in the list


    public E removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            Node<E> temp = tail.next;
            tail = tail.next;
            if (tail.next == null) {
                tail = null;
            }
            return temp.element;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public E getFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            return tail.next.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            return tail.element;
        }
    }
    public E removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else if (size == 1) {
            Node<E> temp = tail.next;
            tail.next = tail = null;
            size = 0;
            return temp.element;
        }
        else {
            Node<E> current = tail.next;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            Node<E> temp = tail;
            tail = current;
            tail.next = null;
            size--;
            return temp.element;
        }
    }
    /** Add an element to the beginning of the list */
    public void addFirst(E e) {

        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = tail.next; // link the new node with the head
        tail.next = newNode; // head points to the new node
        //tail.next = head;
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
            tail = tail.next;
        tail.next = tail.next;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e
        newNode.next = tail.next;

        if (tail == null) {
            tail.next = tail = newNode; // The new node is the only node in list
            //tail.next = head;
        }
        else {
            tail.next = newNode; // Link the new with the last node
            tail = newNode; // tail now points to the last node
            //tail.next = head;
        }

        size++; // Increase size
    }
    @Override
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        }
        else if (index >= size) {
            addLast(e);
        }
        else {
            Node<E> current = tail.next;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }

            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            size++;
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        else if (index == 0) {
            return getFirst();
        }
        else if (index == size - 1) {
            return getLast();
        }
        else {
            Circularlinklist.Node<E> current = tail.next;

            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            return current.element;
        }

    }

    @Override
    public int indexOf(Object e) {

        if(size == 0)
        {
            return -1;
        }
        else
        {
            Circularlinklist.Node<E> temp = tail.next;
            int result = 0;
            while(temp != null){
                if(temp.element.equals(e))
                {
                    return result;
                }
                temp = temp.next;
                result++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        return 0;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        else if (index == 0) {
            return removeFirst();
        }
        else if (index == size - 1) {
            return removeLast();
        }
        else {
            Circularlinklist.Node<E> previous = tail.next;

            for (int i = 1; i < index; i++) {
                previous = previous.next;
            }

            Circularlinklist.Node<E> current = previous.next;
            previous.next = current.next;
            size--;
            return current.element;
        }
    }
    @Override /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        try {


            Circularlinklist.Node<E> current = tail.next;
            for (int i = 0; i <= size; i++) {
                result.append(current.element);
                current = current.next;
                if (current != null) {
                    result.append(", "); // Separate two elements with a comma
                }
            }

        }catch (NullPointerException e){
            result.append("]"); // Insert the closing ] in the string
        }
        return result.toString();
    }

    @Override
    public E set(int index, E e) {
        if(index < 0 || index > size -1) {
            throw new IndexOutOfBoundsException();
        }
        else{
            Circularlinklist.Node<E> current = tail.next;
            for(int i = 0; i < index; i++){
                current = current.next;
            }
            current.element = e;
            return current.element;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(Object o) {
        E data = (E)o;
        // Left as an exercise
        Circularlinklist.Node<E> current = tail.next;
        //element is int double ==
        //element is string
        for (int i = 1; i <= size; i++) {
            if(current.element.equals(o)){
                return true;
            }
            current = current.next;
        }
        while(current != null){
            if(current.element.equals(o)){
                return true;
            }
            current = current.next;
        }
        return false;
    }


    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        size = 0;
        tail.next = tail = null;
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}
