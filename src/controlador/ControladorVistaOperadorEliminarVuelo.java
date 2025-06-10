package controlador;

import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaOperadorEliminarVuelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaOperadorEliminarVuelo {

    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private VistaOperadorEliminarVuelo vista;
    private JFrame vistaAnterior;

    public ControladorVistaOperadorEliminarVuelo(Aeropuerto aeropuerto, VistaOperadorEliminarVuelo vista, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.aeropuerto = aeropuerto;
        this.vista = vista;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        cargarVuelos();

        vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVuelo();
            }
        });

        vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVista();
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void cargarVuelos() {
        vista.limpiarVuelos();
        for (Vuelo v : aeropuerto.getVuelosAerolinea(aerolinea)) {
            String descripcion = String.format(
                "Origen: %s, Destino: %s, Salida: %s, Llegada: %s",
                v.getOrigen(),
                v.getDestino(),
                v.getfechaHoraSalida() != null ? v.getfechaHoraSalida().toString() : "N/A",
                v.getfechaHoraLlegada() != null ? v.getfechaHoraLlegada().toString() : "N/A"
            );
            vista.agregarVuelo(v.getId(), descripcion);
        }
    }

    private void eliminarVuelo() {
        int idSeleccionado = vista.getVueloSeleccionado();
        Vuelo vueloEliminar = null;

        for (Vuelo v : aeropuerto.getVuelosAerolinea(aerolinea)) {
            if (v.getId() == idSeleccionado) {
                vueloEliminar = v;
                break;
            }
        }

        if (vueloEliminar != null) {
            aeropuerto.eliminarVuelo(vueloEliminar.getId());
            JOptionPane.showMessageDialog(vista, "Vuelo eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            cerrarVista();
        } else {
            JOptionPane.showMessageDialog(vista, "No se encontró el vuelo.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cerrarVista() {
        vista.dispose();
        vistaAnterior.setVisible(true);
    }
}