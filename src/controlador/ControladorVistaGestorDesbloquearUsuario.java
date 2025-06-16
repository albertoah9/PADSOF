package controlador;

import modelo.GestorAeropuerto;
import modelo.OperadorAereo;
import vista.VistaGestorDesbloquearUsuario;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaGestorDesbloquearUsuario {

    private VistaGestorDesbloquearUsuario vista;
    private GestorAeropuerto gestor;

    public ControladorVistaGestorDesbloquearUsuario(VistaGestorDesbloquearUsuario vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        for (OperadorAereo op : gestor.getAeropuerto().getOperadores()) {
            if (op.isBloqueado()) {
                vista.cmbUsuariosBloqueados.addItem(op.getNombre());
            }
        }

        this.vista.btnDesbloquear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreSeleccionado = (String) vista.cmbUsuariosBloqueados.getSelectedItem();
                if (nombreSeleccionado != null) {
                    boolean exito = gestor.desbloquearOperador(nombreSeleccionado);
                    vista.mostrarMensaje(exito ? "Usuario desbloqueado con éxito." : "No se pudo desbloquear.");
                    vista.dispose();
                }
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}