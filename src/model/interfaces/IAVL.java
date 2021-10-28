package model.interfaces;

public interface IAVL<K, V, T> extends IBST<K, V, T> {

    int height(T node);

    void rotateRight(T node);

    void rotateLeft(T node);

    boolean isBalanced(T node);
}
