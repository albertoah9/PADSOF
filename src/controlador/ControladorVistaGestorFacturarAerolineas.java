package controlador;

import modelo.*;
import vista.VistaGestorFacturarAerolineas;
import vista.VistaGestorFacturacion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaGestorFacturarAerolineas {

    private VistaGestorFacturarAerolineas vista;
    private GestorAeropuerto gestor;
    private VistaGestorFacturacion vistaAnterior;

    public ControladorVistaGestorFacturarAerolineas(VistaGestorFacturarAerolineas vista, GestorAeropuerto gestor, VistaGestorFacturacion vistaAnterior) {
        this.vista = vista;
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;

        vista.btnFacturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarFacturacion();
            }
        });

        vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });
    }

    private void realizarFacturacion() {
        Aerolinea aerolinea = vista.getAerolineaSeleccionada();
        int mes = vista.getMesSeleccionado();
        int anio = vista.getAnioSeleccionado();

        if (aerolinea == null) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar una aerolínea.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDateTime inicio = LocalDateTime.of(anio, mes, 1, 0, 0);
        LocalDateTime fin = inicio.plusMonths(1).minusSeconds(1);

        double base = aerolinea.calcularCostoTotal(
                inicio,
                fin,
                new ArrayList<>(gestor.getUsosElementosAeropuerto()),
                new ArrayList<>(gestor.getVuelos()),
                UsoElementoAeropuerto.getCostoVuelo()
        );

        Factura factura = new Factura(base, aerolinea);

        List<Descuento> descuentosSeleccionados = vista.getDescuentosSeleccionados();

        List<Vuelo> vuelosAerolinea = gestor.getAeropuerto().getVuelosAerolinea(aerolinea);
        int vuelosDelMes = 0;
        for (Vuelo v : vuelosAerolinea) {
            if (v.getFechaHora().getMonthValue() == mes && v.getFechaHora().getYear() == anio) {
                vuelosDelMes++;
            }
        }

        factura.aplicarDescuentos(descuentosSeleccionados, aerolinea, LocalDate.of(anio, mes, 1), vuelosDelMes);

        gestor.addFactura(factura);

        JOptionPane.showMessageDialog(
                vista,
                "Factura creada correctamente.\nMonto final: " + factura.getMonto() + " €\nDescuentos aplicados: " + factura.getDescuentosAplicados().size(),
                "Facturación completada",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}