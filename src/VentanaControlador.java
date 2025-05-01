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
        btnOverview.addActionListener(_ -> toggleSubMenu(subMenuOverview));
    
        subMenuOverview = new JPanel();
        subMenuOverview.setLayout(new BoxLayout(subMenuOverview, BoxLayout.Y_AXIS));
        subMenuOverview.setVisible(false);
    
        JButton btnGraficos = new JButton("Gráficos");
        btnGraficos.setAlignmentX(Component.CENTER_ALIGNMENT);

        //para que acceda a la zona de graficos
        btnGraficos.addActionListener(_ -> mostrarVista(new VentanaControladorGraficos()));

    
        JButton btnFacturas = new JButton("Gestion de Facturas");
        btnFacturas.setAlignmentX(Component.CENTER_ALIGNMENT);

    
        JPanel subMenuFacturas = new JPanel();
        subMenuFacturas.setLayout(new BoxLayout(subMenuFacturas, BoxLayout.Y_AXIS));
        subMenuFacturas.setVisible(false);

        JButton btnOrdenDePago = new JButton("Orden de pago");
        btnOrdenDePago.setAlignmentX(Component.CENTER_ALIGNMENT);

        //aparece no permitido para controlador
        btnOrdenDePago.addActionListener(_ -> new PanelControladorSinPermiso());

        JButton btnFacturasPorPagar = new JButton("Facturas por pagar");
        btnFacturasPorPagar.setAlignmentX(Component.CENTER_ALIGNMENT);

        //aparece no permitido para controlador
        btnFacturasPorPagar.addActionListener(_ -> new PanelControladorSinPermiso());
    
        JButton btnBusquedaFactura = new JButton("Búsqueda de factura");
        btnBusquedaFactura.setAlignmentX(Component.CENTER_ALIGNMENT);

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
    
        JButton btnGestiones = new JButton("Gestiones y asignaciones");
        btnGestiones.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnEstado = new JButton("Estado");
        btnEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnGestiones);
        subMenuOverview.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuOverview.add(btnEstado);

        //BOTON SETTINGS
        bntSettings = new JButton("Ajustes");
        bntSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        //BOTON HISTORY
        btnHistory = new JButton("Hsitorial");
        btnHistory.setAlignmentX(Component.CENTER_ALIGNMENT);

        //aparece no permitido para controlador
        btnHistory.addActionListener(_ -> new PanelControladorSinPermiso());

        //BOTON ACTIVE FLIGHTS
        btnActiveFlights = new JButton("VuelosActivos");
        btnActiveFlights.setAlignmentX(Component.CENTER_ALIGNMENT);


        JPanel subMenuActiveFlights = new JPanel();
        subMenuActiveFlights.setLayout(new BoxLayout(subMenuActiveFlights, BoxLayout.Y_AXIS));
        subMenuActiveFlights.setVisible(false);

        JButton btnCreateFlight = new JButton("Crear Vuelo");
        btnCreateFlight.setAlignmentX(Component.CENTER_ALIGNMENT);

        //aparece no permitido para controlador
        btnCreateFlight.addActionListener(_ -> new PanelControladorSinPermiso());

        JButton btnSearchFlight = new JButton("Buscar Vuelo");
        btnSearchFlight.setAlignmentX(Component.CENTER_ALIGNMENT);

        //aparece no permitido para controlador
        btnSearchFlight.addActionListener(_ -> new PanelControladorSinPermiso());

        JButton btnRequestFlight = new JButton("Solicitar Vuelo");
        btnRequestFlight.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        // Finalmente añades al panel lateral
        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(btnOverview);
        panelLateral.add(subMenuOverview);

        panelLateral.add(Box.createVerticalStrut(10));
        panelLateral.add(bntSettings);
    
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


    private void mostrarVista(JPanel nuevaVista) {
        panelContenido.removeAll();  // Limpiar el contenido actual
        panelContenido.add(nuevaVista);  // Añadir la nueva vista
        panelContenido.revalidate();  // Revalidar para refrescar la UI
        panelContenido.repaint();  // Repintar el panel
    }
 


}
