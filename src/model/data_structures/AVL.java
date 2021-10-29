package model.data_structures;

import model.interfaces.IAVL;

import java.util.ArrayList;

public class AVL<K extends Comparable<K>, V> extends BST<K, V> implements IAVL<K, V, Node<K,V>> {

    @Override
    public int height(Node<K, V> node) {
        if (node == null) return -1;
        else if (node.left() == null && node.right() == null) return 0;
        else return Math.max(height(node.left()), height(node.right())) + 1;
    }

    @Override
    public void rotateRight(Node<K, V> node) {
        Node<K, V> child = node.left();
        if (node.key().compareTo(node.parent().key()) >= 0) node.parent().setRight(child);
        else node.parent().setLeft(child);
        node.setLeft(child.right());
        child.setRight(node);
    }

    @Override
    public void rotateLeft(Node<K, V> node) {
        Node<K, V> child = node.right();
        if (node.key().compareTo(node.parent().key()) >= 0) node.parent().setRight(child);
        else node.parent().setLeft(child);
        node.setRight(child.left());
        child.setLeft(node);
    }

    @Override
    public int balance(Node<K, V> node) {
        return height(node.right()) - height(node.left());
    }

    @Override
    public void balanceAgain(Node<K, V> node, ArrayList<Node<K, V>> path) {
        for (Node<K, V> p: path) {
            int balanceFactor = Math.abs(balance(p));
            if (balanceFactor > 1) {
                Node<K, V> q = p.right();
                boolean caseA = balance(q) == 1;
                boolean caseB = balance(q) == 0;
                boolean caseC = balance(q) == -1;
                q = p.left();
                boolean caseD = balance(q) == -1;
                boolean caseE = balance(q) == 0;
                boolean caseF = balance(q) == 1;
                if (caseA || caseB) rotateLeft(p);
                else if (caseD || caseE) rotateRight(p);
                else if (caseC) {
                    q = p.right();
                    rotateRight(q);
                    rotateLeft(p);
                } else if (caseF) {
                    rotateLeft(q);
                    rotateRight(p);
                }
            }
        }
    }

    private ArrayList<Node<K, V>> insertBST(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        Node<K, V> trail = null;
        Node<K, V> current = root();
        ArrayList<Node<K, V>> path = new ArrayList<>();
        while (current != null) {
            trail = current;
            path.add(trail);
            if (node.key().compareTo(current.key()) < 0) current = current.left();
            else current = current.right();
            node.setParent(trail);

        }
        if (trail == null) super.setRoot(node);
        else if (node.key().compareTo(trail.key()) < 0) trail.setLeft(node);
        else trail.setRight(node);
        return path;
    }

    @Override
    public void insert(K key, V value) {
        ArrayList<Node<K, V>> path = insertBST(key, value);
        Node<K, V> inserted = search(root(), key);
        balanceAgain(inserted, path);
    }
}
