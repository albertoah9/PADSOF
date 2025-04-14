import java.util.ArrayList;
public class TerminalCarga extends Terminal {
    private ArrayList<ZonaAparcamiento> zonasAparcamiento;

    public TerminalCarga() {
        super();
        this.zonasAparcamiento = new ArrayList<>();
    }

    public ArrayList<ZonaAparcamiento> getZonaAparcamientos(){
        return new ArrayList<>(zonasAparcamiento);
    }

    public void agregarZonaAparcamiento(ZonaAparcamiento zona){
        if (zona != null && !zonasAparcamiento.contains(zona)) {
            zonasAparcamiento.add(zona);
        }
    }

    public void eliminarZonaAparcamiento(ZonaAparcamiento zona) {
        zonasAparcamiento.remove(zona);
    }

    
    @Override
    public String toString() {
        StringBuilder info = new StringBuilder(super.toString());
        info.append("\nZonas de aparcamiento:\n");
        for (ZonaAparcamiento zona : zonasAparcamiento) {
            info.append(zona).append("\n---\n");
        }
        return info.toString();
    }

}
