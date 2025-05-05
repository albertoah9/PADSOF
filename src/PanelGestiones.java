import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Asumiendo que GestorAeropuerto es visible
// import tu_paquete.GestorAeropuerto;

public class PanelGestiones extends JPanel {

    private GestorAeropuerto gestor;

    // Componentes para cada sección de formulario (declarados si necesitas acceso global, sino pueden ser locales)

    // Alta Operador (Imagen 1)
    private JTextField txtOpNombre;
    private JTextField txtOpApellido;
    private JTextField txtOpDni;
    private JComboBox<String> cmbOpAerolinea;
    private JButton btnOpConfirmar;

    // Alta Controlador (Imagen 1)
    private JTextField txtCoNombre;
    private JTextField txtCoApellido;
    private JTextField txtCoDni;
    private JPasswordField txtCoContrasena;
    private JComboBox<String> cmbCoTerminal;
    private JButton btnCoConfirmar;

    // Alta Terminal (Imagen 2)
    private JComboBox<String> cmbTerTipoTerminal;
    private JTextField txtTerAforoMaximo;
    private JTextField txtTerAparcamientoMercancias;
    private JTextField txtTerCodigoPuerta;
    private JTextField txtTerFingerAparcamientoPasajeros;
    private JButton btnTerConfirmar;

    // Alta Hangar (Imagen 2)
    private JComboBox<String> cmbHanTipoHangar;
    private JTextField txtHanDimensiones;
    private JTextField txtHanCostePorHora;
    private JTextField txtHanCostePorHoraUso;
    private JTextField txtHanCapacidadMercanciasPeligrosas;
    private JButton btnHanConfirmar;

    // Alta Aerolinea (Imagen 3)
    private JTextField txtAeCodigo;
    private JButton btnAeConfirmar;

    // Alta Avion (Imagen 3)
    private JTextField txtAvMarca;
    private JTextField txtAvModelo;
    private JTextField txtAvNumeroSerie;
    private JTextField txtAvDimension;
    private JTextField txtAvCapacidadPasajeros;
    private JTextField txtAvControlTemperatura;
    private JTextField txtAvPermisoMercanciasPeligrosas;
    private JTextField txtAvAñoCompra1; // Basado en la imagen, parece duplicado
    private JTextField txtAvId;
    private JTextField txtAvAñoCompra2; // Basado en la imagen, parece duplicado
    private JComboBox<String> cmbAvTipoAvion;
    private JButton btnAvConfirmar;

    // Alta Pista de aterrizaje (Imagen 4)
    private JComboBox<String> cmbPisTipoPista;
    private JTextField txtPisLongitudPista;
    private JButton btnPisConfirmar;

    // Alta Aparcamientos (Imagen 4)
    private JComboBox<String> cmbApaZonaAparcamiento;
    private JTextField txtApaCostePorHoraZona;
    private JTextField txtApaPlazasDisponibles;
    private JTextField txtApaCostePorHoraPlaza;
    private JTextField txtApaTamañoPlaza;
    private JButton btnApaConfirmar;

    // Alta Fingers (Imagen 4)
    private JTextField txtFinIdFinger;
    private JTextField txtFinPuertasEmbarqueAsociadas;
    private JTextField txtFinAlturaMaxima;
    private JTextField txtFinCostePorHora;
    private JButton btnFinConfirmar;


