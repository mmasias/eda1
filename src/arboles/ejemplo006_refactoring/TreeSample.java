package ejemplo006_refactoring;

import java.util.Scanner;

public class TreeSample {
    public static void main(String[] args) {
        Tree tree = new Tree();

        fillTree(tree);

        tree.printTree();

        boolean verPila = false;

        System.out.println("Recorrido Preorden:");
        tree.printTraversePreorder(verPila);

        System.out.println("Recorrido Inorden:");
        tree.printTraverseInorder(verPila);

        System.out.println("Recorrido Postorden:");
        tree.printTraversePostorder(verPila);
    }

    private static void fillTree(Tree tree) {
        Scanner scanner = new Scanner(System.in);
        int value;
        do {
            value = scanner.nextInt();
            if (value != -1) {
                tree.insert(value);
            }
        } while (value != -1);
        scanner.close();
    }
}
