package modelo;
import javax.swing.*;

public class PanelControladorSinPermiso {

    public PanelControladorSinPermiso() {
        // Crear un cuadro de mensaje con el mensaje de advertencia
        JFrame frame = new JFrame("Acción No Permitida");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150); // Tamaño de la ventana emergente
        
        // Mostrar el mensaje de advertencia en un JOptionPane
        JOptionPane.showMessageDialog(frame, 
            "Esto no es responsabilidad del controlador\n" +
            "y no tiene ningún permiso de entrar en esta opción.", 
            "Acción No Permitida", 
            JOptionPane.WARNING_MESSAGE);

        // Cerrar la ventana emergente después de que el usuario lo vea
        frame.dispose();
    }
    
  
}
