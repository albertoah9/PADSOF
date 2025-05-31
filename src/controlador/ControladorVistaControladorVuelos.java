package controlador;

import vista.VistaControladorVuelos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVistaControladorVuelos {

    private VistaControladorVuelos vista;

    public ControladorVistaControladorVuelos(VistaControladorVuelos vista) {
        this.vista = vista;

        this.vista.btnModificarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Modificar estado del vuelo");
            }
        });

        this.vista.btnBuscarVuelo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Buscar vuelo");
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}