import java.util.ArrayList;
import java.util.List;

public abstract class Usuario {
	private ArrayList<Notificacion> notificaciones;
	private final int id;
	private String contraseña;
	private String nombre;
	private String rol;

	private static int contador = 0;
	
	/* Constructor */
	public Usuario(String nombre, String contraseña, String rol) {
		this.id = ++contador;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.rol = rol;
		this.notificaciones = new ArrayList<>();
	}
	
	public void enviarNotificacion(Notificacion notificacion) {
		notificaciones.add(notificacion);
	}
	
	public void eliminarNotificacion(Notificacion notificacion) {
        notificaciones.remove(notificacion);  
    }
	
	public void mostrarNotificacion() {
	    for (Notificacion notificacion : notificaciones) {
	        notificacion.mostrarNotificacion();  
        }
	}
	 
	public ArrayList<Notificacion> getNotificaciones() {
		return notificaciones;
	}

	public void recibirNotificacion(Notificacion notificacion){
		notificaciones.add(notificacion);
		System.out.println("Nueva notificacion para " + nombre + ": " + notificacion.getMensaje());
	}
	
	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", contraseña=" + contraseña + ", nombre=" + nombre + "]";
	}
	
}