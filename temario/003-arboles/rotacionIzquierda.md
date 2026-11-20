# Árboles > rotaciones > rotación a la izquierda

## ¿Qué?

La rotación a la izquierda se aplica cuando un subárbol está desbalanceado hacia la derecha: el hijo derecho es el que hace pesada la rama. Es una operación local entre dos nodos adyacentes, `P` (padre) y `Q` (su hijo derecho), que invierte su relación jerárquica y preserva el orden inorden.

```
    P                 Q
   / \               / \
  A   Q     ==>     P   C
     / \           / \
    B   C         A   B
```

**Algoritmo:** dado un nodo `P` y su hijo derecho `Q`:

1. `Q` asciende para ocupar la posición de `P`.
2. `P` desciende para convertirse en el hijo izquierdo de `Q`.
3. El subárbol `B` (antes hijo izquierdo de `Q`) se reasigna como hijo derecho de `P`.

Los subárboles `A` y `C` no cambian: solo se desplazan junto con el nodo del que cuelgan. Cualquiera de los tres (`A`, `B`, `C`) puede ser vacío.

**Referencia externa.** Antes de enlazar, hay que recordar quién apuntaba a `P`:

- Si `P` era la raíz, `root` pasa a apuntar a `Q`.
- Si `P` era hijo izquierdo de un nodo `G`, entonces `G.izquierdo = Q`.
- Si `P` era hijo derecho de un nodo `G`, entonces `G.derecho = Q`.

## Ejemplo: de la lista a un árbol balanceado de 8 nodos

**Punto de partida:** el árbol degenerado que resulta de insertar 1, 2, 3, ..., 8 en orden (altura 8). Toda la estructura cuelga a la derecha, por lo que la rotación a la izquierda es la operación natural.

```
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6
           \
            7
             \
              8
```

**Objetivo:** el árbol balanceado de altura 4.

```
        4
      /   \
     2     6
    / \   / \
   1   3 5   7
              \
               8
```

**Estrategia:** se rota sobre nodos alternos para ir "subiendo" a un nodo de cada dos (el 6, el 4, el 2) y, al final, sobre el 2 para llevar el 4 a la raíz. Se emplea únicamente rotación a la izquierda.

### Paso 1. Rotación a la izquierda sobre el 5

`P = 5`, `Q = 6`, `A` vacío, `B` vacío, `C` = subárbol {7, 8}.

```
1
 \
  2
   \
    3
     \
      4
       \
        6
       / \
      5   7
           \
            8
```

El 6 ocupa el lugar del 5, que era hijo derecho del 4; por tanto `4.derecho = 6`. Altura: 7.

### Paso 2. Rotación a la izquierda sobre el 3

`P = 3`, `Q = 4`, `A` vacío, `B` vacío, `C` = subárbol con raíz 6.

```
1
 \
  2
   \
    4
   / \
  3   6
     / \
    5   7
         \
          8
```

El 4 ocupa el lugar del 3, que era hijo derecho del 2; por tanto `2.derecho = 4`. Altura: 6.

### Paso 3. Rotación a la izquierda sobre el 1

`P = 1`, `Q = 2`, `A` vacío, `B` vacío, `C` = subárbol con raíz 4.

```
    2
   / \
  1   4
     / \
    3   6
       / \
      5   7
           \
            8
```

El 1 era la raíz, de modo que `root = 2`. Altura: 5.

### Paso 4. Rotación a la izquierda sobre el 2

`P = 2`, `Q = 4`, `A` = {1}, `B` = {3}, `C` = subárbol con raíz 6.

```
        4
      /   \
     2     6
    / \   / \
   1   3 5   7
              \
               8
```

Es el único paso en el que `B` no es vacío: el 3, que colgaba a la izquierda del 4, pasa a ser hijo derecho del 2. El 2 era la raíz, por lo que `root = 4`. Altura: 4. Se alcanza el árbol objetivo.

### Resumen

| Paso | Pivote `P` | Asciende `Q` | `B` reasignado | Altura resultante |
|------|-----------|--------------|----------------|-------------------|
| 0    | -         | -            | -              | 8 |
| 1    | 5         | 6            | vacío          | 7 |
| 2    | 3         | 4            | vacío          | 6 |
| 3    | 1         | 2            | vacío          | 5 |
| 4    | 2         | 4            | {3}            | 4 |

## Observaciones

1. **El orden inorden no cambia en ningún paso.** En las cinco figuras, el recorrido inorden produce 1, 2, 3, 4, 5, 6, 7, 8. Es la propiedad que garantiza que el árbol sigue siendo un BST válido.
2. **La altura baja en cada paso.** En este ejemplo cada rotación acorta la rama más larga; no siempre ocurre así (véase el ejemplo de la rotación a la derecha, en el que también hay pasos intermedios con efecto local).
3. **El orden de los pivotes importa.** Rotar primero sobre el 5, luego el 3 y luego el 1 deja a 6, 4 y 2 como nodos ascendidos; el último paso, sobre el 2, es el que reparte los subárboles `A`, `B` y `C` de forma equilibrada.
4. **Dos de las cuatro rotaciones actualizan `root`; las otras dos actualizan el hijo derecho de un nodo intermedio.** Olvidar esta referencia externa es el error más habitual al implementar la operación.

## ¿Y ahora qué?

- [Rotación a la derecha](rotacion-derecha.md): la operación simétrica, aplicada al caso espejo.
- [Árboles AVL](avl.md): automatizan la decisión de qué rotación aplicar y sobre qué nodo.
