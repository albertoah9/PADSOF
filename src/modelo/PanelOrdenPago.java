package modelo;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanelOrdenPago extends JPanel {

    private GestorAeropuerto gestor; // Mantenemos la referencia

    // Componentes del formulario
    private JTextField txtNumeroVuelo;
    private JComboBox<String> cmbTipoAvion;
    private JComboBox<String> cmbTipoVuelo;
    private JCheckBox chkFingers;
    private JCheckBox chkAparcamiento;
    private JTextField txtAerolinea;
    private JTextField txtFecha; // Placeholder para la fecha
    private JTextArea txtNotas;
    private JTextField txtPrecioTotal;
    private JButton btnConfirmar;

    public PanelOrdenPago(GestorAeropuerto gestor) {
        this.gestor = gestor;

        setLayout(new BorderLayout()); // Layout principal del panel
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor

        // --- Título del Panel ---
        JLabel lblTitulo = new JLabel("Enviar orden de pago al sistema");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER); // Centrar título
        add(lblTitulo, BorderLayout.NORTH);

        // --- Panel Central del Formulario ---
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.X_AXIS)); // Para poner las dos columnas lado a lado
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0)); // Espacio vertical entre título y botón

        // --- Panel Izquierdo del Formulario (usando GridBagLayout para alineación) ---
        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("")); // Borde sin título visualmente, para estructura
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.insets = new Insets(5, 5, 5, 5); // Margen entre componentes
        gbcLeft.anchor = GridBagConstraints.WEST; // Alinear a la izquierda

        // Número de vuelo
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 0;
        leftPanel.add(new JLabel("Número de vuelo:"), gbcLeft);
        gbcLeft.gridx = 1;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL; // Permitir que el campo se expanda
        txtNumeroVuelo = new JTextField(15); // Tamaño inicial
        leftPanel.add(txtNumeroVuelo, gbcLeft);

        // Tipo de avion
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 1;
        gbcLeft.fill = GridBagConstraints.NONE;
        leftPanel.add(new JLabel("Tipo de avion (pasajeros/mercancias):"), gbcLeft);
        gbcLeft.gridx = 1;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        cmbTipoAvion = new JComboBox<>(new String[]{"Seleccionar", "Pasajeros", "Mercancias"});
        leftPanel.add(cmbTipoAvion, gbcLeft);

        // Tipo de vuelo
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 2;
        gbcLeft.fill = GridBagConstraints.NONE;
        leftPanel.add(new JLabel("Tipo de vuelo (llegada / salida):"), gbcLeft);
        gbcLeft.gridx = 1;
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        cmbTipoVuelo = new JComboBox<>(new String[]{"Seleccionar", "Llegada", "Salida"});
        leftPanel.add(cmbTipoVuelo, gbcLeft);

        // Uso de servicio adicional (Label arriba, checkboxes abajo)
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 3;
        gbcLeft.gridwidth = 2; // Ocupa dos columnas
        gbcLeft.anchor = GridBagConstraints.WEST;
        gbcLeft.fill = GridBagConstraints.NONE;
        leftPanel.add(new JLabel("Uso de servicio adicional:"), gbcLeft);

        gbcLeft.gridy = 4;
        chkFingers = new JCheckBox("Fingers");
        leftPanel.add(chkFingers, gbcLeft);

        gbcLeft.gridy = 5;
        chkAparcamiento = new JCheckBox("Aparcamiento del hangar");
        leftPanel.add(chkAparcamiento, gbcLeft);

        // Añadir un "pegamento" vertical para empujar los componentes hacia arriba
        gbcLeft.gridx = 0;
        gbcLeft.gridy = 6;
        gbcLeft.weighty = 1.0; // Este componente absorberá el espacio vertical extra
        gbcLeft.fill = GridBagConstraints.VERTICAL;
        leftPanel.add(Box.createGlue(), gbcLeft);


        // --- Panel Derecho del Formulario (usando GridBagLayout para alineación) ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("")); // Borde sin título
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.insets = new Insets(5, 5, 5, 5); // Margen entre componentes
        gbcRight.anchor = GridBagConstraints.WEST; // Alinear a la izquierda

        // Aerolinea
        gbcRight.gridx = 0;
        gbcRight.gridy = 0;
        rightPanel.add(new JLabel("Aerolinea:"), gbcRight);
        gbcRight.gridx = 1;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        txtAerolinea = new JTextField(15); // Tamaño inicial
        rightPanel.add(txtAerolinea, gbcRight);

        // Fecha (con placeholder de icono de calendario)
        gbcRight.gridx = 0;
        gbcRight.gridy = 1;
        gbcRight.fill = GridBagConstraints.NONE;
        rightPanel.add(new JLabel("Fecha:"), gbcRight);

        gbcRight.gridx = 1;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // Para poner campo y icono juntos
        txtFecha = new JTextField("DD/MM/YYYY", 10); // Placeholder
        JLabel iconoCalendario = new JLabel(" \uD83D\uDCC5"); // Icono de calendario unicode
        datePanel.add(txtFecha);
        datePanel.add(iconoCalendario); // Puedes añadir un botón real con icono si usas bibliotecas de fecha más avanzadas
        rightPanel.add(datePanel, gbcRight);

        // Notas (TextArea)
        gbcRight.gridx = 0;
        gbcRight.gridy = 2;
        gbcRight.fill = GridBagConstraints.NONE;
        rightPanel.add(new JLabel("Notas (opcional):"), gbcRight);

        gbcRight.gridx = 1;
        gbcRight.fill = GridBagConstraints.BOTH; // Permitir que el área de texto se expanda
        gbcRight.weighty = 0.5; // Darle peso para que ocupe espacio vertical
        txtNotas = new JTextArea(5, 15); // Filas, Columnas (tamaño preferido)
        txtNotas.setLineWrap(true); // Ajuste de línea
        txtNotas.setWrapStyleWord(true); // Ajuste de línea por palabra
        JScrollPane scrollNotas = new JScrollPane(txtNotas); // Añadir scroll si el texto es largo
        rightPanel.add(scrollNotas, gbcRight);

        // Precio total
        gbcRight.gridx = 0;
        gbcRight.gridy = 3;
        gbcRight.fill = GridBagConstraints.NONE;
        gbcRight.weighty = 0; // Resetear peso
        rightPanel.add(new JLabel("Precio total:"), gbcRight);

        gbcRight.gridx = 1;
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        txtPrecioTotal = new JTextField(15);
        txtPrecioTotal.setEditable(false); // Normalmente el precio total es calculado
        rightPanel.add(txtPrecioTotal, gbcRight);
        
        // Añadir un "pegamento" vertical para empujar los componentes hacia arriba
        gbcRight.gridx = 0;
        gbcRight.gridy = 4;
        gbcRight.weighty = 1.0; // Este componente absorberá el espacio vertical extra
        gbcRight.fill = GridBagConstraints.VERTICAL;
        rightPanel.add(Box.createGlue(), gbcRight);


        // Añadir paneles izquierdo y derecho al panel central del formulario
        formPanel.add(Box.createHorizontalGlue()); // Espacio flexible a la izquierda
        formPanel.add(leftPanel);
        formPanel.add(Box.createRigidArea(new Dimension(30, 0))); // Espacio fijo entre columnas
        formPanel.add(rightPanel);
        formPanel.add(Box.createHorizontalGlue()); // Espacio flexible a la derecha

        // --- Botón Confirmar ---
        btnConfirmar = new JButton("Confirmar");
        JPanel panelBotonConfirmar = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Para centrar el botón
        panelBotonConfirmar.add(btnConfirmar);
        // Añadir ActionListener al botón Confirmar (lógica de envío de orden)
        btnConfirmar.addActionListener(e -> {
            // Aquí iría la lógica para recopilar datos del formulario
            // y procesar la orden de pago
            String numeroVuelo = txtNumeroVuelo.getText();
            String tipoAvion = (String) cmbTipoAvion.getSelectedItem();
            String tipoVuelo = (String) cmbTipoVuelo.getSelectedItem();
            boolean usoFingers = chkFingers.isSelected();
            boolean usoAparcamiento = chkAparcamiento.isSelected();
            String aerolinea = txtAerolinea.getText();
            String fecha = txtFecha.getText(); // Deberías validar/parsear esto
            String notas = txtNotas.getText();
            String precioTotal = txtPrecioTotal.getText(); // Este debería ser calculado

            // Ejemplo simple: mostrar los datos recopilados
            String mensaje = "Datos de la Orden de Pago:\n" +
                             "Número de vuelo: " + numeroVuelo + "\n" +
                             "Tipo de avión: " + tipoAvion + "\n" +
                             "Tipo de vuelo: " + tipoVuelo + "\n" +
                             "Servicios adicionales: Fingers=" + usoFingers + ", Aparcamiento=" + usoAparcamiento + "\n" +
                             "Aerolínea: " + aerolinea + "\n" +
                             "Fecha: " + fecha + "\n" +
                             "Notas: " + notas + "\n" +
                             "Precio Total (mostrado): " + precioTotal;

            JOptionPane.showMessageDialog(this, mensaje, "Confirmar Orden de Pago", JOptionPane.INFORMATION_MESSAGE);

            // Luego, aquí llamarías a la lógica de tu GestorAeropuerto o sistema de pago
            // gestor.enviarOrdenPago(...);
        });


        // --- Añadir paneles al panel principal (PanelOrdenPago) ---
        add(formPanel, BorderLayout.CENTER); // El formulario en el centro
        add(panelBotonConfirmar, BorderLayout.SOUTH); // El botón confirmar abajo

        // Opcional: Método main para probar solo este panel
        // Asegúrate de que la clase GestorAeropuerto sea accesible desde aquí
    }
}