package modelo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Necesitaremos una clase GestorAeropuerto (o similar) para pasar información si es necesario
// public class GestorAeropuerto { ... } // Asumo que ya tienes esta clase o una equivalente

public class PanelSettings extends JPanel {

    private GestorAeropuerto gestor; // O el tipo de usuario adecuado

    // Componentes de la interfaz
    private JLabel lblTitulo;

    // Sección Cambiar Contraseña
    private JLabel lblCurrentPassword;
    private JPasswordField txtCurrentPassword;
    private JLabel lblNewPassword;
    private JPasswordField txtNewPassword;
    private JLabel lblConfirmNewPassword;
    private JPasswordField txtConfirmNewPassword;
    private JButton btnChangePassword;

    // Sección Información de Contacto (Ejemplo simple)
    private JLabel lblEmail;
    private JTextField txtEmail;
    private JButton btnUpdateInfo;

    // Sección Preferencias de Notificación (Ejemplo simple)
    private JLabel lblNotificationPreferences;
    private JCheckBox chkEmailNotifications;
    private JCheckBox chkSMSNotifications;

    public PanelSettings(GestorAeropuerto gestor2) {
        this.gestor = gestor2; // Guardamos la referencia al gestor (si es necesaria para lógica futura)

        // Configurar el layout del panel principal
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Apila los componentes verticalmente
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Añade un margen alrededor

        // --- Título del Panel ---
        lblTitulo = new JLabel("Configuración de Usuario");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra el título

        // --- Sección Cambiar Contraseña ---
        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new BoxLayout(panelPassword, BoxLayout.Y_AXIS)); // Apila verticalmente dentro de esta sección
        panelPassword.setBorder(BorderFactory.createTitledBorder("Cambiar Contraseña")); // Borde con título

        lblCurrentPassword = new JLabel("Contraseña Actual:");
        txtCurrentPassword = new JPasswordField(20); // Tamaño preferido inicial
        lblNewPassword = new JLabel("Nueva Contraseña:");
        txtNewPassword = new JPasswordField(20);
        lblConfirmNewPassword = new JLabel("Confirmar Nueva Contraseña:");
        txtConfirmNewPassword = new JPasswordField(20);
        btnChangePassword = new JButton("Cambiar Contraseña");

        // Alinear etiquetas y campos de texto en pares
        JPanel panelCurrentPassword = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Usamos FlowLayout para la pareja
        panelCurrentPassword.add(lblCurrentPassword);
        panelCurrentPassword.add(txtCurrentPassword);

        JPanel panelNewPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNewPassword.add(lblNewPassword);
        panelNewPassword.add(txtNewPassword);

        JPanel panelConfirmPassword = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelConfirmPassword.add(lblConfirmNewPassword);
        panelConfirmPassword.add(txtConfirmNewPassword);

        JPanel panelButtonPassword = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Centramos el botón
        panelButtonPassword.add(btnChangePassword);

        panelPassword.add(panelCurrentPassword);
        panelPassword.add(Box.createRigidArea(new Dimension(0, 5))); // Espacio vertical
        panelPassword.add(panelNewPassword);
        panelPassword.add(Box.createRigidArea(new Dimension(0, 5)));
        panelPassword.add(panelConfirmPassword);
        panelPassword.add(Box.createRigidArea(new Dimension(0, 15)));
        panelPassword.add(panelButtonPassword);
        panelPassword.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear esta sección a la izquierda

        // --- Sección Información de Contacto ---
        JPanel panelContactInfo = new JPanel();
        panelContactInfo.setLayout(new BoxLayout(panelContactInfo, BoxLayout.Y_AXIS));
        panelContactInfo.setBorder(BorderFactory.createTitledBorder("Información de Contacto"));

        lblEmail = new JLabel("Correo Electrónico:");
        txtEmail = new JTextField(gestor2 != null ? gestor2.getEmail() : "", 20); // Ejemplo: precargar email si existe en GestorAeropuerto
        btnUpdateInfo = new JButton("Actualizar Información");

        JPanel panelEmail = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelEmail.add(lblEmail);
        panelEmail.add(txtEmail);

        JPanel panelButtonInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButtonInfo.add(btnUpdateInfo);

        panelContactInfo.add(panelEmail);
        panelContactInfo.add(Box.createRigidArea(new Dimension(0, 15)));
        panelContactInfo.add(panelButtonInfo);
         panelContactInfo.setAlignmentX(Component.LEFT_ALIGNMENT);

