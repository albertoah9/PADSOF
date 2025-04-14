import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Vuelo {
    public enum EstadoVuelo {
        ESPERANDO_PISTA, ESPERANDO_ATERRIZAJE, EN_PREPARACION, APARCADO, EN_HANGAR, EMBARCANDO, ESPERADNO_DESPEGUE, DESPEGADO, RETRASADO, EN_HORA
    }

    public enum TipoVuelo {
        SALIDA, LLEGADA
    }

    public enum ClaseVuelo {
        MERCANCIAS, PASAJEROS
    }
    
    private boolean embarqueFinalizadoPorControlador;
    private boolean pistaAsignada;
    private boolean vueloCercaDelAeropuerto; 
    

    private LocalTime horaLlegada;
    private LocalTime horaSalida;
    private LocalTime horaActual;
    private LocalTime horaEstadoEsperandoDespegue;
    private LocalDateTime horaUltimaVerificacion;

   
    private EstadoVuelo estado;
    private EstadoVuelo estadoAnterior;


    private int id;
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

    public Vuelo(int id, LocalTime horaLlegada, LocalTime horaSalida, Terminal terminal, Avion avion, Pista pista, PuertaEmbarque puertaEmbarque, EstadoVuelo estado, Aeropuerto aeropuerto, TipoVuelo tipoVuelo, ClaseVuelo claseVuelo, Aerolinea aerolinea) {
        this.id = id;
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
        this.horaActual = LocalTime.now();
        this.horaLlegada = horaLlegada;
        this.horaSalida = horaSalida;        
    }

    

    public LocalDateTime getFechaHora() {
        return LocalDateTime.of(LocalDate.now(), this.horaSalida);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getHoraActual() {
        return horaActual;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }
   
    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

    
    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
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
    public void setEstado(EstadoVuelo nuevoEstado, Usuario usuario) {
        this.estado = nuevoEstado;
        String mensaje = "Vuelo " + id + " ha cambiado de estado a: " + nuevoEstado;
        notificar(mensaje, usuario);
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


    //METODOS DE CAMBIOS DE ESTADO
    //RETREASADO VUELO SALIDA si la hora actual es mayor a la hora de la salida
   public boolean estaRetrasadoSalida() {
        if (tipoVuelo == TipoVuelo.SALIDA && horaActual.isAfter(horaSalida)) {
            cambiarEstado(EstadoVuelo.RETRASADO); 
            return true;
        }
        return false;
    }
    //RETRASADO VUELO LLEGADA si la hora actual es mayor a la hora de llegada y no ha aterrizado
    public boolean estaRetrasadoLlegada() {
        if (tipoVuelo == TipoVuelo.LLEGADA && horaActual.isAfter(horaLlegada) 
            && (estado != EstadoVuelo.APARCADO && estado != EstadoVuelo.EN_HANGAR)) {
            cambiarEstado(EstadoVuelo.RETRASADO);
            return true;
        }
        return false;
    }


    //EN_HORA SALIDA si la hora actual es menor o igual a la hora de salida
    public boolean estaPuntualSalida() {
    if (tipoVuelo == TipoVuelo.SALIDA && (horaActual.isBefore(horaSalida) || horaActual.equals(horaSalida))) {
        cambiarEstado(EstadoVuelo.EN_HORA);
        return true;
    }
    return false;
}
    //EN_HORA LLEGADA si la hora actual es menor o igual a la hora de llegada
    public boolean estaPuntualLlegada() {
        if (tipoVuelo == TipoVuelo.LLEGADA && (horaActual.isBefore(horaLlegada) || horaActual.equals(horaLlegada))) {
            cambiarEstado(EstadoVuelo.EN_HORA);
            return true;
        }
        return false;
    }


    //ESPERANDO_PISTA si el controlador finaliza el embarque
    public boolean VueloEsperaPista(){
        if (this.embarqueFinalizadoPorControlador && this.estado == EstadoVuelo.EMBARCANDO) {
            this.estado = EstadoVuelo.ESPERANDO_PISTA; 
            return true;  
        }
        return false;  
    }
    //Necesario para el anterior
    public void controladorFinalizoEmbarque() {
        this.embarqueFinalizadoPorControlador = true;
    }

    public boolean VueloEsperandoDespegue(){
        if (this.estado == EstadoVuelo.ESPERANDO_PISTA && this.pistaAsignada) {
            this.estado = EstadoVuelo.ESPERADNO_DESPEGUE; 
            this.pistaAsignada = false;
            this.horaEstadoEsperandoDespegue = LocalTime.now();
            return true;
        }
        return false;  
    }
    //Necesario para el anterior
    public void asignarPista() {
        this.pistaAsignada = true; // Pista asignada al vuelo
    }

    //DESPEGADO
    public boolean VueloDespegado(){
            if (this.estado == EstadoVuelo.ESPERADNO_DESPEGUE) {
            if (this.horaEstadoEsperandoDespegue != null && 
                ChronoUnit.MINUTES.between(this.horaEstadoEsperandoDespegue, LocalDateTime.now()) >= 2) {
                this.estado = EstadoVuelo.DESPEGADO; 
                return true;
            }
        }
        return false;  
    }

    
    //ESPERANDO_ATERRIZAJE
    public boolean VueloEsperandoAterrizaje() {
        if (this.estado == EstadoVuelo.DESPEGADO && this.vueloCercaDelAeropuerto) {
            this.estado = EstadoVuelo.ESPERANDO_ATERRIZAJE;
            this.vueloCercaDelAeropuerto = false;
            return true; 
        }
        return false; // No se pudo cambiar el estado
    }
    //Para la anterior
    public void marcarVueloCercaDelAeropuerto() {
        this.vueloCercaDelAeropuerto = true; 
    }

    //EN_PREPARACION
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
            horaUltimaVerificacion = LocalDateTime.now();
            return;
        }

        // Verificamos si pasaron al menos 2 minutos
        long minutosPasados = ChronoUnit.MINUTES.between(horaUltimaVerificacion, LocalDateTime.now());

        if (minutosPasados >= 2) {
            // Comprobamos si el estado actual es distinto al anterior
            if (this.estado != estadoAnterior) {
                // Notificamos solo el nuevo estado 
                notificar("El nuevo estado es: " + this.estado, controladorAereo);
                
                estadoAnterior = this.estado;
                horaUltimaVerificacion = LocalDateTime.now();
            }
        }
    }, 0, 1, TimeUnit.MINUTES);
}



    private void notificar(String mensaje, Usuario remitente) {
        for (Usuario usuario : observadores) {
            if (!usuario.equals(remitente)) {  // No notificar al usuario que hizo el cambio
                Notificacion notificacion = new Notificacion(mensaje, this.hashCode(), List.of(usuario));
                usuario.recibirNotificacion(notificacion);
            }
        }
    }

    //Metodo que notifica al controlador Aereo cada vez que el vuelo cambia de estado
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


}

