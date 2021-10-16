package model.data_structures;

import model.interfaces.*;
import model.objects.Client;

import java.util.NoSuchElementException;

/**
 * A custom implementation of the Queue generic data type. <br>
 */
public class DefaultQueue<T> implements Queue<T> {

    private Node<T> front;
    private Node<T> rear;
    private int size;

    /**
     * The main contructor of the class. Creates an empty Queue. <br>
     */
    public DefaultQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enqueue(T data) {
        Node<T> element = new Node<>(data);
        if (front == null) {
            rear = element;
            front = element;
        } else {
            rear.setNext(element);
            rear = element;
        }
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T dequeue() {
        Node<T> trash = front;
        if (front == null) {
            throw new NoSuchElementException("Can't dequeue from an empty queue");
        } else if (front == rear) {
            front = rear = null;
        } else {
            front = front.next();
        }
        size--;
        return trash.data();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T front() {
        return front.data();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<T> head = front;
        sb.append("(");
        String prefix = "";
        while (head != null) {
            sb.append(prefix).append(head.data());
            prefix = ", ";
            head = head.next();
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     * Reverses the Queue,such that the frontmost element is now at the back.
     * <br>
     *
     * @return The reversed Queue, such that the unreversed Queue is still
     * accessible with `front()`. <br>
     */
    public DefaultQueue<T> reverse() {
        DefaultStack<T> aux = new DefaultStack<>();
        DefaultQueue<T> reversed = new DefaultQueue<>();
        while (!isEmpty()) {
            aux.push(dequeue());
        }
        while (!aux.isEmpty()) {
            reversed.enqueue(aux.pop());
        }
        return reversed;
    }

    public Client[] toClientArray() {
        Client[] array = new Client[size];

        for (int i = array.length ; i > 0; i--) {
            array[i - 1] = (Client) this.dequeue();

        }

        return array;
// 612 <- 287 || 287 <- 612 || 612 <- 287
    }

    public void toQueue(T[] array) {
        front = null;
        rear = null;

        for (T var : array) {
            enqueue(var);

        }

    }
}
