package controlador;

import modelo.Aerolinea;
import modelo.Avion;
import modelo.AvionCarga;
import modelo.AvionPasajeros;
import vista.VistaOperadorAviones;
import vista.VistaOperadorVerFlota;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;

public class ControladorVistaOperadorVerFlota {

    private VistaOperadorVerFlota vista;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaOperadorVerFlota(VistaOperadorVerFlota vista, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        cargarFlota();

        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void cargarFlota() {
        vista.limpiarTabla();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Avion avion : aerolinea.getFlota()) {
            String tipo = avion instanceof AvionPasajeros ? "Pasajeros" : "Carga";
            String capacidadCarga = "";
            String detalles = "";

            if (avion instanceof AvionPasajeros) {
                capacidadCarga = ((AvionPasajeros) avion).getCapacidad() + " plazas";
            } else if (avion instanceof AvionCarga) {
                AvionCarga carga = (AvionCarga) avion;
                capacidadCarga = carga.getCargaMax() + " kg";
                detalles = (carga.getControlTemperatura() ? "Temp OK, " : "") +
                           (carga.isMercPeligrosas() ? "Merc. Peligrosas" : "");
            }

            Object[] fila = {
                avion.getId(),
                tipo,
                avion.getMarca(),
                avion.getModelo(),
                avion.getMatricula(),
                avion.getAutonomia() + " km",
                avion.getAnyoCompra().format(formato),
                avion.getUltimaRevis().format(formato),
                capacidadCarga,
                detalles
            };

            vista.agregarAvion(fila);
        }
    }
}