package controlador;

import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaOperadorModificarEstadoVuelo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaOperadorModificarEstadoVuelo {

    private VistaOperadorModificarEstadoVuelo vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaOperadorModificarEstadoVuelo(VistaOperadorModificarEstadoVuelo vista, Aeropuerto aeropuerto, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
        this.aerolinea = aerolinea;

        cargarVuelos();

        this.vista.btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarEstado();
            }
        });

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

    private void cargarVuelos() {
        for (Vuelo v : aeropuerto.getVuelosAerolinea(aerolinea)) {
            vista.cmbVuelos.addItem("Vuelo ID: " + v.getId() + " - " + v.getOrigen() + " -> " + v.getDestino());
        }
    }

    private void modificarEstado() {
        int index = vista.cmbVuelos.getSelectedIndex();
        if (index == -1 || index >= aeropuerto.getVuelosAerolinea(aerolinea).size()) {
            JOptionPane.showMessageDialog(vista, "Debe seleccionar un vuelo válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vuelo vuelo = aeropuerto.getVuelosAerolinea(aerolinea).get(index);
        String nuevoEstadoStr = (String) vista.cmbEstadoNuevo.getSelectedItem();

        try {
            Vuelo.EstadoVuelo nuevoEstado = Vuelo.EstadoVuelo.valueOf(nuevoEstadoStr);
            vuelo.cambiarEstado(nuevoEstado);
            JOptionPane.showMessageDialog(vista, "Estado del vuelo actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al cambiar el estado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}