package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import javax.swing.*;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.AeropuertoDestino;
import modelo.Avion;
import modelo.Vuelo;
import modelo.Vuelo.ClaseVuelo;
import modelo.Vuelo.Periodicidad;
import modelo.Vuelo.TipoVuelo;
import vista.VistaOperadorCrearVuelo;

public class ControladorVistaOperadorCrearVuelo {

    private VistaOperadorCrearVuelo vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private HashMap<String, Avion> mapaAviones;
    private HashMap<String, AeropuertoDestino> mapaAeropuertosDestino;

    public ControladorVistaOperadorCrearVuelo(VistaOperadorCrearVuelo vista, Aeropuerto aeropuerto, Aerolinea aerolinea) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.mapaAviones = new HashMap<>();
        this.mapaAeropuertosDestino = new HashMap<>();

        cargarAviones();
        cargarAeropuertosDestino();
        configurarEventos();
    }

    public void iniciar() {
        vista.setVisible(true);
        vista.cmbTipoVuelo.setSelectedIndex(0);
        vista.cmbTipoVuelo.getActionListeners()[0].actionPerformed(null);
    }

    private void configurarEventos() {
        vista.btnCrear.addActionListener(e -> crearVuelo());
        vista.btnCancelar.addActionListener(e -> vista.dispose());

        vista.cmbTipoVuelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TipoVuelo tipo = TipoVuelo.valueOf(vista.cmbTipoVuelo.getSelectedItem().toString());
                String ciudadAeropuerto = aeropuerto.getCiudad();

                if (tipo == TipoVuelo.LLEGADA) {
                    vista.lblAeropuertoOtro.setText("Aeropuerto de Origen:");
                } else {
                    vista.lblAeropuertoOtro.setText("Aeropuerto de Destino:");
                }

                vista.cmbAeropuertoOtro.removeAllItems();
                for (AeropuertoDestino a : aeropuerto.getAeropuertosDestino()) {
                    vista.cmbAeropuertoOtro.addItem(a.getCiudad() + " - " + a.getNombre());
                    mapaAeropuertosDestino.put(a.getCiudad() + " - " + a.getNombre(), a);
                }
            }
        });
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

    private void cargarAeropuertosDestino() {
        mapaAeropuertosDestino.clear();
        vista.cmbAeropuertoOtro.removeAllItems();
        for (AeropuertoDestino ad : aeropuerto.getAeropuertosDestino()) {
            String nombre = ad.getCiudad() + " - " + ad.getNombre();
            vista.cmbAeropuertoOtro.addItem(nombre);
            mapaAeropuertosDestino.put(nombre, ad);
        }
    }

    private void crearVuelo() {
        try {
            String ciudadFija = aeropuerto.getCiudad();
            String otraCiudad = (String) vista.cmbAeropuertoOtro.getSelectedItem();
            if (otraCiudad == null || otraCiudad.isEmpty()) {
                throw new IllegalArgumentException("Debe seleccionar una ciudad destino/origen.");
            }

            LocalDate fecha = LocalDate.parse(vista.txtFecha.getText().trim());
            LocalTime hora = LocalTime.parse(vista.txtHora.getText().trim());
            LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);

            // Validación de anticipación mínima
            int diasMinimos = aeropuerto.getDiasAnticipacionMinima();
            LocalDate fechaMinima = LocalDate.now().plusDays(diasMinimos);
            if (fecha.isBefore(fechaMinima)) {
                throw new IllegalArgumentException("La fecha debe tener al menos " + diasMinimos + " días de anticipación (mínimo: " + fechaMinima + ").");
            }

            TipoVuelo tipo = TipoVuelo.valueOf(vista.cmbTipoVuelo.getSelectedItem().toString());
            ClaseVuelo clase = ClaseVuelo.valueOf(vista.cmbClaseVuelo.getSelectedItem().toString());
            Periodicidad periodicidad = Periodicidad.valueOf(vista.cmbPeriodicidad.getSelectedItem().toString());
            boolean usaFinger = vista.chkUsaFinger.isSelected();

            String ciudadDestino = mapaAeropuertosDestino.get(otraCiudad).getCiudad();

            String origen, destino;
            LocalDateTime llegada = null, salida = null;

            if (tipo == TipoVuelo.SALIDA) {
                origen = ciudadFija;
                destino = ciudadDestino;
                salida = fechaHora;
            } else {
                origen = ciudadDestino;
                destino = ciudadFija;
                llegada = fechaHora;
            }

            String avionSeleccionado = (String) vista.cmbAvion.getSelectedItem();
            Avion avion = mapaAviones.get(avionSeleccionado);

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
                    aerolinea
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