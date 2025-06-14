package modelo;


public class Hangar extends ElementoAeropuerto {
    private double ancho;
    private double alto;
    private double largo;
    private int numPlazas;

    public Hangar(double ancho, double alto, double largo, int numPlazas){
        super();
        this.alto = alto;
        this.ancho = ancho;
        this.largo = largo;
        this.numPlazas = numPlazas;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
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

    /* Métodos */


}