package model.interfaces;

public interface IAVL<K, V, T> extends IBST<K, V, T> {
    void rotateRight();
}
