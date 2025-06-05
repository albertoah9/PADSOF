package controlador;

import modelo.*;
import vista.VistaOperadorRegistrarAvion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ControladorVistaOperadorRegistrarAvion {

    private VistaOperadorRegistrarAvion vista;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaOperadorRegistrarAvion(VistaOperadorRegistrarAvion vista, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        vista.cmbTipoAvion.addActionListener(e -> actualizarPaneles());

        vista.btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarAvion();
            }
        });

        vista.btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
        actualizarPaneles();
    }

    private void actualizarPaneles() {
        String tipo = (String) vista.cmbTipoAvion.getSelectedItem();
        vista.panelPasajeros.setVisible("PASAJEROS".equals(tipo));
        vista.panelCarga.setVisible("CARGA".equals(tipo));
        vista.repaint();
    }

    private void registrarAvion() {
        try {
            Avion nuevoAvion;

            String marca = vista.txtMarca.getText().trim();
            String modelo = vista.txtModelo.getText().trim();
            String matricula = vista.txtMatricula.getText().trim();
            int autonomia = Integer.parseInt(vista.txtAutonomia.getText().trim());
            LocalDate revision = LocalDate.parse(vista.txtUltimaRevision.getText().trim());
            LocalDate compra = LocalDate.parse(vista.txtAnyoCompra.getText().trim());

            String tipo = (String) vista.cmbTipoAvion.getSelectedItem();

            if ("PASAJEROS".equals(tipo)) {
                int capacidad = Integer.parseInt(vista.txtCapacidad.getText().trim());
                nuevoAvion = new AvionPasajeros(marca, modelo, matricula, autonomia, revision, compra, capacidad, aerolinea);
            } else {
                double cargaMax = Double.parseDouble(vista.txtCargaMax.getText().trim());
                boolean peligrosas = vista.chkMercPeligrosas.isSelected();
                boolean controlTemp = vista.chkControlTemperatura.isSelected();
                nuevoAvion = new AvionCarga(marca, modelo, matricula, autonomia, revision, compra, controlTemp, cargaMax, peligrosas, aerolinea);
            }

            aerolinea.añadirAvion(nuevoAvion);
            JOptionPane.showMessageDialog(vista, "Avión registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();
            vistaAnterior.setVisible(true);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}