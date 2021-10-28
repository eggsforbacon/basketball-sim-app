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
        
    }

    @Override
    public void rotateLeft(Node<K, V> node) {

    }

    @Override
    public boolean isBalanced(Node<K, V> node) {
        int balanceFactor = height(node.left()) - height(node.right());
        return Math.abs(balanceFactor) <= 1;
    }
}
