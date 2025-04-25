import javax.swing.*;
import java.awt.*;

public class PanelOrdenPago extends JPanel {

    public PanelOrdenPago() {
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("ID de la factura:"));
        JTextField txtFactura = new JTextField();
        add(txtFactura);

        add(new JLabel("Importe:"));
        JTextField txtImporte = new JTextField();
        add(txtImporte);

        add(new JLabel("Fecha de pago:"));
        JTextField txtFecha = new JTextField();
        add(txtFecha);

        add(new JLabel("Método de pago:"));
        JComboBox<String> comboMetodo = new JComboBox<>(new String[]{"Transferencia", "Tarjeta", "Efectivo"});
        add(comboMetodo);

        JButton btnPagar = new JButton("Generar orden de pago");
        add(btnPagar);

        btnPagar.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Orden de pago generada correctamente.");
        });
    }
}
