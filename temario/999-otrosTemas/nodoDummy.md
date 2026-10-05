# Nodo dummy

## ¿Por qué?

Al insertar o eliminar en una lista enlazada, la cabeza requiere un trato especial. Si el cambio ocurre en medio de la lista, basta con modificar la referencia `siguiente` del nodo anterior; si ocurre al principio, no existe nodo anterior y hay que actualizar la referencia `cabeza` de la lista. El resultado son bloques `if` o bucles dedicados exclusivamente al primer elemento, que duplican la lógica del caso general y son una fuente habitual de errores.

El nodo dummy elimina ese caso límite: hace que el primer nodo real tenga un nodo anterior, como cualquier otro nodo de la lista.

## ¿Qué?

Un nodo dummy es un nodo ficticio que se coloca justo antes del primer elemento real de la lista durante una operación.

- ***Su valor no importa***: se instancia con un dato de relleno (por ejemplo `-1`) que el algoritmo nunca evalúa, porque siempre compara `actual.siguiente.dato`, nunca `actual.dato`.
- ***Su referencia es la clave***: su campo `siguiente` apunta a la cabeza original de la lista.
- ***Es local a la operación***: no forma parte de la estructura. Al terminar el método queda inalcanzable y pasa a ser elegible para el recolector de basura.

```
Antes:            cabeza -> [1] -> [1] -> [2] -> null

Con dummy:  [dummy] -> [1] -> [1] -> [2] -> null
               ^
             actual
```

Todos los nodos reales tienen ahora un predecesor, así que borrar el primer `1` se hace igual que borrar cualquier otro: `actual.siguiente = actual.siguiente.siguiente`.

**Coste**: O(1) de memoria adicional (un nodo) y la misma complejidad temporal que la versión sin dummy.

## ¿Para qué?

|Un único camino de ejecución|Acceso seguro a `actual.siguiente`|Operaciones donde se aplica|
|-|-|-|
|Desaparecen el `if (cabeza == null)` y el bucle específico de la cabeza: el caso general cubre todos los casos.|`actual` arranca en el dummy y nunca es `null`, así que se puede leer `actual.siguiente` sin comprobarlo antes.|Eliminación por valor, inserción en una posición, fusión de listas ordenadas (como en Merge Sort) o construcción de una lista nueva nodo a nodo.|

## ¿Cómo?

El patrón tiene cinco pasos:

1. **Instanciación**: se crea el nodo ficticio (`Nodo dummy = new Nodo(-1);`).
1. **Anclaje**: se conecta a la cabeza actual (`dummy.siguiente = cabeza;`).
1. **Recorrido**: un puntero auxiliar `actual` arranca en el dummy y evalúa siempre `actual.siguiente`.
1. **Modificación**: las inserciones o eliminaciones se hacen igual en cualquier posición, cabeza incluida.
1. **Redirección**: antes de terminar, se actualiza la cabeza con lo que haya quedado en `dummy.siguiente` (`cabeza = dummy.siguiente;`).

<div align=center>

|Con dummy|Sin dummy|
|-|-|
|<pre>Nodo dummy = new Nodo(-1);<br>dummy.siguiente = cabeza;<br>Nodo actual = dummy;<br>while (actual.siguiente != null) {<br>    if (actual.siguiente.dato == valor) {<br>        actual.siguiente = actual.siguiente.siguiente;<br>    } else {<br>        actual = actual.siguiente;<br>    }<br>}<br>cabeza = dummy.siguiente;</pre>|<pre>while (cabeza != null && cabeza.dato == valor) {<br>    cabeza = cabeza.siguiente;<br>}<br>if (cabeza == null) {<br>    return;<br>}<br>Nodo actual = cabeza;<br>while (actual.siguiente != null) {<br>    if (actual.siguiente.dato == valor) {<br>        actual.siguiente = actual.siguiente.siguiente;<br>    } else {<br>        actual = actual.siguiente;<br>    }<br>}</pre>|

</div>

> *[Ver ejemplo completo](/src/listas/nodoDummy/README.md)*: eliminación por valor e inserción en posición, con y sin dummy, ejecutadas sobre los mismos casos límite.
