package controlador;

import vista.VistaControladorGraficos;
import modelo.Estadisticas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class ControladorVistaControladorGraficos {

    private VistaControladorGraficos vista;
    private JFrame vistaAnterior;
    private Estadisticas estadisticas;

    public ControladorVistaControladorGraficos(VistaControladorGraficos vista, JFrame vistaAnterior,
            Estadisticas estadisticas) {
        this.vista = vista;
        this.vistaAnterior = vistaAnterior;
        this.estadisticas = estadisticas;

        this.vista.btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vista.dispose();
                vistaAnterior.setVisible(true);
            }
        });

        cargarEstadisticas();
    }

    private void cargarEstadisticas() {
        mostrarEstadoVuelos();
        mostrarRetrasosPorFranjaHoraria();
        mostrarRecursos();
    }

    private void mostrarEstadoVuelos() {
        long enHora = estadisticas.getVuelosEnHora();
        long retrasados = estadisticas.getVuelosRetrasados();

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("Vuelos en hora: " + enHora + "\n" +
                "Vuelos retrasados: " + retrasados);
        vista.getPanelEstadoVuelos().add(area);
    }

    private void mostrarRetrasosPorFranjaHoraria() {
        Map<String, Long> retrasos = estadisticas.getRetrasosPorFranjaHoraria();

        JTextArea area = new JTextArea();
        area.setEditable(false);
        if (retrasos.isEmpty()) {
            area.setText("No hay retrasos registrados.");
        } else {
            StringBuilder sb = new StringBuilder("Retrasos por franja horaria:\n");
            for (Map.Entry<String, Long> entry : retrasos.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" retrasos\n");
            }
            area.setText(sb.toString());
        }
        vista.getPanelTraficoAereo().add(new JScrollPane(area));
    }

    private void mostrarRecursos() {
        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setText("Uso de recursos:\n" +
                "- Hangares: " + estadisticas.getUsoHangares() + "\n" +
                "- Zonas Aparcamiento: " + estadisticas.getUsoZonasAparcamiento() + "\n" +
                "- Puertas Embarque: " + estadisticas.getUsoPuertasEmbarque() + "\n" +
                "- Fingers: " + estadisticas.getUsoFingers());
        vista.getPanelRetrasosAerolinea().add(area);
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
