
import java.util.List;

public class OperadorAereo extends Usuario {
    private Aerolinea aerolineaAsignada;


    public OperadorAereo(String nombre, String contraseña, Aerolinea aerolineaAsignada) {
        super(nombre, contraseña);
        this.aerolineaAsignada = aerolineaAsignada;
        aerolineaAsignada.agregarObservador(this);
    }

    public OperadorAereo(String nombre, Aerolinea aerolineaAsignada) {
        super(nombre, ""); // o puedes usar null si tu clase Usuario lo permite
        this.aerolineaAsignada = aerolineaAsignada;
        aerolineaAsignada.agregarObservador(this);
    }

    public Aerolinea getAerolineaAsignada() {
        return aerolineaAsignada;
    }

    public void setAerolineaAsignada(Aerolinea aerolineaAsignada) {
        this.aerolineaAsignada = aerolineaAsignada;
    }
    
    public void asignarAvion(Aerolinea aerolinea, Avion avion) {
        if (aerolinea != null && avion != null) {
            aerolinea.añadirAvion(avion);
            System.out.println("Avión " + avion.getId() + " asignado a la aerolínea " + aerolinea.getNombre());
        } else {
            System.out.println("No se puede asignar el avión.");
        }
    }
    
    public void cambiarAerolinea(Aerolinea nuevaAerolinea) {
        this.aerolineaAsignada = nuevaAerolinea;
    }

    public void cambiarEstadoVuelo(Vuelo vuelo, Vuelo.EstadoVuelo nuevoEstado) { // Notifica al cambiar estado
        if (vuelo != null && vuelo.getAerolinea() == this.aerolineaAsignada) {
            vuelo.setEstado(nuevoEstado); // Pasamos 'this' como usuario que realiza el cambio
            Notificacion notificacion = new Notificacion("El vuelo " + vuelo.getId() + " ha cambiado su estado a " + nuevoEstado, List.of(this));
            this.enviarNotificacion(notificacion);
        }
    }
    

    public void recibirNotificacion(Notificacion notificacion){
        if(!notificacion.getDestinatarios().contains(this)){
            super.recibirNotificacion(notificacion);
        }
    }



    @Override
    public String toString() {
        return super.toString() + ", Aerolínea asignada: " + (aerolineaAsignada != null ? aerolineaAsignada.getNombre() : "Ninguna");
    }
}