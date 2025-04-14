public class HangarCarga extends Hangar {
    private int temperatura;
    private boolean mercPeligrosas;
    private double costePorHora;

    /* Constructor */
    public HangarCarga(double ancho, double alto, double largo, int numPlazas, int temperatura, boolean mercPeligrosas) {
        super(ancho, alto, largo, numPlazas);
        this.temperatura = temperatura;
        this.mercPeligrosas = mercPeligrosas;
    }

    /* Getters y Setters */
    public int getTemperatura() {
        return temperatura;
    }

    public double getCostePorHora() {
        return costePorHora;
    }

    public void setCostePorHora(double costePorHora) {
        this.costePorHora = costePorHora;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public boolean isMercPeligrosas() {
        return mercPeligrosas;
    }

    public void setMercPeligrosas(boolean mercPeligrosas) {
        this.mercPeligrosas = mercPeligrosas;
    }
}