package modelo;

public abstract class Pista {
    private static int contador = 0;

    private Vuelo vueloAsignado;
    private int id;
    private double longitud;
    private double anchura;
    private Pista pista;
    private boolean ocupada = false;

    public Pista(double longitud, double anchura) {
        this.id = ++contador;
        this.longitud = longitud;
        this.anchura = anchura;
    }

    public int getId() {
        return id;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getAnchura() {
        return anchura;
    }

    public void setAnchura(double anchura) {
        this.anchura = anchura;
    }

    public Pista getPista() {
        return pista;
    }

    public void setPista(Pista pista) {
        this.pista = pista;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    public boolean isOcupada() {
        if (vueloAsignado != null && vueloAsignado.getEstado() == Vuelo.EstadoVuelo.ESPERANDO_PISTA) {
            return true;
        } else {
            return false;
        }
    }

}
