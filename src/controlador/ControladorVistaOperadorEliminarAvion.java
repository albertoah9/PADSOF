package controlador;

import modelo.Aerolinea;
import modelo.Avion;
import vista.VistaOperadorEliminarAvion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorVistaOperadorEliminarAvion {

    private VistaOperadorEliminarAvion vista;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;
    private List<Avion> flota;

    public ControladorVistaOperadorEliminarAvion(VistaOperadorEliminarAvion vista, Aerolinea aerolinea, JFrame vistaAnterior) { 
        this.vista = vista;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;
        this.flota = aerolinea.getFlota();

        cargarAviones();

        this.vista.btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarAvion();
            }
        });

        this.vista.btnCancelar.addActionListener(new ActionListener() {
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

    private void cargarAviones() {
        vista.limpiarCombo();
        for (Avion a : flota) {
            String descripcion = "ID: " + a.getId() + " | Matrícula: " + a.getMatricula() +
                                 " | Marca: " + a.getMarca() + " | Modelo: " + a.getModelo();
            vista.agregarAvion(descripcion);
        }
    }

    private void eliminarAvion() {
        int index = vista.getAvionSeleccionadoIndex();
        if (index >= 0 && index < flota.size()) {
            Avion avion = flota.get(index);
            aerolinea.removerAvion(avion);
            JOptionPane.showMessageDialog(vista, "Avión eliminado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
            vistaAnterior.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(vista, "Por favor selecciona un avión válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
