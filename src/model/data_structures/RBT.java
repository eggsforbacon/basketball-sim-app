package model.data_structures;

import java.io.Serializable;
import java.util.ArrayList;

public class RBT<K extends Comparable<K>, V> implements Serializable{

    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private int size;

    private class Node implements Serializable {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED;// El nuevo nodo predeterminado es rojo, es decir, el rojo representa el nodo que debe fusionarse
        }
    }

    private Node root;

    public RBT() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // girar a la izquierda
    //   node                    x
    // / \ Girar a la izquierda / \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;// definición
        // empezar a rotar
        node.right = x.left;
        x.left = node;
        // color
        x.color = node.color;
        node.color = RED;// nodo nodo yx forman 3 nodos
        return x;
    }

    // Gira a la derecha
    //     node                   x
    // / \ Gira a la derecha / \
    //   x    T2   ------->    y   node
    //  / \                        /  \
    // y  T1                      T1  T2
    private Node rightRotate(Node node) {
        Node x = node.left;// definición
        // rotar
        node.left = x.right;
        x.right = node;
        // mantener el color
        x.color = node.color;
        node.color = RED;// Almacenado temporalmente como 4 nodos rojo significa fusión
        return x;// devuelve el nuevo nodo raíz
    }

    // Cambio de color es agregar elementos a la derecha de los 3 nodos
    private void flipColors(Node node) {
        // Agregar elementos a 3 nodos se convertirá en 3 2 nodos
        node.color = RED;// Es posible que el nodo raíz deba fusionarse para que sea rojo
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    // Determine si el nodo raíz del árbol rojo-negro es rojo
    private boolean isRed(Node node) {
        if (node == null)// Naturaleza del árbol rojo-negro Los nodos vacíos son negros por defecto
            return BLACK;
        return node.color;
    }

    // Agrega un nuevo elemento (clave, valor) al árbol
    public void insert(K key, V value) {
        root = insert(root, key, value);
        root.color = BLACK;// Mantenga el nodo raíz en negro después de agregar
    }

    // Insertar elementos (clave, valor) en el árbol rojo-negro enraizado en el nodo, algoritmo recursivo
    // Devuelve la raíz del árbol rojo-negro después de insertar un nuevo nodo
    private Node insert(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value);// nodo rojo
        }

        if (key.compareTo(node.key) < 0)
            node.left = insert(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = insert(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        // girar a la izquierda
        if (isRed(node.right) && !isRed(node.left))// El niño derecho es rojo, el niño izquierdo no es rojo
            node = leftRotate(node);
        // Gira a la derecha
        if (isRed(node.left) && isRed(node.left.left))// El hijo izquierdo es rojo, el hijo izquierdo del hijo izquierdo también es rojo
            node = rightRotate(node);
        // cambio de color
        if (isRed(node.left) && isRed(node.right))// Tanto el niño izquierdo como el derecho son rojos
            flipColors(node);
        return node;
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    // Devuelve el nodo donde se encuentra la clave en el árbol de búsqueda binaria con nodo como nodo raíz
    private Node getNode(Node node, K key) {

        if (node == null) return null;

        if(key.equals(node.key)){
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else return getNode(node.right, key);

    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // Devuelve el nodo donde se encuentra el valor mínimo del árbol de búsqueda binario enraizado en el nodo
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // Elimina el nodo más pequeño en el árbol de búsqueda binario con raíz en el nodo
    // Devuelve la raíz del nuevo árbol de búsqueda binaria después de eliminar el nodo
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // Elimina el nodo clave del árbol de búsqueda binaria
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {

        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else { // key.compareTo(node.key) == 0

            // El subárbol izquierdo del nodo a eliminar está vacío
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // El caso en el que el subárbol derecho del nodo a eliminar está vacío
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // Cuando los subárboles izquierdo y derecho del nodo a eliminar no están vacíos

            // Encuentra el nodo más pequeño más grande que el nodo a eliminar, es decir, el nodo más pequeño del subárbol derecho del nodo a eliminar
            // Utilice este nodo para reemplazar la posición del nodo que se eliminará
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public Node search(Node root, K key) {
        if (root == null || root.key.equals(key)) return root;
        else if (key.compareTo(root.key) < 0) return search(root.left, key);
        else return search(root.right, key);
    }

    public ArrayList<Node> searchApproximate(Node node, String query, ArrayList<Node> results) {
        if (node == null) return results;
        else if (node.key.toString().contains(query)) {
            results = searchApproximate(node.left, query, results);
            results.add(node);
            return searchApproximate(node.right, query, results);
        }
        results = searchApproximate(node.left, query, results);
        results = searchApproximate(node.right, query, results);
        return results;
    }
}
