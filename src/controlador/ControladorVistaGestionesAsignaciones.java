package controlador; // O controlador.gestor

import vista.VistaGestionesAsignaciones; // Importa la vista/panel
import modelo.GestorAeropuerto;

import modelo.*;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List; // Para cargar datos en los JComboBox

public class ControladorVistaGestionesAsignaciones {

    private VistaGestionesAsignaciones vista;
    private GestorAeropuerto gestor;

    public ControladorVistaGestionesAsignaciones(VistaGestionesAsignaciones vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        // --- ActionListeners para el sub-panel de Operadores (Aunque ya está en VistaGestionUsuarios) ---
        // Si decidimos que la gestión de usuarios completa está en PanelSettings y no aquí,
        // estas líneas se podrían eliminar. Por ahora, las mantengo como referencia.
        this.vista.btnOpConfirmar.addActionListener(e -> gestionarOperador());

        // --- ActionListeners para el sub-panel de Controladores ---
        this.vista.btnCoConfirmar.addActionListener(e -> gestionarControlador());

        // --- ActionListeners para el sub-panel de Terminales ---
        this.vista.btnTerConfirmar.addActionListener(e -> gestionarTerminal());

        // --- ActionListeners para el sub-panel de Puertas de Embarque ---
        this.vista.btnPueConfirmar.addActionListener(e -> gestionarPuertaEmbarque());

        // --- ActionListeners para el sub-panel de Pistas ---
        this.vista.btnPisConfirmar.addActionListener(e -> gestionarPista());

        // --- ActionListeners para el sub-panel de Hangares ---
        this.vista.btnHanConfirmar.addActionListener(e -> gestionarHangar());

        // --- ActionListeners para el sub-panel de Fingers ---
        this.vista.btnFinConfirmar.addActionListener(e -> gestionarFinger());

        // --- ActionListeners para el sub-panel de Zonas de Aparcamiento ---
        this.vista.btnZapConfirmar.addActionListener(e -> gestionarZonaAparcamiento());
        
        // --- ActionListeners para el sub-panel de Asignaciones de Vuelo ---
        this.vista.btnAsigAsignar.addActionListener(e -> asignarVueloAPistaPuerta());

        // Cargar datos iniciales en los JComboBoxes (si es necesario)
        cargarDatosIniciales();
    }

    // --- Métodos de lógica para cada operación ---

