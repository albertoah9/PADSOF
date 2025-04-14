
import java.util.ArrayList;
import java.util.List;

public class Notificacion {
	private int id;
    private boolean activada;
    private boolean leida;
    private String mensaje;
    private List<Usuario> destinatarios;
	
	public Notificacion(String mensaje, int id, List<Usuario> destinatarios) {
		this.mensaje = mensaje;
		this.id = id;
		this.destinatarios = destinatarios;
		this.activada = true;
		this.leida = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isActivada() {
		return activada;
	}

	public void setActivada(boolean activada) {
		this.activada = activada;
	}

	public boolean isLeida() {
		return leida;
	}

	public void setLeida(boolean leida) {
		this.leida = leida;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<Usuario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Usuario> destinatarios) {
		this.destinatarios = destinatarios;
	}
	
	public void mostrarNotificacion() {
	        System.out.println("Notificación ID: " + id);
	        System.out.println("Mensaje: " + mensaje);
	        System.out.println("Destinatarios: ");
	        for (Usuario destinatario : destinatarios) {
	            System.out.println("- " + destinatario.getNombre());
	        }
	        System.out.println("Activada: " + (activada ? "Sí" : "No"));
	        System.out.println("Leída: " + (leida ? "Sí" : "No"));
	    }

	public void activarNotificacion() { 
		 this.activada = true;
	}

	public void desactivarNotificacion() {
	     this.activada = false;
    }
	 
	public void marcarComoLeida() {
	     this.leida = true;
    }

    public void marcarComoNoLeida() {
	     this.leida = false;
    }
    
    public String obtenerEstadoNotificacion() {
        return "ID: " + id + ", Activada: " + (activada ? "Sí" : "No") + ", Leída: " + (leida ? "Sí" : "No");
    }

    
    public void agregarDestinatario(Usuario nuevoDestinatario) {
        if (!destinatarios.contains(nuevoDestinatario)) {
            destinatarios.add(nuevoDestinatario);
        }
    }
    
    public void eliminarDestinatario(Usuario destinatario) {
        destinatarios.remove(destinatario);
    }
    
    public void marcarTodasComoLeidas(List<Notificacion> notificaciones) {
        for (Notificacion notificacion : notificaciones) {
            notificacion.marcarComoLeida();
        }
    }
    
    public static List<Notificacion> obtenerNotificacionesNoLeidas(List<Notificacion> notificaciones) {
        List<Notificacion> noLeidas = new ArrayList<>();
        for (Notificacion notificacion : notificaciones) {
            if (!notificacion.isLeida()) {
                noLeidas.add(notificacion);
            }
        }
        return noLeidas;
    }
    
    public static void cambiarEstadoDeTodas(List<Notificacion> notificaciones, boolean activar) {
        for (Notificacion notificacion : notificaciones) {
            notificacion.setActivada(activar);
        }
    }





	
	
	

	
}
