package controlador;

import vista.VistaCrearVuelo;
import modelo.Vuelo;
import modelo.Vuelo.ClaseVuelo;
import modelo.Vuelo.Periodicidad;
import modelo.Vuelo.TipoVuelo;
import modelo.Aeropuerto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ControladorVistaCrearVuelo {

    private VistaCrearVuelo vista;
    private Aeropuerto aeropuerto;

    public ControladorVistaCrearVuelo(VistaCrearVuelo vista, Aeropuerto aeropuerto) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;

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
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    private void crearVuelo() {
        try {
            String origen = vista.txtOrigen.getText().trim();
            String destino = vista.txtDestino.getText().trim();
            LocalDateTime llegada = LocalDateTime.parse(vista.txtFechaLlegada.getText().trim());
            LocalDateTime salida = LocalDateTime.parse(vista.txtFechaSalida.getText().trim());

            TipoVuelo tipo = TipoVuelo.valueOf(vista.cmbTipoVuelo.getSelectedItem().toString());
            ClaseVuelo clase = ClaseVuelo.valueOf(vista.cmbClaseVuelo.getSelectedItem().toString());
            Periodicidad periodicidad = Periodicidad.valueOf(vista.cmbPeriodicidad.getSelectedItem().toString());
            boolean usaFinger = vista.chkUsaFinger.isSelected();

            Vuelo vuelo = new Vuelo(
                origen,
                destino,
                llegada,
                salida,
                null, // Terminal
                null, // Avion
                null, // Pista
                null, // PuertaEmbarque
                Vuelo.EstadoVuelo.EN_PREPARACION,
                aeropuerto,
                tipo,
                clase,
                null // Aerolinea
            );

            vuelo.setPeriodicidad(periodicidad);
            vuelo.setUsaFinger(usaFinger);

            aeropuerto.addVuelo(vuelo);
            JOptionPane.showMessageDialog(vista, "Vuelo creado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.dispose();

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(vista, "Formato de fecha/hora inválido. Usa AAAA-MM-DDTHH:MM", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
