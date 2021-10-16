package model.data_structures;

public class Node<T> {

    private T data;
    private Node<T> next;

    public Node(T data) {
        this.data = data;
        next = null;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> next() {
        return next;
    }

    public T data() {
        return data;
    }
}
