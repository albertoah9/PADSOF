import javax.swing.*;
import java.awt.*;



public class VentanaControlador extends VentanaBase {
    private ControladorAereo controlador;

    public VentanaControlador(ControladorAereo controlador) {
        super(controlador.getNombre());
        this.controlador = controlador;

        crearBotonesMenuLateral();

    }

    private void crearBotonesMenuLateral() {
        // ----- BOTÓN OVERVIEW -----
        btnOverview = new JButton("Overview");
        btnOverview.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOverview.setMaximumSize(new Dimension(170, 30));
        btnOverview.addActionListener(_ -> toggleSubMenu(subMenuOverview));
    
        subMenuOverview = new JPanel();
        subMenuOverview.setLayout(new BoxLayout(subMenuOverview, BoxLayout.Y_AXIS));
        subMenuOverview.setVisible(false);
        subMenuOverview.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));  // Margen izquierdo

    
        JButton btnGraficos = new JButton("Gráficos");
        btnGraficos.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGraficos.setMaximumSize(new Dimension(170, 30));


        //para que acceda a la zona de graficos
        btnGraficos.addActionListener(_ -> mostrarVista(new VentanaControladorGraficos()));

    
        JButton btnFacturas = new JButton("Gestionar Facturas");
        btnFacturas.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFacturas.setMaximumSize(new Dimension(170, 30));


    
        JPanel subMenuFacturas = new JPanel();
        subMenuFacturas.setLayout(new BoxLayout(subMenuFacturas, BoxLayout.Y_AXIS));
        subMenuFacturas.setVisible(false);
        subMenuFacturas.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));  // Margen más profundo


        JButton btnOrdenDePago = new JButton("Orden de pago");
        btnOrdenDePago.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnOrdenDePago.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnOrdenDePago.addActionListener(_ -> new PanelControladorSinPermiso());

        JButton btnFacturasPorPagar = new JButton("Facturas por pagar");
        btnFacturasPorPagar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFacturasPorPagar.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnFacturasPorPagar.addActionListener(_ -> new PanelControladorSinPermiso());
    
        JButton btnBusquedaFactura = new JButton("Buscar Facturas");
        btnBusquedaFactura.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBusquedaFactura.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnBusquedaFactura.addActionListener(_ -> new PanelControladorSinPermiso());

        subMenuFacturas.add(btnOrdenDePago);
        subMenuFacturas.add(btnFacturasPorPagar);
        subMenuFacturas.add(btnBusquedaFactura);
    
        btnFacturas.addActionListener(_ -> toggleSubMenu(subMenuFacturas));
    
        subMenuOverview.add(btnGraficos);
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnFacturas);
        subMenuOverview.add(subMenuFacturas);
    
        JButton btnGestiones = new JButton("Asignaciones");
        btnGestiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnGestiones.setMaximumSize(new Dimension(170, 30));

        JButton btnEstado = new JButton("Estado");
        btnEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEstado.setMaximumSize(new Dimension(170, 30));

        JPanel subMenuEstado = new JPanel();
        subMenuEstado.setLayout(new BoxLayout(subMenuEstado, BoxLayout.Y_AXIS));
        subMenuEstado.setVisible(false);
        subMenuEstado.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0)); // Margen más profundo

        JButton btnDisponibilidades = new JButton("Disponibilidades");
        btnDisponibilidades.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDisponibilidades.setMaximumSize(new Dimension(170, 30));
        

        JButton btnEstadoVuelo = new JButton("EstadoVuelo");
        btnEstadoVuelo.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEstadoVuelo.setMaximumSize(new Dimension(170, 30));

        subMenuEstado.add(btnDisponibilidades);
        subMenuEstado.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuEstado.add(btnEstadoVuelo);

        btnEstado.addActionListener(_ -> toggleSubMenu(subMenuEstado));

        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnGestiones);
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnEstado);
        subMenuOverview.add(subMenuEstado);

        //BOTON SETTINGS
        btnSettings = new JButton("Ajustes");
        btnSettings.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSettings.setMaximumSize(new Dimension(170, 30));


        
        //BOTON HISTORY
        btnHistory = new JButton("Historial");
        btnHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnHistory.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnHistory.addActionListener(_ -> new PanelControladorSinPermiso());

        //BOTON ACTIVE FLIGHTS
        btnActiveFlights = new JButton("VuelosActivos");
        btnActiveFlights.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnActiveFlights.setMaximumSize(new Dimension(170, 30));



        JPanel subMenuActiveFlights = new JPanel();
        subMenuActiveFlights.setLayout(new BoxLayout(subMenuActiveFlights, BoxLayout.Y_AXIS));
        subMenuActiveFlights.setVisible(false);
        subMenuActiveFlights.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));  // Margen izquierdo


        JButton btnCreateFlight = new JButton("Crear Vuelo");
        btnCreateFlight.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnCreateFlight.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnCreateFlight.addActionListener(_ -> new PanelControladorSinPermiso());

        JButton btnSearchFlight = new JButton("Buscar Vuelo");
        btnSearchFlight.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearchFlight.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnSearchFlight.addActionListener(_ -> new PanelControladorSinPermiso());

        JButton btnRequestFlight = new JButton("Solicitar Vuelo");
        btnRequestFlight.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnRequestFlight.setMaximumSize(new Dimension(170, 30));


        //aparece no permitido para controlador
        btnRequestFlight.addActionListener(_ -> new PanelControladorSinPermiso());

        subMenuActiveFlights.add(btnCreateFlight);
        subMenuActiveFlights.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuActiveFlights.add(btnSearchFlight);
        subMenuActiveFlights.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuActiveFlights.add(btnRequestFlight);

        btnActiveFlights.addActionListener(_ -> toggleSubMenu(subMenuActiveFlights));

        //BOTTON FLIGHT SAVETY
        JButton btnFlightSafety = new JButton("Vuelo Seguro");
        btnFlightSafety.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnFlightSafety.setMaximumSize(new Dimension(170, 30));


        // Finalmente añades al panel lateral
        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnOverview);
        panelLateral.add(subMenuOverview);

        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnSettings);
    
        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnHistory);
    
        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnActiveFlights);
        panelLateral.add(subMenuActiveFlights);

        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnFlightSafety);
    
        panelLateral.revalidate();
        panelLateral.repaint();
    }




}
