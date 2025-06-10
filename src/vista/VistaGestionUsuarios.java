package vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import modelo.GestorAeropuerto;
import modelo.Aerolinea;
import modelo.Terminal;

public class VistaGestionUsuarios extends JPanel {

    private GestorAeropuerto gestor;

    private JTabbedPane subTabbedPane;

    private JPanel panelOperadores;
    private JPanel panelControladores;

    public JTextField txtOpNombre;
    public JTextField txtOpApellido;
    public JPasswordField txtOpContrasena;
    public JComboBox<String> cmbOpAerolinea;
    public JButton btnOpAlta;
    public JPasswordField txtOpEditarContrasenaActual;
    public JTextField txtOpEditarNombre;
    public JTextField txtOpEditarApellido;
    public JPasswordField txtOpEditarNuevaContrasena;
    public JComboBox<String> cmbOpEditarAerolinea;
    public JButton btnOpEditar;
    public JPasswordField txtOpEliminarContrasena;
    public JButton btnOpEliminar;

    public JTextField txtCoNombre;
    public JTextField txtCoApellido;
    public JPasswordField txtCoContrasena;
    public JButton btnCoAlta;
    public JPasswordField txtCoEditarContrasenaActual;
    public JTextField txtCoEditarNombre;
    public JTextField txtCoEditarApellido;
    public JPasswordField txtCoEditarNuevaContrasena;
    public JButton btnCoEditar;
    public JPasswordField txtCoEliminarContrasena;
    public JButton btnCoEliminar;
    public JPasswordField txtCoAsignarContrasena;
    public JComboBox<String> cmbCoAsignarTerminal;
    public JButton btnCoAsignarTerminal;


