package model.data_structures;

import model.interfaces.*;

import java.util.NoSuchElementException;

/**
 * A custom implementation of the Stack generic data type. <br>
 */
public class DefaultStack<T> implements Stack<T> {

    private Node<T> top;
    private int size;

    /**
     * The main contructor of the class. Creates an empty Stack. <br>
     */
    public DefaultStack() {
        top = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void push(T data) {
        Node<T> element = new Node<>(data);
        element.setNext(top);
        top = element;
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T pop() {
        if(top == null){
            throw new NoSuchElementException("Can't pop from an empty stack");

        }else {
            Node<T> trash = top;
            top = top.next();
            size--;
            return trash.data();

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T top() {
        return top.data();
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
        Node<T> head = top;
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
     * Reverses the Stack,such that the topmost element is now at the bottom.
     * <br>
     *
     * @return The reversed Stack, such that the unreversed Stack is still
     * accessible with `top()`. <br>
     */
    public DefaultStack<T> reverse() {
        DefaultStack<T> reversed = new DefaultStack<>();
        while (!isEmpty()) {
            reversed.push(pop());
        }
        return reversed;
    }

    /**
     * Returns a string representation of the Stack, as a comma separated list
     * with no other characters or whitespace. <br>
     *
     * @return A trimmed comma separated values list that reresents the stack.
     * <br>
     */
    public String toStringNoFormat() {
        StringBuilder sb = new StringBuilder();
        Node<T> head = top;
        String prefix = "";
        while (head != null) {
            sb.append(prefix).append(head.data());
            prefix = ",";
            head = head.next();
        }
        return sb.toString();
    }

    public Integer[] toArray() {
        Integer[] array = new Integer[size];

        for (int i = array.length ; i > 0; i--) {
            array[i - 1] = (Integer) this.pop();

        }

        return array;
// 612 <- 287 || 287 <- 612 || 612 <- 287
    }

    public void toStack(T[] array) {

        for (T var : array) {
            push(var);

        }

    }
}
