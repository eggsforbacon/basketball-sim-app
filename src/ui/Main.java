package ui;

import model.data_structures.BST;
import model.data_structures.RedBlackTree;
import model.objects.Fiba;
import model.objects.Player;
import model.objects.Team;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Main {

    private static final String SAVE_PATH_FILE = "data/persistent/Data.das";
    private static ArrayList<Team> teams;
    private static RedBlackTree<String, Player> players;

    public static void main(String[] args) throws Exception {
        driver1();
//        driver2();

    }

    public static void driver1() throws Exception {

        Fiba fs = new Fiba();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_PATH_FILE));
        oos.writeObject(fs);
        oos.close();

    }

    public static void driver2() {
        BST<Integer, String> stringTree = new BST<>();

        stringTree.insert(1, "Hello");
        stringTree.insert(-1, "!!!");
        stringTree.insert(3, "!!!");
        stringTree.insert(2, "World");

        System.out.println(stringTree.inorder());
        System.out.println(stringTree.inorderVals());
    }

}
