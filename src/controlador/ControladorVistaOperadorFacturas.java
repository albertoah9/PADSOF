package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import vista.VistaOperadorFacturas;
import vista.VistaOperadorMostrarFacturas;
import vista.VistaOperadorPrincipal;

public class ControladorVistaOperadorFacturas {

    private VistaOperadorFacturas vista;
    private Aeropuerto aeropuerto;
    private Aerolinea aerolinea;
    private VistaOperadorPrincipal vistaAnterior;

    public ControladorVistaOperadorFacturas(VistaOperadorFacturas vista, Aeropuerto aeropuerto, Aerolinea aerolinea, VistaOperadorPrincipal vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.aerolinea = aerolinea;
        this.vistaAnterior = vistaAnterior;

        this.vista.btnVerFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.setVisible(false);
                VistaOperadorMostrarFacturas vistaMostrar = new VistaOperadorMostrarFacturas();
                ControladorVistaOperadorMostrarFacturas controladorMostrar =
                    new ControladorVistaOperadorMostrarFacturas(vistaMostrar, aerolinea, vista);
                controladorMostrar.iniciar();
            }
        });


        this.vista.btnPagarFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Pagar facturas de la aerolínea " + aerolinea.getNombre());
            }
        });

        this.vista.btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}