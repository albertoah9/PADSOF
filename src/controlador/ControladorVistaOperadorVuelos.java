package controlador;

import vista.VistaOperadorVuelos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaOperadorVuelos {

    private VistaOperadorVuelos vista;

    public ControladorVistaOperadorVuelos(VistaOperadorVuelos vista) {
        this.vista = vista;

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
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}