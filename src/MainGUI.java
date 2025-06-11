import controlador.ControladorVistaLogin;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.VistaLogin;

public class MainGUI {
    public static void main(String[] args) {
        // Crea un aeropuerto de prueba (puedes cargarlo desde archivo si lo prefieres)
        Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);

        aeropuerto.addUsuario(new GestorAeropuerto("Marcos", "1234", aeropuerto));

        // Crear vista y controlador
        VistaLogin vistaLogin = new VistaLogin();
        new ControladorVistaLogin(vistaLogin, aeropuerto);

        vistaLogin.setVisible(true);
    }
}