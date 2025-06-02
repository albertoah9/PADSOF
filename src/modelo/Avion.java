package modelo;
import java.time.LocalDate;

public abstract class Avion {

    private static int contador = 1;

    private int id;
    private String marca;
    private String modelo;
    private String matricula;
    private int autonomia;
    private LocalDate ultimaRevis;
    private LocalDate anyoCompra;
    private Aerolinea aerolinea; 

    // Constructor
    public Avion(String marca, String modelo, String matricula, int autonomia, 
                    LocalDate ultimaRevis, LocalDate anyoCompra, Aerolinea aerolinea){
        this.id = contador++;
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.autonomia = autonomia;
        this.ultimaRevis = ultimaRevis;
        this.anyoCompra = anyoCompra;
        this.aerolinea = aerolinea;
    }

    //geters y seters
    public Aerolinea getAerolinea() { return aerolinea; } //Añadido

    
    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAutonomia() {
        return autonomia;
    }
    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

    public LocalDate getUltimaRevis() {
        return ultimaRevis;
    }
    public void setUltimaRevis(LocalDate ultimaRevis) {
        this.ultimaRevis = ultimaRevis;
    }

    public LocalDate getAnyoCompra() {
        return anyoCompra;
    }
    public void setAnyoCompra(LocalDate anyoCompra) {
        this.anyoCompra = anyoCompra;
    }

    public void asignarAerolinea(Aerolinea aerolinea) {
        if (this.aerolinea != null) {
            this.aerolinea.removerAvion(this);
        }
        this.aerolinea = aerolinea;
        aerolinea.añadirAvion(this);
    }
    

}
