import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

// Asumiendo que GestorAeropuerto es visible (Necesario para el constructor)
// import tu_paquete.GestorAeropuerto;

public class PanelEstado extends JPanel {

    private GestorAeropuerto gestor; // Mantenemos la referencia

    // Componentes del filtro
    private JComboBox<String> cmbTipoElemento;
    private JComboBox<String> cmbDisponibilidad;
    private JTextField txtCostePorHoraFiltro; // Basado en la imagen
    private JTextField txtIdFiltro;
    private JComboBox<String> cmbEstadoFiltro; // Basado en la imagen
    private JButton btnFiltrar;

    // Componente de texto descriptivo
    private JTextArea txtAreaDescripcion;

    // Componentes de la tabla
    private JTable tableElementos;
    private DefaultTableModel tableModelElementos;

    // Botones de acción sobre la tabla
    private JButton btnVer;
    private JButton btnEliminar; // Añadimos botón de eliminar basado en la descripción


    public PanelEstado(GestorAeropuerto gestor) {
        this.gestor = gestor;

        setLayout(new BorderLayout()); // Layout principal del panel
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Margen alrededor

        // --- Panel Superior (Título, Filtros y Descripción) ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Apila verticalmente el contenido superior
        topPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Espacio inferior después del topPanel

        // Título
        JLabel lblTitulo = new JLabel("Listas de elementos disponibles / no disponibles para el uso");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar título horizontalmente
        topPanel.add(lblTitulo);

        topPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio vertical

        // Panel de Filtros (usando GridBagLayout para una alineación precisa)
        JPanel filterPanel = new JPanel(new GridBagLayout());
        filterPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el panel de filtros horizontalmente

        GridBagConstraints gbcFilter = new GridBagConstraints();
        gbcFilter.insets = new Insets(5, 5, 5, 5); // Margen entre componentes
        gbcFilter.anchor = GridBagConstraints.WEST; // Alinear componentes a la izquierda por defecto

        // Fila 0 de filtros
        gbcFilter.gridx = 0; gbcFilter.gridy = 0;
        filterPanel.add(new JLabel("Tipo de elemento a buscar:"), gbcFilter);
        gbcFilter.gridx = 1; gbcFilter.fill = GridBagConstraints.HORIZONTAL; // Permitir que el JComboBox se expanda horizontalmente
        cmbTipoElemento = new JComboBox<>(new String[]{"Todos", "Finger", "Hangar", "Terminal", "Pista", "Aparcamiento", "Avion", "Aerolinea", "Operador", "Controlador"}); // Datos placeholder
        filterPanel.add(cmbTipoElemento, gbcFilter);

        gbcFilter.gridx = 2; gbcFilter.gridy = 0; gbcFilter.fill = GridBagConstraints.NONE;
        filterPanel.add(new JLabel("Disponibilidad:"), gbcFilter);
        gbcFilter.gridx = 3; gbcFilter.fill = GridBagConstraints.HORIZONTAL;
        cmbDisponibilidad = new JComboBox<>(new String[]{"Todas", "Disponible", "No disponible"}); // Datos placeholder
        filterPanel.add(cmbDisponibilidad, gbcFilter);

        gbcFilter.gridx = 4; gbcFilter.gridy = 0; gbcFilter.fill = GridBagConstraints.NONE;
        filterPanel.add(new JLabel("Coste por hora (si aplica):"), gbcFilter); // Basado en la imagen
        gbcFilter.gridx = 5; gbcFilter.fill = GridBagConstraints.HORIZONTAL;
        txtCostePorHoraFiltro = new JTextField(10); // Tamaño inicial
        filterPanel.add(txtCostePorHoraFiltro, gbcFilter);

        // Fila 1 de filtros
        gbcFilter.gridx = 0; gbcFilter.gridy = 1; gbcFilter.fill = GridBagConstraints.NONE;
        filterPanel.add(new JLabel("Id:"), gbcFilter);
        gbcFilter.gridx = 1; gbcFilter.gridy = 1; gbcFilter.fill = GridBagConstraints.HORIZONTAL;
        txtIdFiltro = new JTextField(10); // Tamaño inicial
        filterPanel.add(txtIdFiltro, gbcFilter);

        gbcFilter.gridx = 2; gbcFilter.gridy = 1; gbcFilter.fill = GridBagConstraints.NONE;
        filterPanel.add(new JLabel("Estado:"), gbcFilter); // Basado en la imagen
        gbcFilter.gridx = 3; gbcFilter.gridy = 1; gbcFilter.fill = GridBagConstraints.HORIZONTAL;
        cmbEstadoFiltro = new JComboBox<>(new String[]{"Todos", "Activo", "Inactivo", "Ocupado", "Libre", "En Vuelo", "En Hangar"}); // Datos placeholder (adaptar según tipos)
        filterPanel.add(cmbEstadoFiltro, gbcFilter);

        // Botón Filtrar
        gbcFilter.gridx = 5; gbcFilter.gridy = 1; gbcFilter.anchor = GridBagConstraints.EAST; // Alinear a la derecha en su celda
        gbcFilter.fill = GridBagConstraints.NONE; // No expandir el botón
        btnFiltrar = new JButton("Filtrar");
        filterPanel.add(btnFiltrar, gbcFilter);

        // Añadir "pegamento" horizontal para empujar los componentes de filtro a la izquierda si hay espacio extra
        gbcFilter.gridx = 6; gbcFilter.gridy = 0; gbcFilter.weightx = 1.0; // Dar peso horizontal
        gbcFilter.fill = GridBagConstraints.HORIZONTAL; // Permitir que el pegamento se expanda
        filterPanel.add(Box.createHorizontalGlue(), gbcFilter);
         gbcFilter.gridx = 6; gbcFilter.gridy = 1; gbcFilter.weightx = 1.0;
        gbcFilter.fill = GridBagConstraints.HORIZONTAL;
        filterPanel.add(Box.createHorizontalGlue(), gbcFilter);


        topPanel.add(filterPanel); // Añade el panel de filtros al panel superior

        topPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio vertical

        // Área de texto descriptivo (no editable)
        txtAreaDescripcion = new JTextArea(
                "En este apartado se verán las cosas que se han dado de alta (es decir cosas disponibles y no disponibles) para\n" +
                "poder saber si se pueden usar o no. También se pueden modificar sus estados o precios dependiendo del\n" +
                "usuario que este manipulando esta pantalla"
        );
        txtAreaDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 12));
        txtAreaDescripcion.setLineWrap(true); // Habilitar ajuste de línea
        txtAreaDescripcion.setWrapStyleWord(true); // Ajustar por palabras
        txtAreaDescripcion.setEditable(false); // Hacer el texto no editable
        txtAreaDescripcion.setBackground(this.getBackground()); // Usar el mismo color de fondo que el panel
        txtAreaDescripcion.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5)); // Pequeño margen interno
        txtAreaDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT); // Alinear a la izquierda dentro del BoxLayout
        // Si el texto fuera mucho más largo, lo pondríamos en un JScrollPane

        topPanel.add(txtAreaDescripcion); // Añade el área de texto al panel superior

        add(topPanel, BorderLayout.NORTH); // Añade el panel superior completo al NORTH del panel principal


        // --- Tabla de Elementos ---
        // Columnas de la tabla (placeholder, adaptar según los datos reales de los elementos)
        String[] columnNames = {"Id", "Tipo", "Estado", "Ubicación", "Disponibilidad", "Coste/Hora"};
        tableModelElementos = new DefaultTableModel(columnNames, 0); // 0 filas iniciales
        tableElementos = new JTable(tableModelElementos);

        // Configuración básica de la tabla
        tableElementos.setFillsViewportHeight(true); // La tabla ocupa todo el alto del JScrollPane
        tableElementos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Permitir seleccionar solo una fila


        // Añadir algunos datos de ejemplo a la tabla
        loadFilteredData(getAllSampleElementData()); // Método para cargar datos de ejemplo (placeholder)

        // Meter la tabla en un JScrollPane para que tenga scrollbars
        JScrollPane scrollPaneTabla = new JScrollPane(tableElementos);
        add(scrollPaneTabla, BorderLayout.CENTER); // Añade la tabla con scroll al CENTER del panel principal


        // --- Panel Inferior (Botones de Acción de la Tabla) ---
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Usar FlowLayout alineado a la derecha para los botones
        btnVer = new JButton("Ver");
        btnEliminar = new JButton("Eliminar"); // Botón eliminar, como se menciona en la descripción (especialmente para usuarios)

        bottomPanel.add(btnVer);
        bottomPanel.add(btnEliminar); // Añade el botón eliminar
        add(bottomPanel, BorderLayout.SOUTH); // Añade el panel inferior al SOUTH del panel principal

        // --- Añadir Action Listeners a los botones ---

        // Action Listener para el botón Filtrar
        btnFiltrar.addActionListener(e -> {
            // 1. Obtener criterios de filtro
            String tipoElementoFilter = (String) cmbTipoElemento.getSelectedItem();
            String disponibilidadFilter = (String) cmbDisponibilidad.getSelectedItem();
            String costePorHoraFilterText = txtCostePorHoraFiltro.getText().trim(); // trim() para quitar espacios
            String idFilter = txtIdFiltro.getText().trim().toLowerCase(); // toLowerCase() para búsqueda sin distinción de mayúsculas
            String estadoFilter = (String) cmbEstadoFiltro.getSelectedItem();

            // 2. Obtener todos los datos de ejemplo
            java.util.List<Object[]> allData = getAllSampleElementData();

            // 3. Filtrar los datos
            java.util.List<Object[]> filteredData = new java.util.ArrayList<>();
            for (Object[] row : allData) {
                // Asumimos el orden de columnas: {"Id", "Tipo", "Estado", "Ubicación", "Disponibilidad", "Coste/Hora"}
                String currentId = row[0].toString().toLowerCase();
                String currentTipo = row[1].toString();
                String currentEstado = row[2].toString();
                // Object currentUbicacion = row[3]; // No usado en este filtro simple
                String currentDisponibilidad = row[4].toString();
                Object currentCosteHoraObj = row[5]; // Puede ser Double o String ("N/A")


                boolean matches = true; // Empezamos asumiendo que coincide

                // Aplicar filtro por Tipo de Elemento
                if (!"Todos".equals(tipoElementoFilter) && !currentTipo.equals(tipoElementoFilter)) {
                    matches = false;
                }

                // Aplicar filtro por Disponibilidad
                if (!"Todas".equals(disponibilidadFilter) && !currentDisponibilidad.equals(disponibilidadFilter)) {
                    matches = false;
                }

                // Aplicar filtro por ID (contiene)
                if (!idFilter.isEmpty() && !currentId.contains(idFilter)) {
                     matches = false;
                }

                // Aplicar filtro por Estado
                if (!"Todos".equals(estadoFilter) && !currentEstado.equals(estadoFilter)) {
                     matches = false;
                }

                // Aplicar filtro por Coste por Hora (si el campo de filtro no está vacío)
                // NOTA: Esta es una comparación de texto simple. Para un filtro numérico real,
                // necesitarías parsear los valores a números y compararlos (ej. >=, <=).
                // Además, manejar "N/A". Por simplicidad, verificamos si el texto del Coste/Hora *contiene* el texto del filtro.
                if (!costePorHoraFilterText.isEmpty()) {
                    String currentCosteHoraStr = (currentCosteHoraObj != null) ? currentCosteHoraObj.toString() : "";
                     if (!currentCosteHoraStr.contains(costePorHoraFilterText)) {
                         matches = false;
                     }
                }


                if (matches) {
                    filteredData.add(row); // Si coincide con todos los filtros, añadir a la lista filtrada
                }
            }

            // 4. Limpiar la tabla y añadir los datos filtrados
            loadFilteredData(filteredData);

            // Opcional: Mostrar mensaje de cuántos resultados se encontraron
            JOptionPane.showMessageDialog(this, "Filtro aplicado. Resultados encontrados: " + filteredData.size(), "Filtro Aplicado", JOptionPane.INFORMATION_MESSAGE);
        });

        // Action Listener para el botón Ver
        btnVer.addActionListener(e -> {
            int selectedRow = tableElementos.getSelectedRow(); // Obtener la fila seleccionada
            if (selectedRow != -1) { // Verificar que haya una fila seleccionada
                // Obtener datos básicos del elemento de la fila seleccionada
                Object elementId = tableModelElementos.getValueAt(selectedRow, 0);
                Object elementType = tableModelElementos.getValueAt(selectedRow, 1);
                // Puedes obtener más datos de la fila si los necesitas para la ventana de detalle

                // --- Lógica para abrir una nueva ventana de detalle/modificación ---
                // Como se describe, esto abre una ventana aparte. Crearemos una simple ventana placeholder aquí.
                JOptionPane.showMessageDialog(this, "Abriendo ventana de detalles para: " + elementType + " (ID: " + elementId + ")", "Ver Detalles", JOptionPane.INFORMATION_MESSAGE);


                // Creamos una nueva ventana (JFrame)
                 JFrame detalleFrame = new JFrame("Detalles del Elemento: " + elementType + " (ID: " + elementId + ")");
                 detalleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cerrar solo esta ventana, no la aplicación
                 detalleFrame.setSize(450, 350); // Tamaño de la ventana de detalle
                 detalleFrame.setLocationRelativeTo(this); // Centrar la nueva ventana respecto al panel principal


                 JPanel detallePanel = new JPanel(new BorderLayout()); // Panel principal de la ventana de detalle
                 detallePanel.setBorder(new EmptyBorder(15, 15, 15, 15));

                 JLabel lblDetalleTitulo = new JLabel("Detalles de " + elementType + " (ID: " + elementId + ")");
                 lblDetalleTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
                 detallePanel.add(lblDetalleTitulo, BorderLayout.NORTH);

                 // Área donde irían los campos de detalle y modificación
                 JTextArea txtAreaDetalles = new JTextArea("Aquí se mostrarían los detalles específicos del elemento:\n" +
                                                          "- Estado (modificable)\n" +
                                                          "- Ubicación (modificable)\n" +
                                                          "- Otros atributos (precio, etc.)\n\n" +
                                                          "Los campos de modificación solo son editables si el usuario tiene los permisos.");
                 txtAreaDetalles.setLineWrap(true);
                 txtAreaDetalles.setWrapStyleWord(true);
                 txtAreaDetalles.setEditable(false); // Por defecto no editable, solo editable si permisos lo permiten
                 txtAreaDetalles.setBackground(detallePanel.getBackground()); // Fondo transparente
                 JScrollPane scrollDetalles = new JScrollPane(txtAreaDetalles);
                 scrollDetalles.setBorder(BorderFactory.createEmptyBorder()); // Sin borde


                 detallePanel.add(scrollDetalles, BorderLayout.CENTER);


                 // Panel de botones dentro de la ventana de detalle
                 JPanel detalleButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                 JButton btnGuardarCambios = new JButton("Guardar Cambios");
                 JButton btnEliminarDetalle = new JButton("Eliminar"); // Botón Eliminar DENTRO de la ventana de detalle (para usuarios)

                 // --- Lógica básica de permisos y visualización de botón Eliminar ---
                 // Según la descripción, eliminar usuarios se hace DESDE la ventana de detalle.
                 // Asumimos que "Usuarios" son Operador, Controlador, Gestor.
                 boolean isUserElement = "Operador".equals(elementType) || "Controlador".equals(elementType) || "Gestor".equals(elementType);

                 if (isUserElement) {
                      // Si es un usuario, mostramos el botón Eliminar en la ventana de detalle
                      detalleButtonPanel.add(btnEliminarDetalle);
                      btnEliminarDetalle.addActionListener(ev -> {
                          // Lógica de eliminación del usuario
                          int confirm = JOptionPane.showConfirmDialog(detalleFrame, "¿Estás seguro de eliminar a este usuario?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                          if(confirm == JOptionPane.YES_OPTION) {
                              // Aquí llamarías a la lógica de negocio para eliminar al usuario
                              JOptionPane.showMessageDialog(detalleFrame, "Lógica para eliminar " + elementType + " (ID: " + elementId + ")", "Eliminar Usuario", JOptionPane.INFORMATION_MESSAGE);
                              detalleFrame.dispose(); // Cerrar la ventana de detalle
                              // NOTA: Después de eliminar, necesitarías refrescar la tabla en PanelEstado.
                           }
                      });
                 }


                 // Añadir el botón Guardar Cambios
                 detalleButtonPanel.add(btnGuardarCambios);
                 btnGuardarCambios.addActionListener(ev -> {
                      // Lógica para guardar los cambios (validar permisos antes de guardar)
                      // Obtener datos de los campos de modificación en la ventana de detalle
                      JOptionPane.showMessageDialog(detalleFrame, "Lógica para guardar cambios en " + elementType + " (ID: " + elementId + ")", "Guardar Cambios", JOptionPane.INFORMATION_MESSAGE);
                      // Después de guardar, podrías cerrar la ventana o actualizar la vista.
                 });


                 detallePanel.add(detalleButtonPanel, BorderLayout.SOUTH);

                 detalleFrame.add(detallePanel); // Añadir el panel principal a la ventana
                 detalleFrame.setVisible(true); // Mostrar la ventana


            } else {
                // Si no hay fila seleccionada
                JOptionPane.showMessageDialog(this, "Selecciona un elemento para ver/modificar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Action Listener para el botón Eliminar (en el panel principal)
        btnEliminar.addActionListener(e -> {
            // Esta lógica de eliminar desde el panel principal podría usarse para elementos NO-usuario,
            // o como una opción rápida si no requiere la ventana de detalle.
            // Si la eliminación de usuarios SOLO se hace desde la ventana de detalle, esta lógica principal
            // del botón "Eliminar" podría ser solo para otros tipos de elementos o eliminada.

            int selectedRow = tableElementos.getSelectedRow(); // Obtener la fila seleccionada
            if (selectedRow != -1) { // Verificar que haya una fila seleccionada
                 Object elementId = tableModelElementos.getValueAt(selectedRow, 0);
                 Object elementType = tableModelElementos.getValueAt(selectedRow, 1);

                 // --- Verificar permisos antes de eliminar ---
                 // En una aplicación real, aquí verificarías si el gestor actual tiene permiso para eliminar este tipo de elemento
                 boolean tienePermisoParaEliminarTipo = true; // Placeholder para la verificación de permisos

                 if (tienePermisoParaEliminarTipo) {
                      int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de eliminar " + elementType + " (ID: " + elementId + ")?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                      if(confirm == JOptionPane.YES_OPTION) {
                           // --- Lógica real de eliminación ---
                           // Llamar a la lógica de negocio para eliminar el elemento del sistema
                           JOptionPane.showMessageDialog(this, "Lógica para eliminar " + elementType + " (ID: " + elementId + ")", "Eliminar Elemento", JOptionPane.INFORMATION_MESSAGE);

                           // Eliminar la fila de la tabla después de la eliminación exitosa
                           tableModelElementos.removeRow(selectedRow);
                       }
                 } else {
                      JOptionPane.showMessageDialog(this, "No tienes permisos para eliminar este tipo de elemento.", "Permiso Denegado", JOptionPane.WARNING_MESSAGE);
                 }

            } else {
                // Si no hay fila seleccionada
                JOptionPane.showMessageDialog(this, "Selecciona un elemento para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        });

    }

    private void loadFilteredData(java.util.List<Object[]> dataToLoad) {
        tableModelElementos.setRowCount(0); // Limpiar todas las filas actuales
        for (Object[] row : dataToLoad) {
            tableModelElementos.addRow(row); // Añadir cada fila de la lista
        }
    }

    // Método para añadir datos de ejemplo a la tabla
    private java.util.List<Object[]> getAllSampleElementData() {
        java.util.List<Object[]> data = new java.util.ArrayList<>();
        data.add(new Object[]{"F001", "Finger", "Activo", "Terminal 1, Puerta A1", "Disponible", 100.00});
        data.add(new Object[]{"H002", "Hangar", "Ocupado", "Zona de Hangares B", "No disponible", 500.00});
        data.add(new Object[]{"T001", "Terminal", "Activo", "N/A", "Disponible", "N/A"});
        data.add(new Object[]{"P001", "Pista", "Activo", "N/A", "Disponible", "N/A"});
        data.add(new Object[]{"A005", "Aparcamiento", "Libre", "Zona C, Plaza 15", "Disponible", 50.00});
        data.add(new Object[]{"AV123", "Avion", "En Vuelo", "N/A", "No disponible", "N/A"});
        data.add(new Object[]{"AE001", "Aerolinea", "Activa", "N/A", "Disponible", "N/A"});
        data.add(new Object[]{"OP001", "Operador", "Activo", "N/A", "Disponible", "N/A"});
        data.add(new Object[]{"CO001", "Controlador", "Activo", "Terminal 1", "Disponible", "N/A"});
        data.add(new Object[]{"AV456", "Avion", "En Hangar", "Hangar H002", "No disponible", 500.00}); // Ejemplo, Coste/Hora puede aplicar a Hangares
        // Añadir más datos de ejemplo aquí si quieres
        return data;
    }
}