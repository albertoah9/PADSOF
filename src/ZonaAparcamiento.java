
public class ZonaAparcamiento extends ElementoAeropuerto {
    public enum TipoZonaAparcamiento{
        PASAJEROS, MERCANCIAS
    }

    /* Atributos */
    private double ancho;
    private double largo;
    private int numPlazas;
    private double costePorHora;
    private TipoZonaAparcamiento tipoZonaAparcamiento;

    private int plazasOcupadas = 0;

    public ZonaAparcamiento(double ancho, double largo, int numPlazas,
            ZonaAparcamiento.TipoZonaAparcamiento tipoZonaAparcamiento) {
        super();
        this.ancho = ancho;
        this.largo = largo;
        this.numPlazas = numPlazas;
        this.tipoZonaAparcamiento = tipoZonaAparcamiento;
    }
    
    public double getCostePorHora() {
        return costePorHora;
    }

    public void setCostePorHora(double costePorHora) {
        this.costePorHora = costePorHora;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public int getNumPlazas() {
        return numPlazas;
    }

    public void setNumPlazas(int numPlazas) {
        this.numPlazas = numPlazas;
    }

    public TipoZonaAparcamiento getTipoZonaAparcamiento() {
        return tipoZonaAparcamiento;
    }

    public void setTipoZonaAparcamiento(TipoZonaAparcamiento tipoZonaAparcamiento) {
        this.tipoZonaAparcamiento = tipoZonaAparcamiento;
    }

    public double calcularArea() {
        return ancho * largo;
    }

    public void ocuparPlaza() {
        if (plazasOcupadas < numPlazas) {
            plazasOcupadas++;
        } else {
            System.out.println("No hay plazas disponibles.");
        }
    }

    public void liberarPlaza() {
        if (plazasOcupadas > 0) {
            plazasOcupadas--;
        } else {
            System.out.println("No hay plazas ocupadas.");
        }
    }

    public int getPlazasDisponibles() {
        return numPlazas - plazasOcupadas;
    }

    @Override
    public String toString() {
        return "Zona de Aparcamiento ID: " + getId() + "\n" +
            "Tipo: " + tipoZonaAparcamiento + "\n" +
            "Tamaño: " + ancho + "m x " + largo + "m (" + calcularArea() + "m²)\n" +
            "Número de plazas: " + numPlazas + "\n" +
            "Coste por hora: " + costePorHora + "€/hora\n" +
            "Plazas disponibles: " + getPlazasDisponibles();
    }
}
