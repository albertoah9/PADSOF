package controlador;

import modelo.*;
import vista.VistaControladorDisponibilidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ControladorVistaControladorDisponibilidad {

    private VistaControladorDisponibilidad vista;

    // Supongo que tienes listas globales o las recibes de alguna forma
    private List<ElementoAeropuerto> elementos;
    private ArrayList<UsoElementoAeropuerto> usos;

    public ControladorVistaControladorDisponibilidad(VistaControladorDisponibilidad vista,
            List<ElementoAeropuerto> elementos,
            ArrayList<UsoElementoAeropuerto> usos) {
        this.vista = vista;
        this.elementos = elementos;
        this.usos = usos;

        inicializarComboTipos();
        agregarEventos();
    }

    private void inicializarComboTipos() {
        // Según tus clases, pon aquí las opciones reales
        vista.comboTipoElemento.removeAllItems();
        vista.comboTipoElemento.addItem(""); // opción vacío para todos
        vista.comboTipoElemento.addItem("PuertaEmbarque");
        vista.comboTipoElemento.addItem("PistaDespegue");
        vista.comboTipoElemento.addItem("PistaAterrizaje");
        vista.comboTipoElemento.addItem("ZonaAparcamiento");
        vista.comboTipoElemento.addItem("Finger");
    }

    private void agregarEventos() {
        vista.btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarElementos();
            }
        });
    }

    private void filtrarElementos() {
        String tipoSeleccionado = (String) vista.comboTipoElemento.getSelectedItem();
        String estadoSeleccionado = (String) vista.comboEstado.getSelectedItem();
        String idTexto = vista.txtId.getText().trim();

        LocalDateTime ahora = LocalDateTime.now();

        StringBuilder resultado = new StringBuilder();

        for (ElementoAeropuerto elemento : elementos) {

            // Filtrar por tipo
            if (tipoSeleccionado != null && !tipoSeleccionado.isEmpty()) {
                String claseElemento = elemento.getClass().getSimpleName();
                if (!claseElemento.equals(tipoSeleccionado)) {
                    continue;
                }
            }

            // Filtrar por ID
            if (!idTexto.isEmpty()) {
                try {
                    int id = Integer.parseInt(idTexto);
                    if (elemento.getId() != id) {
                        continue;
                    }
                } catch (NumberFormatException ex) {
                    vista.mostrarElementos("ID inválido, debe ser un número entero.");
                    return;
                }
            }

            // Filtrar por estado
            boolean ocupado = elemento.isOcupado(ahora, usos);
            if (estadoSeleccionado != null && !estadoSeleccionado.isEmpty()) {
                boolean coincideEstado = false;
                if (estadoSeleccionado.equalsIgnoreCase("Disponible")) {
                    coincideEstado = !ocupado;
                } else if (estadoSeleccionado.equalsIgnoreCase("No disponible")) {
                    coincideEstado = ocupado;
                }
                if (!coincideEstado) {
                    continue;
                }
            }

            // Mostrar info del elemento
            resultado.append(elemento.toString()).append("\n---------------------\n");
        }

        if (resultado.length() == 0) {
            vista.mostrarElementos("No se encontraron elementos con los criterios indicados.");
        } else {
            vista.mostrarElementos(resultado.toString());
        }
    }

    public void iniciar() {
        vista.setVisible(true);
    }
}
