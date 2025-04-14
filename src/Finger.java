
public class Finger extends ElementoAeropuerto {
    private double alturaMax;
    private PuertaEmbarque puertaEmbarque;

    
    public Finger (double alturaMax) {
        super();
        this.alturaMax = alturaMax;
        this.puertaEmbarque = null;
    }

    public double getAlturaMax() {
        return alturaMax;
    }

    public void setAlturaMax(double alturaMax) {
        this.alturaMax = alturaMax;
    }

    public PuertaEmbarque getPuertaEmbarque() {
        return puertaEmbarque;
    }

    public void setPuertaEmbarque(PuertaEmbarque puertaEmbarque) {
        this.puertaEmbarque = puertaEmbarque;
    }

    
}
