package pilas;

class Ejemplo {
    public static void main(String[] args) {

        Stack plates = new Stack();

        System.out.println("Esta vacia? " + plates.isEmpty());

        plates.push("Plato azul");
        plates.push("Plato rojo");
        plates.push("Plato verde");

        System.out.println("Esta vacia? " + plates.isEmpty());
        System.out.println("Tamaño: " + plates.size());
        System.out.println("Cima: " + plates.peek());

        System.out.println("Retiro: " + plates.pop());
        System.out.println("Tamaño: " + plates.size());

        plates.push("Plato blanco");
        System.out.println("Cima: " + plates.peek());

        viewAndEmpty(plates);

        System.out.println("Esta vacia? " + plates.isEmpty());
        System.out.println("Retiro: " + plates.pop());
    }

    static void viewAndEmpty(Stack pilaDePlatos) {
        System.out.println("LA PILA" + "-".repeat(18));
        while (!pilaDePlatos.isEmpty()) {
            System.out.println(pilaDePlatos.pop());
        }
        System.out.println("-".repeat(25));
    }
}
