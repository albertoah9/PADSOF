package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import modelo.Aeropuerto;
import modelo.Descuento;
import vista.VistaFormularioDescuento;
import vista.VistaGestorDescuentos;
import vista.VistaGestorFacturacion;

public class ControladorVistaGestorDescuentos {

    private VistaGestorDescuentos vista;
    private Aeropuerto aeropuerto;
    private VistaGestorFacturacion vistaAnterior;

    public ControladorVistaGestorDescuentos(VistaGestorDescuentos vista, Aeropuerto aeropuerto, VistaGestorFacturacion vistaAnterior) {
        this.vista = vista;
        this.aeropuerto = aeropuerto;
        this.vistaAnterior = vistaAnterior;

        cargarLista();

        this.vista.btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VistaFormularioDescuento formulario = new VistaFormularioDescuento(vista);
                formulario.btnGuardar.addActionListener(ev -> {
                    try {
                        int porcentaje;
                        try {
                            porcentaje = formulario.getPorcentaje();
                            if (porcentaje < 1 || porcentaje > 50) {
                                JOptionPane.showMessageDialog(formulario, "El porcentaje debe estar entre 1 y 50.", "Porcentaje inválido", JOptionPane.WARNING_MESSAGE);
                                return;
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(formulario, "El porcentaje debe ser un número entero válido.", "Error de porcentaje", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        if (porcentaje < 1 || porcentaje > 50) {
                            JOptionPane.showMessageDialog(
                                formulario,
                                "El porcentaje debe estar entre 1 y 50.",
                                "Porcentaje inválido",
                                JOptionPane.WARNING_MESSAGE
                            );
                            return;
                        }

                        LocalDate inicio = formulario.getFechaInicio();
                        LocalDate fin = formulario.getFechaFin();

                        if (fin.isBefore(inicio)) {
                            JOptionPane.showMessageDialog(
                                formulario,
                                "La fecha de fin no puede ser anterior a la de inicio.",
                                "Fechas inválidas",
                                JOptionPane.WARNING_MESSAGE
                            );
                            return;
                        }

                        Descuento nuevo = new Descuento(
                            formulario.getDescripcion(),
                            inicio,
                            fin,
                            porcentaje,
                            formulario.getCondicion(),
                            formulario.getValorCondicion()
                        );

                        aeropuerto.addDescuento(nuevo);
                        formulario.dispose();
                        cargarLista();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                            formulario,
                            "Error en los datos introducidos: " + ex.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                });
                formulario.btnCancelar.addActionListener(ev -> formulario.dispose());
                formulario.setVisible(true);
            }
        });

        this.vista.btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Descuento seleccionado = vista.getSeleccionado();
                if (seleccionado != null) {
                    try {
                        var fieldInicio = Descuento.class.getDeclaredField("inicio");
                        var fieldFin = Descuento.class.getDeclaredField("fin");
                        fieldInicio.setAccessible(true);
                        fieldFin.setAccessible(true);

                        LocalDate fechaInicioActual = (LocalDate) fieldInicio.get(seleccionado);
                        LocalDate fechaFinActual = (LocalDate) fieldFin.get(seleccionado);

                        String nuevaInicioStr = JOptionPane.showInputDialog(vista, "Nueva fecha de inicio (YYYY-MM-DD):", fechaInicioActual.toString());
                        String nuevaFinStr = JOptionPane.showInputDialog(vista, "Nueva fecha de fin (YYYY-MM-DD):", fechaFinActual.toString());

                        if (nuevaInicioStr == null || nuevaFinStr == null) return;

                        LocalDate nuevaInicio = LocalDate.parse(nuevaInicioStr);
                        LocalDate nuevaFin = LocalDate.parse(nuevaFinStr);

                        if (nuevaFin.isBefore(nuevaInicio)) {
                            JOptionPane.showMessageDialog(vista, "La fecha de fin no puede ser anterior a la fecha de inicio.", "Fechas inválidas", JOptionPane.WARNING_MESSAGE);
                            return;
                        }

                        fieldInicio.set(seleccionado, nuevaInicio);
                        fieldFin.set(seleccionado, nuevaFin);

                        cargarLista();
                        JOptionPane.showMessageDialog(vista, "Fechas actualizadas correctamente.");

                    } catch (java.time.format.DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(vista, "Formato de fecha inválido. Usa el formato YYYY-MM-DD.", "Error de formato", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(vista, "Error al actualizar fechas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(vista, "Selecciona un descuento primero.");
                }
            }
        });

        this.vista.btnVolver.addActionListener(e -> {
            vista.dispose();
            vistaAnterior.setVisible(true);
        });
    }

    private void cargarLista() {
        List<Descuento> descuentos = aeropuerto.getDescuentos();
        vista.limpiarLista();
        for (Descuento d : descuentos) {
            vista.agregarDescuento(d);
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}