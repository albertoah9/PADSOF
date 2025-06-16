package controlador;

import modelo.*;
import vista.VistaControladorVerificaciones;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorVistaControladorVerificaciones {
    private VistaControladorVerificaciones vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaControladorVerificaciones(VistaControladorVerificaciones vista,
            Aeropuerto aeropuerto,
            Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        inicializar();
    }

    private void inicializar() {
        vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            if (vistaAnterior != null) {
                vistaAnterior.setVisible(true);
            }
        });

        cargarPanelPermitirAterrizaje();
        cargarPanelPermitirDespegue();
        cargarPanelConfirmarAterrizaje();
        cargarPanelConfirmarDespegue();
    }

    private void cargarPanelPermitirAterrizaje() {
        JPanel panel = vista.panelPermitirAterrizaje;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_PISTA)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Está seguro de permitir el aterrizaje?",
                        "Confirmar permiso", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPanelPermitirDespegue() {
        JPanel panel = vista.panelPermitirDespegue;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_DESPEGUE)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Está seguro de permitir el despegue?",
                        "Confirmar permiso", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.DESPEGADO);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPanelConfirmarAterrizaje() {
        JPanel panel = vista.panelConfirmarAterrizaje;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Confirmar que el vuelo ha aterrizado?",
                        "Confirmar aterrizaje", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.APARCADO);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPanelConfirmarDespegue() {
        JPanel panel = vista.panelConfirmarDespegue;
        panel.removeAll();

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.DESPEGADO)
                .collect(Collectors.toList());

        for (Vuelo vuelo : vuelos) {
            JCheckBox checkbox = new JCheckBox(vuelo.toString());
            checkbox.addActionListener(_ -> {
                int opcion = JOptionPane.showConfirmDialog(vista,
                        "¿Confirmar que el vuelo ha salido correctamente?",
                        "Confirmar despegue", JOptionPane.YES_NO_OPTION);
                if (opcion == JOptionPane.YES_OPTION) {
                    vuelo.setEstado(Vuelo.EstadoVuelo.EN_HORA);
                    checkbox.setEnabled(false);
                    checkbox.setSelected(true);
                } else {
                    checkbox.setSelected(false);
                }
            });
            panel.add(checkbox);
        }

        panel.revalidate();
        panel.repaint();
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    public void setVistaAnterior(JFrame vistaAnterior) {
        this.vistaAnterior = vistaAnterior;
    }
}
