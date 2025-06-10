package vista;

import java.awt.*;
import javax.swing.*;

public class VistaControladorDisponibilidad extends JFrame {
    public JLabel lblTitulo;
    public JComboBox<String> comboTipoElemento;
    public JComboBox<String> comboEstado;
    public JTextField txtCostoHora;
    public JTextField txtId;
    public JButton btnFiltrar;
    public JTextArea areaTexto;

    public VistaControladorDisponibilidad() {
        setTitle("Disponibilidad de elementos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        // Título
        lblTitulo = new JLabel("Gestión de las disponibilidades de los elementos del aeropuerto",
                SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        panel.add(lblTitulo);

        // Panel de filtros
        JPanel panelControles = new JPanel();
        panelControles.setLayout(new GridBagLayout());
        panelControles.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fila 1: Tipo + ID
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.2;
        panelControles.add(new JLabel("Tipo de elemento a buscar:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        comboTipoElemento = new JComboBox<>(
                new String[] { "", "PuertaEmbarque", "Finger", "ZonaAparcamiento", "Pista" });
        comboTipoElemento.setPreferredSize(new Dimension(200, 30));
        panelControles.add(comboTipoElemento, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.2;
        panelControles.add(new JLabel("ID:"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.8;
        txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(200, 30));
        panelControles.add(txtId, gbc);

        // Fila 2: Estado + Costo
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.2;
        panelControles.add(new JLabel("Estado:"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.8;
        comboEstado = new JComboBox<>(new String[] { "", "Disponible", "No disponible" });
        comboEstado.setPreferredSize(new Dimension(200, 30));
        panelControles.add(comboEstado, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.2;
        panelControles.add(new JLabel("Costo por hora (si aplica):"), gbc);

        gbc.gridx = 3;
        gbc.weightx = 0.8;
        txtCostoHora = new JTextField();
        txtCostoHora.setPreferredSize(new Dimension(200, 30));
        panelControles.add(txtCostoHora, gbc);

        panel.add(panelControles);

        // Botón Filtrar
        btnFiltrar = new JButton("Filtrar");
        btnFiltrar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnFiltrar.setPreferredSize(new Dimension(120, 35));
        btnFiltrar.setMaximumSize(new Dimension(120, 35));
        btnFiltrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnFiltrar);

        // Área de texto resultados
        areaTexto = new JTextArea();
        areaTexto.setLineWrap(false);
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Arial", Font.PLAIN, 13));
        areaTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(areaTexto,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setPreferredSize(new Dimension(Integer.MAX_VALUE, 200));

        panel.add(Box.createVerticalStrut(20));
        panel.add(scrollPane);

        add(panel, BorderLayout.CENTER);
    }

    public void mostrarElementos(String texto) {
        areaTexto.setText(texto);
    }
}
