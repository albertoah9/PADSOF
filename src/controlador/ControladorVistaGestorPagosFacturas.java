package controlador;

import vista.*;
import modelo.GestorAeropuerto;
import modelo.Factura; // Necesitarás una clase Factura en el modelo
import modelo.Pago;    // Necesitarás una clase Pago en el modelo
import modelo.Aerolinea; // Para las aerolíneas

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class ControladorVistaGestorPagosFacturas {

    private VistaGestorPagosFacturas vista;
    private GestorAeropuerto gestor;

    public ControladorVistaGestorPagosFacturas(VistaGestorPagosFacturas vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        // Llenar el JComboBox de aerolíneas al iniciar
        cargarAerolineasEnComboBox();
        // Cargar facturas y pagos existentes al iniciar
        cargarFacturasEnTabla();
        cargarPagosEnLista();


        // ActionListeners para los botones
        this.vista.btnGenerarFactura.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generarNuevaFactura();
            }
        });

        this.vista.btnRegistrarPago.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarPago();
            }
        });

        // Listener para cambio de pestaña si queremos recargar datos
        this.vista.subTabbedPane.addChangeListener(e -> {
            int selectedIndex = vista.subTabbedPane.getSelectedIndex();
            if (selectedIndex == 1) { // Si es la pestaña "Ver Facturas"
                cargarFacturasEnTabla();
            } else if (selectedIndex == 2) { // Si es la pestaña "Registrar Pago"
                cargarPagosEnLista();
            }
        });

        gestor.registrarEvento("CONTROLADOR", "Controlador de VistaGestorPagosFacturas inicializado.");
    }

    private void cargarAerolineasEnComboBox() {
        vista.cmbAerolineaFactura.removeAllItems();
        for (Aerolinea aerolinea : gestor.getAerolineas()) {
            vista.cmbAerolineaFactura.addItem(aerolinea.getNombre());
        }
    }

    private void generarNuevaFactura() {
        String montoStr = vista.txtMontoFactura.getText();
        String aerolineaNombre = (String) vista.cmbAerolineaFactura.getSelectedItem();

        if (montoStr.isEmpty() || aerolineaNombre == null) {
            JOptionPane.showMessageDialog(vista, "Por favor, completa todos los campos para generar la factura.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);
            Aerolinea aerolinea = gestor.getAerolineas().stream()
                                      .filter(a -> a.getNombre().equals(aerolineaNombre))
                                      .findFirst().orElse(null);

            if (aerolinea == null) {
                JOptionPane.showMessageDialog(vista, "Aerolínea no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear y añadir la factura al gestor
            Factura nuevaFactura = new Factura(monto, aerolinea);
            gestor.addFactura(nuevaFactura); // Necesitas este método en GestorAeropuerto

            JOptionPane.showMessageDialog(vista, "Factura generada con éxito: " + nuevaFactura.getId(), "Éxito", JOptionPane.INFORMATION_MESSAGE);
            vista.limpiarCamposGenerarFactura();
            cargarFacturasEnTabla(); // Refrescar la tabla de facturas
            gestor.registrarEvento("FACTURA", "Factura " + nuevaFactura.getId() + " generada para " + aerolinea.getNombre() + " por " + monto + "€.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(vista, "El monto debe ser un número válido.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error al generar factura: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarFacturasEnTabla() {
        DefaultTableModel modelo = vista.getModeloTablaFacturas();
        modelo.setRowCount(0); // Limpiar tabla

        for (Factura factura : gestor.getFacturas()) { // Necesitas getFacturas() en GestorAeropuerto
            modelo.addRow(new Object[]{
                factura.getId(),
                factura.getAerolinea().getNombre(),
                String.format("%.2f€", factura.getMonto()),
                factura.getFechaEmision().toLocalDate().toString(),
                factura.getEstado().toString()
            });
        }
    }

    private void cargarPagosEnLista() {
        vista.getListaPagosModel().clear(); // Limpiar la lista

        // Simulación: podrías mostrar facturas pendientes de pago o pagos registrados
        for (Factura factura : gestor.getFacturas()) {
            if (factura.getEstado() == Factura.EstadoFactura.PENDIENTE_DE_PAGO) {
                vista.getListaPagosModel().addElement("Pagar: Factura " + factura.getId() + " - " + factura.getAerolinea().getNombre() + " - " + String.format("%.2f€", factura.getMonto()));
            }
        }
        // También puedes mostrar pagos ya registrados si tienes una lista de Pagos en el GestorAeropuerto
        for (Pago pago : gestor.getPagos()) { // Necesitas getPagos() en GestorAeropuerto
            vista.getListaPagosModel().addElement("Registrado: " + pago.getId() + " - " + pago.getFacturaId() + " - " + String.format("%.2f€", pago.getMonto()));
        }
    }

    private void registrarPago() {
        int selectedIndex = vista.listaPagos.getSelectedIndex();
        if (selectedIndex == -1) {
            JOptionPane.showMessageDialog(vista, "Selecciona una factura o pago para registrar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String itemSeleccionado = vista.listaPagos.getSelectedValue();
        if (itemSeleccionado.startsWith("Pagar:")) {
            // Extraer el ID de la factura del string
            Integer facturaId = Integer.parseInt(itemSeleccionado.split(" ")[2]);

            Factura factura = gestor.buscarFacturaPorId(facturaId); // Necesitas este método en GestorAeropuerto

            if (factura != null && factura.getEstado() == Factura.EstadoFactura.PENDIENTE_DE_PAGO) {
                // Crear y añadir el pago al gestor
                Pago nuevoPago = new Pago(factura.getId(), factura.getMonto(), LocalDateTime.now());
                gestor.addPago(nuevoPago); // Necesitas este método en GestorAeropuerto
                factura.marcarComoPagado(); // Marcar factura como pagada

                JOptionPane.showMessageDialog(vista, "Pago de la factura " + factura.getId() + " registrado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                gestor.registrarEvento("PAGO", "Pago de la factura " + factura.getId() + " registrado.");
                cargarFacturasEnTabla(); // Refrescar tablas
                cargarPagosEnLista();
            } else {
                JOptionPane.showMessageDialog(vista, "Factura no encontrada o ya pagada.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
             JOptionPane.showMessageDialog(vista, "Esta funcionalidad solo permite registrar pagos de facturas pendientes.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}