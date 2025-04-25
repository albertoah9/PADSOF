
public class VentanaOperador extends VentanaBase {
    public VentanaOperador(OperadorAereo operador) {
        super("Ventana Operador - " + operador.getNombre());

        JPanel panelLateral = construirpanelLateral();
        add(panelLateral, BorderLayout.WEST);
    }
}
