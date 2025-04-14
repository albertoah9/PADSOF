import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class GestorAeropuerto extends Usuario {
	
	private ArrayList<Usuario> usuarios;
    private Set<String> preferenciasNotificaciones;
	
	public GestorAeropuerto(String nombre, String contraseña) {
		super(nombre, contraseña, "Gestor");
		this.usuarios = new ArrayList<>();  // List vacia para que el gestor gestione usuarios
        this.preferenciasNotificaciones = new HashSet<>(); // Set para preferencias de notificaciones
	}
	
	public void modificarUsuario(int idUsuario, String nuevoNombre, String nuevaContraseña) {
	    Usuario usuarioAModificar = null;

	    for (Usuario u : usuarios) {
	        if (u.getId() == idUsuario) {
	            usuarioAModificar = u;
	            break;
	        }
	    }

	    if (usuarioAModificar != null) {
	        usuarioAModificar.setNombre(nuevoNombre); 
	        usuarioAModificar.setContraseña(nuevaContraseña); 
	        System.out.println("El usuario ha sido modificado con los siguientes valores:");
	        System.out.println("Nuevo Nombre: " + nuevoNombre);
	        System.out.println("Nueva Contraseña: " + nuevaContraseña);
	    } else {
	        System.out.println("Error: usuario no encontrado");
	    }
	}
	
	public void añadirUsuario(String tipo, String nombre, String contraseña, int id, Terminal terminal, Aerolinea aerolinea) {
	    Usuario nuevoUsuario = null;

	    for (Usuario u : usuarios) {
	        if (u.getId() == id) {
	            System.out.println("Error, este id ya está en uso");
	            return;
	        }
	    }

	    switch (tipo.toLowerCase()) {
	        case "operador":
	            if (aerolinea != null) {
	                nuevoUsuario = new OperadorAereo(nombre, contraseña, aerolinea);
	            } else {
	                System.out.println("Error: Se necesita una aerolínea para asignar al operador.");
	                return;
	            }
	            break;
	        case "controlador":
	            if (terminal != null) {
	                nuevoUsuario = new ControladorAereo(nombre, contraseña, id, terminal);
	            } else {
	                System.out.println("Error: Se necesita una terminal para asignar al controlador.");
	                return;
	            }
	            break;
	        default:
	            System.out.println("Error: Tipo de usuario no válido.");
	            return;
	    }

	    usuarios.add(nuevoUsuario);

	}
    
    public void eliminarUsuario(int idUsuario) {
        Usuario usuarioAEliminar = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idUsuario) {
                usuarioAEliminar = u;
                break;
            }
        }

        if (usuarioAEliminar != null) {
            usuarios.remove(usuarioAEliminar);
            System.out.println("Usuario con ID " + idUsuario + " eliminado correctamente.");
        } else {
            System.out.println("Error: Usuario no encontrado.");
        }
    }
    
    
    public void verUsuarios() {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Lista de usuarios registrados:");
            for (Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }
    
    public Usuario buscarUsuario(int id) {
        for (Usuario u : usuarios) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;
    }
    
    
    public void cambiarTerminal(int idControlador, Terminal nuevaTerminal) {
        ControladorAereo controlador = (ControladorAereo) buscarUsuario(idControlador);
        if (controlador != null && nuevaTerminal != null) {
            controlador.cambiarTerminal(nuevaTerminal);
            System.out.println("Controlador asignado a la terminal " + nuevaTerminal.getId());
        } else {
            System.out.println("Error: Controlador no encontrado o terminal nula.");
        }
    }

    public void cambiarAerolinea(int idOperador, Aerolinea nuevaAerolinea) {
        OperadorAereo operador = (OperadorAereo) buscarUsuario(idOperador);
        if (operador != null && nuevaAerolinea != null) {
            operador.cambiarAerolinea(nuevaAerolinea);
            System.out.println("Operador asignado a la aerolínea " + nuevaAerolinea.getNombre());
        } else {
            System.out.println("Error: Operador no encontrado o aerolínea nula.");
        }
    }

    public void cambiarEstadoVuelo(int idVuelo, Aeropuerto aeropuerto, Vuelo.EstadoVuelo nuevoEstado, Usuario usuario) {
        if (aeropuerto == null || nuevoEstado == null || idVuelo <= 0) {
            System.out.println("Error: Los datos no pueden ser nulos o menor que 0.");
            return;
        }
        Vuelo vuelo = aeropuerto.buscarVuelo(idVuelo);
        if (vuelo != null) {
            vuelo.setEstado(nuevoEstado, usuario);
        } else {
            System.out.println("Error: Vuelo no encontrado.");
        }
    }

    public void agregarPreferenciaNotificacion(String preferencia) {
        preferenciasNotificaciones.add(preferencia);
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
