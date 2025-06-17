package controlador;

import java.awt.event.*;
import javax.swing.*;
import modelo.*;
import vista.VistaGestorElementosAeropuerto;
import vista.VistaGestorPrincipal;

public class ControladorVistaGestorElementosAeropuerto {

    private VistaGestorElementosAeropuerto vista;
    private Aeropuerto aeropuerto;
    private VistaGestorPrincipal vistaAnterior;

    public ControladorVistaGestorElementosAeropuerto(VistaGestorElementosAeropuerto vista, Aeropuerto aeropuerto, VistaGestorPrincipal vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;

        cargarElementos();

        this.vista.btnNuevoElemento.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] opciones = { "Finger", "Hangar", "Zona Aparcamiento", "Pista Aterrizaje", "Pista Despegue", "Puerta Embarque" };
                String tipo = (String) JOptionPane.showInputDialog(vista, "Selecciona tipo de elemento:", "Nuevo Elemento",
                        JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

                if (tipo == null) return;

                try {
                    switch (tipo) {
                        case "Finger" -> {
                            double altura = Double.parseDouble(JOptionPane.showInputDialog("Altura máxima:"));
                            Finger finger = new Finger(altura);
                            TerminalPasajeros tp = (TerminalPasajeros) aeropuerto.getTerminales().stream()
                                    .filter(t -> t instanceof TerminalPasajeros)
                                    .findFirst()
                                    .orElseThrow(() -> new IllegalStateException("No hay terminal de pasajeros"));
                            tp.addFinger(finger);
                        }
                        case "Hangar" -> {
                            double ancho = Double.parseDouble(JOptionPane.showInputDialog("Ancho:"));
                            double alto = Double.parseDouble(JOptionPane.showInputDialog("Alto:"));
                            double largo = Double.parseDouble(JOptionPane.showInputDialog("Largo:"));
                            int plazas = Integer.parseInt(JOptionPane.showInputDialog("Plazas:"));
                            aeropuerto.addHangar(new Hangar(ancho, alto, largo, plazas));
                        }
                        case "Zona Aparcamiento" -> {
                            double ancho = Double.parseDouble(JOptionPane.showInputDialog("Ancho:"));
                            double largo = Double.parseDouble(JOptionPane.showInputDialog("Largo:"));
                            int plazas = Integer.parseInt(JOptionPane.showInputDialog("Plazas:"));
                            aeropuerto.addZonaAparcamiento(new ZonaAparcamiento(ancho, largo, plazas));
                        }
                        case "Pista Aterrizaje" -> {
                            double longi = Double.parseDouble(JOptionPane.showInputDialog("Longitud:"));
                            double ancho = Double.parseDouble(JOptionPane.showInputDialog("Anchura:"));
                            aeropuerto.addPista(new PistaAterrizaje(longi, ancho));
                        }
                        case "Pista Despegue" -> {
                            double longi = Double.parseDouble(JOptionPane.showInputDialog("Longitud:"));
                            double ancho = Double.parseDouble(JOptionPane.showInputDialog("Anchura:"));
                            aeropuerto.addPista(new PistaDespegue(longi, ancho));
                        }
                        case "Puerta Embarque" -> {
                            int aforo = Integer.parseInt(JOptionPane.showInputDialog("Aforo máximo:"));
                            PuertaEmbarque.TipoPuerta tipoPuerta = JOptionPane.showConfirmDialog(vista, "¿Es para mercancías?")
                                    == JOptionPane.YES_OPTION ? PuertaEmbarque.TipoPuerta.MERCANCIAS : PuertaEmbarque.TipoPuerta.PASAJEROS;
                            Finger finger = aeropuerto.getFingers().stream().findFirst()
                                    .orElseThrow(() -> new IllegalStateException("No hay fingers disponibles"));
                            ZonaAparcamiento zona = aeropuerto.getAparcamientos().stream().findFirst()
                                    .orElseThrow(() -> new IllegalStateException("No hay zonas de aparcamiento"));
                            PuertaEmbarque puerta = new PuertaEmbarque(finger, zona, aforo, tipoPuerta);
                            finger.agregarPuertaEmbarque(puerta);
                            aeropuerto.addPuertaEmbarque(puerta);
                        }
                    }
                    cargarElementos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });
    }

    private void cargarElementos() {
        vista.limpiarLista();

        for (Terminal t : aeropuerto.getTerminales()) {
            if (t instanceof TerminalPasajeros tp) {
                for (Finger f : tp.getFingers()) {
                    vista.agregarElemento(f);
                }
            }
        }

        for (Hangar h : aeropuerto.getHangares()) {
            vista.agregarElemento(h);
        }

        for (Pista p : aeropuerto.getPistas()) {
            vista.agregarElemento(p);
        }

        for (PuertaEmbarque p : aeropuerto.getPuertasEmbarque()) {
            vista.agregarElemento(p);
        }

        for (ZonaAparcamiento z : aeropuerto.getAparcamientos()) {
            vista.agregarElemento(z);
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}