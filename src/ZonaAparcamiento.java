import java.util.List;

public class ZonaAparcamiento extends ElementoAeropuerto {
    

    /* Atributos */
    private double ancho;
    private double largo;
    private double costePorHora;

    private List<PlazaAparcamiento> plazas;

    public ZonaAparcamiento(double ancho, double largo, int numPlazas) {
        super();
        this.ancho = ancho;
        this.largo = largo;
    }

    //CLASE INTERNA ----------------------------------------------
    public class PlazaAparcamiento {
        private String codigo;
        private double ancho;
        private double largo;
        private boolean ocupada;

        public PlazaAparcamiento(String codigo, double ancho, double largo) {
            this.codigo = codigo;
            this.ancho = ancho;
            this.largo = largo;
            this.ocupada = false;
        }

        public String getCodigo() {
            return codigo;
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
            return "Plaza " + codigo + " [" + ancho + "m x " + largo + "m] - " + (ocupada ? "Ocupada" : "Libre");
        }
    }

    //CLASE INTERNA ACABADA---------------------------------------
    
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
        return plazas.size();
    }

    public int getPlazasDisponibles() {
        return (int) plazas.stream().filter(p -> !p.isOcupada()).count();
    }

    public List<PlazaAparcamiento> getPlazas() {
        return plazas;
    }

    //METODOS
    public void agregarPlaza(String codigo, double ancho, double largo) {
        plazas.add(new PlazaAparcamiento(codigo, ancho, largo));
    }


    public double calcularArea() {
        return ancho * largo;
    }

    public void ocuparPlaza(String codigo) {
        for (PlazaAparcamiento plaza : plazas) {
            if (plaza.getCodigo().equals(codigo) && !plaza.isOcupada()) {
                plaza.ocupar();
                return;
            }
        }
    }

    public void liberarPlaza(String codigo) {
        for (PlazaAparcamiento plaza : plazas) {
            if (plaza.getCodigo().equals(codigo) && plaza.isOcupada()) {
                plaza.liberar();
                return;
            }
        }
    }


    @Override
    public String toString() {
        return "Zona de Aparcamiento ID: " + getId() + "\n" +
            "Tamaño: " + ancho + "m x " + largo + "m (" + calcularArea() + "m²)\n" +
            "Coste por hora: " + costePorHora + "€/hora\n" +
            "Plazas disponibles: " + getPlazasDisponibles();
    }
}
