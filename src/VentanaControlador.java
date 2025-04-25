import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
public class VentanaControlador extends JFrame {
    public VentanaControlador(ControladorAereo controlador) {
        setTitle("Control del Aeropuerto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel lateral (menú)
        JPanel panelLateral = new JPanel();
        panelLateral.setBackground(Color.DARK_GRAY);
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(150, getHeight()));

        String[] opciones = {"Overview", "Settings", "History", "Active flights", "Flight safety", "Log out"};
        for (String texto : opciones) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelLateral.add(Box.createRigidArea(new Dimension(0, 20)));
            panelLateral.add(boton);
        }

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel saludo = new JLabel("Bienvenido de vuelta \"" + controlador.getNombre() + "\"");
        saludo.setFont(new Font("SansSerif", Font.BOLD, 14));
        saludo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField campoBusqueda = new JTextField("Buscar", 15);
        JLabel iconoNotificacion = new JLabel("\uD83D\uDD14"); // campanita unicode
        JLabel iconoUsuario = new JLabel("\uD83D\uDC64 \"" + controlador.getNombre() + "\"");

        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(iconoNotificacion);
        panelBusqueda.add(iconoUsuario);

        panelSuperior.add(saludo, BorderLayout.WEST);
        panelSuperior.add(panelBusqueda, BorderLayout.EAST);

        // Panel principal (contenido central)
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Bienvenido a la aplicacion para controlar el aeropuerto");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon mapa = new ImageIcon("C:\\Users\\nuria\\Downloads\\mapa-aeropuerto-orly.jpg"); // ← Asegúrate de cambiar esto a la ruta real
        JLabel imagenMapa = new JLabel(mapa);
        imagenMapa.setAlignmentX(Component.CENTER_ALIGNMENT);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        JLabel horaActual = new JLabel("Hora actual: " + LocalDateTime.now().format(formato));
        horaActual.setFont(new Font("SansSerif", Font.PLAIN, 14));
        horaActual.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelContenido.add(titulo);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelContenido.add(imagenMapa);
        panelContenido.add(Box.createRigidArea(new Dimension(0, 20)));
        panelContenido.add(horaActual);

        // Añadir paneles al frame
        add(panelLateral, BorderLayout.WEST);
        add(panelSuperior, BorderLayout.NORTH);
        add(panelContenido, BorderLayout.CENTER);

        setVisible(true);
    }
}*/
public class VentanaControlador extends VentanaBase {
    public VentanaControlador(ControladorAereo controlador) {
        super("Ventana Controlador - " + controlador.getNombre());

        JPanel panelLateral = construirpanelLateral();
        add(panelLateral, BorderLayout.WEST);
    }
}