        // --- Sección Preferencias de Notificación ---
        JPanel panelNotifications = new JPanel();
        panelNotifications.setLayout(new BoxLayout(panelNotifications, BoxLayout.Y_AXIS));
        panelNotifications.setBorder(BorderFactory.createTitledBorder("Preferencias de Notificación"));

        lblNotificationPreferences = new JLabel("Recibir notificaciones por:");
        chkEmailNotifications = new JCheckBox("Correo Electrónico", true); // true = seleccionado por defecto
        chkSMSNotifications = new JCheckBox("SMS", false);

        panelNotifications.add(lblNotificationPreferences);
        panelNotifications.add(Box.createRigidArea(new Dimension(0, 5)));
        panelNotifications.add(chkEmailNotifications);
        panelNotifications.add(chkSMSNotifications);
        panelNotifications.setAlignmentX(Component.LEFT_ALIGNMENT);


        // --- Añadir todo al panel principal ---
        add(lblTitulo);
        add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre título y primera sección
        add(panelPassword);
        add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones
        add(panelContactInfo);
        add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones
        add(panelNotifications);
        // Puedes añadir más espacio al final si quieres
        // add(Box.createVerticalGlue()); // Empuja los componentes hacia arriba si hay espacio extra

        // --- Añadir Action Listeners (Inicialmente, solo muestran un mensaje) ---
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para cambiar la contraseña
                // Por ahora, solo mostramos un mensaje
                char[] currentPass = txtCurrentPassword.getPassword();
                char[] newPass = txtNewPassword.getPassword();
                char[] confirmNewPass = txtConfirmNewPassword.getPassword();

                // Simple validación (deberías añadir más lógica real aquí)
                if (newPass.length == 0 || confirmNewPass.length == 0) {
                     JOptionPane.showMessageDialog(PanelSettings.this, "La nueva contraseña no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (String.valueOf(newPass).equals(String.valueOf(confirmNewPass))) {
                    JOptionPane.showMessageDialog(PanelSettings.this, "Lógica de cambio de contraseña aquí...", "Cambio de Contraseña", JOptionPane.INFORMATION_MESSAGE);
                    // Limpiar campos de contraseña después del intento
                    txtCurrentPassword.setText("");
                    txtNewPassword.setText("");
                    txtConfirmNewPassword.setText("");
                } else {
                     JOptionPane.showMessageDialog(PanelSettings.this, "Las nuevas contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                     txtNewPassword.setText("");
                     txtConfirmNewPassword.setText("");
                }
                // Importante: Limpiar los arrays de caracteres sensibles
                 java.util.Arrays.fill(currentPass, '0');
                 java.util.Arrays.fill(newPass, '0');
                 java.util.Arrays.fill(confirmNewPass, '0');
            }
        });

        btnUpdateInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí iría la lógica para actualizar la información de contacto
                 String email = txtEmail.getText();
                JOptionPane.showMessageDialog(PanelSettings.this, "Lógica para actualizar email: " + email, "Actualizar Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Los JCheckBox no necesitan ActionListeners si solo guardas el estado al hacer clic en un botón de "Guardar Configuración" general,
        // pero podrías añadir listeners si quisieras aplicar cambios inmediatamente o realizar alguna acción al marcarlos/desmarcarlos.
        // Por ahora, asumimos que se guardarían con un botón "Guardar Configuración" general o al actualizar la información.
    }

    // Método dummy para el ejemplo, asumiendo que GestorAeropuerto tiene un getEmail()
    // Reemplaza esto con la implementación real de tu clase GestorAeropuerto
    private static class GestorAeropuerto {
        private String nombre;
        private String password;
        private String email;

        public GestorAeropuerto(String nombre, String password) {
            this.nombre = nombre;
            this.password = password;
            this.email = "ejemplo@aeropuerto.com"; // Email de ejemplo
        }

        public String getNombre() { return nombre; }
        public String getPassword() { return password; } // Nota: No es seguro manejar contraseñas así en una app real
        public String getEmail() { return email; }

        // Puedes añadir métodos para actualizar el email, cambiar contraseña, etc.
    }

     // Método main para probar solo este panel (opcional)
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Panel Settings Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500); // Ajusta el tamaño según necesites
            frame.setLocationRelativeTo(null); // Centra la ventana

            // Crea un GestorAeropuerto dummy para la prueba
            GestorAeropuerto gestorDummy = new GestorAeropuerto("UsuarioPrueba", "pass123");

            PanelSettings settingsPanel = new PanelSettings(gestorDummy);
            frame.add(settingsPanel); // Añade el panel al frame

            frame.setVisible(true);
        });
    }
}