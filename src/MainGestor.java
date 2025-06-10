import javax.swing.SwingUtilities;

import modelo.Aeropuerto; // Asegúrate de importar la clase Aeropuerto
import modelo.GestorAeropuerto;
import vista.VistaGestorPrincipal;

public class MainGestor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Crear una instancia del objeto Aeropuerto
            // Necesitarás los parámetros correctos para el constructor de tu clase Aeropuerto.
            // Por ejemplo, si tu constructor es Aeropuerto(String nombre, String ciudad, String pais, UbiRelCiudad ubicacion):
            Aeropuerto miAeropuerto = new Aeropuerto("Aeropuerto Adolfo Suárez Madrid-Barajas", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);

            // 2. Crear una instancia del GestorAeropuerto (el modelo)
            // Pasar valores válidos para nombre, contraseña y el objeto Aeropuerto creado
            GestorAeropuerto gestor = new GestorAeropuerto("admin", "password123", miAeropuerto);

            // Crear una instancia de la VistaGestorPrincipal, pasando el gestor
            VistaGestorPrincipal vista = new VistaGestorPrincipal(gestor);
            vista.setVisible(true); // Hacer visible la vista principal

        });
    }
}