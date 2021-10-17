package model.data_structures;

import java.io.Serializable;
import java.util.Objects;

import model.interfaces.*;

class HashNode<K, V> implements Serializable {

    K key;
    V value;

    HashNode<K, V> next;
    HashNode<K, V> prev;

    public HashNode(K key, V value) {
        this.key = key;
        this.value = value;
        next = null;
        prev = null;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

public class DefaultHashTable<K, V> implements HashTable<K, V>, Serializable {

    private HashNode<?, ?>[] table;
    private int size;
    private int arraySize;

    public DefaultHashTable(int arraySize) {
        table = new HashNode<?, ?>[arraySize];
        size = 0; // :)
        this.arraySize = arraySize;
    }

    @Override
    public int hash(K key) {
        return Objects.hashCode(key) % arraySize;
    }

    private int hash(K key, int i) {
        return (Objects.hashCode(key) + i) % arraySize;
    }

    @Override
    public int insert(K key, V value) throws Exception {

        int i = 0;

        do {

            int j = hash(key, i);

            if (table[j] == null) {
                table[j] = new HashNode<>(key, value);
                size++;
                return j;

            }

            i++;

        } while (i != arraySize);
        throw new Exception("Desbordamiento de tabla hash");

    }

    @Override
    public void delete(K key) throws Exception {

        int i = 0;

        do {

            int j = hash(key, i);

            try {
                if (table[j].getKey() == key) {
                    table[j] = null;
                    size--;
                    return;

                }
            }catch (NullPointerException e){}



            i++;

        } while (i != arraySize);
        throw new Exception("Desbordamiento de tabla hash");

    }

    @Override
    @SuppressWarnings("unchecked")
    public V search(K key) {

        int i = 0;
        int j;

        do {

            j = hash(key, i);
           
            if (table[j] != null && table[j].getKey().equals(key)) {
                return (V) table[j].getValue();

            }

            i++;

        } while (i != arraySize);
        return null;

    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

}
