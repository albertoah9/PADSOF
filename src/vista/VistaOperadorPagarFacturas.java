package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Factura;
import java.util.List;

public class VistaOperadorPagarFacturas extends JFrame {
    public JButton btnPagar;
    public JButton btnVolver;
    public JList<Factura> listaFacturas;
    private DefaultListModel<Factura> modeloLista;

    public VistaOperadorPagarFacturas(List<Factura> facturasPendientes) {
        setTitle("Pagar Facturas");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        modeloLista = new DefaultListModel<>();
        for (Factura f : facturasPendientes) {
            modeloLista.addElement(f);
        }

        listaFacturas = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaFacturas);

        btnPagar = new JButton("Pagar Factura Seleccionada");
        btnVolver = new JButton("Volver");

        JPanel botones = new JPanel();
        botones.add(btnPagar);
        botones.add(btnVolver);

        add(new JLabel("Facturas pendientes:"), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(botones, BorderLayout.SOUTH);
    }

    public Factura getFacturaSeleccionada() {
        return listaFacturas.getSelectedValue();
    }

    public void eliminarFactura(Factura f) {
        modeloLista.removeElement(f);
    }
}