package ui;

import model.data_structures.BST;
import model.data_structures.RedBlackTree;
import model.objects.Fiba;
import model.objects.Player;
import model.objects.Team;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final String SAVE_PATH_FILE = "data/persistent/Data.das";
    private static ArrayList<Team> teams;
    private static RedBlackTree<String, Player> players;

    public static void main(String[] args) throws Exception {

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

        System.out.println("Pre delete: " + stringTree.inorderVals());

        System.out.println(stringTree.search(stringTree.root(), 1).value());

        stringTree.delete(2);

        System.out.println("After delete: " + stringTree.inorderVals());
    }

    public static void driver3() {
        BST<Integer, Integer> tree = new BST<>();
        int max = 20, min = 0, range = max - min + 1;
        for (int i = 0; i < 10; i++) {
            int newKey = (int)(Math.random() * range) + min;
            int neg = Math.random() > 0.5 ? 1 : -1;
            tree.insert(newKey/* * neg*/, i);
        }
        dup1(tree);
    }

    public static void driver4() {
        BST<Integer, Integer> tree = new BST<>();
        int[] nums = {12, 5, 15, 11, 4, 18, 14, 7, 16, 4};
        for (int i = 0, numsLength = nums.length; i < numsLength; i++) {
            int num = nums[i];
            tree.insert(num, i);
        }
        dup1(tree);
    }

    private static void dup1(BST<Integer, Integer> tree) {
        System.out.println("Keys: " + tree.inorder() + "\nValues: " + tree.inorderVals());
        Scanner in = new Scanner(System.in);
        System.out.print("Pick a key to delete: ");
        int key = Integer.parseInt(in.nextLine());
        tree.delete(key);
        System.out.println("New Keys: " + tree.inorder() + "\nNew Values: " + tree.inorderVals());
        in.close();
    }

}
