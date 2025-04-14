
import java.util.ArrayList;

public class TerminalPasajeros extends Terminal{
    private int aforoMax;
    private ArrayList<Finger> fingers;
    private ArrayList<PuertaEmbarque> puertasEmbarque;

    
    public TerminalPasajeros(int aforoMax) {
        super();
        this.aforoMax = aforoMax;
        this.fingers = new ArrayList<>();
        this.puertasEmbarque = new ArrayList<>();

    }

    public int getAforoMax() {
        return aforoMax;
    }
    public void setAforoMax(int aforoMax) {
        this.aforoMax = aforoMax;
    }

    public ArrayList<Finger> getFingers() {
        return fingers;
    }
    public void addFinger(Finger finger) {
        this.fingers.add(finger);
    }



    public ArrayList<PuertaEmbarque> getPuertasEmbarque() {
        return new ArrayList<>(puertasEmbarque);
    }

    public void agregarPuertaEmbarque(PuertaEmbarque puerta) {
        if (puerta != null && puerta.getTipoPuerta() == PuertaEmbarque.TipoPuerta.PASAJEROS
                && !puertasEmbarque.contains(puerta)) {
            puertasEmbarque.add(puerta);
        }
    }

    public void eliminarPuertaEmbarque(PuertaEmbarque puerta) {
        puertasEmbarque.remove(puerta);
    }

    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(super.toString());
        info.append("\nAforo máximo: ").append(aforoMax);
        info.append("\nFingers:\n");
        for (Finger f : fingers) {
            info.append(" - Finger ID: ").append(f.getId()).append("\n");
        }
        info.append("Puertas de embarque:\n");
        for (PuertaEmbarque p : puertasEmbarque) {
            info.append(" - Puerta ID: ").append(p.getId()).append(", Aforo: ").append(p.getAforoMaximo()).append("\n");
        }
        return info.toString();
    }
    
}
