package modelo;
import java.util.ArrayList;
import java.util.List;

import modelo.Notificacion;

public abstract class Terminal {
    private static int contador = 1;
    private int id;
    private ArrayList<ControladorAereo> controladores;
    private boolean ocupada;
    private ArrayList<ControladorAereo> observadores;

    public Terminal() {
        this.id = contador++;
        this.controladores = new ArrayList<>();
        this.observadores = new ArrayList<>();
    }

    public ArrayList<ControladorAereo> getControladores() {
        return new ArrayList<>(controladores); // Evita modificaciones externas
    }

    public void agregarControlador(ControladorAereo controlador) {
        if (controlador == null || controladores.contains(controlador)) return;

        if (controlador.getTerminalAsignada() != null) {
            controlador.getTerminalAsignada().eliminarControlador(controlador); // Elimina de la anterior
        }

        controladores.add(controlador);
        controlador.setTerminalAsignada(this);
    }

    public void eliminarControlador(ControladorAereo controlador) {
        if (controlador != null && controladores.remove(controlador)) {
            controlador.setTerminalAsignada(null);
        }
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public int getId() {
        return id;
    }

    public void agregarObservador(ControladorAereo controlador) {
        if (!observadores.contains(controlador)) {
            observadores.add(controlador);
        }
    }

    public void eliminarObservador(ControladorAereo controlador) {
        observadores.remove(controlador);
    }

    public void notificarCambio(String mensaje) {
        for (ControladorAereo controlador : observadores) {
            controlador.recibirNotificacion(new Notificacion(mensaje, List.of(controlador)));
        }
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder("Terminal ID: " + id + ", Controladores: [");
        for (ControladorAereo c : controladores) {
            info.append(c.getNombre()).append(", ");
        }
        if (!controladores.isEmpty()) {
            info.setLength(info.length() - 2); // Elimina la última coma
        }
        info.append("]");
        return info.toString();
    }
}
