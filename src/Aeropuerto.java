import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

import modelo.ControladorAereo;
import modelo.ElementoAeropuerto;
import modelo.Factura;
import modelo.Finger;
import modelo.GestorAeropuerto;
import modelo.Hangar;
import modelo.Notificacion;
import modelo.OperadorAereo;
import modelo.Pista;
import modelo.PuertaEmbarque;
import modelo.Terminal;
import modelo.UsoElementoAeropuerto;
import modelo.Usuario;
import modelo.Vuelo;
import modelo.ZonaAparcamiento;

public class Aeropuerto {
    public enum Status {
        OK, ERROR
    }
    public enum UbiRelCiudad {
        NORTE, SUR, ESTE, OESTE
    }

    private String nombre;
    private String ciudad;
    private String pais;
    private UbiRelCiudad ubiRelCiudad;

    private ArrayList<PuertaEmbarque> puertasEmbarque;
    private ArrayList<Terminal> terminales;
    private ArrayList<Pista> pistas;
    private ArrayList<AeropuertoDestino> aeropuertosDestino;
    private ArrayList<Vuelo> vuelos;
    private ArrayList<Aerolinea> aerolineas;
    private ArrayList<Notificacion> notificaciones;
    private ArrayList<ControladorAereo> controladores;
    private GestorAeropuerto gestor;
    private ArrayList<Hangar> hangares;
    private ArrayList<ZonaAparcamiento> aparcamientos;
    private ArrayList<Usuario> usuarios;
    private Usuario usuarioActivo;
    private ArrayList<UsoElementoAeropuerto> usosElementosAeropuerto;


    private int diasAnticipacionMinima = 30;



    // CAMBIAR POR SISTEMA DE PAGO
    private ArrayList<Factura> facturas;

    private double costeVuelo = 35.0;

    // Constructor
    public Aeropuerto(String nombre, String ciudad, String pais, UbiRelCiudad ubiRelCiudad) {
        this.puertasEmbarque = new ArrayList<>();
        this.terminales = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.aeropuertosDestino = new ArrayList<>();
        this.vuelos = new ArrayList<>();
        this.aerolineas = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.controladores = new ArrayList<>();
        this.hangares = new ArrayList<>();
        this.aparcamientos = new ArrayList<>();
        this.facturas = new ArrayList<>();
        this.usosElementosAeropuerto = new ArrayList<>();
        this.gestor = new GestorAeropuerto("Gestor Aeropuerto", "gestor123");
        this.usuarioActivo = null;
        this.usuarios = new ArrayList<>();
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ubiRelCiudad = ubiRelCiudad;
    }

    public int getDiasAnticipacionMinima() {
        return diasAnticipacionMinima;
    }

