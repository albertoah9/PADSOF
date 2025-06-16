package modelo;

import java.util.List;

public class OperadorAereo extends Usuario {
    private Aerolinea aerolineaAsignada;

    private boolean bloqueado = false;
    private int intentosFallidos = 0;
    private boolean necesitaResetear = false;

    public OperadorAereo(String nombre, String contraseña, Aerolinea aerolineaAsignada) {
        super(nombre, contraseña);
        this.aerolineaAsignada = aerolineaAsignada;
        aerolineaAsignada.agregarObservador(this);
    }

    public OperadorAereo(String nombre, Aerolinea aerolineaAsignada) {
        super(nombre, ""); 
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

    public void cambiarEstadoVuelo(Vuelo vuelo, Vuelo.EstadoVuelo nuevoEstado) {
        if (vuelo != null && vuelo.getAerolinea() == this.aerolineaAsignada) {
            vuelo.setEstado(nuevoEstado);
            Notificacion notificacion = new Notificacion("El vuelo " + vuelo.getId() + " ha cambiado su estado a " + nuevoEstado, List.of(this));
        }
    }

    public void recibirNotificacion(Notificacion notificacion) {
        if (notificacion.getDestinatarios().contains(this)) {
            super.recibirNotificacion(notificacion);
        }
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public void incrementarIntentosFallidos() {
        this.intentosFallidos++;
    }

    public boolean necesitaResetear() {
        return necesitaResetear;
    }
    public void setNecesitaResetear(boolean necesitaResetear) {
        this.necesitaResetear = necesitaResetear;
    }

    @Override
    public String toString() {
        return super.toString() + ", Aerolínea asignada: " + (aerolineaAsignada != null ? aerolineaAsignada.getNombre() : "Ninguna");
    }
}