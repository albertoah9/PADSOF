package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import modelo.OperadorAereo;

public class VistaGestorDesbloquearUsuario extends JFrame {
    public JComboBox<OperadorAereo> comboOperadores;
    public JButton btnDesbloquear;
    public JButton btnCancelar;

    public VistaGestorDesbloquearUsuario(List<OperadorAereo> operadoresBloqueados) {
        setTitle("Desbloquear Operador");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Seleccione un operador bloqueado:", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        comboOperadores = new JComboBox<>(operadoresBloqueados.toArray(new OperadorAereo[0]));
        comboOperadores.setFont(new Font("Arial", Font.PLAIN, 14));
        JPanel centerPanel = new JPanel();
        centerPanel.add(comboOperadores);
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

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}