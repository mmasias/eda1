package ejemplo105;

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
                if (key < current.getKey()) {
                    if (current.hasLeftChild()) {
                        current = current.getLeft();
                    } else {
                        current.attachLeftChild(new Node(key));
                        inserted = true;
                    }
                } else if (key > current.getKey()) {
                    if (current.hasRightChild()) {
                        current = current.getRight();
                    } else {
                        current.attachRightChild(new Node(key));
                        inserted = true;
                    }
                } else {
                    inserted = true;
                }
            }
        }
    }

    public void printTree() {
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

    public void rotateRight(int key) {
        if (root != null) {

            Node parent = null;
            Node current = root;

            while (current != null && current.getKey() != key) {
                parent = current;
                if (key < current.getKey()) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }

            if (current == null || !current.hasLeftChild()) {
                return;
            }

            Node newRoot = current.getLeft();
            Node grandChild = newRoot.getRight();

            current.detachLeftChild();
            if (grandChild != null) {
                current.attachLeftChild(grandChild);
            }

            if (newRoot.hasRightChild()) {
                newRoot.detachRightChild();
            }
            newRoot.attachRightChild(current);

            if (parent == null) {
                root = newRoot;
            } else {
                if (parent.getLeft() == current) {
                    parent.detachLeftChild();
                    parent.attachLeftChild(newRoot);
                } else {
                    parent.detachRightChild();
                    parent.attachRightChild(newRoot);
                }
            }
        }
    }

    public void rotateLeft(int key) {
        if (root != null) {
            Node parent = null;
            Node current = root;

            while (current != null && current.getKey() != key) {
                parent = current;
                if (key < current.getKey()) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }

            if (current == null || !current.hasRightChild()) {
                return;
            }

            Node newRoot = current.getRight();
            Node grandChild = newRoot.getLeft();

            current.detachRightChild();
            if (grandChild != null) {
                current.attachRightChild(grandChild);
            }

            if (newRoot.hasLeftChild()) {
                newRoot.detachLeftChild();
            }
            newRoot.attachLeftChild(current);

            if (parent == null) {
                root = newRoot;
            } else {
                if (parent.getLeft() == current) {
                    parent.detachLeftChild();
                    parent.attachLeftChild(newRoot);
                } else {
                    parent.detachRightChild();
                    parent.attachRightChild(newRoot);
                }
            }
        }
    }

}
