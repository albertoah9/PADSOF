package controlador; // O controlador.gestor

import modelo.*;
import vista.VistaGestionUsuarios;
import modelo.Aerolinea;
import modelo.Aeropuerto;
import modelo.ControladorAereo;
import modelo.OperadorAereo;
import modelo.Terminal;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

public class ControladorVistaGestionUsuarios {

    private VistaGestionUsuarios vista;
    private Aeropuerto aeropuerto;
    private GestorAeropuerto gestor;

    public ControladorVistaGestionUsuarios(VistaGestionUsuarios vista, GestorAeropuerto gestor, Aeropuerto aeropuerto) {
        this.vista = vista;
        this.gestor = gestor;
        this.aeropuerto = aeropuerto;

        // --- ActionListeners para Operadores ---
        this.vista.btnOpAlta.addActionListener(e -> darAltaOperador());
        this.vista.btnOpEditar.addActionListener(e -> editarOperador());
        this.vista.btnOpEliminar.addActionListener(e -> eliminarOperador());

        // --- ActionListeners para Controladores ---
        this.vista.btnCoAlta.addActionListener(e -> darAltaControlador());
        this.vista.btnCoEditar.addActionListener(e -> editarControlador());
        this.vista.btnCoEliminar.addActionListener(e -> eliminarControlador());
        this.vista.btnCoAsignarTerminal.addActionListener(e -> asignarTerminalControlador());

        // Cargar datos iniciales en los JComboBox si es necesario
        cargarAerolineasEnComboBox();
        cargarTerminalesEnComboBox();
    }

