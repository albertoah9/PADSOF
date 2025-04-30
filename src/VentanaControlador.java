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
        btnOverview.addActionListener(e -> toggleSubMenu(subMenuOverview));
    
        subMenuOverview = new JPanel();
        subMenuOverview.setLayout(new BoxLayout(subMenuOverview, BoxLayout.Y_AXIS));
        subMenuOverview.setVisible(false);
    
        JButton btnGraficos = new JButton("Gráficos");
        btnGraficos.setAlignmentX(Component.CENTER_ALIGNMENT);

        //para que acceda a la zona de graficos
        btnGraficos.addActionListener(e -> mostrarVista(new VentanaControladorGraficos()));

    
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

        //BOTON SETTINGS
        bntSettings = new JButton("Settings");
        bntSettings.setAlignmentX(Component.CENTER_ALIGNMENT);

        
        //BOTON HISTORY
        btnHistory = new JButton("History");
        btnHistory.setAlignmentX(Component.CENTER_ALIGNMENT);
        //BOTON ACTIVE FLIGHTS
        btnActiveFlights = new JButton("ActiveFlights");
        btnActiveFlights.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel subMenuActiveFlights = new JPanel();
        subMenuActiveFlights.setLayout(new BoxLayout(subMenuActiveFlights, BoxLayout.Y_AXIS));
        subMenuActiveFlights.setVisible(false);

        JButton btnCreateFlight = new JButton("Create Flight");
        btnCreateFlight.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnSearchFlight = new JButton("Search Flight");
        btnSearchFlight.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnRequestFlight = new JButton("Request Flight");
        btnRequestFlight.setAlignmentX(Component.CENTER_ALIGNMENT);

        subMenuActiveFlights.add(btnCreateFlight);
        subMenuActiveFlights.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuActiveFlights.add(btnSearchFlight);
        subMenuActiveFlights.add(Box.createRigidArea(new Dimension(0, 5)));
        subMenuActiveFlights.add(btnRequestFlight);

        btnActiveFlights.addActionListener(e -> toggleSubMenu(subMenuActiveFlights));

        //BOTTON FLIGHT SAVETY
        JButton btnFlightSafety = new JButton("Flight Safety");
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
