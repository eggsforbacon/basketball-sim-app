package model.interfaces;

/**
 * Interface representing the main operations of a Stack abstract data type. <br>
 */
public interface Stack<T> {
    /**
     * Main constructor of the class. Crates an empty stack. <br>
     */
    abstract public void push(T data);

    /**
     * Deletes (pops) the first element of the Stack, moves the next element to the top of the stack and returns the deleted one upon deletion. <br>
     * @return The data of the element that was just deleted. <br>
     */
    abstract public T pop();

    /**
     * @return The top of the stack
     */
    abstract public T top();

    /**
     * @return `true` if there are no elements in the stack, `false` otherwise. <br>
     */
    abstract public boolean isEmpty();

    /**
     * @return The ammount of elements in the stack. <br>
     */
    abstract public int size();
}
