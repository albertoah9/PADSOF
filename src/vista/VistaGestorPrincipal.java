package vista;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

import modelo.GestorAeropuerto;
import vista.paneles.*;

public class VistaGestorPrincipal extends JFrame {

    public JPanel panelContenido;
    public JPanel panelLateral;
    public JButton btnOverview;
    public JButton btnSettings;
    public JButton btnHistory;
    public JButton btnActiveFlights;
    public JButton btnFlightSafety;
    public JPanel subMenuOverview;
    public JPanel subMenuPagos;
    public JPanel subMenuActiveFlights;
    
    // Botones principales
    public JButton btnPagosFacturas;
    public JButton btnFacturasPorPagar;
    public JButton btnBusquedaFactura;
    public JButton btnEstado;
    
    // Botones de submenús
    public JButton btnGraficos;
    public JButton btnOrdenPago;
    public JButton btnCreateFlight;
    public JButton btnSearchFlights;
    public JButton btnRequestFlight;

    private final GestorAeropuerto gestor;

    public VistaGestorPrincipal(String nombreUsuario) {
        this.gestor = null;
        setTitle("Gestión del Gestor Aéreo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Superior (existente)
        JPanel panelSuperior = crearPanelSuperior(nombreUsuario);
        
        // Panel de Contenido (existente)
        panelContenido = crearPanelContenido();
        
        // Panel Lateral (modificado con menús desplegables)
        panelLateral = new JPanel();
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(250, getHeight()));
        panelLateral.setBackground(new Color(240, 240, 240));
        panelLateral.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        
        // Agregar elementos al panel lateral
        agregarElementosPanelLateral();

        // Añadir todo al frame
        add(panelSuperior, BorderLayout.NORTH);
        add(panelLateral, BorderLayout.WEST);
        add(panelContenido, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel crearPanelSuperior(String nombreUsuario) {
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setPreferredSize(new Dimension(getWidth(), 60));
        panelSuperior.setBackground(new Color(51, 102, 153));
        
        JLabel saludo = new JLabel("Bienvenido de vuelta \"" + nombreUsuario + "\"");
        saludo.setFont(new Font("SansSerif", Font.BOLD, 16));
        saludo.setForeground(Color.WHITE);
        saludo.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBusqueda.setOpaque(false);
        
        JTextField campoBusqueda = new JTextField("Buscar", 15);
        campoBusqueda.setFont(new Font("SansSerif", Font.PLAIN, 14));
        
        JLabel iconoNotificacion = new JLabel(new ImageIcon("notificacion.png"));
        JLabel iconoUsuario = new JLabel(new ImageIcon("usuario.png"));
        JLabel lblNombreUsuario = new JLabel(nombreUsuario);
        lblNombreUsuario.setForeground(Color.WHITE);

        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(Box.createHorizontalStrut(10));
        panelBusqueda.add(iconoNotificacion);
        panelBusqueda.add(Box.createHorizontalStrut(5));
        panelBusqueda.add(iconoUsuario);
        panelBusqueda.add(Box.createHorizontalStrut(5));
        panelBusqueda.add(lblNombreUsuario);

        panelSuperior.add(saludo, BorderLayout.WEST);
        panelSuperior.add(panelBusqueda, BorderLayout.EAST);
        
        return panelSuperior;
    }

    private JPanel crearPanelContenido() {
        JPanel contenido = new JPanel();
        contenido.setLayout(new BoxLayout(contenido, BoxLayout.Y_AXIS));
        contenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenido.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Bienvenido a la aplicación de gestión del aeropuerto");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Usar una imagen genérica si no se encuentra
        ImageIcon mapa = new ImageIcon("mapa-aeropuerto.jpg");
        JLabel imagenMapa = new JLabel(mapa);
        imagenMapa.setAlignmentX(Component.CENTER_ALIGNMENT);

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
        JLabel horaActual = new JLabel("Hora actual: " + LocalDateTime.now().format(formato));
        horaActual.setFont(new Font("SansSerif", Font.PLAIN, 16));
        horaActual.setAlignmentX(Component.CENTER_ALIGNMENT);

        contenido.add(Box.createVerticalGlue());
        contenido.add(titulo);
        contenido.add(Box.createRigidArea(new Dimension(0, 30)));
        contenido.add(imagenMapa);
        contenido.add(Box.createRigidArea(new Dimension(0, 30)));
        contenido.add(horaActual);
        contenido.add(Box.createVerticalGlue());
        
        return contenido;
    }

    private void agregarElementosPanelLateral() {
        // Botón principal Overview (con submenú)
        btnOverview = crearBotonLateral("Overview", true);
        subMenuOverview = new JPanel();
        subMenuOverview.setLayout(new BoxLayout(subMenuOverview, BoxLayout.Y_AXIS));
        subMenuOverview.setOpaque(false);
        subMenuOverview.setVisible(false); // Inicialmente oculto
        subMenuOverview.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        btnGraficos = crearBotonSubmenu("Gráficos");
        btnGraficos.addActionListener(e -> mostrarVista(new PanelGraficos(this.gestor)));
        subMenuOverview.add(btnGraficos);

        // Submenú Pagos y facturas (anidado dentro de Overview)
        JPanel subMenuPagosFacturas = new JPanel();
        subMenuPagosFacturas.setLayout(new BoxLayout(subMenuPagosFacturas, BoxLayout.Y_AXIS));
        subMenuPagosFacturas.setOpaque(false);
        subMenuPagosFacturas.setVisible(false); // Inicialmente oculto
        subMenuPagosFacturas.setBorder(BorderFactory.createEmptyBorder(0, 20 + 20, 0, 0)); // Doble indentación

        btnPagosFacturas = crearBotonSubmenu("Pagos y facturas");
        btnPagosFacturas.addActionListener(e -> toggleSubMenu(subMenuPagosFacturas));
        subMenuOverview.add(btnPagosFacturas);

        btnOrdenPago = crearBotonSubmenu("Orden de pago");
        btnOrdenPago.addActionListener(e -> mostrarVista(new PanelOrdenPago(this.gestor)));
        subMenuPagosFacturas.add(btnOrdenPago);

        btnFacturasPorPagar = crearBotonSubmenu("Facturas por pagar");
        btnFacturasPorPagar.addActionListener(e -> mostrarVista(new PanelFacturasPorPagar(this.gestor)));
        subMenuPagosFacturas.add(btnFacturasPorPagar);

        btnBusquedaFactura = crearBotonSubmenu("Búsqueda de factura");
        btnBusquedaFactura.addActionListener(e -> mostrarVista(new PanelBusquedaFactura(this.gestor)));
        subMenuPagosFacturas.add(btnBusquedaFactura);
        subMenuOverview.add(subMenuPagosFacturas);

        JButton btnGestionesAsignaciones = crearBotonSubmenu("Gestiones y asignaciones");
        btnGestionesAsignaciones.addActionListener(e -> mostrarVista(new JPanel())); // Reemplaza con tu panel
        subMenuOverview.add(btnGestionesAsignaciones);

        JButton btnEstadoOverview = crearBotonSubmenu("Estado");
        btnEstadoOverview.addActionListener(e -> mostrarVista(new PanelEstado(this.gestor)));
        subMenuOverview.add(btnEstadoOverview);

        btnOverview.addActionListener(e -> toggleSubMenu(subMenuOverview));

        // Botón principal Settings (sin submenú por ahora)
        btnSettings = crearBotonLateral("Settings", false);
        btnSettings.addActionListener(e -> mostrarVista(new PanelSettings(this.gestor)));

        // Botón principal History (sin submenú)
        btnHistory = crearBotonLateral("History", false);
        btnHistory.addActionListener(e -> mostrarVista(new PanelHistory()));

        // Botón principal Active flights (con submenú)
        btnActiveFlights = crearBotonLateral("Active flights", true);
        subMenuActiveFlights = new JPanel();
        subMenuActiveFlights.setLayout(new BoxLayout(subMenuActiveFlights, BoxLayout.Y_AXIS));
        subMenuActiveFlights.setOpaque(false);
        subMenuActiveFlights.setVisible(false); // Inicialmente oculto
        subMenuActiveFlights.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));

        btnCreateFlight = crearBotonSubmenu("Create flight");
        btnCreateFlight.addActionListener(e -> mostrarVista(new PanelCreateFlight()));
        subMenuActiveFlights.add(btnCreateFlight);

        btnSearchFlights = crearBotonSubmenu("Search flights");
        btnSearchFlights.addActionListener(e -> mostrarVista(new PanelSearchFlights()));
        subMenuActiveFlights.add(btnSearchFlights);

        btnRequestFlight = crearBotonSubmenu("Request flights");
        btnRequestFlight.addActionListener(e -> mostrarVista(new PanelRequestFlight()));
        subMenuActiveFlights.add(btnRequestFlight);

        btnActiveFlights.addActionListener(e -> toggleSubMenu(subMenuActiveFlights));

        // Botón principal Flight safety (sin submenú)
        btnFlightSafety = crearBotonLateral("Flight safety", false);
        btnFlightSafety.addActionListener(e -> mostrarVista(new PanelFlightSafety()));

        // Limpiar el panel lateral y agregar los elementos en el orden correcto
        panelLateral.removeAll();

        panelLateral.add(btnOverview);
        panelLateral.add(subMenuOverview);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 5)));
        panelLateral.add(btnSettings);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 5)));
        panelLateral.add(btnHistory);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 5)));
        panelLateral.add(btnActiveFlights);
        panelLateral.add(subMenuActiveFlights);
        panelLateral.add(Box.createRigidArea(new Dimension(0, 5)));
        panelLateral.add(btnFlightSafety);
        panelLateral.add(Box.createVerticalGlue());

        panelLateral.revalidate();
        panelLateral.repaint();
    }

    private JButton crearBotonLateral(String texto, boolean tieneFlecha) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 14));
        boton.setBackground(new Color(240, 240, 240));
        boton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        
        if (tieneFlecha) {
            boton.setIcon(new ImageIcon("flecha-derecha.png"));
            boton.setHorizontalTextPosition(SwingConstants.LEFT);
            boton.setIconTextGap(10);
        }
        
        return boton;
    }

    private JButton crearBotonSubmenu(String texto) {
        JButton boton = new JButton(texto);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT);
        boton.setFont(new Font("SansSerif", Font.PLAIN, 13));
        boton.setForeground(new Color(80, 80, 80));
        boton.setBorder(BorderFactory.createEmptyBorder(6, 30, 6, 15));
        boton.setContentAreaFilled(false);
        boton.setFocusPainted(false);
        boton.setHorizontalAlignment(SwingConstants.LEFT);
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));
        
        return boton;
    }

    protected void mostrarVista(JPanel nuevoPanel) {
        panelContenido.removeAll();
        panelContenido.add(nuevoPanel);
        panelContenido.revalidate();
        panelContenido.repaint();
    }

    protected void toggleSubMenu(JPanel subMenu) {
        subMenu.setVisible(!subMenu.isVisible());
        revalidate();
        repaint();
    }
    
    // Clases internas para paneles de ejemplo (deben ser implementadas)

    private class PanelCreateFlight extends JPanel {
        public PanelCreateFlight() {
            setBackground(Color.WHITE);
            add(new JLabel("Contenido de Crear Vuelo"));
        }
    }

    private class PanelRequestFlight extends JPanel {
        public PanelRequestFlight() {
            setBackground(Color.WHITE);
            add(new JLabel("Contenido de Solicitar Vuelo"));
        }
    }

}