    public PanelGestiones(GestorAeropuerto gestor) {
        this.gestor = gestor;

        setLayout(new BorderLayout()); // Main panel layout to hold a scroll pane
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor del panel principal

        // Panel para contener todas las secciones de gestión, apiladas verticalmente
        JPanel sectionsPanel = new JPanel();
        sectionsPanel.setLayout(new BoxLayout(sectionsPanel, BoxLayout.Y_AXIS));
        // sectionsPanel.setBorder(BorderFactory.createLineBorder(Color.RED)); // Para depurar layout

        // Envolver sectionsPanel en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(sectionsPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // No necesitamos scroll horizontal
        add(scrollPane, BorderLayout.CENTER); // Añadir el scroll pane al panel principal


        // --- Añadir cada sección de gestión (Dar de alta) ---

        // Sección: Dar de alta a operadores (Imagen 1)
        sectionsPanel.add(createAltaOperadorPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta a Controladores (Imagen 1)
        sectionsPanel.add(createAltaControladorPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta a Terminales (Imagen 2)
        sectionsPanel.add(createAltaTerminalPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta a Hangares (Imagen 2)
        sectionsPanel.add(createAltaHangarPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta a Aerolineas (Imagen 3)
        sectionsPanel.add(createAltaAerolineaPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta a Aviones (Imagen 3)
        sectionsPanel.add(createAltaAvionPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta a Pistas de aterrizaje (Imagen 4)
        sectionsPanel.add(createAltaPistaPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta Aparcamientos (Imagen 4)
        sectionsPanel.add(createAltaAparcamientoPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones

        // Sección: Dar de alta Fingers (Imagen 4)
        sectionsPanel.add(createAltaFingerPanel());
        sectionsPanel.add(Box.createRigidArea(new Dimension(0, 20))); // Espacio entre secciones


        // Añadir pegamento vertical para empujar todo hacia arriba si hay espacio extra
        sectionsPanel.add(Box.createVerticalGlue());
    }

    // --- Métodos para crear cada panel de sección (Dar de alta) ---

    // Sección: Dar de alta a operadores (Imagen 1)
    private JPanel createAltaOperadorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a operadores"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre de Usuario
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre de Usuario"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtOpNombre = new JTextField(15);
        panel.add(txtOpNombre, gbc);

        // Apellido de Usuario
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Apellido de Usuario"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtOpApellido = new JTextField(15);
        panel.add(txtOpApellido, gbc);

        // Dni de Usuario
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Dni de Usuario"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtOpDni = new JTextField(15);
        panel.add(txtOpDni, gbc);

        // Aerolinea del Usuario
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Aerolinea del Usuario"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbOpAerolinea = new JComboBox<>(new String[]{"Seleccionar Aerolínea", "Aerolinea A", "Aerolinea B"}); // Placeholder data
        panel.add(cmbOpAerolinea, gbc);

        // Botón Confirmar
        gbc.gridx = 3; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnOpConfirmar = new JButton("Confirmar");
        panel.add(btnOpConfirmar, gbc);

        // Add ActionListener to Confirm button (Operador)
        btnOpConfirmar.addActionListener(e -> {
            String nombre = txtOpNombre.getText();
            String apellido = txtOpApellido.getText();
            String dni = txtOpDni.getText();
            String aerolinea = (String) cmbOpAerolinea.getSelectedItem();

            if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || "Seleccionar Aerolínea".equals(aerolinea)) {
                 JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos para el Operador.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                 return;
            }

            String mensaje = "Dar de alta Operador:\n" +
                             "Nombre: " + nombre + "\n" +
                             "Apellido: " + apellido + "\n" +
                             "DNI: " + dni + "\n" +
                             "Aerolínea: " + aerolinea;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Operador", JOptionPane.INFORMATION_MESSAGE);

            txtOpNombre.setText("");
            txtOpApellido.setText("");
            txtOpDni.setText("");
            cmbOpAerolinea.setSelectedIndex(0);
        });

        // Add horizontal glue to push components to the left
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);

        return panel;
    }

     // Sección: Dar de alta a Controladores (Imagen 1)
     private JPanel createAltaControladorPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a Controladores"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Nombre de Usuario
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Nombre de Usuario"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCoNombre = new JTextField(15);
        panel.add(txtCoNombre, gbc);

        // Apellido de Usuario
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Apellido de Usuario"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCoApellido = new JTextField(15);
        panel.add(txtCoApellido, gbc);

        // Dni de Usuario
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Dni de Usuario"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCoDni = new JTextField(15);
        panel.add(txtCoDni, gbc);

        // Contraseña del Usuario
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Contraseña del Usuario"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtCoContrasena = new JPasswordField(15); // Usar JPasswordField por seguridad
        panel.add(txtCoContrasena, gbc);

        // Terminal del Usuario
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Terminal del Usuario"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbCoTerminal = new JComboBox<>(new String[]{"Seleccionar Terminal", "Terminal 1", "Terminal 2"}); // Placeholder data
        panel.add(cmbCoTerminal, gbc);

        // Botón Confirmar
        gbc.gridx = 3; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnCoConfirmar = new JButton("Confirmar");
        panel.add(btnCoConfirmar, gbc);

        // Add ActionListener to Confirm button (Controlador)
        btnCoConfirmar.addActionListener(e -> {
            String nombre = txtCoNombre.getText();
            String apellido = txtCoApellido.getText();
            String dni = txtCoDni.getText();
            String contrasena = new String(txtCoContrasena.getPassword());
            String terminal = (String) cmbCoTerminal.getSelectedItem();

             if (nombre.isEmpty() || apellido.isEmpty() || dni.isEmpty() || contrasena.isEmpty() || "Seleccionar Terminal".equals(terminal)) {
                 JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos para el Controlador.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                 return;
            }

            String mensaje = "Dar de alta Controlador:\n" +
                             "Nombre: " + nombre + "\n" +
                             "Apellido: " + apellido + "\n" +
                             "DNI: " + dni + "\n" +
                             "Contraseña: " + contrasena + " (No mostrar contraseñas en mensajes reales)\n" +
                             "Terminal: " + terminal;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Controlador", JOptionPane.INFORMATION_MESSAGE);

            txtCoNombre.setText("");
            txtCoApellido.setText("");
            txtCoDni.setText("");
            txtCoContrasena.setText("");
            cmbCoTerminal.setSelectedIndex(0);
             java.util.Arrays.fill(txtCoContrasena.getPassword(), '0'); // Limpiar array por seguridad
        });

         // Add horizontal glue
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);

        return panel;
    }

    // Sección: Dar de alta a Terminales (Imagen 2)
    private JPanel createAltaTerminalPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a Terminales"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Tipo de terminal
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo de terminal"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbTerTipoTerminal = new JComboBox<>(new String[]{"Seleccionar Tipo", "Pasajeros", "Mercancias"}); // Placeholder data
        panel.add(cmbTerTipoTerminal, gbc);

        // Aforo máximo (Solo si es pasajeros)
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Aforo máximo (Solo si es pasajeros)"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTerAforoMaximo = new JTextField(15);
        panel.add(txtTerAforoMaximo, gbc);

         // Aparcamiento (Solo si es mercancías)
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Aparcamiento (Solo si es mercancías)"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTerAparcamientoMercancias = new JTextField(15);
        panel.add(txtTerAparcamientoMercancias, gbc); // Assuming text input based on image

        // Código de puerta de embarque (Solo si es pasajeros)
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Código de puerta de embarque(Solo si es pasajeros)"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTerCodigoPuerta = new JTextField(15);
        panel.add(txtTerCodigoPuerta, gbc);

        // Finger / Aparcamiento (Solo si es pasajeros) - Nombre de campo un poco ambiguo en imagen
        gbc.gridx = 2; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Finger / Aparcamiento(Solo si es pasajeros)"), gbc); // Usando nombre de imagen
        gbc.gridx = 3; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtTerFingerAparcamientoPasajeros = new JTextField(15);
        panel.add(txtTerFingerAparcamientoPasajeros, gbc); // Assuming text input


        // Botón Confirmar
        gbc.gridx = 3; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnTerConfirmar = new JButton("Confirmar");
        panel.add(btnTerConfirmar, gbc);

        // Add ActionListener to Confirm button (Terminal)
        btnTerConfirmar.addActionListener(e -> {
            String tipoTerminal = (String) cmbTerTipoTerminal.getSelectedItem();
            String aforoMaximo = txtTerAforoMaximo.getText();
            String aparcamientoMercancias = txtTerAparcamientoMercancias.getText();
            String codigoPuerta = txtTerCodigoPuerta.getText();
            String fingerAparcamientoPasajeros = txtTerFingerAparcamientoPasajeros.getText();


             if ("Seleccionar Tipo".equals(tipoTerminal)) {
                 JOptionPane.showMessageDialog(this, "Por favor, selecciona el tipo de terminal.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                 return;
            }
            // Añadir más validación según el tipo de terminal seleccionado

            String mensaje = "Dar de alta Terminal:\n" +
                             "Tipo: " + tipoTerminal + "\n" +
                             "Aforo Máximo: " + aforoMaximo + "\n" +
                             "Aparcamiento Mercancías: " + aparcamientoMercancias + "\n" +
                             "Código Puerta: " + codigoPuerta + "\n" +
                             "Finger/Aparcamiento Pasajeros: " + fingerAparcamientoPasajeros;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Terminal", JOptionPane.INFORMATION_MESSAGE);

            cmbTerTipoTerminal.setSelectedIndex(0);
            txtTerAforoMaximo.setText("");
            txtTerAparcamientoMercancias.setText("");
            txtTerCodigoPuerta.setText("");
            txtTerFingerAparcamientoPasajeros.setText("");
        });

         // Add horizontal glue
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);


        return panel;
    }

     // Sección: Dar de alta a Hangares (Imagen 2)
     private JPanel createAltaHangarPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a Hangares"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Tipo de hangar
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo de hangar"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbHanTipoHangar = new JComboBox<>(new String[]{"Seleccionar Tipo", "Reparación", "Mantenimiento", "Almacenamiento"}); // Placeholder data
        panel.add(cmbHanTipoHangar, gbc);

        // Coste por hora de uso
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Coste por hora de uso"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtHanCostePorHoraUso = new JTextField(15);
        panel.add(txtHanCostePorHoraUso, gbc);

        // Dimensiones (alto x ancho x largo)
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Dimensiones (alto x ancho x largo)"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtHanDimensiones = new JTextField(15);
        panel.add(txtHanDimensiones, gbc);

        // Capacidad para mercancías peligrosas (si permite)
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Capacidad para mercancías peligrosas (si permite)"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtHanCapacidadMercanciasPeligrosas = new JTextField(15); // Assuming text input
        panel.add(txtHanCapacidadMercanciasPeligrosas, gbc);


        // Coste por hora (aparece en otra línea en la imagen, puede ser diferente al de uso?) - Asumo que es otro campo
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Coste por hora"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtHanCostePorHora = new JTextField(15);
        panel.add(txtHanCostePorHora, gbc);


        // Botón Confirmar
        gbc.gridx = 3; gbc.gridy = 3; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnHanConfirmar = new JButton("Confirmar");
        panel.add(btnHanConfirmar, gbc);

        // Add ActionListener to Confirm button (Hangar)
        btnHanConfirmar.addActionListener(e -> {
            String tipoHangar = (String) cmbHanTipoHangar.getSelectedItem();
            String dimensiones = txtHanDimensiones.getText();
            String costePorHora = txtHanCostePorHora.getText();
            String costePorHoraUso = txtHanCostePorHoraUso.getText();
            String capacidadPeligrosas = txtHanCapacidadMercanciasPeligrosas.getText();


            if ("Seleccionar Tipo".equals(tipoHangar)) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona el tipo de hangar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
             // Añadir validación para campos numéricos si es necesario

            String mensaje = "Dar de alta Hangar:\n" +
                             "Tipo: " + tipoHangar + "\n" +
                             "Dimensiones: " + dimensiones + "\n" +
                             "Coste por Hora: " + costePorHora + "\n" +
                             "Coste por Hora Uso: " + costePorHoraUso + "\n" +
                             "Capacidad Mercancías Peligrosas: " + capacidadPeligrosas;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Hangar", JOptionPane.INFORMATION_MESSAGE);

            cmbHanTipoHangar.setSelectedIndex(0);
            txtHanDimensiones.setText("");
            txtHanCostePorHora.setText("");
            txtHanCostePorHoraUso.setText("");
            txtHanCapacidadMercanciasPeligrosas.setText("");
        });

         // Add horizontal glue
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);


        return panel;
    }

