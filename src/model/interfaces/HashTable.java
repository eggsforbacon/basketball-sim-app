package model.interfaces;

/**
 * Interface representing the main operations of a Hash Table abstract data type. <br>
 */
public interface HashTable<K, V> {
    
    /**
     * The hashing algorithm. <br>
     * @param key The key to be hashed. <br>
     * @return The index to be set into the array. <br>
     */
    int hash(K key);

    /**
     * Inserts a new value into the table. <br>
     * @param key The key to calculate the index. <br>
     * @param value The value to be inserted. <br>
     * @throws Exception
     */
    
    int insert(K key, V value) throws Exception;

    void delete(K key) throws Exception;

    Object search(K key);

    boolean isEmpty();

    int size();
}
