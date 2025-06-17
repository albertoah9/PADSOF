package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import modelo.*;
import vista.VistaOperadorFacturas;
import vista.VistaOperadorPagarFacturas;

public class ControladorVistaOperadorPagarFacturas {
    private VistaOperadorPagarFacturas vista;
    private Aerolinea aerolinea;
    private GestorAeropuerto gestor;
    private VistaOperadorFacturas vistaAnterior;
    private Aeropuerto aeropuerto;

    public ControladorVistaOperadorPagarFacturas(VistaOperadorPagarFacturas vista, Aerolinea aerolinea, GestorAeropuerto gestor, VistaOperadorFacturas vistaAnterior, Aeropuerto aeropuerto) {
        this.vista = vista;
        this.aerolinea = aerolinea;
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;
        this.aeropuerto = aeropuerto;

        this.vista.btnPagar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Factura seleccionada = vista.getFacturaSeleccionada();
                if (seleccionada != null && seleccionada.getEstado() == Factura.EstadoFactura.PENDIENTE_DE_PAGO) {
                    seleccionada.marcarComoPagado();

                    List<Usuario> destinatarios = new ArrayList<>();
                    if (aeropuerto.getGestor() != null) {
                        destinatarios.add(gestor);
                    }
                    for (OperadorAereo oa : aerolinea.getOperadores()) {
                        destinatarios.add(oa);
                    }
                    aeropuerto.addNotificacion(new Notificacion("Factura con ID: " + seleccionada.getId() + "pagada.", destinatarios));

                    vista.eliminarFactura(seleccionada);
                    JOptionPane.showMessageDialog(vista, "Factura pagada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(vista, "Debe seleccionar una factura pendiente.");
                }
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