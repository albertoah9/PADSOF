package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Factura;
import modelo.GestorAeropuerto;
import vista.VistaOperadorFacturas;
import vista.VistaOperadorMostrarFacturas;
import vista.VistaOperadorPagarFacturas;
import vista.VistaOperadorPrincipal;

public class ControladorVistaOperadorFacturas {

    private VistaOperadorFacturas vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private VistaOperadorPrincipal vistaAnterior;

    public ControladorVistaOperadorFacturas(VistaOperadorFacturas vista, Aeropuerto aeropuerto, Aerolinea aerolinea, VistaOperadorPrincipal vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        this.vista.btnVerFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaOperadorMostrarFacturas vistaMostrar = new VistaOperadorMostrarFacturas();
                ControladorVistaOperadorMostrarFacturas controladorMostrar =
                    new ControladorVistaOperadorMostrarFacturas(vistaMostrar, aerolinea, vista);
                controladorMostrar.iniciar();
            }
        });


        this.vista.btnPagarFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Factura> pendientes = aerolinea.getFacturas().stream()
                    .filter(f -> f.getEstado() == Factura.EstadoFactura.PENDIENTE_DE_PAGO)
                    .toList();

                if (pendientes.isEmpty()) {
                    JOptionPane.showMessageDialog(vista, "No hay facturas pendientes.");
                    return;
                }

                VistaOperadorPagarFacturas vistaPagar = new VistaOperadorPagarFacturas(pendientes);
                ControladorVistaOperadorPagarFacturas controladorPagar = new ControladorVistaOperadorPagarFacturas(
                        vistaPagar, aerolinea, (GestorAeropuerto) aeropuerto.getGestor(), vista, aeropuerto);
                vista.setVisible(false);
                controladorPagar.iniciar();
            }
        });

        this.vista.btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}