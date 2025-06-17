package controlador;

import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import modelo.OperadorAereo;
import vista.VistaGestorDesbloquearUsuario;
import vista.VistaGestorUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaGestorDesbloquearUsuario {

    private VistaGestorDesbloquearUsuario vista;
    private GestorAeropuerto gestor;
    private VistaGestorUsuarios vistaAnterior;

    public ControladorVistaGestorDesbloquearUsuario(GestorAeropuerto gestor, VistaGestorUsuarios vistaAnterior) {
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;

        List<OperadorAereo> operadoresBloqueados = obtenerOperadoresBloqueados(gestor.getAeropuerto());
        this.vista = new VistaGestorDesbloquearUsuario(operadoresBloqueados);

        this.vista.btnDesbloquear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperadorAereo seleccionado = vista.getOperadorSeleccionado();
                String nuevaContrasena = vista.getNuevaContrasena();

                if (seleccionado == null) {
                    vista.mostrarMensaje("Debe seleccionar un operador.");
                    return;
                }

                if (nuevaContrasena == null || nuevaContrasena.trim().isEmpty()) {
                    vista.mostrarMensaje("Debe ingresar una nueva contraseña.");
                    return;
                }

                if (gestor.desbloquearOperador(seleccionado)) {
                    seleccionado.setContraseña(nuevaContrasena);
                    vista.mostrarMensaje("Operador desbloqueado y contraseña actualizada.");
                    vista.setVisible(false);
                    vistaAnterior.setVisible(true);
                } else {
                    vista.mostrarMensaje("No se pudo desbloquear al operador.");
                }
            }
        });

        this.vista.btnCancelar.addActionListener(e -> {
            vista.setVisible(false);
            vistaAnterior.setVisible(true);
        });
    }

    private List<OperadorAereo> obtenerOperadoresBloqueados(Aeropuerto aeropuerto) {
        List<OperadorAereo> bloqueados = new ArrayList<>();
        for (var usuario : aeropuerto.getUsuarios()) {
            if (usuario instanceof OperadorAereo oa && oa.isBloqueado()) {
                bloqueados.add(oa);
            }
        }
        return bloqueados;
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}