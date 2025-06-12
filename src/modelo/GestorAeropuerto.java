package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.Aeropuerto.Status; 

/**
 * Clase principal del modelo que gestiona todas las operaciones y datos del aeropuerto.
 * Sirve como el punto central para interactuar con la lógica de negocio.
 */
public class GestorAeropuerto extends Usuario {

    // Atributo para acceder a la instancia del Aeropuerto
    private Aeropuerto aeropuerto;

    private Set<String> preferenciasNotificaciones;
    private int horasVentanaTerminal = 48;
    private int minutosDesocupacion = 30;

    // ESTAS LISTAS PERMANECEN EN GESTOR AEROPUERTO:
    private List<EventoHistorial> historialEventos;
    private List<Factura> facturas;
    private List<Pago> pagos;
    private List<IncidenteSeguridad> incidentesSeguridad;

    private List<OperadorAereo> operadores; // Lista de operadores aéreos
    private List<ControladorAereo> controladores; // Lista de controladores aéreos

    public GestorAeropuerto(String nombre, String contraseña, Aeropuerto aeropuerto) {
        super(nombre, contraseña);
        if (aeropuerto == null) {
            throw new IllegalArgumentException("El objeto Aeropuerto no puede ser nulo para GestorAeropuerto.");
        }
        this.aeropuerto = aeropuerto; // Asignar la instancia de Aeropuerto

        this.preferenciasNotificaciones = new HashSet<>();
        this.historialEventos = new ArrayList<>();
        this.facturas = new ArrayList<>(); // Inicializar aquí
        this.pagos = new ArrayList<>(); // Inicializar aquí
        this.incidentesSeguridad = new ArrayList<>(); // Inicializar aquí
        this.operadores = new ArrayList<>(); // Inicializar aquí
        this.controladores = new ArrayList<>(); // Inicializar aquí

        // Registrar un evento de inicio del gestor (en su propio historial)
        registrarEvento("INICIO_GESTOR", "Gestor Aeropuerto iniciado.");
    }

    public List<EventoHistorial> getHistorialEventos() {
        return Collections.unmodifiableList(historialEventos);
    }

    public void registrarEvento(String tipo, String descripcion) {
        historialEventos.add(new EventoHistorial(LocalDateTime.now(), tipo, descripcion));
        System.out.println("EVENTO REGISTRADO: [" + tipo + "] " + descripcion); // Para depuración
    }

    public void addFactura(Factura factura) {
        if (factura == null) throw new IllegalArgumentException("La factura no puede ser nula.");
        this.facturas.add(factura);
        registrarEvento("FACTURA_GENERADA", "Factura ID " + factura.getId() + " generada.");
    }
    public List<Factura> getFacturas() {
        return Collections.unmodifiableList(facturas);
    }
    public Factura buscarFacturaPorId(int id) {
        for (Factura f : facturas) {
            if (f.getId() == id) {
                return f;
            }
        }
        return null;
    }

    public void addPago(Pago pago) {
        if (pago == null) throw new IllegalArgumentException("El pago no puede ser nulo.");
        this.pagos.add(pago);
        registrarEvento("PAGO_REGISTRADO", "Pago de " + pago.getMonto() + "€ registrado para factura " + pago.getFacturaId() + ".");
    }
    public List<Pago> getPagos() {
        return Collections.unmodifiableList(pagos);
    }
    public void registrarPagoParaFactura(int idFactura, double montoPago) {
        Factura factura = buscarFacturaPorId(idFactura); // Usa el método propio del gestor
        if (factura != null) {
            if (factura.getMonto() >= montoPago && factura.getEstado() == Factura.EstadoFactura.PENDIENTE_DE_PAGO) {
                LocalDateTime fechaPago = LocalDateTime.now();
                Pago nuevoPago = new Pago(idFactura, montoPago, fechaPago); // Suponiendo constructor de Pago
                addPago(nuevoPago); // Usa el addPago propio del gestor
                if (factura.getMonto() == montoPago) {
                    factura.marcarComoPagado();
                    registrarEvento("FACTURA_PAGADA", "Factura " + idFactura + " pagada completamente.");
                } else {
                    registrarEvento("PAGO_PARCIAL_REGISTRADO", "Pago parcial de " + montoPago + "€ registrado para factura " + idFactura + ".");
                }
            } else {
                registrarEvento("ERROR_PAGO", "Intento de registrar pago inválido (monto o estado) para factura " + idFactura);
            }
        } else {
            registrarEvento("ERROR_PAGO", "Factura no encontrada para pago: " + idFactura);
        }
    }