    public VistaGestionUsuarios(GestorAeropuerto gestor) {
        this.gestor = gestor;
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));
        subTabbedPane = new JTabbedPane();
        panelOperadores = crearPanelOperadores();
        panelControladores = crearPanelControladores();

        subTabbedPane.addTab("Operadores", panelOperadores);
        subTabbedPane.addTab("Controladores", panelControladores);

        add(subTabbedPane, BorderLayout.CENTER);
    }

    private JPanel crearPanelOperadores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Operadores"));

        // Alta Operador
        panel.add(new JLabel("Dar de alta Operador:"));
        panel.add(new JLabel("Nombre:"));
        txtOpNombre = new JTextField(15);
        panel.add(txtOpNombre);
        panel.add(new JLabel("Apellido:"));
        txtOpApellido = new JTextField(15);
        panel.add(txtOpApellido);
        panel.add(new JLabel("Contraseña:"));
        txtOpContrasena = new JPasswordField(15);
        panel.add(txtOpContrasena);
        panel.add(new JLabel("Aerolínea:"));
        cmbOpAerolinea = new JComboBox<>();
        cargarAerolineas(cmbOpAerolinea);
        panel.add(cmbOpAerolinea);
        btnOpAlta = new JButton("Dar de Alta Operador");
        panel.add(btnOpAlta);
        panel.add(Box.createVerticalStrut(15));

        // Editar Operador
        panel.add(new JLabel("Editar Operador:"));
        panel.add(new JLabel("Contraseña Actual:"));
        txtOpEditarContrasenaActual = new JPasswordField(15);
        panel.add(txtOpEditarContrasenaActual);
        panel.add(new JLabel("Nuevo Nombre:"));
        txtOpEditarNombre = new JTextField(15);
        panel.add(txtOpEditarNombre);
        panel.add(new JLabel("Nuevo Apellido:"));
        txtOpEditarApellido = new JTextField(15);
        panel.add(txtOpEditarApellido);
        panel.add(new JLabel("Nueva Contraseña:"));
        txtOpEditarNuevaContrasena = new JPasswordField(15);
        panel.add(txtOpEditarNuevaContrasena);
        panel.add(new JLabel("Nueva Aerolínea:"));
        cmbOpEditarAerolinea = new JComboBox<>();
        cargarAerolineas(cmbOpEditarAerolinea);
        panel.add(cmbOpEditarAerolinea);
        btnOpEditar = new JButton("Guardar Cambios Operador");
        panel.add(btnOpEditar);
        panel.add(Box.createVerticalStrut(15));

        // Eliminar Operador
        panel.add(new JLabel("Eliminar Operador:"));
        panel.add(new JLabel("Contraseña del Operador a Eliminar:"));
        txtOpEliminarContrasena = new JPasswordField(15);
        panel.add(txtOpEliminarContrasena);
        btnOpEliminar = new JButton("Eliminar Operador");
        panel.add(btnOpEliminar);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private JPanel crearPanelControladores() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Gestión de Controladores"));

        // Alta Controlador
        panel.add(new JLabel("Dar de alta Controlador:"));
        panel.add(new JLabel("Nombre:"));
        txtCoNombre = new JTextField(15);
        panel.add(txtCoNombre);
        panel.add(new JLabel("Apellido:"));
        txtCoApellido = new JTextField(15);
        panel.add(txtCoApellido);
        panel.add(new JLabel("Contraseña:"));
        txtCoContrasena = new JPasswordField(15);
        panel.add(txtCoContrasena);
        btnCoAlta = new JButton("Dar de Alta Controlador");
        panel.add(btnCoAlta);
        panel.add(Box.createVerticalStrut(15));

        // Editar Controlador
        panel.add(new JLabel("Editar Controlador:"));
        panel.add(new JLabel("Contraseña Actual:"));
        txtCoEditarContrasenaActual = new JPasswordField(15);
        panel.add(txtCoEditarContrasenaActual);
        panel.add(new JLabel("Nuevo Nombre:"));
        txtCoEditarNombre = new JTextField(15);
        panel.add(txtCoEditarNombre);
        panel.add(new JLabel("Nuevo Apellido:"));
        txtCoEditarApellido = new JTextField(15);
        panel.add(txtCoEditarApellido);
        panel.add(new JLabel("Nueva Contraseña:"));
        txtCoEditarNuevaContrasena = new JPasswordField(15);
        panel.add(txtCoEditarNuevaContrasena);
        btnCoEditar = new JButton("Guardar Cambios Controlador");
        panel.add(btnCoEditar);
        panel.add(Box.createVerticalStrut(15));

        // Eliminar Controlador
        panel.add(new JLabel("Eliminar Controlador:"));
        panel.add(new JLabel("Contraseña del Controlador a Eliminar:"));
        txtCoEliminarContrasena = new JPasswordField(15);
        panel.add(txtCoEliminarContrasena);
        btnCoEliminar = new JButton("Eliminar Controlador");
        panel.add(btnCoEliminar);
        panel.add(Box.createVerticalStrut(15));

        // Asignar Terminal
        panel.add(new JLabel("Asignar Terminal a Controlador:"));
        panel.add(new JLabel("Contraseña del Controlador:"));
        txtCoAsignarContrasena = new JPasswordField(15);
        panel.add(txtCoAsignarContrasena);
        panel.add(new JLabel("Seleccionar Terminal:"));
        cmbCoAsignarTerminal = new JComboBox<>();
        cargarTerminales(cmbCoAsignarTerminal);
        panel.add(cmbCoAsignarTerminal);
        btnCoAsignarTerminal = new JButton("Asignar Terminal");
        panel.add(btnCoAsignarTerminal);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private void cargarAerolineas(JComboBox<String> cmb) {
        cmb.removeAllItems();
        cmb.addItem("Seleccionar Aerolínea");
        for (Aerolinea al : gestor.getAerolineas()) {
            cmb.addItem(al.getNombre());
        }
    }

    private void cargarTerminales(JComboBox<String> cmb) {
        cmb.removeAllItems();
        cmb.addItem("Seleccionar Terminal");
        for (Terminal t : gestor.getTerminales()) {
            cmb.addItem("Terminal " + t.getId());
        }
    }

    public void limpiarCampos() {
        // Campos de Operadores
        txtOpNombre.setText("");
        txtOpApellido.setText("");
        txtOpContrasena.setText("");
        cmbOpAerolinea.setSelectedIndex(0);

        txtOpEditarContrasenaActual.setText("");
        txtOpEditarNombre.setText("");
        txtOpEditarApellido.setText("");
        txtOpEditarNuevaContrasena.setText("");
        cmbOpEditarAerolinea.setSelectedIndex(0);

        txtOpEliminarContrasena.setText("");

        // Campos de Controladores
        txtCoNombre.setText("");
        txtCoApellido.setText("");
        txtCoContrasena.setText("");

        txtCoEditarContrasenaActual.setText("");
        txtCoEditarNombre.setText("");
        txtCoEditarApellido.setText("");
        txtCoEditarNuevaContrasena.setText("");

        txtCoEliminarContrasena.setText("");

        txtCoAsignarContrasena.setText("");
        cmbCoAsignarTerminal.setSelectedIndex(0);
    }
}