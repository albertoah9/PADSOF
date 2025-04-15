import java.util.HashSet;
import java.util.Set;


public class GestorAeropuerto extends Usuario {
    private Set<String> preferenciasNotificaciones;
	
	public GestorAeropuerto(String nombre, String contraseña) {
		super(nombre, contraseña);
        this.preferenciasNotificaciones = new HashSet<>(); // Set para preferencias de notificaciones
	}    

    public void agregarPreferenciaNotificacion(String preferencia) {
        preferenciasNotificaciones.add(preferencia);
    }

    public boolean iniciarSesion(String contraseña) {
        return this.contraseña.equals(contraseña); // Comparación directa
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
