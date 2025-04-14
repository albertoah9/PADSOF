import java.util.List;  
public class OperadorAereo extends Usuario {
    private Aerolinea aerolineaAsignada;
    private boolean primeraVez = true;


    public OperadorAereo(String nombre, String contraseña, Aerolinea aerolineaAsignada) {
        super(nombre, contraseña, "Operador");
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
            vuelo.setEstado(nuevoEstado, this); // Pasamos 'this' como usuario que realiza el cambio
            Notificacion notificacion = new Notificacion(
               "El vuelo " + vuelo.getId() + " ha cambiado su estado a " + nuevoEstado, 
                0, 
                List.of(this)
            );
            this.enviarNotificacion(notificacion);
        }
    }
    

    public void recibirNotificacion(Notificacion notificacion){
        if(!notificacion.getDestinatarios().contains(this)){
            super.recibirNotificacion(notificacion);
        }
    }

    public boolean iniciarSesion(String contraseña) {
        if (primeraVez) {
            this.contraseña = contraseña;
            primeraVez = false;
            return true; 
        }
        return this.contraseña.equals(contraseña); 
    }

    @Override
    public String toString() {
        return super.toString() + ", Aerolínea asignada: " + (aerolineaAsignada != null ? aerolineaAsignada.getNombre() : "Ninguna");
    }
}