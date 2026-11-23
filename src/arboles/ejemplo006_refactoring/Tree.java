package ejemplo006_refactoring;

class Tree {
    private Node root;

    public Tree() {
        root = null;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            Node current = root;
            boolean inserted = false;

            while (!inserted) {
                if (key <= current.getKey()) {
                    if (current.hasLeftChild()) {
                        current = current.getLeft();
                    } else {
                        current.attachLeftChild(new Node(key));
                        inserted = true;
                    }
                } else {
                    if (current.hasRightChild()) {
                        current = current.getRight();
                    } else {
                        current.attachRightChild(new Node(key));
                        inserted = true;
                    }
                }
            }
        }
    }

    public void printTree() {
        linea();
        if (root == null) {
            System.out.println("Árbol vacío");
        } else {
            Node[] nodeStack = new Node[100];
            String[] prefixStack = new String[100];
            String[] labelStack = new String[100];
            boolean[] lastStack = new boolean[100];
            int top = -1;

            top = top + 1;
            nodeStack[top] = root;
            prefixStack[top] = "";
            labelStack[top] = "";
            lastStack[top] = true;

            while (top >= 0) {
                Node current = nodeStack[top];
                String prefix = prefixStack[top];
                String label = labelStack[top];
                boolean last = lastStack[top];
                top = top - 1;

                String childPrefix;
                if (current == root) {
                    System.out.println(current.getKey());
                    childPrefix = "";
                } else {
                    String connector = last ? "`-- " : "+-- ";
                    System.out.println(prefix + connector + label + current.getKey());
                    childPrefix = prefix + (last ? "    " : "|   ");
                }

                if (current.hasRightChild()) {
                    top = top + 1;
                    nodeStack[top] = current.getRight();
                    prefixStack[top] = childPrefix;
                    labelStack[top] = "D: ";
                    lastStack[top] = true;
                }

                if (current.hasLeftChild()) {
                    top = top + 1;
                    nodeStack[top] = current.getLeft();
                    prefixStack[top] = childPrefix;
                    labelStack[top] = "I: ";
                    lastStack[top] = !current.hasRightChild();
                }
            }
        }
    }

    public void printTraversePreorder(boolean verPila) {
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Node[] stackNode = new Node[100];
        boolean[] stackPrint = new boolean[100];
        int top = -1;

        top = top + 1;
        stackNode[top] = root;
        stackPrint[top] = false;

        while (top >= 0) {
            if (verPila) {
                printStack(stackNode, stackPrint, top);
            }
            Node current = stackNode[top];
            boolean toPrint = stackPrint[top];
            top = top - 1;

            if (toPrint) {
                System.out.print(current.getKey() + " ");
            } else {
                top = stackRight(current, stackNode, stackPrint, top);
                top = stackLeft(current, stackNode, stackPrint, top);
                top = stackHead(current, stackNode, stackPrint, top);
            }
        }
        System.out.println();
        linea();
    }

    public void printTraverseInorder(boolean verPila) {
        
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Node[] stackNode = new Node[100];
        boolean[] stackPrint = new boolean[100];
        int top = -1;

        top = top + 1;
        stackNode[top] = root;
        stackPrint[top] = false;

        while (top >= 0) {
            if (verPila) {
                printStack(stackNode, stackPrint, top);
            }

            Node current = stackNode[top];
            boolean toPrint = stackPrint[top];
            top = top - 1;

            if (toPrint) {
                System.out.print(current.getKey() + " ");
            } else {
                top = stackRight(current, stackNode, stackPrint, top);
                top = stackHead(current, stackNode, stackPrint, top);
                top = stackLeft(current, stackNode, stackPrint, top);
            }
        }
        System.out.println();
        linea();
    }

    public void printTraversePostorder(boolean verPila) {
        if (root == null) {
            System.out.println("Árbol vacío");
            return;
        }

        Node[] stackNode = new Node[100];
        boolean[] stackPrint = new boolean[100];
        int top = -1;

        top = top + 1;
        stackNode[top] = root;
        stackPrint[top] = false;

        while (top >= 0) {
            if (verPila) {
                printStack(stackNode, stackPrint, top);
            }

            Node current = stackNode[top];
            boolean toPrint = stackPrint[top];
            top = top - 1;

            if (toPrint) {
                System.out.print(current.getKey() + " ");
            } else {
                top = stackHead(current, stackNode, stackPrint, top);
                top = stackRight(current, stackNode, stackPrint, top);
                top = stackLeft(current, stackNode, stackPrint, top);
            }
        }
        System.out.println();
        linea();
    }

    private int stackHead(Node current, Node[] stackNode, boolean[] stackPrint, int top) {
        top = top + 1;
        stackNode[top] = current;
        stackPrint[top] = true;
        return top;
    }

    private int stackLeft(Node current, Node[] stackNode, boolean[] stackPrint, int top) {
        if (current.hasLeftChild()) {
            top = top + 1;
            stackNode[top] = current.getLeft();
            stackPrint[top] = false;
        }
        return top;
    }

    private int stackRight(Node current, Node[] stackNode, boolean[] stackPrint, int top) {
        if (current.hasRightChild()) {
            top = top + 1;
            stackNode[top] = current.getRight();
            stackPrint[top] = false;
        }
        return top;

    }

    private void printStack(Node[] stackNode, boolean[] stackPrint, int top) {
        System.out.print("[");
        for (int i = 0; i <= top; i++) {
            System.out.print((stackPrint[i] ? "P:" : "V:") + stackNode[i].getKey());
            if (i < top) {
                System.out.print(" ");
            }
        }
        System.out.println("]");
    }

    private void linea() {
        System.out.println("==".repeat(10));
    }
}