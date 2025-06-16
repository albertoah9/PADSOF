package controlador;

import modelo.*;
import vista.VistaControladorAsignarAlVuelo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorVistaControladorAsignarAlVuelo {
    private VistaControladorAsignarAlVuelo vista;
    private ControladorAereo controladorAereo;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private JFrame vistaAnterior;

    public ControladorVistaControladorAsignarAlVuelo(VistaControladorAsignarAlVuelo vista,
            ControladorAereo controladorAereo,
            Aeropuerto aeropuerto, Aerolinea aerolinea, JFrame vistaAnterior) {
        this.vista = vista;
        this.controladorAereo = controladorAereo;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;
    }

    public void iniciar() {
        inicializar();
        vista.setVisible(true);
    }

    private void inicializar() {
        vista.btnVolver.addActionListener(_ -> {
            vista.dispose();
            if (vistaAnterior != null) {
                vistaAnterior.setVisible(true);
            }
        });
        cargarPestañaAterrizaje();
        cargarPestañaDespegue();
        cargarPestañaAparcamiento();
        cargarPestañaHangar();
        cargarPestañaFinger();
    }

    private void cargarPestañaAterrizaje() {
        JPanel panel = vista.panelAsignarPistaAterrizaje;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE)
                .collect(Collectors.toList());

        DefaultListModel<String> model = new DefaultListModel<>();
        vuelos.forEach(v -> model.addElement(v.toString()));

        JList<String> listaVuelos = new JList<>(model);
        panel.add(new JScrollPane(listaVuelos), BorderLayout.CENTER);

        JButton btnAsignar = new JButton("Asignar Pista de Aterrizaje");
        btnAsignar.addActionListener(_ -> asignarPistaAterrizaje(listaVuelos, vuelos));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnAsignar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPestañaDespegue() {
        JPanel panel = vista.panelAsignarPistaDespegue;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_DESPEGUE)
                .collect(Collectors.toList());

        DefaultListModel<String> model = new DefaultListModel<>();
        vuelos.forEach(v -> model.addElement(v.toString()));

        JList<String> listaVuelos = new JList<>(model);
        panel.add(new JScrollPane(listaVuelos), BorderLayout.CENTER);

        JButton btnAsignar = new JButton("Asignar Pista de Despegue");
        btnAsignar.addActionListener(_ -> asignarPistaDespegue(listaVuelos, vuelos));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnAsignar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPestañaAparcamiento() {
        JPanel panel = vista.panelAsignarAparcamiento;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.EN_PREPARACION)
                .collect(Collectors.toList());

        DefaultListModel<String> model = new DefaultListModel<>();
        vuelos.forEach(v -> model.addElement(v.toString()));

        JList<String> listaVuelos = new JList<>(model);
        panel.add(new JScrollPane(listaVuelos), BorderLayout.CENTER);

        JButton btnAsignar = new JButton("Asignar Aparcamiento");
        btnAsignar.addActionListener(_ -> asignarAparcamiento(listaVuelos, vuelos));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnAsignar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPestañaHangar() {
        JPanel panel = vista.panelAsignarHangar;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.EN_PREPARACION)
                .collect(Collectors.toList());

        DefaultListModel<String> model = new DefaultListModel<>();
        vuelos.forEach(v -> model.addElement(v.toString()));

        JList<String> listaVuelos = new JList<>(model);
        panel.add(new JScrollPane(listaVuelos), BorderLayout.CENTER);

        JButton btnAsignar = new JButton("Asignar Hangar");
        btnAsignar.addActionListener(_ -> asignarHangar(listaVuelos, vuelos));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnAsignar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    private void cargarPestañaFinger() {
        JPanel panel = vista.panelAsignarFinger;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelosAerolinea(aerolinea).stream()
                .filter(v -> v.getEstado() == Vuelo.EstadoVuelo.EN_PREPARACION)
                .collect(Collectors.toList());

        DefaultListModel<String> model = new DefaultListModel<>();
        vuelos.forEach(v -> model.addElement(v.toString()));

        JList<String> listaVuelos = new JList<>(model);
        panel.add(new JScrollPane(listaVuelos), BorderLayout.CENTER);

        JButton btnAsignar = new JButton("Asignar Finger");
        btnAsignar.addActionListener(_ -> asignarFinger(listaVuelos, vuelos));

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnAsignar);
        panel.add(panelBoton, BorderLayout.SOUTH);

        panel.revalidate();
        panel.repaint();
    }

    private void asignarPistaAterrizaje(JList<String> listaVuelos, List<Vuelo> vuelos) {
        int idx = listaVuelos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vuelo primero.");
            return;
        }
        Vuelo vuelo = vuelos.get(idx);

        Pista pistaLibre = aeropuerto.obtenerPistaLibre();
        if (pistaLibre != null) {
            vuelo.setPista(pistaLibre);
            pistaLibre.setOcupada(true);

            controladorAereo.cambiarEstadoVuelo(vuelo, Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE);

            String mensaje = "Se ha asignado la pista " + pistaLibre.getId() +
                    " para aterrizaje al vuelo " + vuelo.getId() + ".";
            new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

            JOptionPane.showMessageDialog(vista,
                    "Pista de aterrizaje asignada y estado cambiado a ESPERANDO_ATERRIZAJE: vuelo " + vuelo.getId());
            cargarPestañaAterrizaje();
        } else {
            JOptionPane.showMessageDialog(vista, "No hay pista de aterrizaje disponible.");
        }
    }

    private void asignarPistaDespegue(JList<String> listaVuelos, List<Vuelo> vuelos) {
        int idx = listaVuelos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vuelo primero.");
            return;
        }
        Vuelo vuelo = vuelos.get(idx);

        Pista pistaLibre = aeropuerto.obtenerPistaLibre();
        if (pistaLibre != null) {
            vuelo.setPista(pistaLibre);
            pistaLibre.setOcupada(true);

            controladorAereo.cambiarEstadoVuelo(vuelo, Vuelo.EstadoVuelo.ESPERANDO_DESPEGUE);

            String mensaje = "Se ha asignado la pista " + pistaLibre.getId() +
                    " para despegue al vuelo " + vuelo.getId() + ".";
            new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

            JOptionPane.showMessageDialog(vista,
                    "Pista de despegue asignada y estado cambiado a ESPERANDO_DESPEGUE: vuelo " + vuelo.getId());
            cargarPestañaDespegue();
        } else {
            JOptionPane.showMessageDialog(vista, "No hay pista de despegue disponible.");
        }
    }

    private void asignarAparcamiento(JList<String> listaVuelos, List<Vuelo> vuelos) {
        int idx = listaVuelos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vuelo primero.");
            return;
        }

        Vuelo vuelo = vuelos.get(idx);
        ZonaAparcamiento zonaLibre = aeropuerto.getZonasAparcamientoDisponibles().stream().findFirst().orElse(null);

        if (zonaLibre != null) {
            int idPlaza = zonaLibre.ocuparPlaza();
            if (idPlaza != -1) {
                vuelo.setAparcamiento(zonaLibre);

                String mensaje = "Se ha asignado aparcamiento en la plaza " + idPlaza +
                        " para el vuelo " + vuelo.getId() + ".";
                new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

                JOptionPane.showMessageDialog(vista,
                        "Aparcamiento asignado al vuelo " + vuelo.getId() + ", plaza ID: " + idPlaza);
                cargarPestañaAparcamiento();
            } else {
                JOptionPane.showMessageDialog(vista, "No hay plazas libres en la zona asignada.");
            }
        } else {
            JOptionPane.showMessageDialog(vista, "No hay zona de aparcamiento disponible.");
        }
    }

    private void asignarHangar(JList<String> listaVuelos, List<Vuelo> vuelos) {
        int idx = listaVuelos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vuelo primero.");
            return;
        }

        Vuelo vuelo = vuelos.get(idx);

        Hangar hangarLibre = aeropuerto.getHangaresDisponibles().stream().findFirst().orElse(null);

        if (hangarLibre != null) {
            vuelo.setHangar(hangarLibre);
            hangarLibre.ocupar(vuelo);

            String mensaje = "Se ha asignado el hangar " + hangarLibre.getId() +
                    " al vuelo " + vuelo.getId() + ".";
            new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

            JOptionPane.showMessageDialog(vista, "Hangar asignado al vuelo " + vuelo.getId());
            cargarPestañaHangar();
        } else {
            JOptionPane.showMessageDialog(vista, "No hay hangar disponible.");
        }
    }

    private void asignarFinger(JList<String> listaVuelos, List<Vuelo> vuelos) {
        int idx = listaVuelos.getSelectedIndex();
        if (idx < 0) {
            JOptionPane.showMessageDialog(vista, "Seleccione un vuelo primero.");
            return;
        }

        Vuelo vuelo = vuelos.get(idx);
        Finger fingerLibre = aeropuerto.getFingersDisponibles().stream().findFirst().orElse(null);

        if (fingerLibre != null) {
            vuelo.setFinger(fingerLibre);
            fingerLibre.ocupar(vuelo);

            String mensaje = "Se ha asignado el finger " + fingerLibre.getId() +
                    " al vuelo " + vuelo.getId() + ".";
            new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

            JOptionPane.showMessageDialog(vista, "Finger asignado al vuelo " + vuelo.getId());
            cargarPestañaFinger();
        } else {
            JOptionPane.showMessageDialog(vista, "No hay finger disponible.");
        }
    }
}
