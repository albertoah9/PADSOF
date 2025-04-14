
public class PistaDespegue extends Pista {
    private boolean zonaEsperandoAvion;


    public PistaDespegue(double longitud, double anchura) {
        super(longitud, anchura);
        this.zonaEsperandoAvion = true; 

    }

    public boolean isZonaEsperandoAvion() {
        return zonaEsperandoAvion;
    }

    public void setZonaEsperandoAvion(boolean zonaEsperandoAvion) {
        this.zonaEsperandoAvion = zonaEsperandoAvion;
    }
}