class Sneaker {
    String modelo;
    int talla;
    String color;
    double precio;
    String imagen;
    String seccion;
    int stock;

    // Constructor que inicializa las propiedades del Sneaker.
    public Sneaker(String modelo, int talla, String color, double precio, String imagen, String seccion, int stock) {
        this.modelo = modelo;
        this.talla = talla;
        this.color = color;
        this.precio = precio;
        this.imagen = imagen;
        this.seccion = seccion;
        this.stock = stock;
    }
}
