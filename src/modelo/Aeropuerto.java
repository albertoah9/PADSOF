package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
    private ArrayList<OperadorAereo> operadores; // ¡NUEVO! Lista de operadores
    private GestorAeropuerto gestor; // Esta referencia es opcional si el gestor se pasa como parámetro o se gestiona externamente.
    private ArrayList<Hangar> hangares;
    private ArrayList<ZonaAparcamiento> aparcamientos;
    private ArrayList<Usuario> usuarios; // Lista general de todos los usuarios
    private Usuario usuarioActivo;
    private ArrayList<UsoElementoAeropuerto> usosElementosAeropuerto;

    private int diasAnticipacionMinima = 30;

    public Aeropuerto(String nombre, String ciudad, String pais, UbiRelCiudad ubiRelCiudad) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.ubiRelCiudad = ubiRelCiudad;
        this.puertasEmbarque = new ArrayList<>();
        this.terminales = new ArrayList<>();
        this.pistas = new ArrayList<>();
        this.aeropuertosDestino = new ArrayList<>();
        this.vuelos = new ArrayList<>();
        this.aerolineas = new ArrayList<>();
        this.notificaciones = new ArrayList<>();
        this.controladores = new ArrayList<>();
        this.operadores = new ArrayList<>(); // ¡Inicializar aquí!
        this.hangares = new ArrayList<>();
        this.aparcamientos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.usosElementosAeropuerto = new ArrayList<>();
    }
    public String getNombre() {
        return nombre;
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void addVuelo(Vuelo vuelo) {
        if (vuelo == null) throw new IllegalArgumentException("El vuelo no puede ser nulo.");
        this.vuelos.add(vuelo);
    }

    public boolean eliminarVuelo(int idVuelo) {
        Iterator<Vuelo> iterator = vuelos.iterator();
        while (iterator.hasNext()) {
            Vuelo vuelo = iterator.next();
            if (vuelo.getId() == idVuelo) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Vuelo> getVuelos() {
        return new ArrayList<>(vuelos);
    }

    public List<Vuelo> getVuelosAerolinea(Aerolinea aerolinea) {
        List<Vuelo> vuelosAerolinea = new ArrayList<>();
        for (Vuelo v : this.vuelos) {
            if (v.getAerolinea().equals(aerolinea)) {
                vuelosAerolinea.add(v);
            }
        }
        return vuelosAerolinea;
    }

    public Vuelo buscarVuelo(int idVuelo) {
        return vuelos.stream()
                     .filter(v -> v.getId() == idVuelo)
                     .findFirst()
                     .orElse(null);
    }

    public void addAerolinea(Aerolinea aerolinea) {
        if (aerolinea == null) throw new IllegalArgumentException("La aerolínea no puede ser nula.");
        this.aerolineas.add(aerolinea);
    }

    public List<Aerolinea> getAerolineas() {
        return new ArrayList<>(aerolineas);
    }

    public void addNotificacion(Notificacion notificacion) {
        if (notificacion == null) throw new IllegalArgumentException("La notificación no puede ser nula.");
        this.notificaciones.add(notificacion);
    }

    public List<Notificacion> getNotificaciones() {
        return new ArrayList<>(notificaciones);
    }

    public void agregarControladorAereo(ControladorAereo controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador aéreo no puede ser nulo.");
        }
        if (this.controladores.contains(controlador)) {
            System.out.println("El controlador aéreo " + controlador.getNombre() + " ya está registrado en el aeropuerto.");
        } else {
            this.controladores.add(controlador);
            this.usuarios.add(controlador); 
            System.out.println("Controlador aéreo " + controlador.getNombre() + " añadido con éxito al aeropuerto.");
        }
    }

    public boolean eliminarControladorAereo(ControladorAereo controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador aéreo no puede ser nulo.");
        }
        if (this.controladores.remove(controlador)) {
            this.usuarios.remove(controlador);
            System.out.println("Controlador aéreo " + controlador.getNombre() + " eliminado del aeropuerto con éxito.");
            return true;
        }
        return false;
    }

    public List<ControladorAereo> getControladores() {
        return new ArrayList<>(controladores);
    }

    public void agregarOperadorAereo(OperadorAereo operador) {
        if (operador == null) {
            throw new IllegalArgumentException("El operador aéreo no puede ser nulo.");
        }
        if (this.operadores.contains(operador)) {
            System.out.println("El operador aéreo " + operador.getNombre() + " ya está registrado en el aeropuerto.");
        } else {
            this.operadores.add(operador);
            this.usuarios.add(operador);
            System.out.println("Operador aéreo " + operador.getNombre() + " añadido con éxito al aeropuerto.");
        }
    }

    public boolean eliminarOperadorAereo(OperadorAereo operador) {
        if (operador == null) {
            throw new IllegalArgumentException("El operador aéreo no puede ser nulo.");
        }
        if (this.operadores.remove(operador)) {
            this.usuarios.remove(operador); // Eliminar también de la lista general de usuarios
            System.out.println("Operador aéreo " + operador.getNombre() + " eliminado del aeropuerto con éxito.");
            return true;
        }
        return false;
    }

    public List<OperadorAereo> getOperadores() {
        return new ArrayList<>(operadores);
    }

    public ControladorAereo buscarControladorPorContrasena(String contrasena) {
        for (ControladorAereo ctrl : controladores) {
            if (ctrl.getContraseña().equals(contrasena)) {
                return ctrl;
            }
        }
        return null;
    }

    public void addUsuario(Usuario usuario) {
        if (usuario != null && !this.usuarios.contains(usuario)) {
            this.usuarios.add(usuario);
        }
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public Usuario getUsuarioActivo() {
        return usuarioActivo;
    }

    public void setUsuarioActivo(Usuario usuarioActivo) {
        this.usuarioActivo = usuarioActivo;
    }

    public void addPuertaEmbarque(PuertaEmbarque puerta) {
        if (puerta == null) throw new IllegalArgumentException("La puerta de embarque no puede ser nula.");
        this.puertasEmbarque.add(puerta);
    }

    public List<PuertaEmbarque> getPuertasEmbarque() {
        return new ArrayList<>(puertasEmbarque);
    }

    public void addTerminal(Terminal terminal) {
        if (terminal == null) throw new IllegalArgumentException("La terminal no puede ser nula.");
        this.terminales.add(terminal);
    }

    public List<Terminal> getTerminales() {
        return new ArrayList<>(terminales);
    }

    public Terminal buscarTerminalPorId(int idTerminal) {
        return terminales.stream()
                         .filter(t -> t.getId() == idTerminal)
                         .findFirst()
                         .orElse(null);
    }

    public void addPista(Pista pista) {
        if (pista == null) throw new IllegalArgumentException("La pista no puede ser nula.");
        this.pistas.add(pista);
    }

    public List<Pista> getPistas() {
        return new ArrayList<>(pistas);
    }

    public void addHangar(Hangar hangar) {
        if (hangar == null) throw new IllegalArgumentException("El hangar no puede ser nulo.");
        this.hangares.add(hangar);
    }

    public List<Hangar> getHangares() {
        return new ArrayList<>(hangares);
    }

    public void addZonaAparcamiento(ZonaAparcamiento zona) {
        if (zona == null) throw new IllegalArgumentException("La zona de aparcamiento no puede ser nula.");
        this.aparcamientos.add(zona);
    }

    public List<ZonaAparcamiento> getAparcamientos() {
        return new ArrayList<>(aparcamientos);
    }

    public void addUsoElementoAeropuerto(UsoElementoAeropuerto uso) {
        if (uso == null) throw new IllegalArgumentException("El uso del elemento no puede ser nulo.");
        this.usosElementosAeropuerto.add(uso);
    }

    public List<UsoElementoAeropuerto> getUsosElementosAeropuerto() {
        return new ArrayList<>(usosElementosAeropuerto);
    }

    public void addAeropuertoDestino(AeropuertoDestino aeropuertoDestino) {
        if (aeropuertoDestino == null) throw new IllegalArgumentException("El aeropuerto de destino no puede ser nulo.");
        this.aeropuertosDestino.add(aeropuertoDestino);
    }

    public List<AeropuertoDestino> getAeropuertosDestino() {
        return new ArrayList<>(aeropuertosDestino);
    }

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
