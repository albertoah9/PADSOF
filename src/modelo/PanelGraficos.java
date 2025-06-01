package modelo;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder; // Para añadir margen

// Asumiendo que GestorAeropuerto es visible
// import tu_paquete.GestorAeropuerto;

public class PanelGraficos extends JPanel {

    private GestorAeropuerto gestor; // Mantenemos la referencia por si es necesaria más adelante

    public PanelGraficos(GestorAeropuerto gestor) {
        this.gestor = gestor;

        setLayout(new BorderLayout()); // Layout principal: gráficos arriba, botón abajo
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Añade un margen alrededor del panel

        // --- Panel para contener los gráficos ---
        // Usamos GridLayout: 2 filas, 3 columnas, con espacio de 10px entre componentes
        JPanel graphsPanel = new JPanel(new GridLayout(2, 3, 15, 15)); // Aumentado el espacio un poco
        graphsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0)); // Espacio entre los gráficos y el botón de abajo

        // --- Marcadores de posición para cada gráfico ---
        // Creamos paneles simples para representar cada gráfico
        graphsPanel.add(createGraphPlaceholder("Gráfico Circular (Pie Chart)"));
        graphsPanel.add(createGraphPlaceholder("Gráfico de Área (Area Chart)"));
        graphsPanel.add(createGraphPlaceholder("Gráfico de Embudo (Funnel Chart)"));
        graphsPanel.add(createGraphPlaceholder("Gráfico de Líneas (Line Chart)"));
        graphsPanel.add(createGraphPlaceholder("Gráfico de Barras (Bar Chart)"));
        graphsPanel.add(createGraphPlaceholder("Otro Gráfico (Ejemplo)")); // Añadimos un sexto marcador

        // --- Botón "Más detalles" ---
        JButton btnMasDetalles = new JButton("Más detalles");
        // Centrar el botón en la parte inferior
        JPanel panelBotonDetalles = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Alinear a la derecha
        panelBotonDetalles.add(btnMasDetalles);

        // --- Añadir los paneles al panel principal ---
        add(graphsPanel, BorderLayout.CENTER); // Los gráficos en el centro
        add(panelBotonDetalles, BorderLayout.SOUTH); // El botón abajo

        // Opcional: Añadir un ActionListener al botón
        btnMasDetalles.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Aquí se mostrarían más detalles o un panel con opciones.", "Más Detalles", JOptionPane.INFORMATION_MESSAGE);
            // Implementar lógica para mostrar detalles adicionales
        });
    }

    // Método auxiliar para crear un marcador de posición de gráfico
    private JPanel createGraphPlaceholder(String title) {
        JPanel placeholder = new JPanel(new BorderLayout()); // Layout para el marcador
        placeholder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1)); // Borde simple para simular un gráfico
        // placeholder.setBackground(Color.WHITE); // Fondo blanco

        JLabel label = new JLabel(title);
        label.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto
        label.setVerticalAlignment(SwingConstants.CENTER); // Centra el texto verticalmente
        label.setFont(new Font("SansSerif", Font.PLAIN, 12));
        label.setForeground(Color.DARK_GRAY);

        placeholder.add(label, BorderLayout.CENTER);

        // Podrías sobrescribir paintComponent aquí para dibujar formas simples
        // Esto es más avanzado y no es necesario para un marcador simple
        /*
        placeholder = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Ejemplo: dibujar un rectángulo simple
                g.setColor(Color.GREEN);
                g.fillRect(10, 10, getWidth() - 20, getHeight() - 20);
            }
        };
        placeholder.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        placeholder.add(label, BorderLayout.NORTH); // Poner el título arriba
        */

        return placeholder;
    }

}