package controlador;

import modelo.GestorAeropuerto;
import vista.*;

public class ControladorVistaGestorUsuarios {

    private VistaGestorUsuarios vista;
    private GestorAeropuerto gestor;
    private VistaGestorPrincipal vistaPrincipal;

    public ControladorVistaGestorUsuarios(VistaGestorUsuarios vista, GestorAeropuerto gestor, VistaGestorPrincipal vistaPrincipal) {
        this.vista = vista;
        this.gestor = gestor;
        this.vistaPrincipal = vistaPrincipal;

        this.vista.btnRegistrarUsuario.addActionListener(e -> {
            vista.setVisible(false);
            VistaGestorRegistrarUsuario vistaRegistrar = new VistaGestorRegistrarUsuario(
                    gestor.getAerolineas(), gestor.getTerminales());
            new ControladorVistaGestorRegistrarUsuario(vistaRegistrar, gestor, vista).iniciar();
        });

        this.vista.btnDesbloquearUsuario.addActionListener(e -> {
            vista.setVisible(false);
            new ControladorVistaGestorDesbloquearUsuario(gestor, vista).iniciar();
        });

        this.vista.btnEliminarUsuario.addActionListener(e -> {
            vista.setVisible(false);
            new ControladorVistaGestorEliminarUsuario(gestor, vista).iniciar();
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaPrincipal.setVisible(true);
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}