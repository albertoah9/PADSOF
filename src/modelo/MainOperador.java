package modelo;
import controlador.ControladorVistaOperadorPrincipal;
import vista.VistaOperadorPrincipal;

public class MainOperador {
    public static void main(String[] args) {
        VistaOperadorPrincipal vista = new VistaOperadorPrincipal();
        ControladorVistaOperadorPrincipal controlador = new ControladorVistaOperadorPrincipal(vista);
        controlador.iniciar();
    }
}