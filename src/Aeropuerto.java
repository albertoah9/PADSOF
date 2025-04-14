import java.time.LocalDateTime;
import java.util.ArrayList;

public class Aeropuerto {
    public enum Status {
        OK, ERROR
    }

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
    private Usuario usuarioActivo;
    private ArrayList<UsoElementoAeropuerto> usosElementosAeropuerto;

    // CAMBIAR POR SISTEMA DE PAGO
    private ArrayList<Factura> facturas;

    private double costeVuelo = 35.0;

    // Constructor
    public Aeropuerto() {
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
    }

    // Getters y Setters
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
        this.costeVuelo = costeVuelo;
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

    public void addVuelo(Vuelo vuelo) {
        if (usuarioActivo instanceof OperadorAereo) {
            this.vuelos.add(vuelo);
            ArrayList<Usuario> usuariosDest = new ArrayList<>();
            usuariosDest.add(gestor);
            usuariosDest.add(usuarioActivo);
            Notificacion notificacion = new Notificacion("Nuevo vuelo programado: ", vuelo.getId(), usuariosDest);
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


    /* Métodos */
    public ArrayList<UsoElementoAeropuerto> getUsosElementosAeropuertoElemento(ElementoAeropuerto elemento) {
        ArrayList<UsoElementoAeropuerto> usosElementosAeropuerto = new ArrayList<>();
        for (UsoElementoAeropuerto uso : usosElementosAeropuerto) {
            if (uso.getElementoAeropuerto().equals(elemento)) {
                usosElementosAeropuerto.add(uso);
            }
        }

        return usosElementosAeropuerto;
    }

    // Asignar terminal a un vuelo
    public void asignarTerminalAVuelo(Vuelo vuelo) {
        Terminal terminalAsignada = buscarTerminalDisponible();
        ArrayList<Usuario> usuariosDest = new ArrayList<>();
        usuariosDest.add(gestor);
        usuariosDest.add(usuarioActivo);
        if (terminalAsignada != null) {
            vuelo.setTerminal(terminalAsignada);
            Notificacion notificacion = new Notificacion("Terminal asignada al vuelo: ", vuelo.getId(), usuariosDest);
            notificaciones.add(notificacion);
            notificarUsuarios(notificacion, usuariosDest);
        } else {
            // No hay terminal disponible
            Notificacion notificacion = new Notificacion("No hay terminal disponible para el vuelo: ", vuelo.getId(), usuariosDest);
            notificaciones.add(notificacion);
            notificarUsuarios(notificacion, usuariosDest);
        }
    }

    // Buscar terminal disponible
    private Terminal buscarTerminalDisponible() {
        for (Terminal terminal : terminales) {
            if (!terminal.isOcupada()) {
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
                Notificacion notificacion = new Notificacion("Pista de aterrizaje asignada para el vuelo: ", vuelo.getId(), usuariosDest);
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
                Notificacion notificacion = new Notificacion("Puerta de embarque y finger asignados para el vuelo: ", vuelo.getId(), usuariosDest);
                notificaciones.add(notificacion);
                notificarUsuarios(notificacion, usuariosDest);
                break;
            }
        }
    }

    // Buscar finger disponible
    private Finger buscarFingerDisponible(Vuelo vuelo) {
        if (vuelo.getPuertaEmbarque().getFinger().getEstadoFinger() == Finger.EstadoFinger.LIBRE) {
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
                Notificacion notificacion = new Notificacion("Hangar asignado al vuelo: ", vuelo.getId(), usuariosDest);
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
        Notificacion notificacion = new Notificacion("Controlador asignado al vuelo: ", vuelo.getId(), usuariosDest);
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
                aerolinea.calcularCostoTotal(fechaInicio, fechaFin, usosAerolinea, vuelos, costeVuelo);
                Factura factura = new Factura();
            }
        }
    }

}
