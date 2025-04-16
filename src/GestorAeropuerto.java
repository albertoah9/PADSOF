import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class GestorAeropuerto extends Usuario {
    private Set<String> preferenciasNotificaciones;
    private int diasAnticipados = 30;
	
	public GestorAeropuerto(String nombre, String contraseña) {
		super(nombre, contraseña);
        this.preferenciasNotificaciones = new HashSet<>(); // Set para preferencias de notificaciones
	}   
    
    public int getDiasAnticipacionMinima() {
        return diasAnticipados;
    }

    public boolean setDiasAnticipacionMinima(int nuevosDias) {
        if (nuevosDias >= 1 && nuevosDias <= 365) {
            this.diasAnticipados = nuevosDias;
            return true;
        } else {
            return false;
        }
    }


    public void agregarPreferenciaNotificacion(String preferencia) {
        preferenciasNotificaciones.add(preferencia);
    }

    public boolean puedeCrearVuelo(LocalDateTime fechaHoraVuelo){
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime fechaMinima = hoy.plusDays(diasAnticipados);

        return fechaHoraVuelo.isAfter(fechaMinima);
    }


    @Override
    public void recibirNotificacion(Notificacion notificacion) {
        for (String preferencia : preferenciasNotificaciones) {
            if (notificacion.getMensaje().contains(preferencia)) {
                super.recibirNotificacion(notificacion);
                return;
            }
        }
    }

}
