# Nodo dummy: ejemplo

Código de apoyo de [Nodo dummy](/temario/999-otrosTemas/nodoDummy.md). Cada operación aparece dos veces, con y sin nodo dummy, para comparar las dos versiones con la misma entrada.

<div align=center>

|Fichero|Contenido|
|-|-|
|[Nodo.java](Nodo.java)|Nodo con un `int` y la referencia al siguiente.|
|[ListaEnlazada.java](ListaEnlazada.java)|`insertarEnPosicion` / `insertarEnPosicionSinDummy` y `eliminarPorValor` / `eliminarPorValorSinDummy`.|
|[Ejemplo.java](Ejemplo.java)|Ejecuta las dos versiones sobre los casos límite: apariciones seguidas en la cabeza, lista que se vacía entera, lista vacía, inserción en la posición 0 y más allá del final.|

</div>

## Qué observar

- Las versiones sin dummy necesitan un tratamiento propio de la cabeza: un `if` en la inserción y un `while` previo en la eliminación. Las versiones con dummy tienen un único bucle.
- En `eliminarPorValor`, `actual` solo avanza cuando no se borra. Por eso se eliminan correctamente las apariciones consecutivas.

## Ejecución

Desde `src/`:

```bash
javac listas/nodoDummy/*.java
java listas.nodoDummy.Ejemplo
```
