package model.data_structures;

import model.interfaces.IAVL;

import java.io.Serializable;

public class AVL<K extends Comparable<K>, V> extends BST<K, V> implements IAVL<K, V, Node<K,V>>, Serializable {
    private static final long serialVersionUID = 1;

    @Override
    public int height(Node<K, V> node) {
        if (node == null) return -1;
        else if (node.left() == null && node.right() == null) return 0;
        else {
            return Math.max(height(node.left()), height(node.right())) + 1;
        }
    }

    @Override
    public void rightRotate(Node<K, V> x) {
        if (x == null) return;
        Node<K, V> pivot = x.left();
        if (pivot == null) return;
        if (x.parent() == null) {
            setRoot(pivot);
            pivot.setParent(null);
        }
        else if (x.key().compareTo(x.parent().key()) >= 0) x.parent().setRight(pivot);
        else x.parent().setLeft(pivot);
        pivot.setParent(x.parent());
        x.setParent(pivot);
        x.setLeft(pivot.right());
        pivot.setRight(x);
    }

    @Override
    public void leftRotate(Node<K, V> x) {
        if (x == null) return;
        Node<K, V> pivot = x.right();
        if (pivot == null) return;
        if (x.parent() == null) {
            setRoot(pivot);
            pivot.setParent(null);
        } else if (x.key().compareTo(x.parent().key()) >= 0) x.parent().setRight(pivot);
        else x.parent().setLeft(pivot);
        pivot.setParent(x.parent());
        x.setParent(pivot);
        x.setRight(pivot.left());
        pivot.setLeft(x);
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
                if (caseA || caseB) leftRotate(p);
                else if (caseD || caseE) rightRotate(p);
                else if (caseC) {
                    q = p.right();
                    rightRotate(q);
                    leftRotate(p);
                } else if (caseF) {
                    leftRotate(q);
                    rightRotate(p);
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
