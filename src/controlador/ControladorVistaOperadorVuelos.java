package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import vista.VistaOperadorVuelos;

public class ControladorVistaOperadorVuelos {

    private VistaOperadorVuelos vista;
    private JFrame vistaPrincipal;

    public ControladorVistaOperadorVuelos(VistaOperadorVuelos vista, JFrame vistaPrincipal) {
        this.vista = vista;
        this.vistaPrincipal = vistaPrincipal;

        this.vista.btnCrearVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Crear nuevo vuelo");
                // Aquí se abriría un formulario o lógica de creación
            }
        });

        this.vista.btnModificarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modificar estado del vuelo");
            }
        });

        this.vista.btnEliminarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Eliminar vuelo");
            }
        });

        this.vista.btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose(); // Cierra la ventana de vuelos
                vistaPrincipal.setVisible(true); // Muestra de nuevo la ventana principal
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}