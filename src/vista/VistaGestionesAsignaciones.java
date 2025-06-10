package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import modelo.GestorAeropuerto; // Necesario para la referencia al modelo
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaGestionesAsignaciones extends JPanel {

    private GestorAeropuerto gestor; // Referencia al modelo

    // Componentes para cada sección de formulario (hacer públicos o con getters para el controlador)

    // Alta Operador (Sección de gestión de usuarios)
    public JTextField txtOpNombre;
    public JTextField txtOpApellido;
    public JTextField txtOpDni;
    public JComboBox<String> cmbOpAerolinea;
    public JButton btnOpConfirmar;

    // Alta Controlador (Sección de gestión de usuarios)
    public JTextField txtCoNombre;
    public JTextField txtCoApellido;
    public JTextField txtCoDni;
    public JPasswordField txtCoContrasena;
    public JComboBox<String> cmbCoTerminal; // Este CMB se usaría para asignar terminal
    public JButton btnCoConfirmar;

    // Alta Terminal (Sección de gestión de aeropuerto)
    public JTextField txtTerIdTerminal; // Añadido para el ID de la terminal
    public JComboBox<String> cmbTerTipoTerminal;
    public JTextField txtTerAforoMaximo;
    public JTextField txtTerAparcamientoMercancias; // Podría ser un checkbox o un campo para capacidad específica
    public JButton btnTerConfirmar;

    // Alta Puerta de Embarque (Sección de gestión de aeropuerto)
    public JTextField txtPueCodigoPuerta;
    public JComboBox<String> cmbPueTipoVuelo; // Si la puerta es para Llegada/Salida
    public JTextField txtPueIdTerminalAsociada; // Nuevo campo para asociar a una terminal
    public JButton btnPueConfirmar;

    // Alta Pista (Sección de gestión de aeropuerto)
    public JTextField txtPisIdPista;
    public JComboBox<String> cmbPisTipoPista; // Tipo de pista (aterrizaje, despegue)
    public JTextField txtPisDimensiones; // Por ejemplo "Largo x Ancho"
    public JButton btnPisConfirmar;

    // Alta Hangar (Sección de gestión de aeropuerto)
    public JTextField txtHanIdHangar;
    public JComboBox<String> cmbHanTipoHangar; // Mantenimiento, Reparación, Almacenamiento
    public JTextField txtHanCapacidad; // Capacidad de aviones o carga
    public JTextField txtHanCostePorDia;
    public JTextField txtHanCapacidadMercanciasPeligrosas; // ¿Booleano o cantidad?
    public JButton btnHanConfirmar;

    // Alta Finger (Sección de gestión de aeropuerto)
    public JTextField txtFinIdFinger;
    public JTextField txtFinPuertasEmbarqueAsociadas; // Lista de puertas separadas por coma
    public JTextField txtFinAlturaMaxima;
    public JTextField txtFinCostePorHora;
    public JButton btnFinConfirmar;

    // Alta Zona de Aparcamiento (Sección de gestión de aeropuerto)
    public JTextField txtZapIdZona;
    public JComboBox<String> cmbZapTipoZona; // Pasajeros, Mercancías, Aviones
    public JTextField txtZapCapacidad; // Capacidad de vehículos/aviones
    public JTextField txtZapCostePorHora;
    public JButton btnZapConfirmar;
    
    // Asignación de Vuelo a Pista/Puerta
    public JComboBox<String> cmbAsigVueloId;
    public JComboBox<String> cmbAsigPistaId;
    public JComboBox<String> cmbAsigPuertaId;
    public JButton btnAsigAsignar;


    public VistaGestionesAsignaciones(GestorAeropuerto gestor) {
        this.gestor = gestor;
        setLayout(new GridBagLayout()); // Usar GridBagLayout para un control más preciso
        setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // --- Título del Panel Principal ---
        JLabel lblTitulo = new JLabel("Gestiones y Asignaciones", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4; // Ocupa 4 columnas
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1; // Reset gridwidth

        // --- Pestañas para organizar las gestiones ---
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14));

        // Pestaña 1: Gestión de Personal (Operadores y Controladores)
        JTabbedPane personalTabbedPane = new JTabbedPane();
        personalTabbedPane.addTab("Operadores", crearPanelAltaOperador()); // Reutilizar el método de PanelSettings
        personalTabbedPane.addTab("Controladores", crearPanelAltaControlador()); // Reutilizar el método de PanelSettings
        tabbedPane.addTab("Personal", personalTabbedPane);


        // Pestaña 2: Gestión de Infraestructura (Terminales, Puertas, Pistas, Hangares, Fingers, Aparcamientos)
        JTabbedPane infraestructuraTabbedPane = new JTabbedPane();
        infraestructuraTabbedPane.addTab("Terminales", crearPanelAltaTerminal());
        infraestructuraTabbedPane.addTab("Puertas Embarque", crearPanelAltaPuertaEmbarque());
        infraestructuraTabbedPane.addTab("Pistas", crearPanelAltaPista());
        infraestructuraTabbedPane.addTab("Hangares", crearPanelAltaHangar());
        infraestructuraTabbedPane.addTab("Fingers", crearPanelAltaFinger());
        infraestructuraTabbedPane.addTab("Aparcamientos", crearPanelAltaZonaAparcamiento());
        tabbedPane.addTab("Infraestructura", infraestructuraTabbedPane);

        // Pestaña 3: Asignaciones (Vuelo a Pista/Puerta)
        tabbedPane.addTab("Asignaciones de Vuelo", crearPanelAsignacionesVuelo());


        gbc.gridx = 0;
        gbc.gridy = 1; // Debajo del título
        gbc.gridwidth = 4; // Ocupa todo el ancho disponible
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Para que el tabbed pane ocupe el espacio restante
        gbc.fill = GridBagConstraints.BOTH;
        add(tabbedPane, gbc);
    }

    // --- Métodos para crear cada sub-panel del TabbedPane ---

    private JPanel crearPanelAltaOperador() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Alta de Operador"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Nombre
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtOpNombre = new JTextField(20); panel.add(txtOpNombre, gbc);

        // Fila 2: Apellido
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; txtOpApellido = new JTextField(20); panel.add(txtOpApellido, gbc);

        // Fila 3: DNI
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1; txtOpDni = new JTextField(20); panel.add(txtOpDni, gbc);

        // Fila 4: Aerolínea
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Aerolínea:"), gbc);
        gbc.gridx = 1; cmbOpAerolinea = new JComboBox<>(new String[]{"Aerolinea A", "Aerolinea B", "Aerolinea C"}); // Placeholder
        panel.add(cmbOpAerolinea, gbc);

        // Fila 5: Botón
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnOpConfirmar = new JButton("Dar de Alta Operador");
        panel.add(btnOpConfirmar, gbc);

        // Espacio para empujar componentes hacia arriba
        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel crearPanelAltaControlador() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Alta de Controlador"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Nombre
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtCoNombre = new JTextField(20); panel.add(txtCoNombre, gbc);

        // Fila 2: Apellido
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Apellido:"), gbc);
        gbc.gridx = 1; txtCoApellido = new JTextField(20); panel.add(txtCoApellido, gbc);

        // Fila 3: DNI
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("DNI:"), gbc);
        gbc.gridx = 1; txtCoDni = new JTextField(20); panel.add(txtCoDni, gbc);

        // Fila 4: Contraseña
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1; txtCoContrasena = new JPasswordField(20); panel.add(txtCoContrasena, gbc);

        // Fila 5: Botón
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnCoConfirmar = new JButton("Dar de Alta Controlador");
        panel.add(btnCoConfirmar, gbc);

        // Espacio para empujar componentes hacia arriba
        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel crearPanelAltaTerminal() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Terminales"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID Terminal
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID Terminal:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtTerIdTerminal = new JTextField(20); panel.add(txtTerIdTerminal, gbc);

        // Fila 2: Tipo de Terminal
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1; cmbTerTipoTerminal = new JComboBox<>(new String[]{"Pasajeros", "Mercancías"});
        panel.add(cmbTerTipoTerminal, gbc);

        // Fila 3: Aforo Máximo (Solo para pasajeros)
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Aforo Máximo (Pasajeros):"), gbc);
        gbc.gridx = 1; txtTerAforoMaximo = new JTextField(20); panel.add(txtTerAforoMaximo, gbc);

        // Fila 4: Capacidad Aparcamiento Mercancías (Solo para mercancías)
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Capacidad Aparc. Mercancías:"), gbc);
        gbc.gridx = 1; txtTerAparcamientoMercancias = new JTextField(20); panel.add(txtTerAparcamientoMercancias, gbc);
        
        // Listener para cambiar visibilidad de campos según tipo de terminal
        cmbTerTipoTerminal.addActionListener(e -> {
            String tipo = (String) cmbTerTipoTerminal.getSelectedItem();
            txtTerAforoMaximo.setEnabled("Pasajeros".equals(tipo));
            txtTerAparcamientoMercancias.setEnabled("Mercancías".equals(tipo));
        });
        // Inicializar estado
        cmbTerTipoTerminal.setSelectedIndex(0); // Esto disparará el listener y ajustará los campos

        // Fila 5: Botón
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnTerConfirmar = new JButton("Guardar/Actualizar Terminal");
        panel.add(btnTerConfirmar, gbc);

        // Espacio flexible
        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);

        return panel;
    }

    private JPanel crearPanelAltaPuertaEmbarque() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Puertas de Embarque"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Código Puerta
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Código Puerta:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtPueCodigoPuerta = new JTextField(20); panel.add(txtPueCodigoPuerta, gbc);

        // Fila 2: Tipo de Vuelo
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Tipo Vuelo (Llegada/Salida):"), gbc);
        gbc.gridx = 1; cmbPueTipoVuelo = new JComboBox<>(new String[]{"LLEGADA", "SALIDA", "AMBAS"});
        panel.add(cmbPueTipoVuelo, gbc);
        
        // Fila 3: ID Terminal Asociada
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("ID Terminal Asociada:"), gbc);
        gbc.gridx = 1; txtPueIdTerminalAsociada = new JTextField(20); panel.add(txtPueIdTerminalAsociada, gbc);

        // Fila 4: Botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnPueConfirmar = new JButton("Guardar/Actualizar Puerta");
        panel.add(btnPueConfirmar, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);
        return panel;
    }
    
    private JPanel crearPanelAltaPista() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Pistas"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID Pista
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID Pista:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtPisIdPista = new JTextField(20); panel.add(txtPisIdPista, gbc);

        // Fila 2: Tipo Pista
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Tipo Pista:"), gbc);
        gbc.gridx = 1; cmbPisTipoPista = new JComboBox<>(new String[]{"Aterrizaje", "Despegue", "Ambos"});
        panel.add(cmbPisTipoPista, gbc);

        // Fila 3: Dimensiones
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Dimensiones (ej. 3000x60):"), gbc);
        gbc.gridx = 1; txtPisDimensiones = new JTextField(20); panel.add(txtPisDimensiones, gbc);

        // Fila 4: Botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnPisConfirmar = new JButton("Guardar/Actualizar Pista");
        panel.add(btnPisConfirmar, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);
        return panel;
    }

    private JPanel crearPanelAltaHangar() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Hangares"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID Hangar
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID Hangar:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtHanIdHangar = new JTextField(20); panel.add(txtHanIdHangar, gbc);

        // Fila 2: Tipo Hangar
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Tipo Hangar:"), gbc);
        gbc.gridx = 1; cmbHanTipoHangar = new JComboBox<>(new String[]{"Mantenimiento", "Reparación", "Almacenamiento"});
        panel.add(cmbHanTipoHangar, gbc);

        // Fila 3: Capacidad
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Capacidad (Aviones/Carga):"), gbc);
        gbc.gridx = 1; txtHanCapacidad = new JTextField(20); panel.add(txtHanCapacidad, gbc);
        
        // Fila 4: Coste por Día
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Coste por Día:"), gbc);
        gbc.gridx = 1; txtHanCostePorDia = new JTextField(20); panel.add(txtHanCostePorDia, gbc);

        // Fila 5: Capacidad Mercancías Peligrosas
        gbc.gridx = 0; gbc.gridy = 4; panel.add(new JLabel("Cap. Mercancías Peligrosas (KG):"), gbc);
        gbc.gridx = 1; txtHanCapacidadMercanciasPeligrosas = new JTextField(20); panel.add(txtHanCapacidadMercanciasPeligrosas, gbc);


        // Fila 6: Botón
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnHanConfirmar = new JButton("Guardar/Actualizar Hangar");
        panel.add(btnHanConfirmar, gbc);

        gbc.gridx = 0; gbc.gridy = 6; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);
        return panel;
    }

    private JPanel crearPanelAltaFinger() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Fingers"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID Finger
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID Finger:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtFinIdFinger = new JTextField(20); panel.add(txtFinIdFinger, gbc);

        // Fila 2: Puertas de Embarque Asociadas
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Puertas Asociadas (ej. P1,P2):"), gbc);
        gbc.gridx = 1; txtFinPuertasEmbarqueAsociadas = new JTextField(20); panel.add(txtFinPuertasEmbarqueAsociadas, gbc);

        // Fila 3: Altura Máxima
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Altura Máxima (metros):"), gbc);
        gbc.gridx = 1; txtFinAlturaMaxima = new JTextField(20); panel.add(txtFinAlturaMaxima, gbc);
        
        // Fila 4: Coste por Hora
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Coste por Hora (€):"), gbc);
        gbc.gridx = 1; txtFinCostePorHora = new JTextField(20); panel.add(txtFinCostePorHora, gbc);

        // Fila 5: Botón
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnFinConfirmar = new JButton("Guardar/Actualizar Finger");
        panel.add(btnFinConfirmar, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);
        return panel;
    }

    private JPanel crearPanelAltaZonaAparcamiento() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Zonas de Aparcamiento"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: ID Zona
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("ID Zona:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; txtZapIdZona = new JTextField(20); panel.add(txtZapIdZona, gbc);

        // Fila 2: Tipo de Zona
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Tipo de Zona:"), gbc);
        gbc.gridx = 1; cmbZapTipoZona = new JComboBox<>(new String[]{"Pasajeros", "Mercancías", "Aviones"});
        panel.add(cmbZapTipoZona, gbc);

        // Fila 3: Capacidad
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Capacidad (vehículos/aviones):"), gbc);
        gbc.gridx = 1; txtZapCapacidad = new JTextField(20); panel.add(txtZapCapacidad, gbc);
        
        // Fila 4: Coste por Hora
        gbc.gridx = 0; gbc.gridy = 3; panel.add(new JLabel("Coste por Hora (€):"), gbc);
        gbc.gridx = 1; txtZapCostePorHora = new JTextField(20); panel.add(txtZapCostePorHora, gbc);

        // Fila 5: Botón
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnZapConfirmar = new JButton("Guardar/Actualizar Zona");
        panel.add(btnZapConfirmar, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);
        return panel;
    }

    private JPanel crearPanelAsignacionesVuelo() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Asignaciones de Vuelo a Infraestructura"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Seleccionar Vuelo
        gbc.gridx = 0; gbc.gridy = 0; panel.add(new JLabel("Seleccionar Vuelo:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; cmbAsigVueloId = new JComboBox<>(new String[]{"Vuelo ID1", "Vuelo ID2"}); // Placeholder
        panel.add(cmbAsigVueloId, gbc);

        // Fila 2: Asignar Pista
        gbc.gridx = 0; gbc.gridy = 1; panel.add(new JLabel("Asignar Pista:"), gbc);
        gbc.gridx = 1; cmbAsigPistaId = new JComboBox<>(new String[]{"Pista 1", "Pista 2"}); // Placeholder
        panel.add(cmbAsigPistaId, gbc);

        // Fila 3: Asignar Puerta de Embarque
        gbc.gridx = 0; gbc.gridy = 2; panel.add(new JLabel("Asignar Puerta de Embarque:"), gbc);
        gbc.gridx = 1; cmbAsigPuertaId = new JComboBox<>(new String[]{"Puerta A1", "Puerta B2"}); // Placeholder
        panel.add(cmbAsigPuertaId, gbc);

        // Fila 4: Botón
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        btnAsigAsignar = new JButton("Realizar Asignación");
        panel.add(btnAsigAsignar, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.weighty = 1.0; panel.add(Box.createVerticalGlue(), gbc);
        return panel;
    }

}