package model.interfaces;

/**
 * Interface representing the main operations of a Queue abstract data type. <br>
 */
public interface Queue<T> {
    /**
     * Enqueues the given data to the back of the Queue, making it the last element. <br>
     * @param data The data to be inserted into the Queue. <b>Must be of type T and shouldn't be null</b>. <br>
     */
    abstract public void enqueue(T data);

    /**
     * Deletes (dequeues) the first element of the Queue, moves the next element to the front of the Queue and returns the deleted one upon deletion. <br>
     * @return The data of the element that was just deleted. <br>
     */
    abstract public T dequeue();

    /**
     * @return The front of the stack. <br>
     */
    abstract public T front();

    /**
     * @return `true` if empty, `false` otherwise .<br>
     */
    abstract public boolean isEmpty();

    /**
     * @return The size of the Queue. <br>
     */
    abstract public int size();
}
