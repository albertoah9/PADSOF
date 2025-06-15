package modelo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import modelo.Notificacion;
import modelo.Usuario;

public class Vuelo {
    public enum EstadoVuelo {
        ESPERANDO_PISTA, ESPERANDO_ATERRIZAJE, EN_PREPARACION, APARCADO, EN_HANGAR, EMBARCANDO, ESPERANDO_DESPEGUE,
        DESPEGADO, RETRASADO, EN_HORA
    }

    public enum TipoVuelo {
        SALIDA, LLEGADA
    }

    public enum ClaseVuelo {
        MERCANCIAS, PASAJEROS
    }

    public enum Periodicidad {
        PUNTUAL, DIARIO, DIAS_SEMANA
    }

    private boolean embarqueFinalizadoPorControlador;
    private boolean pistaAsignada;
    private boolean vueloCercaDelAeropuerto;
    private boolean usaFinger;

    private LocalDateTime fechaHoraLlegada;
    private LocalDateTime fechaHoraSalida;
    private LocalDateTime fechaHoraEstadoEsperandoDespegue;
    private LocalDateTime fechaHoraUltimaVerificacion;

    private EstadoVuelo estado;
    private EstadoVuelo estadoAnterior;

    private static int contador = 0;

    private final int id;
    private String origen;
    private String destino;
    private Terminal terminal;
    private Avion avion;
    private Pista pista;
    private PuertaEmbarque puertaEmbarque;
    private Aeropuerto aeropuerto;
    private TipoVuelo tipoVuelo;
    private ClaseVuelo claseVuelo;
    private Aerolinea aerolinea;
    private Finger finger;
    private ControladorAereo controladorAereo;
    private Hangar hangar;
    private List<Usuario> observadores;
    private Periodicidad periodicidad;

