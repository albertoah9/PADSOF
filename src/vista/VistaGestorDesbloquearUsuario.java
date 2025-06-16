package vista;

import javax.swing.*;
import java.awt.*;

public class VistaGestorDesbloquearUsuario extends JFrame {

    public JComboBox<String> cmbUsuariosBloqueados;
    public JButton btnDesbloquear;

    public VistaGestorDesbloquearUsuario() {
        setTitle("Desbloquear Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel lblTitulo = new JLabel("Seleccione un usuario bloqueado:");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(lblTitulo);

        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        cmbUsuariosBloqueados = new JComboBox<>();
        cmbUsuariosBloqueados.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panel.add(cmbUsuariosBloqueados);

        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        btnDesbloquear = new JButton("Desbloquear");
        btnDesbloquear.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnDesbloquear);

        add(panel, BorderLayout.CENTER);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}