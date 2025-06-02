
import modelo.GestorAeropuerto;
import vista.VistaGestorPrincipal;
import javax.swing.SwingUtilities;

public class MainGestor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Crear una instancia del GestorAeropuerto (el modelo)
            GestorAeropuerto gestor = new GestorAeropuerto(null, null);

            // Crear una instancia de la VistaGestorPrincipal, pasando el nombre de usuario y el gestor
            VistaGestorPrincipal vista = new VistaGestorPrincipal("Gestor");

            // No necesitamos instanciar controladores individuales aquí para la visualización básica
            // Los controladores se instanciarán donde se necesiten para la interacción
        });
    }
}