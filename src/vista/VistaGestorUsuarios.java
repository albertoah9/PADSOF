package vista;

import java.awt.*;
import javax.swing.*;

public class VistaGestorUsuarios extends JFrame {

    public JButton btnRegistrarUsuario;
    public JButton btnDesbloquearUsuario;
    public JButton btnEliminarUsuario;
    public JLabel lblTitulo;
    public JButton btnVolver;

    public VistaGestorUsuarios() {
        setTitle("Gestión de Usuarios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        lblTitulo = new JLabel("Gestión de Usuarios", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        btnRegistrarUsuario = crearBoton("Registrar Nuevo Usuario");
        panel.add(btnRegistrarUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnDesbloquearUsuario = crearBoton("Desbloquear Usuario");
        panel.add(btnDesbloquearUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        btnEliminarUsuario = crearBoton("Eliminar Usuario");
        panel.add(btnEliminarUsuario);

        btnVolver = new JButton("Volver");
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(btnVolver);

        add(panel, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Arial", Font.PLAIN, 16));
        boton.setPreferredSize(new Dimension(250, 50));
        boton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setFocusPainted(false);
        return boton;
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}