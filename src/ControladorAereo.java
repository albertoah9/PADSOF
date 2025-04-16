import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControladorAereo extends Usuario {
    private Terminal terminalAsignada;
    private Map<Vuelo.EstadoVuelo, Integer> vuelosPorEstado;


    public ControladorAereo(String nombre, String contraseña, Terminal terminalAsignada) {
        super(nombre, contraseña);
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
            vuelo.setEstado(nuevoEstado); 
            Notificacion notificacion = new Notificacion("El vuelo " + vuelo.getId() + " ha cambiado su estado a " + nuevoEstado,  List.of(this));
            this.enviarNotificacion(notificacion);
        }
    }


    public void notificarCambioEstado(Vuelo vuelo) {
        Vuelo.EstadoVuelo estadoActual = vuelo.getEstado();
        
        // Actualizar el contador de vuelos para ese estado
        vuelosPorEstado.put(estadoActual, vuelosPorEstado.get(estadoActual) + 1);
        
        switch (estadoActual) {
            case DESPEGADO:
                System.out.println("El vuelo con ID " + vuelo.getId() + " ha despegado exitosamente.");
                break;
            case RETRASADO:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está retrasado.");
                break;
            case EN_HORA:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está en hora.");
                break;
            case ESPERANDO_PISTA:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está esperando pista.");
                break;
            case EN_PREPARACION:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está en preparación.");
                break;
            case APARCADO:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está aparcado.");
                break;
            case EN_HANGAR:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está en el hangar.");
                break;
            case EMBARCANDO:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está embarcando.");
                break;
            case ESPERANDO_ATERRIZAJE:
                System.out.println("El vuelo con ID " + vuelo.getId() + " está esperando aterrizaje.");
                break;
            default:
                System.out.println("El vuelo con ID " + vuelo.getId() + " tiene un estado desconocido.");
        }

    
    }

    

  

    @Override
    public String toString() {
        return super.toString() + ", Terminal asignada: " + (terminalAsignada != null ? terminalAsignada.getId() : "Ninguna");
    }
}