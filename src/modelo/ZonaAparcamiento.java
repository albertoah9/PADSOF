package modelo;

import java.util.*;

public class ZonaAparcamiento extends ElementoAeropuerto {

    /* Atributos */
    private double ancho;
    private double largo;
    private double costePorHora;
    private int numPlazas;

    private List<PlazaAparcamiento> plazas;

    public ZonaAparcamiento(double ancho, double largo, int numPlazas) {
        super();
        this.ancho = ancho;
        this.largo = largo;
        this.plazas = new ArrayList<>();
        this.numPlazas = numPlazas;

        for (int i = 0; i < numPlazas; i++) {
            plazas.add(new PlazaAparcamiento(ancho, largo));
        }
    }

    // Clase interna
    public class PlazaAparcamiento {
        private int id;
        private double ancho;
        private double largo;
        private boolean ocupada;

        private static int contador = 1;

        public PlazaAparcamiento(double ancho, double largo) {
            this.id = contador++;
            this.ancho = ancho;
            this.largo = largo;
            this.ocupada = false;
        }

        public int getId() {
            return id;
        }

        public double getAncho() {
            return ancho;
        }

        public double getLargo() {
            return largo;
        }

        public boolean isOcupada() {
            return ocupada;
        }

        public void ocupar() {
            this.ocupada = true;
        }

        public void liberar() {
            this.ocupada = false;
        }

        public double calcularArea() {
            return ancho * largo;
        }

        @Override
        public String toString() {
            return "Plaza " + id + " [" + ancho + "m x " + largo + "m] - " + (ocupada ? "Ocupada" : "Libre");
        }
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

    public int setNumPlazas() {
        return plazas.size();
    }

    public int getPlazasDisponibles() {
        return (int) plazas.stream().filter(p -> !p.isOcupada()).count();

    }

    public int plazasOcupadas() {
        return (int) plazas.stream().filter(PlazaAparcamiento::isOcupada).count();
    }

    public List<PlazaAparcamiento> getPlazas() {
        return plazas;
    }

    // METODOS
    public void agregarPlaza(double ancho, double largo) {
        plazas.add(new PlazaAparcamiento(ancho, largo));
        numPlazas += 1;
    }

    public double calcularArea() {
        return ancho * largo;
    }

    public int ocuparPlaza() {
        for (PlazaAparcamiento plaza : plazas) {
            if (!plaza.isOcupada()) {
                plaza.ocupar();
                return plaza.getId();
            }
        }
        return -1;
    }

    public void liberarPlaza(int id) {
        for (PlazaAparcamiento plaza : plazas) {
            if (plaza.getId() == id && plaza.isOcupada()) {
                plaza.liberar();
                return;
            }
        }
    }

    public boolean tienePlazaLibre() {
        return getPlazasDisponibles() > 0;
    }

    @Override
    public String toString() {
        return "Zona de Aparcamiento ID: " + getId() + "\n" +
                "Tamaño: " + ancho + "m x " + largo + "m (" + calcularArea() + "m²)\n" +
                "Coste por hora: " + costePorHora + "€/hora\n" +
                "Plazas disponibles: " + getPlazasDisponibles();
    }
}
