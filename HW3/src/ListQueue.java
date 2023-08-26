//Aya Salama
//I pledge my honor that I have abided by the Stevens Honor System

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListQueue<E>{
    private Node<E> front;
    private int size;
    public static class Node<E> {
        private E data;
        private Node<E> next;
        private int priority;

        /**
         * constructor
         * @param dataItem
         */
        public Node(E dataItem){
            data = dataItem;
            next = null;
            priority = Integer.MAX_VALUE;
        }

        /**
         * constructor
         * @param dataItem
         * @param priority
         */
        public Node(E dataItem, int priority){
            data = dataItem;
            next = null;
            this.priority = priority;
        }

        /**
         * constructor
         * @param dataItem
         * @param ref
         * @param priority
         */
        public Node(E dataItem, Node<E> ref, int priority){
            data = dataItem;
            next = ref;
            this.priority = priority;
        }

        /**
         * returns the node's data
         * @return E
         */
        public E getData(){
            return data;
        }

        /**
         * returns the next node
         * @return Node<E>
         */
        public Node<E> getNext(){
            return next;
        }
    }

    private class Iter implements Iterator<E>{
        private Node<E> next = front;

        /**
         * checks if next is null
         * @return true if next is not null, false otherwise
         */
        @Override
        public boolean hasNext(){
            return next != null;
        }

        /**
         * updates next value to next of next
         * @return the old next value
         */
        @Override
        public E next() {
            if (next == null) {
                throw new NoSuchElementException("Next Node is null");
            }
            else {
                Node<E> copy = next;
                next = next.next;
                return copy.data;
            }
        }

        /**
         *
         * @throws UnsupportedOperationException
         */
        public void remove() throws UnsupportedOperationException {
            throw new UnsupportedOperationException();
        }
    }


    /**
     * constructor
     */
    public ListQueue(){
        front = new Node<>(null);
        size = 0;
    }

    /**
     * constructor
     * @param first
     */
    public ListQueue(Node<E> first){
        front = first;
        size = 1;
    }

    /**
     *
     * @return front of queue
     */
    public Node<E> getFront(){
        return front;
    }

    /**
     * sets the front Node
     * @param front
     */
    public void setFront(Node<E> front){
        this.front = front;
    }

    /**
     *
     * @return size of queue
     */
    public int getSize(){
        return size;
    }

    /**
     * returns info at front of queue
     * @return E
     */
    public  E peek(){
        return front.data;
    }


    /**
     * adds item to a position according to its priority
     * @param item
     * @param priority
     * @return true if item is not null, otherwise throws exception
     */
    public boolean offer(E item, int priority){
        if(item == null){
            throw new NullPointerException("item is null");
        }
        else if(size == 0){
            front = new Node<E>(item, priority);
            size++;
        }
        else if(priority < front.priority){
            front = new Node<>(item, front, priority);
            size++;
        }
        else{
            Node<E> current = front;
            while(current.next != null && current.next.priority <= priority){
                current = current.next;
            }
            current.next = new Node(item, current.next, priority);
            size++;
        }
        return true;
    }


    /**
     * adds item at end of queue
     * @param item
     * @return true if item is not null, otherwise throws exception
     */
    public boolean addRear(E item){
        if(item == null){
            throw new NullPointerException("item is null.");
        }
        else if(size == 0){
            front = new Node<E>(item);
            size++;
        }
        else{
            Node<E> current = front;
            for(int i = 0; i < size - 1; i++){
                current = current.next;
            }
            current.next = new Node(item);
            size++;
        }
        return true;
    }

    /**
     *
     * @return data at the front and removes it
     */
    public E poll(){
        if(front == null){
            throw new NullPointerException("front item is null");
        }
        else{
            Node<E> copy = front;
            front = front.next;
            size--;
            return copy.data;
        }
    }

    /**
     *
     * @param toBeRemoved
     * @return false of size is 0 or toBeRemoved was not found, true otherwise
     */
    public boolean remove(Node<E> toBeRemoved){
        if(size == 0){
            return false;
        }
        else{
            Node<E> current = front;
            Node<E> remove = null;
            while(current != null && current.data != toBeRemoved.data){
                remove = current;
                current = current.next;
            }
            if(current == null){
                return false;
            }
            if(remove == null){
                front = current.next;
            } else {
                remove.next = current.next;
            }
            size--;
            return true;
        }
    }

    /**
     *
     * @return new iter
     */
    public Iterator<E> iterator(){
        return new Iter();
    }

}