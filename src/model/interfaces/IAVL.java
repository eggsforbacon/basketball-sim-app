package model.interfaces;

import java.util.ArrayList;

public interface IAVL<K, V, T> extends IBST<K, V, T> {

    int height(T node);

    void rightRotate(T node);

    void leftRotate(T node);

    int balance(T node);

    void balanceAgain(T node);
}
