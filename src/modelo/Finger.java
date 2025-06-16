package modelo;

import java.util.ArrayList;
import java.util.List;

public class Finger extends ElementoAeropuerto {
    private double alturaMax;
    private List<PuertaEmbarque> puertasEmbarque;
    private Vuelo vueloAsignado;

    public Finger(double alturaMax) {
        super();
        this.alturaMax = alturaMax;
        this.puertasEmbarque = new ArrayList<>();
    }

    public double getAlturaMax() {
        return alturaMax;
    }

    public boolean estaOcupado() {
        return vueloAsignado != null;
    }

    public void asignarVuelo(Vuelo vuelo) {
        this.vueloAsignado = vuelo;
    }

    public void ocupar(Vuelo vuelo) {
        this.vueloAsignado = vuelo;
    }

    public void liberar() {
        this.vueloAsignado = null;
    }

    public void setAlturaMax(double alturaMax) {
        this.alturaMax = alturaMax;
    }

    public List<PuertaEmbarque> getPuertasEmbarque() {
        return puertasEmbarque;
    }

    public void setPuertasEmbarque(List<PuertaEmbarque> puertas) {
        this.puertasEmbarque = puertas;
    }

    public void agregarPuertaEmbarque(PuertaEmbarque puerta) {
        if (!puertasEmbarque.contains(puerta)) {
            puertasEmbarque.add(puerta);
        }
    }

    public void eliminarPuertaEmbarque(PuertaEmbarque puerta) {
        puertasEmbarque.remove(puerta);
    }

}
