import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;

public interface MyList<E> extends Collection<E> {
    /** Add a new element at the specified index in this list */
    public void add(int index, E e);

    /** Return the element from this list at the specified index */
    public E get(int index);

    /** Return the index of the first matching element in this list.
     *  Return -1 if no match. */
    public int indexOf(Object e);

    /** Return the index of the last matching element in this list
     *  Return -1 if no match. */
    public int lastIndexOf(E e);

    /** Remove the element at the specified position in this list
     *  Shift any subsequent elements to the left.
     *  Return the element that was removed from the list. */
    public E remove(int index);

    /** Replace the element at the specified position in this list
     *  with the specified element and returns the new set. */
    public E set(int index, E e);

    @Override /** Add a new element at the end of this list */
    public default boolean add(E e) {
        add(size(), e);
        return true;
    }

    @Override /** Return true if this list contains no elements */
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override /** Remove the first occurrence of the element e
     *  from this list. Shift any subsequent elements to the left.
     *  Return true if the element is removed. */
    public default boolean remove(Object e) {
        if (indexOf(e) >= 0) {
            remove(indexOf(e));
            return true;
        }
        else
            return false;
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        // We did this exercise in class.
        for (Object obj : c)
            if (!contains(obj))
                return false;
        return true;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        // Left as an exercise
        boolean modifier = false;
        for (E e: c)
            if (add(e))
                modifier = true;
        return true;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        // Left as an exercise
        boolean modifier = false;
        Iterator<?> e = iterator();
        while(e.hasNext()){
            if(c.contains(e.next())){
                e.remove();
                modifier = true;
            }
        }
        return modifier;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // Left as an exercise
        if(c == null){
            throw new NullPointerException("Collection is null");
        }
        Iterator itr = c.iterator();

        while(itr.hasNext()){
            if(!c.contains(itr.next())){
                itr.remove();
                return true;
            }
        }

        return false;
    }

    @Override
    public default Object[] toArray() {
        // Left as an exercise
       /* Object[] j = new Object[this.size()];

         for(int i = 0; i <this.size(); i++)
             j[i] = (Object)(this.get(i));
            if(size() > 0)
                return (E[]) temp;
            else return null; */
        return null;



    }

    @Override
    public default <T> T[] toArray(T[] array) {
        // Left as an exercise
        int size = size();
        if(array.length < size){
            array = (T[]) Array.newInstance(array.getClass().componentType(),size);
       }
        else if (array.length > size){
            array[size] = null;
        }
        int i = 0;
        for(E e: this){
            array[i] = (T) e;
            i++;
        }
        return array;

    }
}
