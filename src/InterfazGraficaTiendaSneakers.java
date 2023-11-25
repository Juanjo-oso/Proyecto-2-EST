import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class InterfazGraficaTiendaSneakers extends JFrame {
    private ArbolBinarioBusqueda abb;
    private JTextField campoModeloBusqueda;
    private JTextField campoTallaBusqueda;
    private JTextArea areaResultado;
    private JLabel etiquetaImagen;

    private JTextField campoModelo;
    private JTextField campoTalla;
    private JTextField campoColor;
    private JTextField campoPrecio;
    private JTextField campoImagen;
    private JTextField campoSeccion;
    private JTextField campoStock;

    // Constructor que inicializa la interfaz gráfica y el árbol binario de búsqueda.
    public InterfazGraficaTiendaSneakers() {
        abb = new ArbolBinarioBusqueda();

        setTitle("Tienda de Sneakers");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(255, 255, 255));

        // Título
        JPanel panelTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitulo.setBackground(new Color(52, 152, 219));
        JLabel etiquetaTitulo = new JLabel("Tienda de Sneakers");
        etiquetaTitulo.setFont(new Font("Arial", Font.BOLD, 36));
        etiquetaTitulo.setForeground(Color.WHITE);
        panelTitulo.add(etiquetaTitulo);

        // Ajustar espacio entre el título y el área de texto
        panelTitulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        // Panel de búsqueda
        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        campoModeloBusqueda = new JTextField(20);
        campoTallaBusqueda = new JTextField(10);
        JButton botonBusqueda = new JButton("Buscar");
        areaResultado = new JTextArea(10, 40);
        areaResultado.setEditable(false);
        etiquetaImagen = new JLabel();
        panelBusqueda.add(new JLabel("Modelo:"));
        panelBusqueda.add(campoModeloBusqueda);
        panelBusqueda.add(new JLabel("Talla:"));
        panelBusqueda.add(campoTallaBusqueda);
        panelBusqueda.add(botonBusqueda);
        panelBusqueda.add(new JScrollPane(areaResultado));
        panelBusqueda.add(etiquetaImagen);

        botonBusqueda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarSneaker();
            }
        });

        // Panel de formulario
        JPanel panelFormulario = new JPanel(new GridLayout(15, 1, 5, 5));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 0));

        campoModelo = new JTextField(20);
        campoTalla = new JTextField(10);
        campoColor = new JTextField(20);
        campoPrecio = new JTextField(20);
        campoImagen = new JTextField(20);
        campoSeccion = new JTextField(20);
        campoStock = new JTextField(10);

        JButton botonElegirImagen = new JButton("Elegir Imagen");
        botonElegirImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elegirImagen();
            }
        });

        panelFormulario.add(new JLabel("Modelo:"));
        panelFormulario.add(campoModelo);
        panelFormulario.add(new JLabel("Talla:"));
        panelFormulario.add(campoTalla);
        panelFormulario.add(new JLabel("Color:"));
        panelFormulario.add(campoColor);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(campoPrecio);
        panelFormulario.add(new JLabel("Imagen:"));
        panelFormulario.add(campoImagen);
        panelFormulario.add(botonElegirImagen);
        panelFormulario.add(new JLabel("Sección:"));
        panelFormulario.add(campoSeccion);
        panelFormulario.add(new JLabel("Stock:"));
        panelFormulario.add(campoStock);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton botonAgregar = new JButton("Agregar");
        JButton botonEliminarStock = new JButton("Eliminar del Stock");

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarSneaker();
            }
        });

        botonEliminarStock.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarDelStock();
            }
        });

        panelBotones.add(botonAgregar);
        panelBotones.add(botonEliminarStock);

        // Añadir componentes al frame
        add(panelTitulo, BorderLayout.NORTH);
        add(panelBusqueda, BorderLayout.CENTER);
        add(panelFormulario, BorderLayout.WEST);
        add(panelBotones, BorderLayout.SOUTH);

        // Configuración de visibilidad
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Método para buscar un Sneaker y mostrar los resultados en la interfaz.
    private void buscarSneaker() {
        String modelo = campoModeloBusqueda.getText();
        String tallaStr = campoTallaBusqueda.getText();
        int talla = tallaStr.isEmpty() ? -1 : Integer.parseInt(tallaStr);

        Sneaker resultado = abb.buscar(modelo, talla);

        if (resultado != null) {
            areaResultado.setText("Modelo: " + resultado.modelo +
                    "\nTalla: " + resultado.talla +
                    "\nColor: " + resultado.color +
                    "\nPrecio: $" + resultado.precio +
                    "\nSección: " + resultado.seccion +
                    "\nStock: " + resultado.stock);

            mostrarImagen(resultado.imagen);
        } else {
            areaResultado.setText("Sneaker no encontrada.");
            limpiarImagen();
        }
    }

    // Método para agregar un Sneaker al árbol y mostrar el mensaje en el terminal.
    private void agregarSneaker() {
        String modelo = campoModelo.getText();
        int talla = Integer.parseInt(campoTalla.getText());
        String color = campoColor.getText();
        double precio = Double.parseDouble(campoPrecio.getText());
        String imagen = campoImagen.getText();
        String seccion = campoSeccion.getText();
        int stock = Integer.parseInt(campoStock.getText());

        Sneaker nuevaSneaker = new Sneaker(modelo, talla, color, precio, imagen, seccion, stock);
        abb.insertar(nuevaSneaker);

        // Imprimir mensaje en el terminal
        System.out.println("Se ha agregado un nuevo tenis al stock:");
        System.out.println("Modelo: " + modelo);
        System.out.println("Talla: " + talla);
        System.out.println("Color: " + color);
        System.out.println("Precio: $" + precio);
        System.out.println("Sección: " + seccion);
        System.out.println("Stock: " + stock);

        limpiarCampos();
        limpiarImagen();
    }

    // Método para eliminar un Sneaker del stock y mostrar el mensaje en el terminal.
    private void eliminarDelStock() {
        String modelo = campoModelo.getText();
        int talla = Integer.parseInt(campoTalla.getText());
        String color = campoColor.getText();

        Sneaker sneaker = abb.buscar(modelo, talla);

        if (sneaker != null && sneaker.stock > 0 && sneaker.color.equalsIgnoreCase(color)) {
            sneaker.stock--;

            // Imprimir mensaje en el terminal
            System.out.println("Se ha eliminado un tenis del stock:");
            System.out.println("Modelo: " + sneaker.modelo);
            System.out.println("Talla: " + sneaker.talla);
            System.out.println("Color: " + sneaker.color);
            System.out.println("Precio: $" + sneaker.precio);
            System.out.println("Sección: " + sneaker.seccion);
            System.out.println("Nuevo stock: " + sneaker.stock);

            areaResultado.setText("Modelo: " + sneaker.modelo +
                    "\nTalla: " + sneaker.talla +
                    "\nColor: " + sneaker.color +
                    "\nPrecio: $" + sneaker.precio +
                    "\nSección: " + sneaker.seccion +
                    "\nStock: " + sneaker.stock);

            mostrarImagen(sneaker.imagen);
        } else {
            areaResultado.setText("Sneaker no encontrada o sin stock para la talla y color especificados.");
            limpiarImagen();
        }
    }

    // Método para elegir una imagen y mostrarla en la interfaz.
    private void elegirImagen() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Imagen", "jpg", "png", "jpeg", "gif");
        fileChooser.setFileFilter(filtro);

        int resultado = fileChooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            campoImagen.setText(archivoSeleccionado.getAbsolutePath());

            mostrarImagen(archivoSeleccionado.getAbsolutePath());
        }
    }

    // Método para mostrar una imagen en la etiqueta de la interfaz.
    private void mostrarImagen(String rutaImagen) {
        ImageIcon icono = new ImageIcon(rutaImagen);
        Image imagen = icono.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        etiquetaImagen.setIcon(new ImageIcon(imagen));
    }

    // Método para limpiar la etiqueta de imagen.
    private void limpiarImagen() {
        etiquetaImagen.setIcon(null);
    }

    // Método para limpiar los campos de entrada del formulario.
    private void limpiarCampos() {
        campoModelo.setText("");
        campoTalla.setText("");
        campoColor.setText("");
        campoPrecio.setText("");
        campoImagen.setText("");
        campoSeccion.setText("");
        campoStock.setText("");
    }

    // Método principal que crea una instancia de la interfaz gráfica.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InterfazGraficaTiendaSneakers();
            }
        });
    }
}
