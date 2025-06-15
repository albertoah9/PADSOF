package modelo;

import java.util.ArrayList;

public abstract class Usuario {
	private ArrayList<Notificacion> notificaciones;
	private final int id;
	protected String contraseña;
	private String nombre;

	private static int contador = 0;

	private boolean bloqueado = false;
	private boolean necesitaResetear = false;
	private int intentosFallidos = 0;

	/* Constructor */
	public Usuario(String nombre, String contraseña) {
		this.id = ++contador;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.notificaciones = new ArrayList<>();
	}
	
	public void recibirNotificacion(Notificacion notificacion) {
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
	
	public boolean isBloqueado() {
		return bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public boolean necesitaResetear() {
		return necesitaResetear;
	}

	public void setNecesitaResetear(boolean necesitaResetear) {
		this.necesitaResetear = necesitaResetear;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nombre=" + nombre + ", bloqueado=" + bloqueado +
		       ", necesitaResetear=" + necesitaResetear + ", intentosFallidos=" + intentosFallidos + "]";
	}
}