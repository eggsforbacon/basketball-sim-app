package model.data_structures;

import java.io.Serializable;

public class Node<K extends Comparable<K>, V> implements Serializable {
    private K key;
    private V value;
    private Node<K, V> parent;
    private Node<K, V> left, right;
    private static final long serialVersionUID = 1;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        parent = null;
        left = null;
        right = null;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }

    public Node<K, V> left() {
        return left;
    }

    public Node<K, V> right() {
        return right;
    }

    public Node<K, V> parent() {
        return parent;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setParent(Node<K, V> parent) {
        this.parent = parent;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key = " + key +
                ", value = " + value.toString() +
                '}';
    }
}
