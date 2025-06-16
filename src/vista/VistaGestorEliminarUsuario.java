package vista;

import javax.swing.*;
import java.awt.*;

public class VistaGestorEliminarUsuario extends JFrame {

    public JComboBox<String> cmbUsuariosAEliminar;
    public JButton btnEliminar;

    public VistaGestorEliminarUsuario() {
        setTitle("Eliminar Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Seleccione un usuario a eliminar:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        cmbUsuariosAEliminar = new JComboBox<>();
        cmbUsuariosAEliminar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(cmbUsuariosAEliminar);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnEliminar);

        add(panel, BorderLayout.CENTER);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}