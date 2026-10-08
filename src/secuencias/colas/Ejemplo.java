package colas;

class Ejemplo {
    public static void main(String[] args) {

        Queue nephews = new Queue();

        System.out.println("Esta vacia? " + nephews.isEmpty());

        nephews.enqueue("Juanito");
        nephews.enqueue("Pepito");
        nephews.enqueue("Anita");

        System.out.println("Esta vacia? " + nephews.isEmpty());
        System.out.println("Tamaño: " + nephews.size());
        System.out.println("Primero: " + nephews.peek());

        System.out.println("Atiendo a: " + nephews.dequeue());
        System.out.println("Tamaño: " + nephews.size());

        nephews.enqueue("Luisito");
        System.out.println("Primero: " + nephews.peek());

        viewAndEmpty(nephews);

        System.out.println("Esta vacia? " + nephews.isEmpty());
        System.out.println("Atiendo a: " + nephews.dequeue());
    }

    static void viewAndEmpty(Queue filaDeSobrinos) {
        System.out.println("LA FILA" + "-".repeat(18));
        while (!filaDeSobrinos.isEmpty()) {
            System.out.println(filaDeSobrinos.dequeue());
        }
        System.out.println("-".repeat(25));
    }
}