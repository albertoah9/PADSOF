package modelo;
import java.time.LocalTime;

public class PuertaEmbarque {
    public enum TipoPuerta {
        MERCANCIAS, PASAJEROS
    }

    private static int contador = 0;

    private int id;
    private Finger finger;
    private ZonaAparcamiento zonaAparcamiento;
    private int aforoMaximo;
    private TipoPuerta tipoPuerta;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean embarqueIniciado = false;
    private boolean embarqueFinalizado = false;
    private boolean ocupado = false;

    public PuertaEmbarque(Finger finger, ZonaAparcamiento zonaAparcamiento, int aforoMaximo, TipoPuerta tipoPuerta) {
        this.id = ++contador;
        this.finger = finger;
        this.zonaAparcamiento = zonaAparcamiento;
        this.aforoMaximo = aforoMaximo;
        this.tipoPuerta = tipoPuerta;
    }

    public PuertaEmbarque(String string) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Finger getFinger() {
        return finger;
    }

    public void setFinger(Finger finger) {
        this.finger = finger;
    }

    public ZonaAparcamiento getZonaAparcamiento() {
        return zonaAparcamiento;
    }

    public void setZonaAparcamiento(ZonaAparcamiento zonaAparcamiento) {
        this.zonaAparcamiento = zonaAparcamiento;
    }

    public int getAforoMaximo() {
        return aforoMaximo;
    }

    public void setAforoMaximo(int aforoMaximo) {
        this.aforoMaximo = aforoMaximo;
    }

    public TipoPuerta getTipoPuerta() {
        return tipoPuerta;
    }

    public void setTipoDeAvion(TipoPuerta tipoPuerta) {
        this.tipoPuerta = tipoPuerta;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isEmbarqueIniciado() {
        return embarqueIniciado;
    }

    public boolean isEmbarqueFinalizado() {
        return embarqueFinalizado;
    }

    public boolean esAdecuadaParaAvion(TipoPuerta tipoPuerta) {
        return this.tipoPuerta.equals(tipoPuerta);
    }

    public boolean estaDisponible() {
        return ocupado;
    }

    public void ocuparPuerta() {
        ocupado = true;
    }

    public void asignarHoras(LocalTime inicio, LocalTime fin) {
        this.horaInicio = inicio;
        this.horaFin = fin;
    }

    public void iniciarEmbarque() {
        embarqueIniciado = true;
    }

    public void finalizarEmbarque() {
        embarqueFinalizado = true;
    }

    public void mostrarEstado() {
        System.out.println("Puerta de Embarque ID: " + id);
        System.out.println("Tipo de avión: " + tipoPuerta);
        System.out.println("Finger asociado: " + (finger != null ? finger.getId() : "No asignado"));
        System.out.println("Zona de aparcamiento: " + (zonaAparcamiento != null ? zonaAparcamiento.getId() : "No asignada"));
        System.out.println("Aforo máximo: " + aforoMaximo);
        System.out.println("Hora de inicio: " + (horaInicio != null ? horaInicio : "No asignada"));
        System.out.println("Hora de fin: " + (horaFin != null ? horaFin : "No asignada"));
        System.out.println("Embarque iniciado: " + embarqueIniciado);
        System.out.println("Embarque finalizado: " + embarqueFinalizado);
    }
}
