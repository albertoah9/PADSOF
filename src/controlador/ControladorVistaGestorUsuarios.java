package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.*;

public class ControladorVistaGestorUsuarios {

    private VistaGestorUsuarios vista;
    private GestorAeropuerto gestor;
    private Aeropuerto aeropuerto;
    private VistaGestorPrincipal vistaPrincipal;

    public ControladorVistaGestorUsuarios(VistaGestorUsuarios vista, Aeropuerto aeropuerto, GestorAeropuerto gestor, VistaGestorPrincipal vistaPrincipal) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.gestor = gestor;
        this.vistaPrincipal = vistaPrincipal;

        this.vista.btnRegistrarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaGestorRegistrarUsuario vistaRegistrar = new VistaGestorRegistrarUsuario(
                        gestor.getAerolineas(), gestor.getTerminales());
                ControladorVistaGestorRegistrarUsuario controladorRegistrar =
                        new ControladorVistaGestorRegistrarUsuario(vistaRegistrar, gestor, vista);
                controladorRegistrar.iniciar();
            }
        });

        this.vista.btnDesbloquearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaGestorDesbloquearUsuario vistaDesbloquear = new VistaGestorDesbloquearUsuario();
                ControladorVistaGestorDesbloquearUsuario controladorDesbloquear =
                        new ControladorVistaGestorDesbloquearUsuario(vistaDesbloquear, gestor, vista);
                controladorDesbloquear.iniciar();
            }
        });

        this.vista.btnEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaGestorEliminarUsuario vistaEliminar = new VistaGestorEliminarUsuario();
                ControladorVistaGestorEliminarUsuario controladorEliminar =
                        new ControladorVistaGestorEliminarUsuario(vistaEliminar, gestor, vista);
                controladorEliminar.iniciar();
            }
        });

        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaPrincipal.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}