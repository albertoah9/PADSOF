import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorAereo extends Usuario {
    private Terminal terminalAsignada;
    private Map<Vuelo.EstadoVuelo, Integer> vuelosPorEstado;


    public ControladorAereo(String nombre, String contraseña, int id, Terminal terminalAsignada) {
        super(nombre, contraseña, "Controlador");
        this.terminalAsignada = null;// Se asigna con agregarControlador() en Terminal
        terminalAsignada.agregarObservador(this);
        vuelosPorEstado = new HashMap<>();
        for (Vuelo.EstadoVuelo estado : Vuelo.EstadoVuelo.values()) {
            vuelosPorEstado.put(estado, 0); // Inicializa cada estado con un contador en 0
        }
    }

    public Terminal getTerminalAsignada() {
        return terminalAsignada;
    }

    public void setTerminalAsignada(Terminal nuevaTerminal) {
        if (this.terminalAsignada != null) {
            this.terminalAsignada.eliminarControlador(this); // Elimina de la terminal anterior
        }
        this.terminalAsignada = nuevaTerminal;
        if (nuevaTerminal != null) {
            nuevaTerminal.agregarControlador(this); // Agrega a la nueva terminal
        }
    }
    
    public void cambiarTerminal(Terminal nuevaTerminal) {
        this.terminalAsignada = nuevaTerminal;
    }

    public void cambiarEstadoVuelo(Vuelo vuelo, Vuelo.EstadoVuelo nuevoEstado) { //notifica al cambiar estado
        if (vuelo != null) {
            vuelo.setEstado(nuevoEstado, this);  // Pasamos 'this' como usuario
            Notificacion notificacion = new Notificacion("El vuelo " + vuelo.getId() + " ha cambiado su estado a " + nuevoEstado, 0, List.of(this));
            this.enviarNotificacion(notificacion);
        }
    }

    public boolean iniciarSesion(String contraseña) {
        return this.contraseña.equals(contraseña); // Comparación directa
    }

    @Override
    public String toString() {
        return super.toString() + ", Terminal asignada: " + (terminalAsignada != null ? terminalAsignada.getId() : "Ninguna");
    }
}