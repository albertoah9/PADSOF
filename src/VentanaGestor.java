

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/*
public class VentanaGestor extends JFrame {*/

    /*public VentanaGestor(GestorAeropuerto gestor) {
        setTitle("Gestión del Aeropuerto");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa   
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel lateral (menú)
        JPanel panelLateral = new JPanel();
        panelLateral.setBackground(Color.DARK_GRAY);
        panelLateral.setLayout(new BoxLayout(panelLateral, BoxLayout.Y_AXIS));
        panelLateral.setPreferredSize(new Dimension(150, getHeight()));

        /*String[] opciones = {"Overview", "Settings", "History", "Active flights", "Flight safety", "Log out"};
        for (String texto : opciones) {
            JButton boton = new JButton(texto);
            boton.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelLateral.add(Box.createRigidArea(new Dimension(0, 20)));
            panelLateral.add(boton);
        }*//*

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        panelSuperior.setPreferredSize(new Dimension(getWidth(), 60));

        JLabel saludo = new JLabel("Bienvenido de vuelta \"" + gestor.getNombre() + "\"");
        saludo.setFont(new Font("SansSerif", Font.BOLD, 14));
        saludo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel panelBusqueda = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JTextField campoBusqueda = new JTextField("Buscar", 15);
        JLabel iconoNotificacion = new JLabel("\uD83D\uDD14"); // campanita unicode
        JLabel iconoUsuario = new JLabel("\uD83D\uDC64 \"" + gestor.getNombre() + "\"");

        panelBusqueda.add(campoBusqueda);
        panelBusqueda.add(iconoNotificacion);
        panelBusqueda.add(iconoUsuario);

        panelSuperior.add(saludo, BorderLayout.WEST);
        panelSuperior.add(panelBusqueda, BorderLayout.EAST);

        // Panel principal (contenido central)
        JPanel panelContenido = new JPanel();
        panelContenido.setLayout(new BoxLayout(panelContenido, BoxLayout.Y_AXIS));
        panelContenido.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Bienvenido a la aplicacion de gestión del aeropuerto");
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
    }*//*
    private JPanel contentPanel; // Panel central donde irán los contenidos dinámicos

    public VentanaGestor(GestorAeropuerto gestor) {
        setTitle("Panel del Gestor");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout principal
        setLayout(new BorderLayout());

        // Panel lateral
        JPanel menuPanel = crearMenuLateral();
        add(menuPanel, BorderLayout.WEST);

        // Panel de contenido
        contentPanel = new JPanel(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Mostrar sección por defecto (por ejemplo, gráficos)
        mostrarContenido(new PanelGraficos());
    }

    private JPanel crearMenuLateral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton overviewBtn = new JButton("Overview");
        JButton graficosBtn = new JButton("Gráficos");
        JButton ordenPagoBtn = new JButton("Orden de pago");
        graficosBtn.setFont(graficosBtn.getFont().deriveFont(Font.BOLD)); // en negrita

        graficosBtn.addActionListener(e -> {
            mostrarContenido(new PanelGraficos());
            resaltarBoton(graficosBtn);
        });

        ordenPagoBtn.addActionListener(e -> {
            mostrarContenido(new PanelOrdenPago());
            resaltarBoton(ordenPagoBtn);
        });

        // Puedes ir añadiendo más botones y acciones aquí

        panel.add(overviewBtn);
        panel.add(graficosBtn);
        panel.add(ordenPagoBtn);

        setVisible(true);

        return panel;
    }

    private void mostrarContenido(JPanel nuevoPanel) {
        contentPanel.removeAll();
        contentPanel.add(nuevoPanel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void resaltarBoton(JButton botonActivo) {
        // Opción para resaltar visualmente el botón actual y resetear los demás
        // Esto se puede mejorar guardando una lista de botones si quieres automatizarlo
        botonActivo.setFont(botonActivo.getFont().deriveFont(Font.BOLD));
    }
}*/
public class VentanaGestor extends VentanaBase {
    private GestorAeropuerto gestor;

    public VentanaGestor(GestorAeropuerto gestor) {
        super(gestor.getNombre());
        this.gestor = gestor;

        crearBotonesMenuLateral();
    }

    private void crearBotonesMenuLateral() {
        // ----- BOTÓN OVERVIEW -----
        btnOverview = new JButton("Overview");
        btnOverview.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOverview.addActionListener(e -> toggleSubMenu(subMenuOverview));
    
        subMenuOverview = new JPanel();
        subMenuOverview.setLayout(new BoxLayout(subMenuOverview, BoxLayout.Y_AXIS));
        subMenuOverview.setVisible(false);
    
        JButton btnGraficos = new JButton("Gráficos");
        btnGraficos.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JButton btnOrdenPago = new JButton("Orden de Pago");
        btnOrdenPago.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JPanel subMenuOrdenPago = new JPanel();
        subMenuOrdenPago.setLayout(new BoxLayout(subMenuOrdenPago, BoxLayout.Y_AXIS));
        subMenuOrdenPago.setVisible(false);
    
        JButton btnFacturasPorPagar = new JButton("Facturas por pagar");
        btnFacturasPorPagar.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        JButton btnBusquedaFactura = new JButton("Búsqueda de factura");
        btnBusquedaFactura.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        subMenuOrdenPago.add(btnFacturasPorPagar);
        subMenuOrdenPago.add(btnBusquedaFactura);
    
        btnOrdenPago.addActionListener(e -> toggleSubMenu(subMenuOrdenPago));
    
        subMenuOverview.add(btnGraficos);
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnOrdenPago);
        subMenuOverview.add(subMenuOrdenPago);
    
        JButton btnGestiones = new JButton("Gestiones y asignaciones");
        btnGestiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnEstado = new JButton("Estado");
        btnEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnGestiones);
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnEstado);
    
        // Finalmente añades al panel lateral
        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnOverview);
        panelLateral.add(subMenuOverview);
    
        panelLateral.revalidate();
        panelLateral.repaint();
    }
}
