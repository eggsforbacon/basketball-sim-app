package model.data_structures;

import model.interfaces.IBST;

import java.io.Serializable;

public class BST<K extends Comparable<K>, V> implements IBST<K, V, Node<K, V>>, Serializable {

    private Node<K, V> root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }


    @Override
    public Node<K, V> search(Node<K, V> root, K key) {
        if (root == null || root.key.equals(key)) {
            return root;
        }
        return null;
    }

    @Override
    public void insert(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        Node<K, V> trail = null;
        Node<K, V> current = root;
        while (current != null) {
            trail = current;
            if (node.key.compareTo(current.key) < 0) current = current.left;
            else current = current.right;
            node.parent = trail;

        }
        if (trail == null) root = node;
        else if (node.key.compareTo(trail.key) < 0) trail.left = node;
        else trail.right = node;

        size++;

    }

    @Override
    public void delete(Node<K, V> node) {

    }

    public Node<K, V> min() {
        return min(root);
    }

    @Override
    public Node<K, V> min(Node<K, V> root) {
        while (root.left != null) root = root.left;
        return root;
    }

    public Node<K, V> max() {
        return max(root);
    }

    @Override
    public Node<K, V> max(Node<K, V> root) {
        while (root.right != null) root = root.right;
        return root;
    }

    @Override
    public Node<K, V> successor(Node<K, V> node) {
        if (node.right != null) return node.right;
        Node<K, V> successor = node.parent;
        while (successor != null && node == successor.right) {
            node = successor;
            successor = successor.parent;
        }
        return successor;
    }

    @Override
    public Node<K, V> predecessor(Node<K, V> node) {
        if (node.left != null) return node.left;
        Node<K, V> successor = node.parent;
        while (successor != null && node == successor.left) {
            node = successor;
            successor = successor.parent;
        }
        return successor;
    }

    public Node<K, V> root() {
        return root;
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        return inorderRec(root, sb);
    }

    private String inorderRec(Node<K, V> root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left, sb);
            sb.append(root.key.toString()).append(" ");
            inorderRec(root.right, sb);
        }
        return sb.toString();
    }

    public String inorderVals() {
        StringBuilder sb = new StringBuilder();
        return inorderRecVals(root, sb);
    }

    private String inorderRecVals(Node<K, V> root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left, sb);
            sb.append(root.value.toString()).append(" ");
            inorderRec(root.right, sb);
        }
        return sb.toString();
    }
}


class Node<K extends Comparable<K>, V> implements Serializable{
    K key;
    V value;
    Node<K, V> parent;
    Node<K, V> left, right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        parent = null;
        left = null;
        right = null;
    }
}

