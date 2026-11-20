# Árboles > rotaciones > rotación a la derecha

## ¿Qué?

La rotación a la derecha se aplica cuando un subárbol está desbalanceado hacia la izquierda: el hijo izquierdo es el que hace pesada la rama. Es una operación local entre dos nodos adyacentes, `Q` (padre) y `P` (su hijo izquierdo), que invierte su relación jerárquica y preserva el orden inorden.

```
      Q             P
     / \           / \
    P   C   ==>   A   Q
   / \               / \
  A   B             B   C
```

**Algoritmo:** dado un nodo `Q` y su hijo izquierdo `P`:

1. `P` asciende para ocupar la posición de `Q`.
2. `Q` desciende para convertirse en el hijo derecho de `P`.
3. El subárbol `B` (antes hijo derecho de `P`) se reasigna como hijo izquierdo de `Q`.

Los subárboles `A` y `C` no cambian: solo se desplazan junto con el nodo del que cuelgan. Cualquiera de los tres (`A`, `B`, `C`) puede ser vacío.

**Referencia externa.** Antes de enlazar, hay que recordar quién apuntaba a `Q`:

- Si `Q` era la raíz, `root` pasa a apuntar a `P`.
- Si `Q` era hijo izquierdo de un nodo `G`, entonces `G.izquierdo = P`.
- Si `Q` era hijo derecho de un nodo `G`, entonces `G.derecho = P`.

## Ejemplo: de la lista a un árbol balanceado de 8 nodos

**Punto de partida:** el árbol degenerado que resulta de insertar 8, 7, 6, ..., 1 en orden descendente (altura 8). Es el caso espejo del que degenera a la derecha: toda la estructura cuelga a la izquierda, por lo que la rotación a la derecha es la operación natural.

```
              8
             /
            7
           /
          6
         /
        5
       /
      4
     /
    3
   /
  2
 /
1
```

**Objetivo:** un árbol balanceado de altura 4. Es la imagen especular del árbol objetivo de la [rotación a la izquierda](rotacion-izquierda.md).

```
        5
      /   \
     3     7
    / \   / \
   2   4 6   8
  /
 1
```

**Estrategia:** se rota sobre nodos alternos para ir "subiendo" a un nodo de cada dos (el 3, el 5, el 7) y, al final, sobre el 7 para llevar el 5 a la raíz. Se emplea únicamente rotación a la derecha.

### Paso 1. Rotación a la derecha sobre el 4

`Q = 4`, `P = 3`, `A` = subárbol {2, 1}, `B` vacío, `C` vacío.

```
              8
             /
            7
           /
          6
         /
        5
       /
      3
     / \
    2   4
   /
  1
```

El 3 ocupa el lugar del 4, que era hijo izquierdo del 5; por tanto `5.izquierdo = 3`. Altura: 7.

### Paso 2. Rotación a la derecha sobre el 6

`Q = 6`, `P = 5`, `A` = subárbol con raíz 3, `B` vacío, `C` vacío.

```
            8
           /
          7
         /
        5
       / \
      3   6
     / \
    2   4
   /
  1
```

El 5 ocupa el lugar del 6, que era hijo izquierdo del 7; por tanto `7.izquierdo = 5`. Altura: 6.

### Paso 3. Rotación a la derecha sobre el 8

`Q = 8`, `P = 7`, `A` = subárbol con raíz 5, `B` vacío, `C` vacío.

```
        7
       / \
      5   8
     / \
    3   6
   / \
  2   4
 /
1
```

El 8 era la raíz, de modo que `root = 7`. Altura: 5.

### Paso 4. Rotación a la derecha sobre el 7

`Q = 7`, `P = 5`, `A` = subárbol con raíz 3, `B` = {6}, `C` = {8}.

```
        5
      /   \
     3     7
    / \   / \
   2   4 6   8
  /
 1
```

Es el único paso en el que `B` no es vacío: el 6, que colgaba a la derecha del 5, pasa a ser hijo izquierdo del 7. El 7 era la raíz, por lo que `root = 5`. Altura: 4. Se alcanza el árbol objetivo.

### Resumen

| Paso | Pivote `Q` | Asciende `P` | `B` reasignado | Altura resultante |
|------|-----------|--------------|----------------|-------------------|
| 0    | -         | -            | -              | 8 |
| 1    | 4         | 3            | vacío          | 7 |
| 2    | 6         | 5            | vacío          | 6 |
| 3    | 8         | 7            | vacío          | 5 |
| 4    | 7         | 5            | {6}            | 4 |

## Observaciones

1. **El orden inorden no cambia en ningún paso.** En las cinco figuras, el recorrido inorden produce 1, 2, 3, 4, 5, 6, 7, 8. Es la propiedad que garantiza que el árbol sigue siendo un BST válido.
2. **Es el espejo exacto de la rotación a la izquierda.** Si se sustituye cada clave `k` por `9 - k` y se intercambian izquierda y derecha, la secuencia de este ejemplo se convierte en la del otro. Conviene comprobarlo con ambos documentos abiertos.
3. **El orden de los pivotes importa.** Rotar sobre el 4, luego el 6 y luego el 8 deja a 3, 5 y 7 como nodos ascendidos; el último paso, sobre el 7, es el que reparte los subárboles `A`, `B` y `C` de forma equilibrada.
4. **Dos de las cuatro rotaciones actualizan `root`; las otras dos actualizan el hijo izquierdo de un nodo intermedio.** Olvidar esta referencia externa es el error más habitual al implementar la operación.

## ¿Y ahora qué?

- [Rotación a la izquierda](rotacion-izquierda.md): la operación simétrica, aplicada al caso que degenera a la derecha.
- [Árboles AVL](avl.md): automatizan la decisión de qué rotación aplicar y sobre qué nodo.
