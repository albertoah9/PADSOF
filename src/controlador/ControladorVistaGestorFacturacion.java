package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.GestorAeropuerto;
import vista.*;

public class ControladorVistaGestorFacturacion {

    private VistaGestorFacturacion vista;
    private GestorAeropuerto gestor;
    private VistaGestorPrincipal vistaAnterior;

    public ControladorVistaGestorFacturacion(VistaGestorFacturacion vista, GestorAeropuerto gestor, VistaGestorPrincipal vistaAnterior) {
        this.vista = vista;
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;

        this.vista.btnGestionarDescuentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaGestorDescuentos vistaDescuentos = new VistaGestorDescuentos(gestor.getDescuentos());
                ControladorVistaGestorDescuentos controladorDescuentos = new ControladorVistaGestorDescuentos(
                        vistaDescuentos, gestor.getAeropuerto(), vista);
                vista.setVisible(false);
                controladorDescuentos.iniciar();
            }
        });

        this.vista.btnFacturarAerolineas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hola");
            }
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}