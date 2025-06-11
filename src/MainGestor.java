import javax.swing.SwingUtilities;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.VistaGestorPrincipal;

public class MainGestor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Aeropuerto miAeropuerto = new Aeropuerto("Aeropuerto Adolfo Suárez Madrid-Barajas", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);

            GestorAeropuerto gestor = new GestorAeropuerto("admin", "password123", miAeropuerto);

            VistaGestorPrincipal vista = new VistaGestorPrincipal(gestor);
            vista.setVisible(true);

        });
    }
}