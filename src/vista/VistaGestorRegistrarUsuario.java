package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import modelo.Aerolinea;
import modelo.Terminal;

public class VistaGestorRegistrarUsuario extends JFrame {

    public JComboBox<String> cmbTipoUsuario;
    public JTextField txtNombre;
    public JPasswordField txtContrasena;
    public JComboBox<Aerolinea> cmbAerolinea;
    public JComboBox<Terminal> cmbTerminal;
    public JButton btnRegistrar;
    public JLabel lblMensaje;
    public JButton btnCancelar;

    public VistaGestorRegistrarUsuario(List<Aerolinea> aerolineas, List<Terminal> terminales) {
        setTitle("Registrar Nuevo Usuario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JLabel lblTitulo = new JLabel("Registrar Usuario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(lblTitulo);

        cmbTipoUsuario = new JComboBox<>(new String[] { "Operador Aéreo", "Controlador Aéreo" });
        cmbTipoUsuario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(new JLabel("Tipo de Usuario:"));
        panel.add(cmbTipoUsuario);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtNombre = new JTextField();
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        txtContrasena = new JPasswordField();
        panel.add(new JLabel("Contraseña:"));
        panel.add(txtContrasena);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        cmbAerolinea = new JComboBox<>();
        for (Aerolinea a : aerolineas) cmbAerolinea.addItem(a);
        panel.add(new JLabel("Aerolínea asignada:"));
        panel.add(cmbAerolinea);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        cmbTerminal = new JComboBox<>();
        for (Terminal t : terminales) cmbTerminal.addItem(t);
        panel.add(new JLabel("Terminal asignada:"));
        panel.add(cmbTerminal);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        btnRegistrar = new JButton("Registrar Usuario");
        btnRegistrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(btnRegistrar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setForeground(Color.RED);
        panel.add(lblMensaje);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 16));
        btnCancelar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(btnCancelar);

        add(panel, BorderLayout.CENTER);

        actualizarCamposVisibles();

        cmbTipoUsuario.addActionListener(e -> actualizarCamposVisibles());
    }

    private void actualizarCamposVisibles() {
        String tipo = (String) cmbTipoUsuario.getSelectedItem();
        cmbAerolinea.setEnabled("Operador Aéreo".equals(tipo));
        cmbTerminal.setEnabled("Controlador Aéreo".equals(tipo));
    }

    public void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
    }
}