    private void gestionarOperador() {
        // Implementar lógica para alta, edición o eliminación de operador
        // Usar los campos txtOpNombre, txtOpApellido, txtOpDni, cmbOpAerolinea
        // y el gestor.darDeAltaOperador(nuevoOperador);
        JOptionPane.showMessageDialog(vista, "Lógica de gestión de Operador a implementar.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void gestionarControlador() {
        // Implementar lógica para alta, edición o eliminación de controlador
        // Usar los campos txtCoNombre, txtCoApellido, txtCoDni, txtCoContrasena, cmbCoTerminal
        // y el gestor.darDeAltaControlador(nuevoControlador);
        JOptionPane.showMessageDialog(vista, "Lógica de gestión de Controlador a implementar.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void gestionarTerminal() {
        String idTerminal = vista.txtTerIdTerminal.getText();
        String tipo = (String) vista.cmbTerTipoTerminal.getSelectedItem();
        String aforoStr = vista.txtTerAforoMaximo.getText();
        String capacidadMercanciasStr = vista.txtTerAparcamientoMercancias.getText();

        if (idTerminal.isEmpty() || tipo == null || (tipo.equals("Pasajeros") && aforoStr.isEmpty()) || (tipo.equals("Mercancías") && capacidadMercanciasStr.isEmpty())) {
            JOptionPane.showMessageDialog(vista, "Por favor, complete todos los campos de la Terminal.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            int aforo = 0;
            double capacidadMercancias = 0.0;

            if (tipo.equals("Pasajeros")) {
                aforo = Integer.parseInt(aforoStr);
            } else if (tipo.equals("Mercancías")) {
                capacidadMercancias = Double.parseDouble(capacidadMercanciasStr);
            }

            // Aquí llamarías al método del gestor:
            // gestor.gestionarTerminal(idTerminal, tipo, aforo, capacidadMercancias);
            JOptionPane.showMessageDialog(vista, "Terminal " + idTerminal + " guardada/actualizada. (Tipo: " + tipo + ")", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.txtTerIdTerminal.setText("");
            vista.txtTerAforoMaximo.setText("");
            vista.txtTerAparcamientoMercancias.setText("");
            vista.cmbTerTipoTerminal.setSelectedIndex(0);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Aforo/Capacidad de Mercancías debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al gestionar Terminal: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gestionarPuertaEmbarque() {
        String codigoPuerta = vista.txtPueCodigoPuerta.getText();
        String tipoVuelo = (String) vista.cmbPueTipoVuelo.getSelectedItem();
        String idTerminal = vista.txtPueIdTerminalAsociada.getText();

        if (codigoPuerta.isEmpty() || tipoVuelo == null || idTerminal.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos de Puerta de Embarque.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Aquí llamarías al método del gestor:
            // gestor.gestionarPuertaEmbarque(codigoPuerta, tipoVuelo, idTerminal);
            JOptionPane.showMessageDialog(vista, "Puerta de Embarque " + codigoPuerta + " guardada/actualizada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.txtPueCodigoPuerta.setText("");
            vista.cmbPueTipoVuelo.setSelectedIndex(0);
            vista.txtPueIdTerminalAsociada.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al gestionar Puerta de Embarque: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void gestionarPista() {
        String idPista = vista.txtPisIdPista.getText();
        String tipoPista = (String) vista.cmbPisTipoPista.getSelectedItem();
        String dimensiones = vista.txtPisDimensiones.getText();

        if (idPista.isEmpty() || tipoPista == null || dimensiones.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos de Pista.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // Aquí llamarías al método del gestor:
            // gestor.gestionarPista(idPista, tipoPista, dimensiones);
            JOptionPane.showMessageDialog(vista, "Pista " + idPista + " guardada/actualizada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.txtPisIdPista.setText("");
            vista.cmbPisTipoPista.setSelectedIndex(0);
            vista.txtPisDimensiones.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al gestionar Pista: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gestionarHangar() {
        String idHangar = vista.txtHanIdHangar.getText();
        String tipoHangar = (String) vista.cmbHanTipoHangar.getSelectedItem();
        String capacidadStr = vista.txtHanCapacidad.getText();
        String costeDiaStr = vista.txtHanCostePorDia.getText();
        String capMercanciasPeligrosasStr = vista.txtHanCapacidadMercanciasPeligrosas.getText();

        if (idHangar.isEmpty() || tipoHangar == null || capacidadStr.isEmpty() || costeDiaStr.isEmpty() || capMercanciasPeligrosasStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos de Hangar.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int capacidad = Integer.parseInt(capacidadStr);
            double costeDia = Double.parseDouble(costeDiaStr);
            double capMercanciasPeligrosas = Double.parseDouble(capMercanciasPeligrosasStr);

            // Aquí llamarías al método del gestor:
            // gestor.gestionarHangar(idHangar, tipoHangar, capacidad, costeDia, capMercanciasPeligrosas);
            JOptionPane.showMessageDialog(vista, "Hangar " + idHangar + " guardado/actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.txtHanIdHangar.setText("");
            vista.cmbHanTipoHangar.setSelectedIndex(0);
            vista.txtHanCapacidad.setText("");
            vista.txtHanCostePorDia.setText("");
            vista.txtHanCapacidadMercanciasPeligrosas.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Capacidad/Coste/Cap. Mercancías Peligrosas debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al gestionar Hangar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gestionarFinger() {
        String idFinger = vista.txtFinIdFinger.getText();
        String puertasAsociadas = vista.txtFinPuertasEmbarqueAsociadas.getText();
        String alturaMaximaStr = vista.txtFinAlturaMaxima.getText();
        String costePorHoraStr = vista.txtFinCostePorHora.getText();

        if (idFinger.isEmpty() || puertasAsociadas.isEmpty() || alturaMaximaStr.isEmpty() || costePorHoraStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos de Finger.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double alturaMaxima = Double.parseDouble(alturaMaximaStr);
            double costePorHora = Double.parseDouble(costePorHoraStr);
            
            // Aquí procesarías puertasAsociadas (ej. split por coma y buscar cada PuertaEmbarque)
            // List<PuertaEmbarque> puertas = ...

            // Aquí llamarías al método del gestor:
            // gestor.gestionarFinger(idFinger, puertas, alturaMaxima, costePorHora);
            JOptionPane.showMessageDialog(vista, "Finger " + idFinger + " guardado/actualizado.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.txtFinIdFinger.setText("");
            vista.txtFinPuertasEmbarqueAsociadas.setText("");
            vista.txtFinAlturaMaxima.setText("");
            vista.txtFinCostePorHora.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Altura Máxima/Coste por Hora debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al gestionar Finger: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void gestionarZonaAparcamiento() {
        String idZona = vista.txtZapIdZona.getText();
        String tipoZona = (String) vista.cmbZapTipoZona.getSelectedItem();
        String capacidadStr = vista.txtZapCapacidad.getText();
        String costePorHoraStr = vista.txtZapCostePorHora.getText();

        if (idZona.isEmpty() || tipoZona == null || capacidadStr.isEmpty() || costePorHoraStr.isEmpty()) {
            JOptionPane.showMessageDialog(vista, "Complete todos los campos de Zona de Aparcamiento.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            int capacidad = Integer.parseInt(capacidadStr);
            double costePorHora = Double.parseDouble(costePorHoraStr);

            // Aquí llamarías al método del gestor:
            // gestor.gestionarZonaAparcamiento(idZona, tipoZona, capacidad, costePorHora);
            JOptionPane.showMessageDialog(vista, "Zona de Aparcamiento " + idZona + " guardada/actualizada.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.txtZapIdZona.setText("");
            vista.cmbZapTipoZona.setSelectedIndex(0);
            vista.txtZapCapacidad.setText("");
            vista.txtZapCostePorHora.setText("");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "Capacidad/Coste por Hora debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al gestionar Zona de Aparcamiento: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void asignarVueloAPistaPuerta() {
        String vueloId = (String) vista.cmbAsigVueloId.getSelectedItem();
        String pistaId = (String) vista.cmbAsigPistaId.getSelectedItem();
        String puertaId = (String) vista.cmbAsigPuertaId.getSelectedItem();

        if (vueloId.equals("Seleccionar Vuelo") || pistaId.equals("Seleccionar Pista") || puertaId.equals("Seleccionar Puerta")) {
            JOptionPane.showMessageDialog(vista, "Seleccione Vuelo, Pista y Puerta para la asignación.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Recuperar objetos de vuelo, pista y puerta del gestor
            // Vuelo vuelo = gestor.buscarVueloPorId(vueloId);
            // Pista pista = gestor.buscarPistaPorId(pistaId);
            // PuertaEmbarque puerta = gestor.buscarPuertaPorId(puertaId);

            // if (vuelo == null || pista == null || puerta == null) {
            //    JOptionPane.showMessageDialog(vista, "Vuelo, Pista o Puerta no encontrados.", "Error", JOptionPane.ERROR_MESSAGE);
            //    return;
            // }

            // Aquí se llamaría al método del gestor para realizar la asignación
            // gestor.asignarVuelo(vuelo, pista, puerta);
            JOptionPane.showMessageDialog(vista, "Vuelo " + vueloId + " asignado a Pista " + pistaId + " y Puerta " + puertaId + ".", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            // Limpiar o actualizar combos
            vista.cmbAsigVueloId.setSelectedIndex(0);
            vista.cmbAsigPistaId.setSelectedIndex(0);
            vista.cmbAsigPuertaId.setSelectedIndex(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al asignar vuelo: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarDatosIniciales() {
        // Ejemplo de cómo cargar JComboBoxes (descomenta y adapta cuando tengas los métodos en GestorAeropuerto)
        // vista.cmbOpAerolinea.removeAllItems();
        // vista.cmbOpAerolinea.addItem("Seleccionar Aerolínea");
        // List<Aerolinea> aerolineas = gestor.getAerolineas();
        // for (Aerolinea al : aerolineas) {
        //     vista.cmbOpAerolinea.addItem(al.getNombre());
        // }

        // vista.cmbTerTipoTerminal.setSelectedIndex(0); // Para que se dispare el listener y se configure el estado inicial de campos

        // Cargar vuelos, pistas, puertas para las asignaciones
        // vista.cmbAsigVueloId.removeAllItems();
        // vista.cmbAsigVueloId.addItem("Seleccionar Vuelo");
        // for (Vuelo v : gestor.getVuelos()) { vista.cmbAsigVueloId.addItem(v.getId()); }

        // vista.cmbAsigPistaId.removeAllItems();
        // vista.cmbAsigPistaId.addItem("Seleccionar Pista");
        // for (Pista p : gestor.getPistas()) { vista.cmbAsigPistaId.addItem(p.getId()); }

        // vista.cmbAsigPuertaId.removeAllItems();
        // vista.cmbAsigPuertaId.addItem("Seleccionar Puerta");
        // for (PuertaEmbarque pe : gestor.getPuertasEmbarque()) { vista.cmbAsigPuertaId.addItem(pe.getCodigo()); }
    }
}