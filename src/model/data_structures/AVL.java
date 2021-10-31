package model.data_structures;

import model.interfaces.IAVL;

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
    public void rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right();
        if (x.parent() == null) {
            setRoot(y);
            y.setParent(null);
        }
        else if (x.key().compareTo(x.parent().key()) >= 0) x.parent().setRight(y);
        else x.parent().setLeft(y);
        y.setParent(x.parent());
        x.setParent(y);
        x.setRight(y.left());
        y.setLeft(x);
    }

    @Override
    public int balance(Node<K, V> node) {
        if (node == null) return -1;
        return height(node.right()) - height(node.left());
    }

    @Override
    public void balanceAgain(Node<K, V> p) {
            int balanceFactor = Math.abs(balance(p));
            if (balanceFactor == 2) {
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

    @Override
    public void insert(K key, V value) {
        super.insert(key, value);
        Node<K, V> inserted = search(root(), key);
        while (inserted != null) {
            balanceAgain(inserted);
            inserted = inserted.parent();
        }
    }

    @Override
    public void delete(K key) {
        Node<K, V> deleted = search(root(), key);
        super.delete(key);
        while (deleted != null) {
            balanceAgain(deleted);
            deleted = deleted.parent();
        }
    }
}
