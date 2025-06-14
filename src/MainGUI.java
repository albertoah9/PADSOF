import controlador.ControladorVistaLogin;
import modelo.Aeropuerto;
import modelo.GestorAeropuerto;
import vista.VistaLogin;

public class MainGUI {
    public static void main(String[] args) {
        Aeropuerto aeropuerto = new Aeropuerto("Adolfo Suárez", "Madrid", "España", Aeropuerto.UbiRelCiudad.ESTE);

        aeropuerto.addUsuario(new GestorAeropuerto("Marcos", "1234", aeropuerto));

        VistaLogin vistaLogin = new VistaLogin();
        new ControladorVistaLogin(vistaLogin, aeropuerto);

        vistaLogin.setVisible(true);
    }
}