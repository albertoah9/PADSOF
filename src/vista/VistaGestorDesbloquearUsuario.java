package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import modelo.OperadorAereo;

public class VistaGestorDesbloquearUsuario extends JFrame {
    public JComboBox<OperadorAereo> comboOperadores;
    public JPasswordField campoNuevaContrasena;
    public JButton btnDesbloquear;
    public JButton btnCancelar;

    public VistaGestorDesbloquearUsuario(List<OperadorAereo> operadoresBloqueados) {
        setTitle("Desbloquear Operador");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JLabel lblTitulo = new JLabel("Desbloquear operador", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblOperador = new JLabel("Operador:");
        lblOperador.setHorizontalAlignment(SwingConstants.RIGHT);
        centerPanel.add(lblOperador);

        comboOperadores = new JComboBox<>(operadoresBloqueados.toArray(new OperadorAereo[0]));
        comboOperadores.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(comboOperadores);

        JLabel lblContrasena = new JLabel("Nueva contraseña:");
        lblContrasena.setHorizontalAlignment(SwingConstants.RIGHT);
        centerPanel.add(lblContrasena);

        campoNuevaContrasena = new JPasswordField();
        campoNuevaContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        campoNuevaContrasena.setPreferredSize(new Dimension(200, 24));
        centerPanel.add(campoNuevaContrasena);

        add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        btnDesbloquear = new JButton("Desbloquear");
        btnCancelar = new JButton("Cancelar");
        bottomPanel.add(btnDesbloquear);
        bottomPanel.add(btnCancelar);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    public OperadorAereo getOperadorSeleccionado() {
        return (OperadorAereo) comboOperadores.getSelectedItem();
    }

    public String getNuevaContrasena() {
        return new String(campoNuevaContrasena.getPassword());
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}