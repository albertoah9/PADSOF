import javax.swing.*;
import java.awt.*;

public class VentanaControladorGraficos extends JPanel {
    public VentanaControladorGraficos() {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Gráficos");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(titulo, BorderLayout.NORTH);

        // Crear un panel para contener los 4 cuadrados.
        JPanel panelCuadrados = new JPanel();
        panelCuadrados.setLayout(new GridLayout(2, 2, 10, 10)); // Cuadrados en una cuadrícula 2x2

        JPanel cuadrado1 = crearCuadrado("Grafico1");
        JPanel cuadrado2 = crearCuadrado("Grafico2");
        JPanel cuadrado3 = crearCuadrado("Grafico3");
        JPanel cuadrado4 = crearCuadrado("Grafico4");

        // Añadir los cuadrados al panel
        panelCuadrados.add(cuadrado1);
        panelCuadrados.add(cuadrado2);
        panelCuadrados.add(cuadrado3);
        panelCuadrados.add(cuadrado4);

        // Añadir el panel de cuadrados al centro del layout
        add(panelCuadrados, BorderLayout.CENTER);
        
    }

        // Método para crear un cuadrado con un título
        private JPanel crearCuadrado(String titulo) {
            JPanel cuadrado = new JPanel();
            cuadrado.setBackground(Color.WHITE);
            cuadrado.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Bordes de los cuadrados
    
            JLabel etiqueta = new JLabel(titulo, SwingConstants.CENTER);
            etiqueta.setFont(new Font("SansSerif", Font.BOLD, 14));
    
            cuadrado.setLayout(new BorderLayout());
            cuadrado.add(etiqueta, BorderLayout.CENTER);
    
            return cuadrado;
        }
}