    // Sección: Dar de alta a Aerolineas (Imagen 3)
    private JPanel createAltaAerolineaPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a aerolineas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Codigo
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Codigo"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAeCodigo = new JTextField(15);
        panel.add(txtAeCodigo, gbc);

        // Botón Confirmar
        gbc.gridx = 2; gbc.gridy = 0; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnAeConfirmar = new JButton("Confirmar");
        panel.add(btnAeConfirmar, gbc);

        // Add ActionListener to Confirm button (Aerolinea)
        btnAeConfirmar.addActionListener(e -> {
            String codigo = txtAeCodigo.getText();

            if (codigo.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Por favor, ingresa el código de la aerolínea.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                 return;
            }

            String mensaje = "Dar de alta Aerolinea:\n" +
                             "Codigo: " + codigo;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Aerolinea", JOptionPane.INFORMATION_MESSAGE);

            txtAeCodigo.setText("");
        });

         // Add horizontal glue
        gbc.gridx = 3; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);


        return panel;
    }

    // Sección: Dar de alta a Aviones (Imagen 3)
    private JPanel createAltaAvionPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a aviones"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Marca
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Marca"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvMarca = new JTextField(15);
        panel.add(txtAvMarca, gbc);

        // Capacidad máxima de pasajeros
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Capacidad máxima de pasajeros"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvCapacidadPasajeros = new JTextField(15);
        panel.add(txtAvCapacidadPasajeros, gbc);

        // Año de compra (Primer aparición en imagen)
        gbc.gridx = 4; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Año de compra"), gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvAñoCompra1 = new JTextField(15);
        panel.add(txtAvAñoCompra1, gbc);


        // Modelo
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Modelo"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvModelo = new JTextField(15);
        panel.add(txtAvModelo, gbc);

        // Control de temperatura (si lo necesita)
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Control de temperatura (si lo necesita)"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvControlTemperatura = new JTextField(15); // Assuming text input (Yes/No or temperature range)
        panel.add(txtAvControlTemperatura, gbc);

        // Id
        gbc.gridx = 4; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Id"), gbc);
        gbc.gridx = 5; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvId = new JTextField(15);
        panel.add(txtAvId, gbc);


        // Número de Serie
        gbc.gridx = 0; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Número de Serie"), gbc);
        gbc.gridx = 1; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvNumeroSerie = new JTextField(15);
        panel.add(txtAvNumeroSerie, gbc);


        // Permiso de mercancías peligrosas (si lo necesita)
        gbc.gridx = 2; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Permiso de mercancías peligrosas (si lo necesita)"), gbc);
        gbc.gridx = 3; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvPermisoMercanciasPeligrosas = new JTextField(15); // Assuming text input (Yes/No or details)
        panel.add(txtAvPermisoMercanciasPeligrosas, gbc);

        // Año de compra (Segunda aparición en imagen) - Usaré este como el principal
        gbc.gridx = 4; gbc.gridy = 2; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Año de compra"), gbc);
        gbc.gridx = 5; gbc.gridy = 2; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvAñoCompra2 = new JTextField(15);
        panel.add(txtAvAñoCompra2, gbc);


        // Dimensión (ancho x largo x alto)
        gbc.gridx = 0; gbc.gridy = 3; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Dimensión (ancho x largo x alto)"), gbc);
        gbc.gridx = 1; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtAvDimension = new JTextField(15);
        panel.add(txtAvDimension, gbc);


        // Tipo de avión
        gbc.gridx = 3; gbc.gridy = 3; gbc.fill = GridBagConstraints.HORIZONTAL; // Posición ajustada según imagen
        panel.add(new JLabel("Tipo de avión"), gbc); // Etiqueta para el JComboBox
        gbc.gridx = 4; gbc.gridy = 3; gbc.gridwidth = 2; // JComboBox puede ocupar más columnas
        cmbAvTipoAvion = new JComboBox<>(new String[]{"Seleccionar Tipo", "Pasajeros", "Mercancias", "Mixto"}); // Placeholder data
        panel.add(cmbAvTipoAvion, gbc);

        // Botón Confirmar
        gbc.gridx = 5; gbc.gridy = 4; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnAvConfirmar = new JButton("Confirmar");
        panel.add(btnAvConfirmar, gbc);


        // Add ActionListener to Confirm button (Avion)
        btnAvConfirmar.addActionListener(e -> {
            String marca = txtAvMarca.getText();
            String modelo = txtAvModelo.getText();
            String numeroSerie = txtAvNumeroSerie.getText();
            String dimension = txtAvDimension.getText();
            String capacidadPasajeros = txtAvCapacidadPasajeros.getText();
            String controlTemperatura = txtAvControlTemperatura.getText();
            String permisoMercanciasPeligrosas = txtAvPermisoMercanciasPeligrosas.getText();
            String añoCompra = txtAvAñoCompra2.getText(); // Usando el segundo campo de año compra
            String id = txtAvId.getText();
            String tipoAvion = (String) cmbAvTipoAvion.getSelectedItem();

             // Añadir validación

            String mensaje = "Dar de alta Avion:\n" +
                             "Marca: " + marca + "\n" +
                             "Modelo: " + modelo + "\n" +
                             "Número de Serie: " + numeroSerie + "\n" +
                             "Dimensión: " + dimension + "\n" +
                             "Capacidad Pasajeros: " + capacidadPasajeros + "\n" +
                             "Control Temperatura: " + controlTemperatura + "\n" +
                             "Permiso Mercancías Peligrosas: " + permisoMercanciasPeligrosas + "\n" +
                             "Año Compra: " + añoCompra + "\n" +
                             "Id: " + id + "\n" +
                             "Tipo Avión: " + tipoAvion;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Avion", JOptionPane.INFORMATION_MESSAGE);

            // Clear fields (optional)
            txtAvMarca.setText("");
            txtAvModelo.setText("");
            txtAvNumeroSerie.setText("");
            txtAvDimension.setText("");
            txtAvCapacidadPasajeros.setText("");
            txtAvControlTemperatura.setText("");
            txtAvPermisoMercanciasPeligrosas.setText("");
            txtAvAñoCompra1.setText(""); // Limpiar ambos campos por si acaso
            txtAvAñoCompra2.setText("");
            txtAvId.setText("");
            cmbAvTipoAvion.setSelectedIndex(0);
        });


         // Add horizontal glue to push components to the left
        gbc.gridx = 6; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);


        return panel;
    }

     // Sección: Dar de alta a Pistas de aterrizaje (Imagen 4)
    private JPanel createAltaPistaPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta a pistas de aterrizaje"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Tipo de pista
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Tipo de pista"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbPisTipoPista = new JComboBox<>(new String[]{"Seleccionar Tipo", "Asfalto", "Hormigón", "Hierba"}); // Placeholder data
        panel.add(cmbPisTipoPista, gbc);

        // Longitud de pista
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Longitud de pista"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtPisLongitudPista = new JTextField(15);
        panel.add(txtPisLongitudPista, gbc);

        // Botón Confirmar
        gbc.gridx = 3; gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnPisConfirmar = new JButton("Confirmar");
        panel.add(btnPisConfirmar, gbc);

        // Add ActionListener to Confirm button (Pista)
        btnPisConfirmar.addActionListener(e -> {
            String tipoPista = (String) cmbPisTipoPista.getSelectedItem();
            String longitudPista = txtPisLongitudPista.getText();

             if ("Seleccionar Tipo".equals(tipoPista) || longitudPista.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, selecciona el tipo y la longitud de la pista.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            // Añadir validación para longitud (numérico)

            String mensaje = "Dar de alta Pista de aterrizaje:\n" +
                             "Tipo: " + tipoPista + "\n" +
                             "Longitud: " + longitudPista;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Pista", JOptionPane.INFORMATION_MESSAGE);

            cmbPisTipoPista.setSelectedIndex(0);
            txtPisLongitudPista.setText("");
        });

         // Add horizontal glue
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);

        return panel;
    }

     // Sección: Dar de alta Aparcamientos (Imagen 4)
     private JPanel createAltaAparcamientoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta aparcamientos"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Zona de aparcamiento
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Zona de aparcamiento"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        cmbApaZonaAparcamiento = new JComboBox<>(new String[]{"Seleccionar Zona", "Zona A", "Zona B", "Zona C"}); // Placeholder data
        panel.add(cmbApaZonaAparcamiento, gbc);

        // Plazas disponibles en la zona
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Plazas disponibles en la zona"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtApaPlazasDisponibles = new JTextField(15);
        panel.add(txtApaPlazasDisponibles, gbc);

        // Tamaño de cada plaza
        gbc.gridx = 4; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Tamaño de cada plaza"), gbc);
        gbc.gridx = 5; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtApaTamañoPlaza = new JTextField(15);
        panel.add(txtApaTamañoPlaza, gbc);


        // Coste por hora de la zona
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Coste por hora de la zona"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtApaCostePorHoraZona = new JTextField(15);
        panel.add(txtApaCostePorHoraZona, gbc);

        // Coste por hora de cada plaza
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Coste por hora de cada plaza"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtApaCostePorHoraPlaza = new JTextField(15);
        panel.add(txtApaCostePorHoraPlaza, gbc);


        // Botón Confirmar
        gbc.gridx = 5; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnApaConfirmar = new JButton("Confirmar");
        panel.add(btnApaConfirmar, gbc);

        // Add ActionListener to Confirm button (Aparcamiento)
        btnApaConfirmar.addActionListener(e -> {
            String zonaAparcamiento = (String) cmbApaZonaAparcamiento.getSelectedItem();
            String costePorHoraZona = txtApaCostePorHoraZona.getText();
            String plazasDisponibles = txtApaPlazasDisponibles.getText();
            String costePorHoraPlaza = txtApaCostePorHoraPlaza.getText();
            String tamañoPlaza = txtApaTamañoPlaza.getText();

             if ("Seleccionar Zona".equals(zonaAparcamiento) || costePorHoraZona.isEmpty() || plazasDisponibles.isEmpty() || costePorHoraPlaza.isEmpty() || tamañoPlaza.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos para Aparcamiento.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                 return;
            }
             // Añadir validación para campos numéricos

            String mensaje = "Dar de alta Aparcamiento:\n" +
                             "Zona: " + zonaAparcamiento + "\n" +
                             "Coste por Hora Zona: " + costePorHoraZona + "\n" +
                             "Plazas Disponibles: " + plazasDisponibles + "\n" +
                             "Coste por Hora Plaza: " + costePorHoraPlaza + "\n" +
                             "Tamaño Plaza: " + tamañoPlaza;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Aparcamiento", JOptionPane.INFORMATION_MESSAGE);

            cmbApaZonaAparcamiento.setSelectedIndex(0);
            txtApaCostePorHoraZona.setText("");
            txtApaPlazasDisponibles.setText("");
            txtApaCostePorHoraPlaza.setText("");
            txtApaTamañoPlaza.setText("");
        });

         // Add horizontal glue
        gbc.gridx = 6; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);


        return panel;
    }

    // Sección: Dar de alta Fingers (Imagen 4)
     private JPanel createAltaFingerPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Dar de alta fingers"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Id del finger
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(new JLabel("Id del finger"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFinIdFinger = new JTextField(15);
        panel.add(txtFinIdFinger, gbc);

        // Altura máxima
        gbc.gridx = 2; gbc.gridy = 0; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Altura máxima"), gbc);
        gbc.gridx = 3; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFinAlturaMaxima = new JTextField(15);
        panel.add(txtFinAlturaMaxima, gbc);


        // Puertas de embarque asociadas (1 o 2)
        gbc.gridx = 0; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Puertas de embarque asociadas (1 o 2)"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFinPuertasEmbarqueAsociadas = new JTextField(15); // Assuming text input
        panel.add(txtFinPuertasEmbarqueAsociadas, gbc);


        // Coste por hora
        gbc.gridx = 2; gbc.gridy = 1; gbc.fill = GridBagConstraints.NONE;
        panel.add(new JLabel("Coste por hora"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        txtFinCostePorHora = new JTextField(15);
        panel.add(txtFinCostePorHora, gbc);


        // Botón Confirmar
        gbc.gridx = 3; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        btnFinConfirmar = new JButton("Confirmar");
        panel.add(btnFinConfirmar, gbc);

        // Add ActionListener to Confirm button (Finger)
        btnFinConfirmar.addActionListener(e -> {
            String idFinger = txtFinIdFinger.getText();
            String puertasAsociadas = txtFinPuertasEmbarqueAsociadas.getText();
            String alturaMaxima = txtFinAlturaMaxima.getText();
            String costePorHora = txtFinCostePorHora.getText();

             if (idFinger.isEmpty() || puertasAsociadas.isEmpty() || alturaMaxima.isEmpty() || costePorHora.isEmpty()) {
                 JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos para el Finger.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                 return;
            }
            // Añadir validación para campos numéricos o formatos específicos

            String mensaje = "Dar de alta Finger:\n" +
                             "Id: " + idFinger + "\n" +
                             "Puertas Asociadas: " + puertasAsociadas + "\n" +
                             "Altura Máxima: " + alturaMaxima + "\n" +
                             "Coste por Hora: " + costePorHora;
            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Alta Finger", JOptionPane.INFORMATION_MESSAGE);

            txtFinIdFinger.setText("");
            txtFinPuertasEmbarqueAsociadas.setText("");
            txtFinAlturaMaxima.setText("");
            txtFinCostePorHora.setText("");
        });

         // Add horizontal glue
        gbc.gridx = 4; gbc.gridy = 0; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Box.createHorizontalGlue(), gbc);


        return panel;
    }

}