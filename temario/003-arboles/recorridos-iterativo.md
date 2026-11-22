### Iterativa: la pila de notas

Para recorrer el árbol sin recursión hace falta que la pila recuerde **qué falta por hacer**. Para eso cada entrada de la pila lleva dos datos: un nodo y una **nota** que dice qué hacer con él.

<div align=center>

|Nota|En el código|Significa|
|:-:|:-:|-|
|**V**|`stackPrint[i] == false`|"ver": este nodo aún no se ha expandido, hay que apilar sus hijos|
|**P**|`stackPrint[i] == true`|"en breve voy a imprimir": ya llegó su turno de actuar, solo falta imprimirlo|

</div>

#### El bucle: siempre lo mismo

1. Sacar la entrada de arriba (el **tope**).
2. Si la nota es **P**: imprimir el nodo.
3. Si la nota es **V**: apilar tres cosas (ver abajo) en lugar de esta entrada.
4. Repetir mientras la pila no esté vacía (`top >= 0`).

La pila arranca con la raíz y la nota **V**.

#### Las tres zonas del código

Cuando se saca una entrada **V**, el código tiene tres zonas, cada una apila una entrada:

<div align=center>

|Zona|Qué apila|Condición|Nota|
|:-:|-|-|:-:|
|**D**|el hijo derecho|solo si existe|V|
|**C**|la cabeza (el propio nodo)|siempre|P|
|**I**|el hijo izquierdo|solo si existe|V|

</div>

```java
if (current.hasRightChild()) {          // zona D
    top = top + 1;
    stackNode[top] = current.getRight();
    stackPrint[top] = false;            // V
}

top = top + 1;                          // zona C
stackNode[top] = current;
stackPrint[top] = true;                 // P

if (current.hasLeftChild()) {           // zona I
    top = top + 1;
    stackNode[top] = current.getLeft();
    stackPrint[top] = false;            // V
}
```

Una entrada **V** desaparece y deja en su lugar hasta tres. La figura muestra qué ocurre al sacar `V:4` (cuyo hijo izquierdo es 2 y cuyo hijo derecho es 6) en cada recorrido. El tope está arriba en el dibujo y a la derecha en la salida del programa:

```
4
+-- I: 2
|   +-- I: 1
|   \-- D: 3
\-- D: 6
    +-- I: 5
    \-- D: 7
```

```
 saca               preorden           inorden            postorden
 +------+           +------+           +------+           +------+
 | V:4  | <- tope   | P:4  | <- tope   | V:2  | <- tope   | V:2  | <- tope
 +------+           | V:2  |           | P:4  |           | V:6  |
                    | V:6  |           | V:6  |           | P:4  |
                    +------+           +------+           +------+
                    sale: C I D        sale: I C D        sale: I D C
```

Leyenda: **C** es la cabeza (`P:4`, "en breve voy a imprimir"), **I** es el hijo izquierdo (`V:2`) y **D** es el hijo derecho (`V:6`). Las tres pilas contienen exactamente las mismas tres entradas: solo cambia su orden.

**El orden en que se escriben las tres zonas es lo único que cambia entre los tres recorridos.** Como la pila saca primero lo último que entró, el orden de escritura es el inverso al orden en que se ejecutan:

<div align=center>

|Recorrido|Se escribe en el código (de arriba abajo)|Sale de la pila en este orden|
|-|:-:|:-:|
|Preorden|D, I, C|C, I, D|
|Inorden|D, C, I|I, C, D|
|Postorden|C, D, I|I, D, C|

</div>

En preorden la cabeza queda arriba del todo y sale al instante. En inorden queda en medio: antes sale todo lo de la izquierda. En postorden queda al fondo: salen antes los dos subárboles.

#### Verlo funcionar

El código está en [ejemplo006](/src/arboles/ejemplo006/) y cada vuelta del bucle llama a `printStack`, que imprime la pila (tope a la derecha). Insertando `4, 2, 6, 1, 3, 5, 7` se obtiene este árbol:

```
4
+-- I: 2
|   +-- I: 1
|   \-- D: 3
\-- D: 6
    +-- I: 5
    \-- D: 7
```

Traza de `printTraverseInorder()`:

|Paso|Pila antes de sacar (tope a la derecha)|Saca|Qué ocurre|
|:-:|-|:-:|-|
|1|`[V:4]`|V:4|apila V:6, P:4, V:2|
|2|`[V:6 P:4 V:2]`|V:2|apila V:3, P:2, V:1|
|3|`[V:6 P:4 V:3 P:2 V:1]`|V:1|es hoja: apila solo P:1|
|4|`[V:6 P:4 V:3 P:2 P:1]`|P:1|**imprime 1**|
|5|`[V:6 P:4 V:3 P:2]`|P:2|**imprime 2**|
|6|`[V:6 P:4 V:3]`|V:3|es hoja: apila solo P:3|
|7|`[V:6 P:4 P:3]`|P:3|**imprime 3**|
|8|`[V:6 P:4]`|P:4|**imprime 4**|
|9|`[V:6]`|V:6|apila V:7, P:6, V:5|
|10|`[V:7 P:6 V:5]`|V:5|es hoja: apila solo P:5|
|11|`[V:7 P:6 P:5]`|P:5|**imprime 5**|
|12|`[V:7 P:6]`|P:6|**imprime 6**|
|13|`[V:7]`|V:7|es hoja: apila solo P:7|
|14|`[P:7]`|P:7|**imprime 7**|

Salida: `1 2 3 4 5 6 7`. Obsérvese que cada **P** espera en la pila exactamente hasta que se ha terminado todo lo que debía ir antes que él.

En la consola, el programa muestra en cada línea lo impreso en el paso anterior seguido de la pila actual (por ejemplo `1 [V:6 P:4 V:3 P:2]` es el paso 5).

#### Práctica

1. **Predecir antes de ejecutar.** Sin consultar el programa, completar en papel una tabla como la de arriba para `printTraversePostorder()` con el mismo árbol (columnas: pila antes de sacar, saca, qué ocurre). Después ejecutar el programa y comparar paso a paso.
2. **Cambiar una zona.** En el código de preorden, reordenar las tres zonas para que se escriban C, I, D. Antes de ejecutar, predecir qué recorrido sale. ¿Es alguno de los tres conocidos?

> ***2Think:*** En preorden la nota **P** de la cabeza se saca justo después de apilarla. ¿Qué diferencia hay entre "imprimir al sacar con nota P" e "imprimir directamente al sacar con nota V"? ¿Por qué en inorden y postorden esa diferencia sí es imprescindible?
>
> ***2Think:*** ¿Cuántas entradas llega a tener la pila en cada recorrido si el árbol es una lista (insertando `1, 2, 3, 4, 5, 6, 7` en ese orden)? ¿Y si se insertan `7, 6, 5, 4, 3, 2, 1`? ¿Qué relación tiene con el tamaño del arreglo de la pila?
