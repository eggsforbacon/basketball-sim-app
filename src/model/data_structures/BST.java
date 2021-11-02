package model.data_structures;

import model.interfaces.IBST;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class BST<K extends Comparable<K>, V> implements IBST<K, V, Node<K, V>>, Serializable {

    private Node<K, V> root;
    private int size;
    private static final long serialVersionUID = 1;

    public BST() {
        root = null;
        size = 0;
    }

    @Override
    public Node<K, V> search(Node<K, V> root, K key) {
        if (root == null || root.key().equals(key)) return root;
        else if (key.compareTo(root.key()) < 0) return search(root.left(), key);
        else return search(root.right(), key);
    }

    public ArrayList<V> searchApproximate(Node<K, V> node, String query, ArrayList<V> results) {
        if (node == null) return results;
        else if(node.key().toString().contains(query)) {
            results = searchApproximate(node.left(), query, results);
            results.add(node.value());
            return searchApproximate(node.right(), query, results);
        }
        results = searchApproximate(node.left(), query, results);
        results = searchApproximate(node.right(), query, results);
        return results;
    }

    @Override
    public void insert(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        Node<K, V> trail = null;
        Node<K, V> current = root;
        while (current != null) {
            trail = current;
            if (node.key().compareTo(current.key()) < 0) current = current.left();
            else current = current.right();
            node.setParent(trail);
        }
        if (trail == null) root = node;
        else if (node.key().compareTo(trail.key()) < 0) trail.setLeft(node);
        else trail.setRight(node);

        size++;

    }

    @Override
    public void delete(K key) {
        Node<K, V> found = search(root, key);
        if (found == null) throw new NoSuchElementException("Element with key \"" + key + "\" not found in tree");
        int leftRight = found.parent() != null ? key.compareTo(found.parent().key()) : 0;
        if (found.left() == null && found.right() == null) {
            System.out.println("Key " + key + " case 1");
            if (leftRight < 0) found.parent().setLeft(null);
            else found.parent().setRight(null);
        } else if (found.left() != null && found.right() != null) {
            System.out.println("Key " + key + " case 3");
            Node<K, V> replacement = successor(found);
            delete(replacement.key());
            found.setKey(replacement.key());
            found.setValue(replacement.value());
        } else {
            System.out.println("Key " + key + " case 2");
            Node<K, V> child = found.left() != null ? found.left() : found.right();
            if (leftRight < 0) found.parent().setLeft(child);
            else found.parent().setRight(child);
        }

        size--;
    }

    public Node<K, V> min() {
        return min(root);
    }

    @Override
    public Node<K, V> min(Node<K, V> root) {
        while (root.left() != null) root = root.left();
        return root;
    }

    public Node<K, V> max() {
        return max(root);
    }

    @Override
    public Node<K, V> max(Node<K, V> root) {
        while (root.right() != null) root = root.right();
        return root;
    }

    @Override
    public Node<K, V> successor(Node<K, V> node) {
        if (node.right() != null) return min(node.right());
        Node<K, V> successor = node.parent();
        while (successor != null && node == successor.right()) {
            node = successor;
            successor = successor.parent();
        }
        return successor;
    }

    @Override
    public Node<K, V> predecessor(Node<K, V> node) {
        if (node.left() != null) return max(node.left());
        Node<K, V> successor = node.parent();
        while (successor != null && node == successor.left()) {
            node = successor;
            successor = successor.parent();
        }
        return successor;
    }

    public String inorder() {
        StringBuilder sb = new StringBuilder();
        return inorderRec(root, sb);
    }

    private String inorderRec(Node<K, V> root, StringBuilder sb) {
        if (root != null) {
            inorderRec(root.left(), sb);
            sb.append(root.key().toString()).append(" ");
            inorderRec(root.right(), sb);
        }
        return sb.toString();
    }

    public String inorderVals() {
        StringBuilder sb = new StringBuilder();
        return inorderRecVals(root, sb);
    }

    private String inorderRecVals(Node<K, V> root, StringBuilder sb) {
        if (root != null) {
            inorderRecVals(root.left(), sb);
            sb.append(root.value().toString()).append(" ");
            inorderRecVals(root.right(), sb);
        }
        return sb.toString();
    }

    public ArrayList<V> toArrayList() {
        ArrayList<V> array = new ArrayList<>();
        return toArrayListRec(root, array);
    }

    private ArrayList<V> toArrayListRec(Node<K, V> root, ArrayList<V> array) {
        if (root!= null) {
            toArrayListRec(root.left(), array);
            array.add(root.value());
            toArrayListRec(root.right(), array);
        }
        return array;
    }

    public Node<K, V> root() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }

    public int size() {
        return size;
    }
}