    public void addIncidenteSeguridad(IncidenteSeguridad incidente) {
        if (incidente == null) throw new IllegalArgumentException("El incidente de seguridad no puede ser nulo.");
        this.incidentesSeguridad.add(incidente);
        String vueloAfectadoId = (incidente.getVueloAfectado() != null) ? String.valueOf(incidente.getVueloAfectado().getId()) : "N/A";
        registrarEvento("INCIDENTE_SEGURIDAD", "Incidente de seguridad (" + incidente.getTipoIncidente() + ") reportado para vuelo " + vueloAfectadoId + ".");
    }
    public List<IncidenteSeguridad> getIncidentesSeguridad() {
        return Collections.unmodifiableList(incidentesSeguridad);
    }
    public IncidenteSeguridad buscarIncidenteSeguridadPorId(String id) {
        for (IncidenteSeguridad i : incidentesSeguridad) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }


    /**
     * Da de alta un nuevo ControladorAereo y lo añade al aeropuerto.
     * @param nombre El nombre del controlador.
     * @param contraseña La contraseña del controlador.
     * @param terminalAsignada La terminal a la que se asigna el controlador (puede ser null).
     * @return El ControladorAereo creado.
     */
    public ControladorAereo darDeAltaControladorAereo(String nombre, String contraseña, Terminal terminalAsignada) {
        if (nombre == null || nombre.trim().isEmpty() || contraseña == null || contraseña.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre y contraseña del controlador no pueden estar vacíos.");
        }
        ControladorAereo nuevoControlador = new ControladorAereo(nombre, contraseña, terminalAsignada);
        this.aeropuerto.agregarControladorAereo(nuevoControlador); // Delega la adición al aeropuerto

        // Si se asigna una terminal, también hay que gestionarlo
        if (terminalAsignada != null) {
            terminalAsignada.agregarControlador(nuevoControlador); // Asigna al controlador a la terminal
            registrarEvento("ALTA_CONTROLADOR", "Controlador Aéreo '" + nombre + "' dado de alta y asignado a Terminal " + terminalAsignada.getId() + ".");
        } else {
            registrarEvento("ALTA_CONTROLADOR", "Controlador Aéreo '" + nombre + "' dado de alta.");
        }
        return nuevoControlador;
    }

