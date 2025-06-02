
import controlador.ControladorVistaControladorPrincipal;
import vista.VistaControladorPrincipal;

public class MainControlador {
    public static void main(String[] args) {
        VistaControladorPrincipal vista = new VistaControladorPrincipal();
        ControladorVistaControladorPrincipal controlador = new ControladorVistaControladorPrincipal(vista);
        controlador.iniciar();
    }
}