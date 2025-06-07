
import javax.swing.SwingUtilities;
import modelo.GestorAeropuerto;
import vista.VistaGestorPrincipal;

public class MainGestor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            GestorAeropuerto gestor = new GestorAeropuerto(null, null);

            VistaGestorPrincipal vista = new VistaGestorPrincipal("Gestor");

        });
    }
}