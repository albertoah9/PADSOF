package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.*;

public class ControladorVistaGestorFacturacion {

    private VistaGestorFacturacion vista;
    private GestorAeropuerto gestor;
    private VistaGestorPrincipal vistaAnterior;
    private Aeropuerto aeropuerto;

    public ControladorVistaGestorFacturacion(VistaGestorFacturacion vista, GestorAeropuerto gestor, VistaGestorPrincipal vistaAnterior) {
        this.vista = vista;
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;
        this.aeropuerto = gestor.getAeropuerto();

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

        vista.btnFacturarAerolineas.addActionListener(e -> {
            VistaGestorFacturarAerolineas vistaFacturar =
                new VistaGestorFacturarAerolineas(aeropuerto.getAerolineas(), aeropuerto.getDescuentos());

            ControladorVistaGestorFacturarAerolineas controlador =
                new ControladorVistaGestorFacturarAerolineas(vistaFacturar, gestor, vista);

            vista.setVisible(false);
            controlador.iniciar();
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