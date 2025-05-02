import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class VentanaBase extends JFrame {

    protected JPanel panelContenido;
    protected JPanel panelLateral;
    protected JButton btnOverview;
    protected JButton btnSettings;
    protected JButton btnHistory;
    protected JButton btnActiveFlights;
    protected JButton btnFlightSafety;
    protected JPanel subMenuOverview;

    public VentanaBase(String nombreUsuario) {
        setTitle("Aplicación de Gestión Aeroportuaria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ------------------------
        // PANEL SUPERIOR
        // ------------------------
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel saludo = new JLabel("Bienvenido de vuelta \"" + nombreUsuario + "\"");
        saludo.setFont(new Font("SansSerif", Font.BOLD, 14));
        saludo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField campoBusqueda = new JTextField("Buscar", 15);
        JLabel iconoNotificacion = new JLabel("\uD83D\uDD14"); // campanita unicode
        JLabel iconoUsuario = new JLabel("\uD83D\uDC64 \"" + nombreUsuario + "\"");

        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(iconoNotificacion);
        panelBusqueda.add(iconoUsuario);

        panelSuperior.add(saludo, BorderLayout.WEST);
        panelSuperior.add(panelBusqueda, BorderLayout.EAST);

        // ------------------------
        // PANEL CONTENIDO CENTRAL
        // ------------------------
        panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Bienvenido a la aplicación de gestión del aeropuerto");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon mapa = new ImageIcon("C:\\Users\\nuria\\Downloads\\mapa-aeropuerto-orly.jpg"); // cambia la ruta si es necesario
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

        // PANEL LATERAL
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(250, getHeight()));

        // ------------------------
        // AÑADIR TODO AL FRAME
        // ------------------------
        add(panelSuperior, BorderLayout.NORTH);
        add(panelLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        setVisible(true);
    }

    /*protected void inicializarMenu() {
        JButton botonOverview = new JButton("Overview");
        panelMenu.add(botonOverview);
    
        JPanel submenuOverview = new JPanel();
        submenuOverview.setLayout(new BoxLayout(submenuOverview, BoxLayout.Y_AXIS));
        submenuOverview.setBackground(Color.LIGHT_GRAY);
        submenuOverview.setVisible(false);
    
        // Botón de gráficos
        JButton btnGraficos = new JButton("Gráficos");
        submenuOverview.add(btnGraficos);
    
        // BOTÓN ORDEN DE PAGO (con su propio submenú)
        JButton btnOrdenPago = new JButton("Orden de pago");
        submenuOverview.add(btnOrdenPago);
    
        JPanel submenuOrdenPago = new JPanel();
        submenuOrdenPago.setLayout(new BoxLayout(submenuOrdenPago, BoxLayout.Y_AXIS));
        submenuOrdenPago.setBackground(new Color(200, 200, 200)); // Más clarito para distinguir
        submenuOrdenPago.setVisible(false);
    
        JButton btnFacturasPorPagar = new JButton("Facturas por pagar");
        JButton btnBusquedaFactura = new JButton("Búsqueda de factura");
        submenuOrdenPago.add(btnFacturasPorPagar);
        submenuOrdenPago.add(btnBusquedaFactura);
    
        // Añadimos también el submenú de orden de pago al menú general
        submenuOverview.add(submenuOrdenPago);
    
        // Otros botones del overview
        JButton btnGestionesAsignaciones = new JButton("Gestiones y asignaciones");
        JButton btnEstadoVuelos = new JButton("Estado");
        submenuOverview.add(btnGestionesAsignaciones);
        submenuOverview.add(btnEstadoVuelos);
    
        panelMenu.add(submenuOverview);
    
        // Acciones para mostrar/ocultar submenús
        botonOverview.addActionListener(e -> {
            submenuOverview.setVisible(!submenuOverview.isVisible());
            revalidate();
        });
    
        btnOrdenPago.addActionListener(e -> {
            submenuOrdenPago.setVisible(!submenuOrdenPago.isVisible());
            revalidate();
        });
    
        // Acciones de contenido
        btnGraficos.addActionListener(e -> mostrarVista("Graficos"));
        btnFacturasPorPagar.addActionListener(e -> mostrarVista("FacturasPorPagar"));
        btnBusquedaFactura.addActionListener(e -> mostrarVista("BusquedaFactura"));
        btnGestionesAsignaciones.addActionListener(e -> mostrarVista("GestionesAsignaciones"));
        btnEstadoVuelos.addActionListener(e -> mostrarVista("Estado"));
    
        // Paneles dummy de contenido
        panelContenido.add(new JLabel("Vista de gráficos"), "Graficos");
        panelContenido.add(new JLabel("Vista de facturas por pagar"), "FacturasPorPagar");
        panelContenido.add(new JLabel("Vista de búsqueda de factura"), "BusquedaFactura");
        panelContenido.add(new JLabel("Vista de gestiones y asignaciones"), "GestionesAsignaciones");
        panelContenido.add(new JLabel("Vista del estado de vuelos"), "Estado");
    }

    protected void mostrarVista(String nombreVista) {
        cardLayoutContenido.show(panelContenido, nombreVista);
    }*/

    protected void toggleSubMenu(JPanel subMenu) {
        subMenu.setVisible(!subMenu.isVisible());
        revalidate();
        repaint();
    }
}
