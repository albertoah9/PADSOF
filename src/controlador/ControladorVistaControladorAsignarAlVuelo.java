package controlador;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import modelo.*;
import vista.VistaControladorAsignarAlVuelo;

/**
 * Controlador encargado de gestionar la asignación de recursos a vuelos,
 * tales como pistas, hangares, zonas de aparcamiento y fingers.
 * Permite al usuario realizar asignaciones según el estado del vuelo.
 */
public class ControladorVistaControladorAsignarAlVuelo {
    private VistaControladorAsignarAlVuelo vista;
    private ControladorAereo controladorAereo;
    private Aeropuerto aeropuerto;
    private JFrame vistaAnterior;

    /**
     * Constructor del controlador.
     *
     * @param vista            Vista de asignación
     * @param controladorAereo Controlador aéreo responsable de cambiar estados de
     *                         vuelos
     * @param aeropuerto       Aeropuerto sobre el que se gestionan los vuelos y
     *                         recursos
     * @param vistaAnterior    Vista previa a esta, que se mostrará al volver
     */
    public ControladorVistaControladorAsignarAlVuelo(VistaControladorAsignarAlVuelo vista,
            ControladorAereo controladorAereo,
            Aeropuerto aeropuerto, JFrame vistaAnterior) {
        this.vista = vista;
        this.controladorAereo = controladorAereo;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;
    }

    /**
     * Inicia la vista y carga las pestañas de asignación.
     */
    public void iniciar() {
        inicializar();
        vista.setVisible(true);
    }

    /**
     * Configura los listeners de botones y carga las pestañas de la vista.
     */
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

    /**
     * Carga y muestra los vuelos en espera de aterrizaje, y permite asignar una
     * pista.
     */
    private void cargarPestañaAterrizaje() {
        JPanel panel = vista.panelAsignarPistaAterrizaje;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
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

    /**
     * Carga y muestra los vuelos en espera de despegue, y permite asignar una
     * pista.
     */
    private void cargarPestañaDespegue() {
        JPanel panel = vista.panelAsignarPistaDespegue;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
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

    /**
     * Carga y muestra los vuelos en preparación, y permite asignar una zona de
     * aparcamiento.
     */
    private void cargarPestañaAparcamiento() {
        JPanel panel = vista.panelAsignarAparcamiento;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
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

    /**
     * Carga y muestra los vuelos en preparación, y permite asignar un hangar.
     */
    private void cargarPestañaHangar() {
        JPanel panel = vista.panelAsignarHangar;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
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

    /**
     * Carga y muestra los vuelos en preparación, y permite asignar un finger.
     */
    private void cargarPestañaFinger() {
        JPanel panel = vista.panelAsignarFinger;
        panel.removeAll();
        panel.setLayout(new BorderLayout());

        List<Vuelo> vuelos = aeropuerto.getVuelos().stream()
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

    /**
     * Asigna una pista de aterrizaje a un vuelo seleccionado.
     *
     * @param listaVuelos Lista visual de vuelos
     * @param vuelos      Lista lógica de vuelos filtrados
     */
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

    /**
     * Asigna una pista de despegue a un vuelo seleccionado.
     *
     * @param listaVuelos Lista visual de vuelos
     * @param vuelos      Lista lógica de vuelos filtrados
     */
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

    /**
     * Asigna una plaza de aparcamiento disponible a un vuelo.
     *
     * @param listaVuelos Lista visual de vuelos
     * @param vuelos      Lista lógica de vuelos filtrados
     */
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

                vuelo.setEstado(Vuelo.EstadoVuelo.APARCADO);

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

    /**
     * Asigna un hangar disponible a un vuelo.
     *
     * @param listaVuelos Lista visual de vuelos
     * @param vuelos      Lista lógica de vuelos filtrados
     */
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

            vuelo.setEstado(Vuelo.EstadoVuelo.EN_HANGAR);

            String mensaje = "Se ha asignado el hangar " + hangarLibre.getId() +
                    " al vuelo " + vuelo.getId() + ".";
            new Notificacion(mensaje, List.of(aeropuerto.getUsuarioActivo()));

            JOptionPane.showMessageDialog(vista, "Hangar asignado al vuelo " + vuelo.getId());
            cargarPestañaHangar();
        } else {
            JOptionPane.showMessageDialog(vista, "No hay hangar disponible.");
        }
    }

    /**
     * Asigna un finger disponible a un vuelo.
     *
     * @param listaVuelos Lista visual de vuelos
     * @param vuelos      Lista lógica de vuelos filtrados
     */
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

            vuelo.setEstado(Vuelo.EstadoVuelo.EMBARCANDO);

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