    public void setDiasAnticipacionMinima(int dias) {
        diasAnticipacionMinima = dias;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public UbiRelCiudad getUbiRelCiudad() {
        return ubiRelCiudad;
    }

    public void setUbiRelCiudad(UbiRelCiudad ubiRelCiudad) {
        this.ubiRelCiudad = ubiRelCiudad;
    }

    public ArrayList<PuertaEmbarque> getPuertasEmbarque() {
        return puertasEmbarque;
    }

    public ArrayList<Terminal> getTerminales() {
        return terminales;
    }

    public ArrayList<Pista> getPistas() {
        return pistas;
    }

    public ArrayList<AeropuertoDestino> getAeropuertosDestino() {
        return aeropuertosDestino;
    }

    public ArrayList<Vuelo> getVuelos() {
        return vuelos;
    }

    public ArrayList<Aerolinea> getAerolineas() {
        return aerolineas;
    }

    public ArrayList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public ArrayList<ControladorAereo> getControladores() {
        return controladores;
    }

    public GestorAeropuerto getGestor() {
        return gestor;
    }

    public void setGestor(GestorAeropuerto gestor) {
        this.gestor = gestor;
    }

    public ArrayList<Hangar> getHangares() {
        return hangares;
    }

    public ArrayList<ZonaAparcamiento> getAparcamientos() {
        return aparcamientos;
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuario) {
        this.usuarioActivo = usuario;
    }

    public ArrayList<Factura> getFacturas() {
        return facturas;
    }

    public double getCosteVuelo() {
        return costeVuelo;
    }

    public void setCosteVuelo(double costeVuelo) {
        if(usuarioActivo instanceof GestorAeropuerto) {
            this.costeVuelo = costeVuelo;
        } else { 
            System.err.println("Usuario con permisos insuficientes");
        }
    }

    public Status iniciarSesion(String nombreUsuario, String contraseña) {
        for (ControladorAereo controladorAereo : controladores) {
            if (controladorAereo.getNombre().equals(nombreUsuario) && controladorAereo.getContraseña().equals(contraseña)) {
                usuarioActivo = controladorAereo;
                return Status.OK;
            }
        }

        for (Aerolinea aerolinea : aerolineas) {
            for (OperadorAereo operador : aerolinea.getOperadores()) {
                if (operador.getNombre().equals(nombreUsuario) && operador.getContraseña().equals(contraseña)) {
                    usuarioActivo = operador;
                    return Status.OK;
                }
            }
        }

        if (gestor != null &&
            gestor.getNombre().equals(nombreUsuario) &&
            gestor.getContraseña().equals(contraseña)) {
            usuarioActivo = gestor;
            return Status.OK;
        }

        return Status.ERROR;
    }

    /* Métodos */
    public Status addPuertaEmbarque(PuertaEmbarque puerta) {
        if(usuarioActivo instanceof GestorAeropuerto) {
            this.puertasEmbarque.add(puerta);
            return Status.OK;
        }
        System.err.println("Usuario con permisos insuficientes");
        return Status.ERROR;
    }

    public Status addTerminal(Terminal terminal) {
        if(usuarioActivo instanceof GestorAeropuerto) {
            this.terminales.add(terminal);
            return Status.OK;
        }
        System.err.println("Usuario con permisos insuficientes");
        return Status.ERROR;
    }

    public void addPista(Pista pista) {
        this.pistas.add(pista);
    }

    public void addAeropuertoDestino(AeropuertoDestino aeropuertoDestino) {
        this.aeropuertosDestino.add(aeropuertoDestino);
    }

    public void addVuelo(Vuelo vuelo) throws IllegalArgumentException{
        if (usuarioActivo instanceof OperadorAereo) {
            // DIferencia en dias respecto a diasAnticipacion Minima
            if (vuelo.getFechaHora().isBefore(LocalDateTime.now().plusDays(diasAnticipacionMinima))) {
                throw new IllegalArgumentException("Error, la fecha de salida no puede ser menor a " + diasAnticipacionMinima + " días");
            }

            this.vuelos.add(vuelo);
            ArrayList<Usuario> usuariosDest = new ArrayList<>();
            usuariosDest.add(gestor);
            usuariosDest.add(usuarioActivo);
            Notificacion notificacion = new Notificacion("Nuevo vuelo programado: " + vuelo.getId(), usuariosDest);
            this.notificaciones.add(notificacion);
            notificarUsuarios(notificacion, usuariosDest);        
        }
    }

    public void addAerolinea(Aerolinea aerolinea) {
        this.aerolineas.add(aerolinea);
    }

    public void addNotificacion(Notificacion notificacion) {
        this.notificaciones.add(notificacion);
    }

    public void addControlador(ControladorAereo controlador) {
        this.controladores.add(controlador);
    }

    public void addHangar(Hangar hangar) {
        this.hangares.add(hangar);
    }

    public void addZonaAparcamiento(ZonaAparcamiento aparcamiento) {
        this.aparcamientos.add(aparcamiento);
    }

    public ArrayList<UsoElementoAeropuerto> getUsosElementosAeropuerto() {
        return usosElementosAeropuerto;
    }

    public ArrayList<UsoElementoAeropuerto> getUsosElementosAeropuertoElemento(ElementoAeropuerto elemento) {
        ArrayList<UsoElementoAeropuerto> usosElementosAeropuerto = new ArrayList<>();
        for (UsoElementoAeropuerto uso : usosElementosAeropuerto) {
            if (uso.getElementoAeropuerto().equals(elemento)) {
                usosElementosAeropuerto.add(uso);
            }
        }

        return usosElementosAeropuerto;
    }

    /* Asignar terminal a un vuelo */
    public void asignarTerminalAVuelo(Vuelo vuelo) {
        Terminal terminalAsignada = buscarTerminalDisponible();
        ArrayList<Usuario> usuariosDest = new ArrayList<>();
        usuariosDest.add(gestor);
        usuariosDest.add(usuarioActivo);
        if (terminalAsignada != null) {
            vuelo.setTerminal(terminalAsignada);
            Notificacion notificacion = new Notificacion("Terminal asignada al vuelo: " + vuelo.getId(), usuariosDest);
            notificaciones.add(notificacion);
            notificarUsuarios(notificacion, usuariosDest);
        } else {
            // No hay terminal disponible
            Notificacion notificacion = new Notificacion("No hay terminal disponible para el vuelo: " + vuelo.getId(), usuariosDest);
            notificaciones.add(notificacion);
            notificarUsuarios(notificacion, usuariosDest);
        }
    }

    

    // Buscar terminal disponible
    private Terminal buscarTerminalDisponible() {
        LocalDateTime fechaConsulta = LocalDateTime.now();

        for (Terminal terminal : terminales) {
            if (!terminal.isOcupada() && gestor.puedeConsultarTerminal(fechaConsulta)) {
                return terminal;
            }
        }
        return null; // No hay terminal disponible
    }

   

    // Asignar pista de aterrizaje
    public void asignarPistaAterrizaje(Vuelo vuelo) {
            ArrayList<Usuario> usuariosDest = new ArrayList<>();
            usuariosDest.add(gestor);
            usuariosDest.add(usuarioActivo);
        for (Pista pista : pistas) {
            if (pista.isOcupada()) {
                vuelo.setPista(pista);
                Notificacion notificacion = new Notificacion("Pista de aterrizaje asignada para el vuelo: " + vuelo.getId(), usuariosDest);
                notificaciones.add(notificacion);
                notificarUsuarios(notificacion, usuariosDest);
                break;
            }
        }
    }

    // Asignar puerta de embarque y finger
    public void asignarPuertaYFinger(Vuelo vuelo) {
        ArrayList<Usuario> usuariosDest = new ArrayList<>();
        usuariosDest.add(gestor);
        usuariosDest.add(usuarioActivo);
        for (PuertaEmbarque puerta : puertasEmbarque) {
            if (puerta.estaDisponible()) {
                vuelo.setPuertaEmbarque(puerta);
                // Asignar finger si es necesario
                Finger finger = buscarFingerDisponible(vuelo);
                if (finger != null) {
                    vuelo.setFinger(finger);
                }
                Notificacion notificacion = new Notificacion("Puerta de embarque y finger asignados para el vuelo: " + vuelo.getId(), usuariosDest);
                notificaciones.add(notificacion);
                notificarUsuarios(notificacion, usuariosDest);
                break;
            }
        }
    }

    // Buscar finger disponible
    private Finger buscarFingerDisponible(Vuelo vuelo) {
        if (vuelo.getPuertaEmbarque().getFinger().isOcupado(vuelo.getFechaHora(), usosElementosAeropuerto)) {
            return vuelo.getPuertaEmbarque().getFinger();
        }
        return null; // No hay finger disponible
    }

    // Asignar hangar si el vuelo está retrasado
    public void asignarHangarSiRetrasado(Vuelo vuelo) {
        ArrayList<Usuario> usuariosDest = new ArrayList<>();
        usuariosDest.add(gestor);
        usuariosDest.add(usuarioActivo);
        if (vuelo.estaRetrasado()) {
            Hangar hangarDisponible = buscarHangarDisponible(vuelo.getFechaHora());
            if (hangarDisponible != null) {
                vuelo.setHangar(hangarDisponible);
                Notificacion notificacion = new Notificacion("Hangar asignado al vuelo: " + vuelo.getId(), usuariosDest);
                notificaciones.add(notificacion);
                notificarUsuarios(notificacion, usuariosDest);
            }
        }
    }

    // Buscar hangar disponible
    private Hangar buscarHangarDisponible(LocalDateTime fechaHora) {
        for (Hangar hangar : hangares) {
            if (!hangar.isOcupado(fechaHora, getUsosElementosAeropuertoElemento(hangar))) {
                return hangar;
            }
        }

        return null; // No hay hangar disponible
    }

    // Notificar a todos los usuarios
    private void notificarUsuarios(Notificacion notificacion, ArrayList<Usuario> usuariosDest) {
        for (Usuario usuario : usuariosDest) {
            usuario.enviarNotificacion(notificacion);
        }
    }

    // Buscar vuelo por código
    public Vuelo buscarVuelo(int id) {
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getId() == id) {
                return vuelo;
            }
        }
        return null; // Vuelo no encontrado
    }
    //Buscar vuelo por aeropuerto
    public ArrayList<Vuelo> buscarVuelosPorOrigen(String origen){
        ArrayList<Vuelo> resultados = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getOrigen().equalsIgnoreCase(origen)) {
                resultados.add(vuelo);
            }
        }
        return resultados;
    }

    public ArrayList<Vuelo> buscarVuelosPorDestino(String destino) {
        ArrayList<Vuelo> resultados = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getDestino().equalsIgnoreCase(destino)) {
                resultados.add(vuelo);
            }
        }
        return resultados;
    }

    //Buscar Vuelo por hora de salida
    public ArrayList<Vuelo> buscarVuelosPorFechaSalida(LocalDateTime fechaSalida) {
        ArrayList<Vuelo> resultados = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getfechaHoraSalida().equals(fechaSalida)) {
                resultados.add(vuelo);
            }
        }
        return resultados;
    }
    //Buscar Vuelo por hora de llegada
    public ArrayList<Vuelo> buscarVuelosPorFechaLlegada(LocalDateTime fechaLlegada) {
        ArrayList<Vuelo> resultados = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getfechaHoraLlegada() != null && vuelo.getfechaHoraLlegada().equals(fechaLlegada)) {
                resultados.add(vuelo);
            }
        }
        return resultados;
    }
    //Buscar Vuelo por terminal
    public ArrayList<Vuelo> buscarVuelosPorTerminal(Terminal terminal) {
        ArrayList<Vuelo> resultados = new ArrayList<>();
        for (Vuelo vuelo : vuelos) {
            if (vuelo.getTerminal() != null && vuelo.getTerminal().equals(terminal)) {
                resultados.add(vuelo);
            }
        }
        return resultados;
    }
    

    // Asignar controlador a vuelo
    public void asignarControladorAVuelo(Vuelo vuelo) {
        Terminal terminal = vuelo.getTerminal();
        ArrayList<ControladorAereo> controladores = terminal.getControladores();
        // Selecciona uno al hazar para asignar
        ControladorAereo controladorAsignado = controladores.get((int) (Math.random() * controladores.size()));
        vuelo.setControladorAereo(controladorAsignado);
        // Notificar
        ArrayList<Usuario> usuariosDest = new ArrayList<>();
        usuariosDest.add(gestor);
        usuariosDest.add(usuarioActivo);
        usuariosDest.add(controladorAsignado);
        Notificacion notificacion = new Notificacion("Controlador asignado al vuelo: " + vuelo.getId(), usuariosDest);
        notificaciones.add(notificacion);
        notificarUsuarios(notificacion, usuariosDest);
    }

    // Facturar
    public void facturarAerolineas(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (usuarioActivo instanceof GestorAeropuerto) {
            for (Aerolinea aerolinea : aerolineas) {
                ArrayList<UsoElementoAeropuerto> usosAerolinea = new ArrayList<>();
                for (UsoElementoAeropuerto uso : usosElementosAeropuerto) {
                    if (uso.getAerolinea().equals(aerolinea)) {
                        usosAerolinea.add(uso);
                    }
                }
                ArrayList<Vuelo> vuelosAerolinea = new ArrayList<>();
                for (Vuelo vuelo : vuelos) {
                    if (vuelo.getAerolinea().equals(aerolinea)) {
                        vuelos.add(vuelo);
                    }
                }
                double coste = aerolinea.calcularCostoTotal(fechaInicio, fechaFin, usosAerolinea, vuelos, costeVuelo);
                Factura factura = new Factura(coste);

                aerolinea.añadirFactura(factura);
                aerolinea.notificarCambio("Nueva factura pendiente de pago");
            }
        } else {
            throw new IllegalArgumentException("Error, el usuario no tiene permisos suficientes");
        }
    }

    /* Métodos de usuarios Usuarios */

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

	public boolean modificarUsuario(int idUsuario, String nuevoNombre, String nuevaContraseña) {
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
            return true;
	    } else {
	        System.out.println("Error: usuario no encontrado");
            return false;
	    }
	}

    public void añadirOperador(OperadorAereo operador, Aerolinea aerolinea) {
        if (usuarioActivo instanceof GestorAeropuerto) {
            if (aerolinea == null) {
                throw new IllegalArgumentException("Error, la aerolínea no puede ser nula");
            }
            
            for (Usuario u : usuarios) {
                if (u.getNombre().equals(operador.getNombre())) {
                    throw new IllegalArgumentException("Error, este nombre ya está en uso");
                }
            }

            usuarios.add(operador);
            aerolinea.agregarOperador(operador);
        } else {
            throw new IllegalArgumentException("Error, el usuario no tiene permisos suficientes");
        }
    }
	
    public void añadirControlador(String nombre, String contraseña) {
        ControladorAereo nuevoControlador = new ControladorAereo(nombre, contraseña, null);

        for (Usuario u : usuarios) {
            if (u.getNombre().equals(nombre)) {
                throw new IllegalArgumentException("Error, este nombre ya está en uso");
            }
        }

        usuarios.add(nuevoControlador);
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

    public void cambiarTerminal(ControladorAereo controlador, Terminal nuevaTerminal) {
        LocalDateTime fechaConsulta = LocalDateTime.now();
        if (controlador != null && nuevaTerminal != null) {
            if(gestor.puedeConsultarTerminal(fechaConsulta)){
            controlador.cambiarTerminal(nuevaTerminal);
            System.out.println("Controlador asignado a la terminal " + nuevaTerminal.getId());
            }
        } else {
            throw new IllegalArgumentException("Error: Controlador no encontrado o terminal nula.");
        }
    }

    public void cambiarAerolinea(OperadorAereo operador, Aerolinea nuevaAerolinea) {
        if (operador != null && nuevaAerolinea != null) {
            operador.cambiarAerolinea(nuevaAerolinea);
            System.out.println("Operador asignado a la aerolínea " + nuevaAerolinea.getNombre());
        } else {
            throw new IllegalArgumentException("Error: Operador no encontrado o aerolínea nula.");
        }
    }

    public void cambiarEstadoVuelo(int idVuelo, Aeropuerto aeropuerto, Vuelo.EstadoVuelo nuevoEstado, Usuario usuario) {
        if (aeropuerto == null || nuevoEstado == null || idVuelo <= 0) {
            System.out.println("Error: Los datos no pueden ser nulos o menor que 0.");
            return;
        }
        Vuelo vuelo = aeropuerto.buscarVuelo(idVuelo);
        if (vuelo != null) {
            vuelo.setEstado(nuevoEstado);
        } else {
            System.out.println("Error: Vuelo no encontrado.");
        }
    }

    // Función de lectura de aeropuertos de un fichero externo
    public void cargarAeropuertosDesdeArchivo(String nombreArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 5) {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    String ciudad = partes[2];
                    String pais = partes[3];
                    AeropuertoDestino.UbiRelCiudad ubicacion = AeropuertoDestino.UbiRelCiudad.valueOf(partes[4]);

                    AeropuertoDestino aeropuerto = new AeropuertoDestino(id, nombre, ciudad, pais, ubicacion);
                    aeropuertosDestino.add(aeropuerto);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos del archivo: " + e.getMessage());
        }
    }


}

