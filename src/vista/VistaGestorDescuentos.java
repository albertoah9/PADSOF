package vista;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import modelo.Descuento;

public class VistaGestorDescuentos extends JFrame {

    public DefaultListModel<Descuento> modeloLista;
    public JList<Descuento> listaDescuentos;
    public JButton btnNuevo;
    public JButton btnEditar;
    public JButton btnVolver;

    public VistaGestorDescuentos(List<Descuento> descuentos) {
        setTitle("Gestión de Descuentos");
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Descuentos disponibles", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        add(lblTitulo, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaDescuentos = new JList<>(modeloLista);
        JScrollPane scroll = new JScrollPane(listaDescuentos);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        btnNuevo = new JButton("Nuevo descuento");
        btnEditar = new JButton("Editar validez");
        btnVolver = new JButton("Volver");
        panelBotones.add(btnNuevo);
        panelBotones.add(btnEditar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
    }

    public void agregarDescuento(Descuento d) {
        modeloLista.addElement(d);
    }

    public void limpiarLista() {
        modeloLista.clear();
    }

    public Descuento getSeleccionado() {
        return listaDescuentos.getSelectedValue();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
}