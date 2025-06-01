package controlador;

import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.Vuelo;
import vista.VistaOperadorMostrarVuelos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControladorVistaOperadorMostrarVuelos {

    private VistaOperadorMostrarVuelos vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaOperadorMostrarVuelos(VistaOperadorMostrarVuelos vista, Aeropuerto aeropuerto, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;

        cargarVuelos();

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
        List<Vuelo> vuelos = aeropuerto.getVuelos();
        vista.limpiarTabla();

        for (Vuelo v : vuelos) {
            if (v.getAerolinea() == aerolinea) {
                Object[] fila = {
                    v.getId(),
                    v.getOrigen(),
                    v.getDestino(),
                    v.getfechaHoraSalida(),
                    v.getfechaHoraLlegada(),
                    v.getTipoVuelo(),
                    v.getClaseVuelo(),
                    v.getEstado()
                };
                vista.agregarVuelo(fila);
            }

        }
    }
}