    // Métodos de lógica para Operadores
    private void darAltaOperador() {
        String nombre = vista.txtOpNombre.getText();
        String apellido = vista.txtOpApellido.getText(); // Aunque no se use en el constructor de OperadorAereo, se mantiene la coherencia con la vista
        String contrasena = new String(vista.txtOpContrasena.getPassword()); // Obtener la contraseña como el DNI/ID
        String aerolineaNombre = (String) vista.cmbOpAerolinea.getSelectedItem();

        if (nombre.isEmpty() || contrasena.isEmpty() || aerolineaNombre == null || aerolineaNombre.equals("Seleccionar Aerolínea")) {
            JOptionPane.showMessageDialog(vista, "Todos los campos de Operador (excepto Apellido) son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Aerolinea aerolineaSeleccionada = gestor.getAerolineas().stream()
                .filter(a -> a.getNombre().equals(aerolineaNombre))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Aerolínea no encontrada: " + aerolineaNombre));

            // Aquí se asume que la contraseña se usa como DNI/ID en el método darDeAltaOperadorAereo
            OperadorAereo nuevoOperador = gestor.darDeAltaOperadorAereo(nombre, contrasena, aerolineaSeleccionada);

            JOptionPane.showMessageDialog(vista, "Operador dado de alta: " + nuevoOperador.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCampos(); // Limpiar todos los campos después de una operación exitosa
            cargarAerolineasEnComboBox(); // Recargar por si se añadió una nueva aerolínea (si el gestor lo permite)
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado al dar de alta Operador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarOperador() {
        String contrasenaActual = new String(vista.txtOpEditarContrasenaActual.getPassword()); // ID/Contraseña del operador a editar
        String nuevoNombre = vista.txtOpEditarNombre.getText();
        String nuevoApellido = vista.txtOpEditarApellido.getText(); // Se mantiene la coherencia con la vista
        String nuevaContrasena = new String(vista.txtOpEditarNuevaContrasena.getPassword()); // Nueva contraseña
        String nuevaAerolineaNombre = (String) vista.cmbOpEditarAerolinea.getSelectedItem();

        if (contrasenaActual.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "La Contraseña Actual del operador a editar es obligatoria.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Se asume que el método editarOperador en GestorAeropuerto usa la contraseña/ID para buscar al operador
            // y que los otros campos son opcionales para la edición.
            // Si el GestorAeropuerto necesita una Aerolinea, deberás buscarla.
            Aerolinea nuevaAerolinea = null;
            if (nuevaAerolineaNombre != null && !nuevaAerolineaNombre.equals("Nueva Aerolínea (opcional)")) {
                nuevaAerolinea = gestor.getAerolineas().stream()
                    .filter(a -> a.getNombre().equals(nuevaAerolineaNombre))
                    .findFirst()
                    .orElse(null); // O lanzar excepción si es obligatoria
            }

            boolean cambiosRealizados = gestor.editarOperador(contrasenaActual, nuevoNombre, nuevaContrasena, nuevaAerolinea);

            if (cambiosRealizados) {
                JOptionPane.showMessageDialog(vista, "Operador editado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "No se encontraron cambios o el operador no existe (Contraseña/ID no válido).", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            vista.limpiarCampos();
            cargarAerolineasEnComboBox();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(vista, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error inesperado al editar Operador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarOperador() {
        String contrasenaEliminar = new String(vista.txtOpEliminarContrasena.getPassword()); // ID/Contraseña del operador a eliminar
        if (contrasenaEliminar.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "La Contraseña del operador a eliminar es obligatoria.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar al operador con Contraseña/ID: " + contrasenaEliminar + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                OperadorAereo operadorAEliminar = gestor.buscarOperadorPorContrasena(contrasenaEliminar); // Nuevo método en GestorAeropuerto
                // Se asume que el método eliminarOperador en GestorAeropuerto usa la contraseña/ID para buscar al operador
                boolean eliminado = gestor.eliminarOperadorAereo(operadorAEliminar);

                if (eliminado) {
                    JOptionPane.showMessageDialog(vista, "Operador eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    vista.limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "Operador no encontrado (Contraseña/ID no válido).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(vista, "Error de validación: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error inesperado al eliminar Operador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Métodos de lógica para Controladores
    private void darAltaControlador() {
        String nombre = vista.txtCoNombre.getText();
        String apellido = vista.txtCoApellido.getText(); // Se mantiene la coherencia con la vista
        String contrasena = new String(vista.txtCoContrasena.getPassword()); // La contraseña es el ID para este caso

        if (nombre.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Todos los campos de Controlador (excepto Apellido) son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Se asume que el Aeropuerto tiene un método para añadir controladores que usa la contraseña como ID
            // Y que el constructor de ControladorAereo toma nombre y contraseña.
            ControladorAereo nuevoControlador = new ControladorAereo(nombre, contrasena, null);
            aeropuerto.agregarControladorAereo(nuevoControlador); // O gestor.addControlador(nuevoControlador) si lo centralizas
            JOptionPane.showMessageDialog(vista, "Controlador dado de alta: " + nombre, "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al dar de alta Controlador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarControlador() {
        String contrasenaActual = new String(vista.txtCoEditarContrasenaActual.getPassword()); // Contraseña/ID del controlador a editar
        String nuevoNombre = vista.txtCoEditarNombre.getText();
        String nuevaContrasena = new String(vista.txtCoEditarNuevaContrasena.getPassword());

        if (contrasenaActual.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "La Contraseña Actual del controlador a editar es obligatoria.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Asumiendo que el método de búsqueda usa la contraseña como ID
            ControladorAereo controlador = aeropuerto.buscarControladorPorContrasena(contrasenaActual); // Nuevo método en Aeropuerto
            if (controlador == null) {
                JOptionPane.showMessageDialog(vista, "Controlador no encontrado (Contraseña/ID no válido).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean cambiosRealizados = false;
            if (!nuevoNombre.isEmpty()) {
                controlador.setNombre(nuevoNombre);
                cambiosRealizados = true;
            }
            if (!nuevaContrasena.isEmpty()) {
                controlador.setContraseña(nuevaContrasena);
                cambiosRealizados = true;
            }

            if (cambiosRealizados) {
                JOptionPane.showMessageDialog(vista, "Controlador editado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(vista, "No se realizaron cambios.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
            vista.limpiarCampos();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al editar Controlador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarControlador() {
        String contrasenaEliminar = new String(vista.txtCoEliminarContrasena.getPassword()); // Contraseña/ID del controlador a eliminar
        if (contrasenaEliminar.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "La Contraseña del controlador a eliminar es obligatoria.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(vista, "¿Está seguro de eliminar al controlador con Contraseña/ID: " + contrasenaEliminar + "?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                // Asumiendo que el método de búsqueda y eliminación usa la contraseña como ID
                ControladorAereo controladorAEliminar = aeropuerto.buscarControladorPorContrasena(contrasenaEliminar); // Nuevo método en Aeropuerto
                boolean eliminado = false;
                if (controladorAEliminar != null) {
                    eliminado = aeropuerto.eliminarControladorAereo(controladorAEliminar);
                }
                if (eliminado) {
                    JOptionPane.showMessageDialog(vista, "Controlador eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    vista.limpiarCampos();
                } else {
                    JOptionPane.showMessageDialog(vista, "Controlador no encontrado (Contraseña/ID no válido).", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(vista, "Error al eliminar Controlador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void asignarTerminalControlador() {
        String contrasenaControlador = new String(vista.txtCoAsignarContrasena.getPassword()); // Contraseña/ID del controlador
        String terminalIdStr = (String) vista.cmbCoAsignarTerminal.getSelectedItem();

        if (contrasenaControlador.isEmpty() || terminalIdStr == null || terminalIdStr.equals("Seleccionar Terminal")) {
            JOptionPane.showMessageDialog(vista, "La Contraseña del controlador y la Terminal son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Extraer solo el número de la terminal (ej. "Terminal 1" -> "1")
            int terminalId = Integer.parseInt(terminalIdStr.replaceAll("[^0-9]", ""));

            ControladorAereo controlador = aeropuerto.buscarControladorPorContrasena(contrasenaControlador); // Nuevo método
            Terminal terminal = aeropuerto.buscarTerminalPorId(terminalId);

            if (controlador == null || terminal == null) {
                JOptionPane.showMessageDialog(vista, "Controlador (Contraseña/ID no válido) o Terminal no encontrados.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            terminal.agregarControlador(controlador);
            terminal.notificarCambio("Se le ha asignado la terminal " + terminal.getId());


            JOptionPane.showMessageDialog(vista, "Terminal " + terminalId + " asignada a Controlador " + controlador.getNombre(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCampos();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(vista, "El ID de la Terminal debe ser un número válido.", "Error de Validación", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al asignar terminal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Métodos para cargar datos en ComboBoxes
    private void cargarAerolineasEnComboBox() {
        vista.cmbOpAerolinea.removeAllItems();
        vista.cmbOpEditarAerolinea.removeAllItems();
        vista.cmbOpAerolinea.addItem("Seleccionar Aerolínea");
        vista.cmbOpEditarAerolinea.addItem("Nueva Aerolínea (opcional)");
        List<Aerolinea> aerolineas = gestor.getAerolineas(); // Usar el gestor para obtener las aerolíneas
        for (Aerolinea al : aerolineas) {
            vista.cmbOpAerolinea.addItem(al.getNombre());
            vista.cmbOpEditarAerolinea.addItem(al.getNombre());
        }
    }

    private void cargarTerminalesEnComboBox() {
        vista.cmbCoAsignarTerminal.removeAllItems();
        vista.cmbCoAsignarTerminal.addItem("Seleccionar Terminal");
        List<Terminal> terminales = gestor.getTerminales(); // Usar el gestor para obtener las terminales
        for (Terminal t : terminales) {
            vista.cmbCoAsignarTerminal.addItem("Terminal " + t.getId());
        }
    }
}