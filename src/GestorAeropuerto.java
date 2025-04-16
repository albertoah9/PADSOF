import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class GestorAeropuerto extends Usuario {
    private Set<String> preferenciasNotificaciones;
    private int diasAnticipados = 30;
    private int horasVentanaTerminal = 48;
    private int minutosDesocupacion = 30;
	
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

    public int getHorasVentanaTerminal() {
        return horasVentanaTerminal;
    }

    public int getMinutosDesocupacion() {
        return minutosDesocupacion;
    }

    public boolean setMinutosDesocupacion(int nuevoMinuto) {
        if (nuevoMinuto >= 1 && nuevoMinuto <= 60) { 
            this.minutosDesocupacion = nuevoMinuto;
            return true;
        } else {
            return false;
        }
    }
    
    public boolean setHorasVentanaTerminal(int nuevasHoras) {
        if (nuevasHoras >= 1 && nuevasHoras <= 168) { // 1 hora a 7 días por ejemplo
            this.horasVentanaTerminal = nuevasHoras;
            return true;
        } else {
            return false;
        }
    }

    public int getDiasAnticipados() {
        return diasAnticipados;
    }

    public boolean puedeCrearVuelo(LocalDateTime fechaHoraVuelo){
        LocalDateTime hoy = LocalDateTime.now();
        LocalDateTime fechaMinima = hoy.plusDays(diasAnticipados);

        return fechaHoraVuelo.isAfter(fechaMinima);
    }

    public boolean puedeConsultarTerminal(LocalDateTime fechaConsulta) {
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime limiteInferior = ahora.minusHours(horasVentanaTerminal);
        LocalDateTime limiteSuperior = ahora.plusHours(horasVentanaTerminal);
    
        return !fechaConsulta.isBefore(limiteInferior) && !fechaConsulta.isAfter(limiteSuperior);
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
