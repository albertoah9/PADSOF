package vista.paneles;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import modelo.GestorAeropuerto; // Asegúrate de que esta importación sea correcta

public class PanelSettings extends JPanel {

    private GestorAeropuerto gestor; // Necesitamos una referencia al gestor

    public PanelSettings(GestorAeropuerto gestor) {
        this.gestor = gestor; // Asignamos la instancia del gestor
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor del panel

        // Crear un JTabbedPane para organizar las secciones
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("SansSerif", Font.BOLD, 14));

        // 1. Gestión de usuarios
        tabbedPane.addTab("Gestión de Usuarios", crearPanelGestionUsuarios());

        // 2. Configuración del aeropuerto
        tabbedPane.addTab("Configuración del Aeropuerto", crearPanelConfiguracionAeropuerto());

        // 3. Gestión de aerolíneas
        tabbedPane.addTab("Gestión de Aerolíneas", crearPanelGestionAerolineas());

        // 4. Notificaciones
        tabbedPane.addTab("Notificaciones", crearPanelNotificaciones());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // --- Métodos para crear cada panel de pestaña ---

    private JPanel crearPanelGestionUsuarios() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane subTabbedPane = new JTabbedPane();
        subTabbedPane.addTab("Operadores", crearSubPanelOperadores());
        subTabbedPane.addTab("Controladores", crearSubPanelControladores());

