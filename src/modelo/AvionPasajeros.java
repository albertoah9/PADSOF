package modelo;
import java.time.LocalDate;

import Aerolinea;

public class AvionPasajeros extends Avion {
    private int capacidad;

    public AvionPasajeros(String marca, String modelo, String matricula, int autonomia, LocalDate ultimaRevis,
            LocalDate anyoCompra, int capacidad, Aerolinea aerolinea) {
        super(marca, modelo, matricula, autonomia, ultimaRevis, anyoCompra, aerolinea);
        this.capacidad = capacidad;
    }

    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    
}
