package vista;

import modelo.Descuento;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class VistaFormularioDescuento extends JDialog {

    public JTextField campoDescripcion;
    public JTextField campoFechaInicio;
    public JTextField campoFechaFin;
    public JSpinner spinnerPorcentaje;
    public JComboBox<Descuento.CondicionAplicacion> comboCondicion;
    public JTextField campoValorCondicion;
    public JButton btnGuardar;
    public JButton btnCancelar;

    public VistaFormularioDescuento(JFrame parent) {
        super(parent, "Nuevo Descuento", true);
        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(7, 2, 10, 10));

        campoDescripcion = new JTextField();
        campoFechaInicio = new JTextField("2025-01-01");
        campoFechaFin = new JTextField("2025-12-31");

        spinnerPorcentaje = new JSpinner(new SpinnerNumberModel(10, 1, 50, 1));

        comboCondicion = new JComboBox<>(Descuento.CondicionAplicacion.values());
        campoValorCondicion = new JTextField();

        btnGuardar = new JButton("Guardar");
        btnCancelar = new JButton("Cancelar");

        add(new JLabel("Descripción:"));
        add(campoDescripcion);

        add(new JLabel("Fecha Inicio (YYYY-MM-DD):"));
        add(campoFechaInicio);

        add(new JLabel("Fecha Fin (YYYY-MM-DD):"));
        add(campoFechaFin);

        add(new JLabel("Porcentaje (%):"));
        add(spinnerPorcentaje);

        add(new JLabel("Condición de Aplicación:"));
        add(comboCondicion);

        add(new JLabel("Valor Umbral:"));
        add(campoValorCondicion);

        add(btnGuardar);
        add(btnCancelar);
    }

    public String getDescripcion() {
        return campoDescripcion.getText().trim();
    }

    public LocalDate getFechaInicio() {
        return LocalDate.parse(campoFechaInicio.getText().trim());
    }

    public LocalDate getFechaFin() {
        return LocalDate.parse(campoFechaFin.getText().trim());
    }

    public int getPorcentaje() {
        return (int) spinnerPorcentaje.getValue();
    }

    public Descuento.CondicionAplicacion getCondicion() {
        return (Descuento.CondicionAplicacion) comboCondicion.getSelectedItem();
    }

    public double getValorCondicion() {
        return Double.parseDouble(campoValorCondicion.getText().trim());
    }
}