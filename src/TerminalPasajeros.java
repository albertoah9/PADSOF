
import java.util.ArrayList;

public class TerminalPasajeros extends Terminal{
    private int aforoMax;
    private ArrayList<Finger> fingers;
    
    public TerminalPasajeros(int aforoMax) {
        super();
        this.aforoMax = aforoMax;
        this.fingers = new ArrayList<>();
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
    
}