        panel.add(subTabbedPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearSubPanelOperadores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Operadores"));

        // Alta Operador
        panel.add(new JLabel("Dar de alta Operador:"));
        panel.add(new JTextField("Nombre"));
        panel.add(new JTextField("Apellido"));
        panel.add(new JTextField("DNI"));
        panel.add(new JComboBox<>(new String[]{"Seleccionar Aerolínea", "Aerolinea A", "Aerolinea B"}));
        panel.add(new JButton("Dar de Alta Operador"));
        panel.add(Box.createVerticalStrut(15));

        // Editar Operador
        panel.add(new JLabel("Editar Operador:"));
        panel.add(new JTextField("ID/DNI Operador a Editar"));
        panel.add(new JTextField("Nuevo Nombre (opcional)"));
        panel.add(new JTextField("Nuevo Apellido (opcional)"));
        panel.add(new JComboBox<>(new String[]{"Nueva Aerolínea (opcional)", "Aerolinea X", "Aerolinea Y"}));
        panel.add(new JButton("Guardar Cambios Operador"));
        panel.add(Box.createVerticalStrut(15));

        // Eliminar Operador
        panel.add(new JLabel("Eliminar Operador:"));
        panel.add(new JTextField("ID/DNI Operador a Eliminar"));
        panel.add(new JButton("Eliminar Operador"));
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel crearSubPanelControladores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Controladores"));

        // Alta Controlador
        panel.add(new JLabel("Dar de alta Controlador:"));
        panel.add(new JTextField("Nombre"));
        panel.add(new JTextField("Apellido"));
        panel.add(new JTextField("DNI"));
        panel.add(new JPasswordField("Contraseña")); // Usar JPasswordField
        panel.add(new JButton("Dar de Alta Controlador"));
        panel.add(Box.createVerticalStrut(15));

        // Editar Controlador
        panel.add(new JLabel("Editar Controlador:"));
        panel.add(new JTextField("ID/DNI Controlador a Editar"));
        panel.add(new JTextField("Nuevo Nombre (opcional)"));
        panel.add(new JTextField("Nuevo Apellido (opcional)"));
        panel.add(new JPasswordField("Nueva Contraseña (opcional)"));
        panel.add(new JButton("Guardar Cambios Controlador"));
        panel.add(Box.createVerticalStrut(15));

        // Eliminar Controlador
        panel.add(new JLabel("Eliminar Controlador:"));
        panel.add(new JTextField("ID/DNI Controlador a Eliminar"));
        panel.add(new JButton("Eliminar Controlador"));
        panel.add(Box.createVerticalStrut(15));

        // Asignar Terminal
        panel.add(new JLabel("Asignar Terminal a Controlador:"));
        panel.add(new JTextField("ID/DNI Controlador"));
        panel.add(new JComboBox<>(new String[]{"Seleccionar Terminal", "Terminal 1", "Terminal 2"}));
        panel.add(new JButton("Asignar Terminal"));
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    // 2.1.1.2. Configuración del aeropuerto
    private JPanel crearPanelConfiguracionAeropuerto() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTabbedPane subTabbedPane = new JTabbedPane();

        // Pestaña para Terminales (gestionar, definir tipo, aforo)
        JPanel panelTerminales = new JPanel();
        panelTerminales.setLayout(new BoxLayout(panelTerminales, BoxLayout.Y_AXIS));
        panelTerminales.setBorder(BorderFactory.createTitledBorder("Gestión de Terminales"));

        // Alta/Edición Terminal
        panelTerminales.add(new JLabel("Gestionar Terminales:"));
        panelTerminales.add(new JTextField("ID Terminal"));
        panelTerminales.add(new JComboBox<>(new String[]{"Tipo de Terminal", "Pasajeros", "Mercancías"}));
        panelTerminales.add(new JTextField("Aforo Máximo (solo pasajeros)"));
        panelTerminales.add(new JButton("Guardar/Actualizar Terminal"));
        panelTerminales.add(Box.createVerticalStrut(15));

        // Eliminar Terminal
        panelTerminales.add(new JLabel("Eliminar Terminal:"));
        panelTerminales.add(new JTextField("ID Terminal a Eliminar"));
        panelTerminales.add(new JButton("Eliminar Terminal"));
        panelTerminales.add(Box.createVerticalGlue());
        subTabbedPane.addTab("Terminales", panelTerminales);

        // Pestaña para Puertas de embarque y Fingers
        JPanel panelPuertasFingers = new JPanel();
        panelPuertasFingers.setLayout(new BoxLayout(panelPuertasFingers, BoxLayout.Y_AXIS));
        panelPuertasFingers.setBorder(BorderFactory.createTitledBorder("Gestión de Puertas y Fingers"));

        // Alta/Edición Puerta de Embarque
        panelPuertasFingers.add(new JLabel("Gestionar Puertas de Embarque:"));
        panelPuertasFingers.add(new JTextField("Código de Puerta"));
        panelPuertasFingers.add(new JTextField("ID Terminal Asociada"));
        panelPuertasFingers.add(new JButton("Guardar/Actualizar Puerta"));
        panelPuertasFingers.add(Box.createVerticalStrut(15));

        // Alta/Edición Finger
        panelPuertasFingers.add(new JLabel("Gestionar Fingers:"));
        panelPuertasFingers.add(new JTextField("ID Finger"));
        panelPuertasFingers.add(new JTextField("Puertas Asociadas (ej. P1,P2)"));
        panelPuertasFingers.add(new JTextField("Altura Máxima"));
        panelPuertasFingers.add(new JTextField("Coste por Hora"));
        panelPuertasFingers.add(new JButton("Guardar/Actualizar Finger"));
        panelPuertasFingers.add(Box.createVerticalGlue());
        subTabbedPane.addTab("Puertas y Fingers", panelPuertasFingers);

        // Pestaña para Zonas de aparcamiento y Hangares
        JPanel panelAparcamientosHangares = new JPanel();
        panelAparcamientosHangares.setLayout(new BoxLayout(panelAparcamientosHangares, BoxLayout.Y_AXIS));
        panelAparcamientosHangares.setBorder(BorderFactory.createTitledBorder("Gestión de Aparcamientos y Hangares"));

        // Alta/Edición Zona de Aparcamiento
        panelAparcamientosHangares.add(new JLabel("Gestionar Zonas de Aparcamiento:"));
        panelAparcamientosHangares.add(new JTextField("ID Zona"));
        panelAparcamientosHangares.add(new JTextField("Coste por Hora de Zona"));
        panelAparcamientosHangares.add(new JTextField("Capacidad Total Plazas"));
        panelAparcamientosHangares.add(new JButton("Guardar/Actualizar Zona"));
        panelAparcamientosHangares.add(Box.createVerticalStrut(15));

        // Alta/Edición Hangar
        panelAparcamientosHangares.add(new JLabel("Gestionar Hangares:"));
        panelAparcamientosHangares.add(new JTextField("ID Hangar"));
        panelAparcamientosHangares.add(new JComboBox<>(new String[]{"Tipo de Hangar", "Reparación", "Mantenimiento", "Almacenamiento"}));
        panelAparcamientosHangares.add(new JTextField("Dimensiones (alto x ancho x largo)"));
        panelAparcamientosHangares.add(new JTextField("Coste por Hora"));
        panelAparcamientosHangares.add(new JTextField("Capacidad Mercancías Peligrosas"));
        panelAparcamientosHangares.add(new JButton("Guardar/Actualizar Hangar"));
        panelAparcamientosHangares.add(Box.createVerticalGlue());
        subTabbedPane.addTab("Aparcamientos y Hangares", panelAparcamientosHangares);

        panel.add(subTabbedPane, BorderLayout.CENTER);
        return panel;
    }

