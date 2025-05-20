

import java.awt.*;
import javax.swing.*;

public class VentanaPrincipal extends JFrame {

    public VentanaPrincipal() {
        setTitle("Inicio de Sesión");
        setSize(1000, 700);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Pantalla completa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra si no está maximizada

        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setBackground(Color.LIGHT_GRAY);
        JLabel titulo = new JLabel("Bienvenido - Iniciar Sesión");
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelSuperior.add(titulo);
        add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(50, 200, 50, 200));

        // Componentes con tamaño fijo en subpaneles
        String[] tipousuario = {"Operador", "Controlador", "Gestor"};
        JComboBox<String> combo = new JComboBox<>(tipousuario);
        combo.setPreferredSize(new Dimension(200, 25));

        JTextField campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(200, 25));

        JPasswordField campoContrasena = new JPasswordField();
        campoContrasena.setPreferredSize(new Dimension(200, 25));

        JButton botonLogin = new JButton("Iniciar sesión");
        botonLogin.setPreferredSize(new Dimension(200, 30));

        // Añadir componentes con etiquetas en subpaneles alineados
        panelCentral.add(crearLinea("Selecciona usuario:", combo));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(crearLinea("Usuario:", campoUsuario));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        panelCentral.add(crearLinea("Contraseña:", campoContrasena));
        panelCentral.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.add(botonLogin);
        panelCentral.add(panelBoton);

        add(panelCentral, BorderLayout.CENTER);

        botonLogin.addActionListener(e -> {
            String tipo = (String) combo.getSelectedItem();
            String nombre = campoUsuario.getText();
            String pass = new String(campoContrasena.getPassword());

            switch (tipo) {
                case "Gestor":
                    GestorAeropuerto gestor = new GestorAeropuerto(nombre, pass);
                    new VentanaGestor(gestor);
                    break;
                case "Controlador":
                    Terminal terminalDummy = new TerminalPasajeros(1500);
                    ControladorAereo controlador = new ControladorAereo(nombre, pass, terminalDummy);
                    new VentanaControlador(controlador);
                    break;
                case "Operador":
                    Aerolinea aerolineaDummy = new Aerolinea("Iberia", "IB123");
                    OperadorAereo operador = new OperadorAereo(nombre, pass, aerolineaDummy);
                    new VentanaOperador();
                    break;
            }

            dispose(); // Cerramos la ventana de login
        });
    }

    // Método para crear una línea con etiqueta + componente alineados
    private JPanel crearLinea(String etiqueta, JComponent campo) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lbl = new JLabel(etiqueta);
        lbl.setPreferredSize(new Dimension(150, 25));
        panel.add(lbl);
        panel.add(campo);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}
