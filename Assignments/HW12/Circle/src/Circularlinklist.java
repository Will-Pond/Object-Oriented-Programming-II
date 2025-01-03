import java.util.Iterator;

public class Circularlinklist<E> implements MyList<E> {

    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {

        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        head = newNode; // head points to the new node
        //tail.next = head;
        size++; // Increase list size

        if (tail == null) // the new node is the only node in list
            tail = head;
        tail.next = head;
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new for element e
        newNode.next = head;

        if (tail == null) {
            head = tail = newNode; // The new node is the only node in list
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
            Node<E> current = head;
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
            Node<E> current = tail.next;

            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            return current.element;
        }
    }

    @Override
    public int indexOf(Object e) {
        return 0;
    }

    @Override
    public int lastIndexOf(E e) {
        return 0;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public E set(int index, E e) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }
    public E getFirst() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.next.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        }
        else {
            return tail.element;
        }
    }

    public E removeFirst() {
        if (size == 0) {
            return null;
        }
        else {
            E temp = tail.next.element;
            tail.next = (tail.next).next;
            size--;
            if (tail.next == null) {
                tail = null;
            }
            return temp;
        }
    }

    /** Remove the last node and
     * return the object that is contained in the removed node. */
    public E removeLast() {
        if (size == 0) {
            return null;
        }
        else if (size == 1) {
            E temp = tail.next.element;
            tail.next = tail = null;
            size = 0;
            return temp;
        }
        else {
            Circularlinklist.Node<E> current = tail.next;

            for (int i = 0; i < size - 2; i++) {
                current = current.next;
            }

            E temp = tail.element;
            tail = current;
            tail.next = null;
            size--;
            return temp;
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Circularlinklist.Node<E> current = tail.next;
        for (int i = 0; i < size; i++) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            }
            else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element) {
            this.element = element;
        }
    }
}
