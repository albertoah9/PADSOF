package modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;

/* Clase padre de Hangar, Finger y Zona Aparcamiento */
public abstract class ElementoAeropuerto {
    private static int contador = 0;

    protected int id;

    public ElementoAeropuerto() {
        this.id = ++contador;
    }

    /* Getters y Setters */
    public int getId() {
        return id;
    }

    /* Métoos */
    public boolean isOcupado(LocalDateTime fechaHora, ArrayList<UsoElementoAeropuerto> usos) {
        for (UsoElementoAeropuerto uso : usos) {
            if (uso.getFechaHoraInicio().isBefore(fechaHora) && uso.getFechaHoraFin().isAfter(fechaHora)) {
                return true;
            }
        }
        
        return false;
    }
}
