public class ArbolBinarioBusqueda {
    private class Nodo {
        Sneaker datos;  // Datos almacenados en el nodo (un Sneaker en este caso).
        Nodo izquierda;  // Referencia al subárbol izquierdo.
        Nodo derecha;    // Referencia al subárbol derecho.

        // Constructor que crea un nuevo nodo con los datos proporcionados.
        Nodo(Sneaker sneaker) {
            this.datos = sneaker;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    private Nodo raiz;  // Nodo raíz del árbol.

    // Constructor que inicializa un árbol binario de búsqueda vacío.
    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    public void insertar(Sneaker sneaker) {
        raiz = insertar(raiz, sneaker);
    }

    // Método privado para la inserción recursiva de un Sneaker en el árbol.
    private Nodo insertar(Nodo raiz, Sneaker sneaker) {
        // Si la raíz es nula, se crea un nuevo nodo con el Sneaker y se devuelve.
        if (raiz == null) {
            return new Nodo(sneaker);
        }

        // Compara el modelo del Sneaker para decidir en qué subárbol insertar.
        if (sneaker.modelo.compareTo(raiz.datos.modelo) < 0) {
            raiz.izquierda = insertar(raiz.izquierda, sneaker); // Inserta en el subárbol izquierdo.
        } else if (sneaker.modelo.compareTo(raiz.datos.modelo) > 0) {
            raiz.derecha = insertar(raiz.derecha, sneaker);    // Inserta en el subárbol derecho.
        } else {
            // Si los modelos son iguales, compara por talla para decidir en qué subárbol insertar.
            if (sneaker.talla < raiz.datos.talla) {
                raiz.izquierda = insertar(raiz.izquierda, sneaker); // Inserta en el subárbol izquierdo.
            } else if (sneaker.talla > raiz.datos.talla) {
                raiz.derecha = insertar(raiz.derecha, sneaker);    // Inserta en el subárbol derecho.
            } else {
                // Manejar el caso de sneakers con el mismo modelo y talla (si es necesario).
            }
        }

        return raiz;
    }

    public Sneaker buscar(String modelo, int talla) {
        return buscar(raiz, modelo, talla);
    }

    // Método privado para la búsqueda recursiva de un Sneaker en el árbol.
    private Sneaker buscar(Nodo raiz, String modelo, int talla) {
        // Si la raíz es nula, el Sneaker no está en el árbol.
        if (raiz == null) {
            return null;
        }

        Sneaker sneakerActual = raiz.datos;

        // Compara el modelo para decidir en qué subárbol buscar.
        if (modelo.compareTo(sneakerActual.modelo) < 0) {
            return buscar(raiz.izquierda, modelo, talla);  // Busca en el subárbol izquierdo.
        } else if (modelo.compareTo(sneakerActual.modelo) > 0) {
            return buscar(raiz.derecha, modelo, talla);    // Busca en el subárbol derecho.
        } else {
            // Si los modelos son iguales, compara por talla para decidir en qué subárbol buscar.
            if (talla == sneakerActual.talla) {
                return sneakerActual;  // Se encontró el Sneaker.
            } else if (talla < sneakerActual.talla) {
                return buscar(raiz.izquierda, modelo, talla);  // Busca en el subárbol izquierdo.
            } else {
                return buscar(raiz.derecha, modelo, talla);    // Busca en el subárbol derecho.
            }
        }
    }

    public void recorridoEnOrden() {
        recorridoEnOrden(raiz);
    }

    // Método privado para el recorrido en orden recursivo.
    private void recorridoEnOrden(Nodo raiz) {
        // Si la raíz no es nula, realiza el recorrido en orden (izquierda, raíz, derecha).
        if (raiz != null) {
            recorridoEnOrden(raiz.izquierda);
            System.out.println(raiz.datos);  // Imprime el Sneaker actual.
            recorridoEnOrden(raiz.derecha);
        }
    }
}
