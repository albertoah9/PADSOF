package modelo;
public class PistaAterrizaje extends Pista {
    private boolean zonaEsperandoAvion; 
    public PistaAterrizaje(double longitud, double anchura) {
        super(longitud, anchura);
        this.zonaEsperandoAvion = false;
    }

    public boolean isZonaEsperandoAvion() {
        return zonaEsperandoAvion;
    }

    public void setZonaEsperandoAvion(boolean zonaEsperandoAvion) {
        this.zonaEsperandoAvion = zonaEsperandoAvion;
    }
}