    /**
     * Elimina un ControladorAereo del aeropuerto.
     * @param controlador El controlador aéreo a eliminar.
     * @return true si se eliminó, false en caso contrario.
     */
    public boolean eliminarControladorAereo(ControladorAereo controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("El controlador aéreo no puede ser nulo.");
        }
        if (this.aeropuerto.eliminarControladorAereo(controlador)) { // Delega la eliminación al aeropuerto
            registrarEvento("BAJA_CONTROLADOR", "Controlador Aéreo '" + controlador.getNombre() + "' dado de baja.");
            return true;
        }
        registrarEvento("ERROR_BAJA_CONTROLADOR", "No se pudo dar de baja al controlador aéreo '" + controlador.getNombre() + "'.");
        return false;
    }

    /**
     * Obtiene la lista de todos los controladores aéreos del aeropuerto.
     * @return Una lista inmutable de ControladoresAereos.
     */
    public List<ControladorAereo> getControladoresAereos() {
        return this.aeropuerto.getControladores(); // Delega la obtención al aeropuerto
    }


    /**
     * Da de alta un nuevo OperadorAereo y lo añade al aeropuerto.
     * @param nombre El nombre del operador.
     * @param contraseña La contraseña del operador.
     * @param aerolinea La aerolínea a la que se asocia el operador.
     * @return El OperadorAereo creado.
     */
    public OperadorAereo darDeAltaOperadorAereo(String nombre, String contraseña, Aerolinea aerolinea) {
        if (nombre == null || nombre.trim().isEmpty() || contraseña == null || contraseña.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre y contraseña del operador no pueden estar vacíos.");
        }
        if (aerolinea == null) {
            throw new IllegalArgumentException("El operador debe estar asociado a una aerolínea.");
        }
        OperadorAereo nuevoOperador = new OperadorAereo(nombre, contraseña, aerolinea);
        this.aeropuerto.agregarOperadorAereo(nuevoOperador); // Delega la adición al aeropuerto
        aerolinea.agregarOperador(nuevoOperador); // Asegurarse de que la aerolínea también lo tenga
        registrarEvento("ALTA_OPERADOR", "Operador Aéreo '" + nombre + "' dado de alta y asociado a aerolínea '" + aerolinea.getNombre() + "'.");
        return nuevoOperador;
    }

    /**
     * Elimina un OperadorAereo del aeropuerto.
     * @param operador El operador aéreo a eliminar.
     * @return true si se eliminó, false en caso contrario.
     */
    public boolean eliminarOperadorAereo(OperadorAereo operador) {
        if (operador == null) {
            throw new IllegalArgumentException("El operador aéreo no puede ser nulo.");
        }
        if (this.aeropuerto.eliminarOperadorAereo(operador)) { // Delega la eliminación al aeropuerto
            // También eliminarlo de su aerolínea asociada si es necesario
            if (operador.getAerolineaAsignada() != null) {
                operador.getAerolineaAsignada().eliminarOperador(operador);
            }
            registrarEvento("BAJA_OPERADOR", "Operador Aéreo '" + operador.getNombre() + "' dado de baja.");
            return true;
        }
        registrarEvento("ERROR_BAJA_OPERADOR", "No se pudo dar de baja al operador aéreo '" + operador.getNombre() + "'.");
        return false;
    }

    /**
     * Obtiene la lista de todos los operadores aéreos del aeropuerto.
     * @return Una lista inmutable de OperadoresAereos.
     */
    public List<OperadorAereo> getOperadoresAereos() {
        return this.aeropuerto.getOperadores();
    }


    public OperadorAereo buscarOperadorPorContrasena(String contrasena) {
        for (OperadorAereo op : operadores ) { 
            if (op.getContraseña().equals(contrasena)) {
                return op;
            }
        }
        return null;
    }


    public boolean editarOperador(String contrasenaActual, String nuevoNombre, String nuevaContrasena, Aerolinea nuevaAerolinea) {
        OperadorAereo operador = buscarOperadorPorContrasena(contrasenaActual);
        if (operador == null) {
            return false; 
        }

        boolean cambios = false;
        if (nuevoNombre != null && !nuevoNombre.isEmpty() && !operador.getNombre().equals(nuevoNombre)) {
            operador.setNombre(nuevoNombre);
            cambios = true;
        }
        if (nuevaContrasena != null && !nuevaContrasena.isEmpty() && !operador.getContraseña().equals(nuevaContrasena)) {
            operador.setContraseña(nuevaContrasena);
            cambios = true;
        }
        if (nuevaAerolinea != null && (operador.getAerolineaAsignada() == null || !operador.getAerolineaAsignada().equals(nuevaAerolinea))) {
            if (operador.getAerolineaAsignada() != null) {
                operador.getAerolineaAsignada().eliminarOperador(operador); 
            }
            operador.setAerolineaAsignada(nuevaAerolinea);
            nuevaAerolinea.agregarOperador(operador); 
            cambios = true;
        }
        if (cambios) {
            registrarEvento("EDICION_OPERADOR", "Operador " + operador.getNombre() + " (" + contrasenaActual + ") editado.");
        }
        return cambios;
    }

    public boolean eliminarOperador(String contrasena) {
        OperadorAereo operador = buscarOperadorPorContrasena(contrasena);
        if (operador == null) {
            return false;
        }
        if (operador.getAerolineaAsignada() != null) {
            operador.getAerolineaAsignada().eliminarOperador(operador); // Asegúrate de tener este método
        }
        boolean eliminado = this.operadores.remove(operador); // Eliminar de la lista del gestor
        if (eliminado) {
            registrarEvento("ELIMINACION_OPERADOR", "Operador " + operador.getNombre() + " (" + contrasena + ") eliminado.");
        }
        return eliminado;
    }


    // Vuelos
    public void addVuelo(Vuelo vuelo) {
        aeropuerto.addVuelo(vuelo);
        registrarEvento("VUELO_AGREGADO", "Nuevo vuelo " + vuelo.getId() + " agregado al aeropuerto.");
    }
    public List<Vuelo> getVuelos() {
        return aeropuerto.getVuelos();
    }
    public Vuelo buscarVuelo(int idVuelo) {
        return aeropuerto.buscarVuelo(idVuelo);
    }
    public Status actualizarEstadoVuelo(int idVuelo, Vuelo.EstadoVuelo nuevoEstado) {
        Vuelo vuelo = aeropuerto.buscarVuelo(idVuelo);
        if (vuelo != null) {
            vuelo.cambiarEstado(nuevoEstado);
            registrarEvento("CAMBIO_ESTADO_VUELO", "Vuelo " + idVuelo + " a estado: " + nuevoEstado.name());
            return Status.OK;
        }
        registrarEvento("ERROR", "Intento de actualizar estado de vuelo no encontrado: " + idVuelo);
        return Status.ERROR;
    }

    // Aerolineas
    public List<Aerolinea> getAerolineas() {
        return aeropuerto.getAerolineas();
    }
    public void addAerolinea(Aerolinea aerolinea) {
        aeropuerto.addAerolinea(aerolinea);
        registrarEvento("AEROLINEA_AGREGADA", "Nueva aerolínea " + aerolinea.getNombre() + " agregada al aeropuerto.");
    }
    public Aerolinea buscarAerolinea(String nombreAerolinea) {
        return aeropuerto.getAerolineas().stream()
                .filter(a -> a.getNombre().equalsIgnoreCase(nombreAerolinea))
                .findFirst()
                .orElse(null);
    }

    public List<Notificacion> getNotificacionesAeropuerto() {
        return aeropuerto.getNotificaciones();
    }
    public void addNotificacionAeropuerto(Notificacion notificacion) { // Nombre diferente
        aeropuerto.addNotificacion(notificacion);
        registrarEvento("NOTIFICACION_AEROPUERTO", "Nueva notificación general en el aeropuerto: " + notificacion.getMensaje());
    }


    public List<Usuario> getUsuarios() {
        return aeropuerto.getUsuarios();
    }
    public void addUsuario(Usuario usuario) {
        aeropuerto.addUsuario(usuario);
        registrarEvento("USUARIO_AGREGADO", "Nuevo usuario " + usuario.getNombre() + " agregado.");
    }
    public Usuario getUsuarioActivo() {
        return aeropuerto.getUsuarioActivo();
    }
    public void setUsuarioActivo(Usuario usuarioActivo) {
        aeropuerto.setUsuarioActivo(usuarioActivo);
        registrarEvento("SESION_USUARIO", "Usuario activo cambiado a: " + (usuarioActivo != null ? usuarioActivo.getNombre() : "N/A"));
    }

    // Puertas de Embarque
    public List<PuertaEmbarque> getPuertasEmbarque() {
        return aeropuerto.getPuertasEmbarque();
    }
    public void addPuertaEmbarque(PuertaEmbarque puerta) {
        aeropuerto.addPuertaEmbarque(puerta);
        registrarEvento("PUERTA_EMBARQUE_AGREGADA", "Puerta de embarque " + puerta.getId() + " agregada.");
    }

    // Terminales
    public List<Terminal> getTerminales() {
        return aeropuerto.getTerminales();
    }
    public void addTerminal(Terminal terminal) {
        aeropuerto.addTerminal(terminal);
        registrarEvento("TERMINAL_AGREGADA", "Terminal " + terminal.getId() + " agregada.");
    }
    public Terminal buscarTerminalPorId(int idTerminal) {
        return aeropuerto.buscarTerminalPorId(idTerminal);
    }

    // Pistas
    public List<Pista> getPistas() {
        return aeropuerto.getPistas();
    }
    public void addPista(Pista pista) {
        aeropuerto.addPista(pista);
        registrarEvento("PISTA_AGREGADA", "Pista " + pista.getId() + " agregada.");
    }

    // Hangares
    public List<Hangar> getHangares() {
        return aeropuerto.getHangares();
    }
    public void addHangar(Hangar hangar) {
        aeropuerto.addHangar(hangar);
        registrarEvento("HANGAR_AGREGADO", "Hangar " + hangar.getId() + " agregado.");
    }

    // Zonas de Aparcamiento
    public List<ZonaAparcamiento> getAparcamientos() {
        return aeropuerto.getAparcamientos();
    }
    public void addZonaAparcamiento(ZonaAparcamiento zona) {
        aeropuerto.addZonaAparcamiento(zona);
        registrarEvento("ZONA_APARCAMIENTO_AGREGADA", "Zona de aparcamiento " + zona.getId() + " agregada.");
    }

    // Usos de Elementos del Aeropuerto
    public List<UsoElementoAeropuerto> getUsosElementosAeropuerto() {
        return aeropuerto.getUsosElementosAeropuerto();
    }
    public void addUsoElementoAeropuerto(UsoElementoAeropuerto uso) {
        aeropuerto.addUsoElementoAeropuerto(uso);
        registrarEvento("USO_ELEMENTO_REGISTRADO", "Uso registrado para elemento: " + uso.getElementoAeropuerto().getClass().getSimpleName() + " " + uso.getElementoAeropuerto().getId());
    }

    public List<AeropuertoDestino> getAeropuertosDestino() {
        return aeropuerto.getAeropuertosDestino();
    }
    public void addAeropuertoDestino(AeropuertoDestino aeropuertoDestino) {
        aeropuerto.addAeropuertoDestino(aeropuertoDestino);
        registrarEvento("AEROPUERTO_DESTINO_AGREGADO", "Aeropuerto de destino " + aeropuertoDestino.getNombre() + " agregado.");
    }

    public Aeropuerto getAeropuerto() {
        return this.aeropuerto;
    }

    public Status verificarEstadoDeVuelo(int idVuelo) {
        Vuelo vuelo = aeropuerto.buscarVuelo(idVuelo);
        if (vuelo == null) {
            registrarEvento("ERROR_VERIFICACION", "Vuelo no encontrado para verificación: " + idVuelo);
            return Status.ERROR;
        }

        if (vuelo.getTipoVuelo() == Vuelo.TipoVuelo.SALIDA) {
            if (vuelo.getEstado() == Vuelo.EstadoVuelo.EN_PREPARACION &&
                vuelo.getfechaHoraSalida().minusHours(horasVentanaTerminal).isBefore(LocalDateTime.now())) {
                vuelo.cambiarEstado(Vuelo.EstadoVuelo.EMBARCANDO);
                registrarEvento("CAMBIO_ESTADO_VUELO", "Vuelo " + idVuelo + " ha comenzado embarque.");
                return Status.OK;
            }
            if (vuelo.estaRetrasadoSalida()) {
                registrarEvento("ESTADO_VUELO", "Vuelo " + idVuelo + " está retrasado (Salida).");
                return Status.OK;
            }
            if (vuelo.estaPuntualSalida()) {
                registrarEvento("ESTADO_VUELO", "Vuelo " + idVuelo + " está en hora (Salida).");
                return Status.OK;
            }
        } else if (vuelo.getTipoVuelo() == Vuelo.TipoVuelo.LLEGADA) {
            if (vuelo.getFechaHora().isBefore(LocalDateTime.now().plusMinutes(minutosDesocupacion)) &&
                vuelo.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_ATERRIZAJE) {
                vuelo.cambiarEstado(Vuelo.EstadoVuelo.APARCADO);
                registrarEvento("CAMBIO_ESTADO_VUELO", "Vuelo " + idVuelo + " ha aterrizado y aparcado.");
                return Status.OK;
            }
            if (vuelo.estaRetrasadoLlegada()) {
                registrarEvento("ESTADO_VUELO", "Vuelo " + idVuelo + " está retrasado (Llegada).");
                return Status.OK;
            }
            if (vuelo.estaPuntualLlegada()) {
                registrarEvento("ESTADO_VUELO", "Vuelo " + idVuelo + " está en hora (Llegada).");
                return Status.OK;
            }
        }
        registrarEvento("ESTADO_VUELO", "El estado del vuelo " + idVuelo + " no ha cambiado automáticamente en esta verificación.");
        return Status.OK;
    }
}