    public Vuelo(String origen, String destino, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida,
            Terminal terminal, Avion avion, Pista pista, PuertaEmbarque puertaEmbarque, EstadoVuelo estado,
            Aeropuerto aeropuerto, TipoVuelo tipoVuelo, ClaseVuelo claseVuelo, Aerolinea aerolinea) {
        this.id = ++contador;
        this.terminal = terminal;
        this.avion = avion;
        this.pista = pista;
        this.puertaEmbarque = puertaEmbarque;
        this.estado = estado;
        this.aeropuerto = aeropuerto;
        this.tipoVuelo = tipoVuelo;
        this.claseVuelo = claseVuelo;
        this.aerolinea = aerolinea;
        this.embarqueFinalizadoPorControlador = false;
        this.pistaAsignada = false;
        this.vueloCercaDelAeropuerto = false;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
        this.observadores = new ArrayList<>();
        this.origen = origen;
        this.destino = destino;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Periodicidad getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(Periodicidad periodicidad) {
        this.periodicidad = periodicidad;
    }

    public boolean isUsaFinger() {
        return usaFinger;
    }

    public void setUsaFinger(boolean usaFinger) {
        this.usaFinger = usaFinger;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getfechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public LocalDateTime getfechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalDateTime getFechaHora() {
        if (this.getTipoVuelo() == TipoVuelo.SALIDA) {
            return fechaHoraSalida;
        } else {
            return fechaHoraLlegada;
        }
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Avion getAvion() {
        return avion;
    }

    public void setAvion(Avion avion) {
        this.avion = avion;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public PuertaEmbarque getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public void setPuertaEmbarque(PuertaEmbarque puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }

    public EstadoVuelo getEstado() {
        return estado;
    }

    public void setEstado(EstadoVuelo nuevoEstado) {
        this.estado = nuevoEstado;
        // String mensaje = "Vuelo " + id + " ha cambiado de estado a: " + nuevoEstado;
    }

    public Aeropuerto getAeropuerto() {
        return aeropuerto;
    }

    public void setAeropuerto(Aeropuerto aeropuerto) {
        this.aeropuerto = aeropuerto;
    }

    public TipoVuelo getTipoVuelo() {
        return tipoVuelo;
    }

    public void setTipoVuelo(TipoVuelo tipoVuelo) {
        this.tipoVuelo = tipoVuelo;
    }

    public ClaseVuelo getClaseVuelo() {
        return claseVuelo;
    }

    public void setClaseVuelo(ClaseVuelo claseVuelo) {
        this.claseVuelo = claseVuelo;
    }

    public Aerolinea getAerolinea() {
        return aerolinea;
    }

    public void setAerolinea(Aerolinea aerolinea) {
        this.aerolinea = aerolinea;
    }

    public Finger getFinger() {
        return finger;
    }

    public void setFinger(Finger finger) {
        this.finger = finger;
    }

    public ControladorAereo getControladorAereo() {
        return controladorAereo;
    }

    public void setControladorAereo(ControladorAereo controladorAereo) {
        this.controladorAereo = controladorAereo;
    }

    public boolean estaRetrasado() {
        return this.estado == EstadoVuelo.RETRASADO;
    }

    public Hangar getHangar() {
        return hangar;
    }

    public void setHangar(Hangar hangar) {
        this.hangar = hangar;
    }

    public void eliminarObservador(Usuario usuario) {
        observadores.remove(usuario);
    }

    // METODOS DE CAMBIOS DE ESTADO
    // RETRASADO VUELO SALIDA si la hora actual es mayor a la hora de la salida
    public boolean estaRetrasadoSalida() {
        if (tipoVuelo == TipoVuelo.SALIDA && LocalDateTime.now().isAfter(fechaHoraSalida)) {
            cambiarEstado(EstadoVuelo.RETRASADO);
            return true;
        }
        return false;
    }

    // RETRASADO VUELO LLEGADA si la hora actual es mayor a la hora de llegada y no
    // ha aterrizado
    public boolean estaRetrasadoLlegada() {
        if (tipoVuelo == TipoVuelo.LLEGADA && LocalDateTime.now().isAfter(fechaHoraLlegada)
                && (estado != EstadoVuelo.APARCADO && estado != EstadoVuelo.EN_HANGAR)) {
            cambiarEstado(EstadoVuelo.RETRASADO);
            return true;
        }
        return false;
    }

    // EN_HORA SALIDA si la hora actual es menor o igual a la hora de salida
    public boolean estaPuntualSalida() {
        if (tipoVuelo == TipoVuelo.SALIDA
                && (LocalDateTime.now().isBefore(fechaHoraSalida) || LocalDateTime.now().equals(fechaHoraSalida))) {
            cambiarEstado(EstadoVuelo.EN_HORA);
            return true;
        }
        return false;
    }

    // EN_HORA LLEGADA si la hora actual es menor o igual a la hora de llegada
    public boolean estaPuntualLlegada() {
        if (tipoVuelo == TipoVuelo.LLEGADA
                && (LocalDateTime.now().isBefore(fechaHoraLlegada) || LocalDateTime.now().equals(fechaHoraLlegada))) {
            cambiarEstado(EstadoVuelo.EN_HORA);
            return true;
        }
        return false;
    }

    // ESPERANDO_PISTA si el controlador finaliza el embarque
    public boolean VueloEsperaPista() {
        if (this.embarqueFinalizadoPorControlador && this.estado == EstadoVuelo.EMBARCANDO) {
            this.estado = EstadoVuelo.ESPERANDO_PISTA;
            return true;
        }
        return false;
    }

    // Necesario para el anterior
    public void controladorFinalizoEmbarque() {
        this.embarqueFinalizadoPorControlador = true;
    }

    public boolean VueloEsperandoDespegue() {
        if (this.estado == EstadoVuelo.ESPERANDO_PISTA && this.pistaAsignada) {
            this.estado = EstadoVuelo.ESPERANDO_DESPEGUE;
            this.pistaAsignada = false;
            this.fechaHoraEstadoEsperandoDespegue = LocalDateTime.now();
            return true;
        }
        return false;
    }

    // Necesario para el anterior
    public void asignarPista() {
        this.pistaAsignada = true; // Pista asignada al vuelo
    }

    // DESPEGADO
    public boolean VueloDespegado() {
        if (this.estado == EstadoVuelo.ESPERANDO_DESPEGUE) {
            if (this.fechaHoraEstadoEsperandoDespegue != null &&
                    ChronoUnit.MINUTES.between(this.fechaHoraEstadoEsperandoDespegue, LocalDateTime.now()) >= 2) {
                this.estado = EstadoVuelo.DESPEGADO;
                return true;
            }
        }
        return false;
    }

    // ESPERANDO_ATERRIZAJE
    public boolean VueloEsperandoAterrizaje() {
        if (this.estado == EstadoVuelo.DESPEGADO && this.vueloCercaDelAeropuerto) {
            this.estado = EstadoVuelo.ESPERANDO_ATERRIZAJE;
            this.vueloCercaDelAeropuerto = false;
            return true;
        }
        return false; // No se pudo cambiar el estado
    }

    // Para la anterior
    public void marcarVueloCercaDelAeropuerto() {
        this.vueloCercaDelAeropuerto = true;
    }

    // EN_PREPARACION
    public boolean cambiarAEnPreparacion() {
        if (estado == EstadoVuelo.ESPERANDO_ATERRIZAJE) {
            estado = EstadoVuelo.EN_PREPARACION;
            return true;
        }
        return false;
    }

    public void iniciarVerificacionEstadoCadaDosMin() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(() -> {
            // Si es la primera vez, guardamos el estado actual y la hora
            if (estadoAnterior == null) {
                estadoAnterior = this.estado;
                fechaHoraUltimaVerificacion = LocalDateTime.now();
                return;
            }

            // Verificamos si pasaron al menos 2 minutos
            long minutosPasados = ChronoUnit.MINUTES.between(fechaHoraUltimaVerificacion, LocalDateTime.now());

            if (minutosPasados >= 2) {
                // Comprobamos si el estado actual es distinto al anterior
                if (this.estado != estadoAnterior) {
                    // Notificamos solo el nuevo estado
                    notificar("El nuevo estado es: " + this.estado, controladorAereo);

                    estadoAnterior = this.estado;
                    fechaHoraUltimaVerificacion = LocalDateTime.now();
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    private void notificar(String mensaje, Usuario remitente) {
        for (Usuario usuario : observadores) {
            if (!usuario.equals(remitente)) { // No notificar al usuario que hizo el cambio
                Notificacion notificacion = new Notificacion(mensaje, List.of(usuario));
                usuario.recibirNotificacion(notificacion);
            }
        }
    }

    // Metodo que notifica al controlador Aereo cada vez que el vuelo cambia de
    // estado
    public void cambiarEstado(EstadoVuelo nuevoEstado) {
        // Cambiar el estado solo si realmente es diferente al actual
        if (this.estado != nuevoEstado) {
            this.estado = nuevoEstado; // Actualiza el estado del vuelo
            notificarEstado(); // Notifica al controlador aéreo del nuevo estado
        }
    }

    private void notificarEstado() {
        if (this.controladorAereo != null) {
            controladorAereo.notificarCambioEstado(this); // Llama al método del controlador aéreo
        }
    }

    public void asignarHangarYActualizarEstado(Hangar hangar) {
        this.hangar = hangar;
        cambiarEstado(EstadoVuelo.EN_HANGAR);
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id='" + id + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", aerolinea=" + (aerolinea != null ? aerolinea.getCodigoAerolinea() : "null") +
                '}';
    }

}
