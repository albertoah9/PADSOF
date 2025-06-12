package controlador;

import modelo.Aerolinea;
import modelo.Factura;
import vista.VistaOperadorMostrarFacturas;
import vista.VistaOperadorFacturas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ControladorVistaOperadorMostrarFacturas {

    private VistaOperadorMostrarFacturas vista;
    private Aerolinea aerolinea;
    private VistaOperadorFacturas vistaAnterior;

    public ControladorVistaOperadorMostrarFacturas(VistaOperadorMostrarFacturas vista, Aerolinea aerolinea, VistaOperadorFacturas vistaAnterior) {
        this.vista = vista;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        cargarFacturas();

        this.vista.btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    private void cargarFacturas() {
        vista.limpiarTabla();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        for (Factura factura : aerolinea.getFacturas()) {
            Object[] fila = new Object[]{
                factura.getId(),
                factura.getFechaEmision().format(formato),
                factura.getFechaVencimiento().format(formato),
                String.format("%.2f €", factura.getMonto()),
                factura.getEstado().toString()
            };
            vista.agregarFila(fila);
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}