package controlador;

import vista.VistaOperadorCrearVuelo;
import modelo.Vuelo;
import modelo.Vuelo.ClaseVuelo;
import modelo.Vuelo.Periodicidad;
import modelo.Vuelo.TipoVuelo;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Avion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

public class ControladorVistaOperadorCrearVuelo {

    private VistaOperadorCrearVuelo vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private HashMap<String, Avion> mapaAviones;

    public ControladorVistaOperadorCrearVuelo(VistaOperadorCrearVuelo vista, Aeropuerto aeropuerto, Aerolinea aerolinea) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.mapaAviones = new HashMap<>();

        cargarAviones();

        this.vista.btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearVuelo();
            }
        });

        this.vista.btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
            }
        });

        this.vista.cmbTipoVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoVuelo tipo = TipoVuelo.valueOf(vista.cmbTipoVuelo.getSelectedItem().toString());
                String ciudadAeropuerto = aeropuerto.getCiudad();
                if (tipo == TipoVuelo.LLEGADA) {
                    vista.txtDestino.setText(ciudadAeropuerto);
                    vista.txtDestino.setEnabled(false);
                    vista.txtOrigen.setText("");
                    vista.txtOrigen.setEnabled(true);
                } else {
                    vista.txtOrigen.setText(ciudadAeropuerto);
                    vista.txtOrigen.setEnabled(false);
                    vista.txtDestino.setText("");
                    vista.txtDestino.setEnabled(true);
                }
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
        vista.cmbTipoVuelo.setSelectedIndex(0);
        vista.cmbTipoVuelo.getActionListeners()[0].actionPerformed(null);
    }

    private void cargarAviones() {
        vista.cmbAvion.removeAllItems();
        mapaAviones.clear();
        for (Avion avion : aerolinea.getFlota()) {
            String nombre = "Avión " + avion.getId() + " - " + avion.getModelo();
            vista.cmbAvion.addItem(nombre);
            mapaAviones.put(nombre, avion);
        }
    }

    private void crearVuelo() {
        try {
            String origen = vista.txtOrigen.getText().trim();
            String destino = vista.txtDestino.getText().trim();

            LocalDate fecha = LocalDate.parse(vista.txtFecha.getText().trim());
            LocalTime hora = LocalTime.parse(vista.txtHora.getText().trim());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            TipoVuelo tipo = TipoVuelo.valueOf(vista.cmbTipoVuelo.getSelectedItem().toString());
            ClaseVuelo clase = ClaseVuelo.valueOf(vista.cmbClaseVuelo.getSelectedItem().toString());
            Periodicidad periodicidad = Periodicidad.valueOf(vista.cmbPeriodicidad.getSelectedItem().toString());
            boolean usaFinger = vista.chkUsaFinger.isSelected();

            LocalDateTime llegada = (tipo == TipoVuelo.LLEGADA) ? fechaHora : null;
            LocalDateTime salida = (tipo == TipoVuelo.SALIDA) ? fechaHora : null;

            String avionSeleccionado = (String) vista.cmbAvion.getSelectedItem();
            Avion avion = mapaAviones.get(avionSeleccionado);

            if (avion == null) {
                throw new IllegalArgumentException("Debes seleccionar un avión válido.");
            }

            Vuelo vuelo = new Vuelo(
                origen,
                destino,
                llegada,
                salida,
                null,
                avion,
                null,
                null,
                Vuelo.EstadoVuelo.EN_PREPARACION,
                aeropuerto,
                tipo,
                clase,
                this.aerolinea
            );

            vuelo.setPeriodicidad(periodicidad);
            vuelo.setUsaFinger(usaFinger);

            aeropuerto.addVuelo(vuelo);

            JOptionPane.showMessageDialog(vista, "Vuelo creado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha u hora inválido. Usa AAAA-MM-DD y HH:MM", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}