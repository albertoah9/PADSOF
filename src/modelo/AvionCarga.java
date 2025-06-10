package modelo;

import java.time.LocalDate;

public class AvionCarga extends Avion {
    private double cargaMax;
    private boolean mercPeligrosas;
    private boolean controlTemperatura;

    public AvionCarga(String marca, String modelo, String matricula, int autonomia, LocalDate ultimaRevis, LocalDate anyoCompra, 
                        boolean controlTemp, double cargaMax, boolean mercPeligrosas, Aerolinea aerolinea) {
        super(marca, modelo, matricula, autonomia, ultimaRevis, anyoCompra, aerolinea);
        
        this.cargaMax = cargaMax;
        this.mercPeligrosas = mercPeligrosas;
        this.controlTemperatura = controlTemp;
    }

    public double getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(double cargaMax) {
        this.cargaMax = cargaMax;
    }

    public boolean isMercPeligrosas() {
        return mercPeligrosas;
    }

    public void setMercPeligrosas(boolean mercPeligrosas) {
        this.mercPeligrosas = mercPeligrosas;
    }

    public boolean getControlTemperatura() {
        return controlTemperatura;
    }
}