    // 2.1.1.3. Gestión de aerolíneas
    private JPanel crearPanelGestionAerolineas() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Aerolíneas"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Dar de alta aerolíneas
        panel.add(new JLabel("Dar de alta Aerolínea:"));
        panel.add(new JTextField("Código de Aerolínea"));
        panel.add(new JTextField("Nombre de la Aerolínea"));
        panel.add(new JButton("Dar de Alta Aerolínea"));
        panel.add(Box.createVerticalStrut(15));

        // Editar Aerolínea
        panel.add(new JLabel("Editar Aerolínea:"));
        panel.add(new JTextField("Código de Aerolínea a Editar"));
        panel.add(new JTextField("Nuevo Nombre (opcional)"));
        panel.add(new JButton("Guardar Cambios Aerolínea"));
        panel.add(Box.createVerticalStrut(15));

        // Eliminar Aerolínea
        panel.add(new JLabel("Eliminar Aerolínea:"));
        panel.add(new JTextField("Código de Aerolínea a Eliminar"));
        panel.add(new JButton("Eliminar Aerolínea"));
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    // 2.1.1.4. Notificaciones
    private JPanel crearPanelNotificaciones() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Configuración de Notificaciones"));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Recibir notificaciones de todos los vuelos (Checkbox)
        panel.add(new JLabel("Recibir Notificaciones:"));
        panel.add(new JCheckBox("Activar Notificaciones de Todos los Vuelos"));
        panel.add(Box.createVerticalStrut(10));

        // Configurar tipo de notificaciones
        panel.add(new JLabel("Tipos de Notificación a Recibir:"));
        panel.add(new JCheckBox("Vuelos con Retraso"));
        panel.add(new JCheckBox("Vuelos Cancelados"));
        panel.add(new JCheckBox("Nuevos Vuelos Programados"));
        panel.add(new JCheckBox("Cambios de Puerta/Pista"));
        panel.add(Box.createVerticalStrut(10));

        // Configurar de qué vuelos
        panel.add(new JLabel("Filtrar Notificaciones por Vuelo:"));
        panel.add(new JTextField("ID de Vuelo Específico (opcional)"));
        panel.add(new JComboBox<>(new String[]{"Todos los vuelos", "Mis vuelos", "Vuelos de Aerolínea X"})); // Placeholder
        panel.add(Box.createVerticalStrut(15));

        panel.add(new JButton("Guardar Configuración de Notificaciones"));
        panel.add(Box.createVerticalGlue());

        return panel;
    }
}