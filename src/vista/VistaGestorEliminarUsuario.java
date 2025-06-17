package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import modelo.Usuario;

public class VistaGestorEliminarUsuario extends JFrame {
    public JComboBox<Usuario> comboUsuarios;
    public JButton btnEliminar;
    public JButton btnCancelar;

    public VistaGestorEliminarUsuario(List<Usuario> usuarios) {
        setTitle("Eliminar Usuario");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Seleccione un usuario a eliminar:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        comboUsuarios = new JComboBox<>(usuarios.toArray(new Usuario[0]));
        comboUsuarios.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel centerPanel = new JPanel();
        centerPanel.add(comboUsuarios);
        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        btnEliminar = new JButton("Eliminar");
        btnCancelar = new JButton("Cancelar");
        bottomPanel.add(btnEliminar);
        bottomPanel.add(btnCancelar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public Usuario getUsuarioSeleccionado() {
        return (Usuario) comboUsuarios.getSelectedItem();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}