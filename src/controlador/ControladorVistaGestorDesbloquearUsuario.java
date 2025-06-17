package controlador;

import java.util.ArrayList;
import java.util.List;
import modelo.GestorAeropuerto;
import modelo.OperadorAereo;
import vista.VistaGestorDesbloquearUsuario;
import vista.VistaGestorUsuarios;

public class ControladorVistaGestorDesbloquearUsuario {

    private VistaGestorDesbloquearUsuario vista;
    private GestorAeropuerto gestor;
    private VistaGestorUsuarios vistaAnterior;

    public ControladorVistaGestorDesbloquearUsuario(GestorAeropuerto gestor, VistaGestorUsuarios vistaAnterior) {
        this.gestor = gestor;
        this.vistaAnterior = vistaAnterior;

        List<OperadorAereo> bloqueados = obtenerOperadoresBloqueados(gestor);
        this.vista = new VistaGestorDesbloquearUsuario(bloqueados);

        this.vista.btnDesbloquear.addActionListener(e -> {
            OperadorAereo seleccionado = vista.getOperadorSeleccionado();
            if (seleccionado != null) {
                if (gestor.desbloquearOperador(seleccionado)) {
                    vista.mostrarMensaje("Operador desbloqueado correctamente.");
                    vista.setVisible(false);
                    vistaAnterior.setVisible(true);
                } else {
                    vista.mostrarMensaje("No se pudo desbloquear al operador.");
                }
            } else {
                vista.mostrarMensaje("Seleccione un operador.");
            }
        });

        this.vista.btnCancelar.addActionListener(e -> {
            vista.setVisible(false);
            vistaAnterior.setVisible(true);
        });
    }

    private List<OperadorAereo> obtenerOperadoresBloqueados(GestorAeropuerto gestor) {
        List<OperadorAereo> bloqueados = new ArrayList<>();
        for (OperadorAereo oa : gestor.getOperadoresAereos()) {
            if (oa.isBloqueado()) {
                bloqueados.add(oa);
            }
        }
        return bloqueados;
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}