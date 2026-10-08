package listas.nodoDummy;

public class Ejemplo {
    public static void main(String[] args) {
        System.out.println("== Eliminar por valor (1)");
        probarEliminar(new int[] { 1, 1, 2, 1, 3 }, 1);
        probarEliminar(new int[] { 1, 1, 1 }, 1);
        probarEliminar(new int[] {}, 1);

        System.out.println();
        System.out.println("== Insertar 9 en posición");
        probarInsertar(new int[] { 1, 2, 3 }, 0);
        probarInsertar(new int[] { 1, 2, 3 }, 2);
        probarInsertar(new int[] { 1, 2, 3 }, 10);
        probarInsertar(new int[] {}, 0);
    }

    static void probarEliminar(int[] datos, int valor) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("original:  ");
        conDummy.imprimirLista();

        conDummy.eliminarPorValor(valor);
        sinDummy.eliminarPorValorSinDummy(valor);

        System.out.print("con dummy: ");
        conDummy.imprimirLista();
        System.out.print("sin dummy: ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static void probarInsertar(int[] datos, int posicion) {
        ListaEnlazada conDummy = crear(datos);
        ListaEnlazada sinDummy = crear(datos);

        System.out.print("original:  ");
        conDummy.imprimirLista();
        System.out.println("posición:  " + posicion);

        conDummy.insertarEnPosicion(posicion, 9);
        sinDummy.insertarEnPosicionSinDummy(posicion, 9);

        System.out.print("con dummy: ");
        conDummy.imprimirLista();
        System.out.print("sin dummy: ");
        sinDummy.imprimirLista();
        System.out.println();
    }

    static ListaEnlazada crear(int[] datos) {
        ListaEnlazada lista = new ListaEnlazada();
        for (int i = 0; i < datos.length; i++) {
            lista.insertarEnPosicion(i, datos[i]);
        }
        return lista;
    }
}
