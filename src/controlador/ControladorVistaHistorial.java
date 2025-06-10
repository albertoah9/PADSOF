package controlador;

import vista.VistaHistorial;
import modelo.GestorAeropuerto;
import modelo.EventoHistorial; // Asegúrate de tener una clase EventoHistorial en tu modelo

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.SwingUtilities; // Para asegurar que las actualizaciones de la UI se hacen en el EDT

public class ControladorVistaHistorial {

    private VistaHistorial vista;
    private GestorAeropuerto gestor;

    public ControladorVistaHistorial(VistaHistorial vista, GestorAeropuerto gestor) {
        this.vista = vista;
        this.gestor = gestor;

        // Cargar el historial cuando la vista se inicializa
        cargarHistorial();
        
        // Si hay algún botón en el panel para "Refrescar Historial", agregar aquí su ActionListener
        // Por ejemplo:
        // this.vista.btnRefrescar.addActionListener(e -> cargarHistorial());
    }

    /**
     * Carga el historial de eventos desde el GestorAeropuerto y lo muestra en la tabla.
     */
    public void cargarHistorial() {
        SwingUtilities.invokeLater(() -> {
            vista.limpiarTabla(); // Limpiar la tabla antes de cargar nuevos datos
            List<EventoHistorial> historial = gestor.getHistorialEventos(); // Asume que GestorAeropuerto tiene este método

            if (historial != null) {
                for (EventoHistorial evento : historial) {
                    // Adaptar los datos del objeto EventoHistorial a las columnas de la tabla
                    Object[] rowData = {
                        evento.getFechaHora().toString(), // Convertir LocalDateTime a String
                        evento.getTipo(),
                        evento.getDescripcion()
                    };
                    vista.agregarFila(rowData);
                }
            } else {
                // Manejar el caso donde no hay historial o el método devuelve null
                System.out.println("No se pudo cargar el historial de eventos.");
            }
        });
    }

    // Puedes añadir más métodos si necesitas interactividad en el historial, como filtros, etc.
}