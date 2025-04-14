import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Aerolinea {
	private String nombre;
	private String pais;
	private String codigoAerolinea;
	private List<Avion> flota;
	private List<Factura> facturas;
	private List<OperadorAereo> operadores;
    private List<OperadorAereo> observadores;
	
	public Aerolinea(String nombre, String pais, String codigoAerolinea) {
		this.nombre = nombre;
		this.pais = pais;
		this.codigoAerolinea = codigoAerolinea;
		this.flota = new ArrayList<>();
		this.facturas = new ArrayList<>();
		this.operadores = new ArrayList<>();
        this.observadores = new ArrayList<>();
	}
	
    public List<Avion> getFlota() { return flota; }

    public List<Factura> getFacturas() { return facturas; }
    
    public List<OperadorAereo> getOperadores() {
        return operadores;
    }

	public String getNombre() {
	    return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getCodigoAerolinea() {
		return codigoAerolinea;
	}
	
	public void setCodigoAerolinea(String codigoAerolinea) {
		this.codigoAerolinea = codigoAerolinea;
	}
	
    public void añadirAvion(Avion avion) {
        if (avion != null && !flota.contains(avion)) {
            flota.add(avion);
            avion.asignarAerolinea(this);
        }
    }

	
    public void removerAvion(Avion avion) {
        if (flota.contains(avion)) {
            flota.remove(avion);
            avion.asignarAerolinea(null);  // Desasignar la aerolínea del avión
        }
    }

    public void mostrarFacturas() {
        System.out.println("Facturas de " + nombre + ":");
        for (Factura factura : facturas) {
            System.out.println(factura);
        }
    }
    
    public void eliminarAerolinea() {
        facturas.clear();  // Elimina todas las facturas antes de eliminar la aerolínea
        System.out.println("Aerolinea " + nombre + " eliminada junto con sus facturas.");
    }
    
    public void agregarOperador(OperadorAereo operador) {
        if (operador == null || operadores.contains(operador)) return;  // Verifica si ya está en la lista
        if (operador.getAerolineaAsignada() != null) {
            operador.getAerolineaAsignada().eliminarOperador(operador); // Elimina de su aerolínea anterior
        }
        operadores.add(operador);
        operador.setAerolineaAsignada(this); // Asigna la aerolínea al operador
    }

    public void eliminarOperador(OperadorAereo operador) {
        if (operador != null && operadores.remove(operador)) {
            operador.setAerolineaAsignada(null); // Elimina la asignación del operador
        }
    }

    public double calcularCostoTotal(LocalDateTime fechaInicio, LocalDateTime fechaFin, ArrayList<UsoElementoAeropuerto> usos, ArrayList<Vuelo> vuelos, double costeVuelo) {
        double costoTotal = 0;

        for (UsoElementoAeropuerto uso : usos) {
            if (uso.getFechaHoraInicio().isAfter(fechaInicio) && uso.getFechaHoraFin().isBefore(fechaFin)) {
                costoTotal += uso.calcularCosteTotal();
            }
        }

        for (Vuelo vuelo : vuelos) {
            if (vuelo.getFechaHora().isAfter(fechaInicio) && vuelo.getFechaHora().isBefore(fechaFin)) {
                costoTotal += costeVuelo;
            }
        }

        return costoTotal;
    }

    public void agregarObservador(OperadorAereo operador) {
        if (!observadores.contains(operador)) {
            observadores.add(operador);
        }
    }

    public void eliminarObservador(OperadorAereo operador){
        observadores.remove(operador);
    }

    public void notificarCambio(String mesage){
        for(OperadorAereo operador : observadores){
            operador.recibirNotificacion(new Notificacion(mesage, 0, List.of(operador)));
        }
    }
    
    @Override
    public String toString() {
        return "Aerolinea [Nombre=" + nombre + ", País=" + pais + ", Código=" + codigoAerolinea +
               ", Flota de aviones=" + flota.size() + ", Operadores=" + operadores.size() + "]";
    }

	
}