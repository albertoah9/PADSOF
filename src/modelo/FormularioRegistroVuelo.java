package modelo;
import java.awt.*;
import java.time.LocalDateTime;
import javax.swing.*;

public class FormularioRegistroVuelo extends JPanel {

    public FormularioRegistroVuelo(Aeropuerto aeropuerto, OperadorAereo operador) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        Aerolinea aerolinea = operador.getAerolineaAsignada();

        add(new JLabel("Registrar nuevo vuelo")).setFont(new Font("SansSerif", Font.BOLD, 16));
        add(Box.createRigidArea(new Dimension(0, 10)));

        // Campos
        JTextField campoOrigen = new JTextField(20);
        JTextField campoDestino = new JTextField(20);
        JTextField campoLlegada = new JTextField("yyyy-MM-ddTHH:mm", 20);
        JTextField campoSalida = new JTextField("yyyy-MM-ddTHH:mm", 20);

        JComboBox<Terminal> comboTerminal = new JComboBox<>(aeropuerto.getTerminales().toArray(new Terminal[0]));
        JComboBox<Avion> comboAvion = new JComboBox<>(aerolinea.getFlota().toArray(new Avion[0]));
        JComboBox<Pista> comboPista = new JComboBox<>(aeropuerto.getPistas().toArray(new Pista[0]));
        JComboBox<PuertaEmbarque> comboPuerta = new JComboBox<>(aeropuerto.getPuertasEmbarque().toArray(new PuertaEmbarque[0]));
        JComboBox<Vuelo.EstadoVuelo> comboEstado = new JComboBox<>(Vuelo.EstadoVuelo.values());
        JComboBox<Vuelo.TipoVuelo> comboTipo = new JComboBox<>(Vuelo.TipoVuelo.values());
        JComboBox<Vuelo.ClaseVuelo> comboClase = new JComboBox<>(Vuelo.ClaseVuelo.values());

        JButton btnRegistrar = new JButton("Registrar Vuelo");

        // Añadir campos al formulario
        add(new JLabel("Origen:")); add(campoOrigen);
        add(new JLabel("Destino:")); add(campoDestino);
        add(new JLabel("Fecha y hora llegada:")); add(campoLlegada);
        add(new JLabel("Fecha y hora salida:")); add(campoSalida);
        add(new JLabel("Terminal:")); add(comboTerminal);
        add(new JLabel("Avión:")); add(comboAvion);
        add(new JLabel("Pista:")); add(comboPista);
        add(new JLabel("Puerta de embarque:")); add(comboPuerta);
        add(new JLabel("Estado:")); add(comboEstado);
        add(new JLabel("Tipo de vuelo:")); add(comboTipo);
        add(new JLabel("Clase de vuelo:")); add(comboClase);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(btnRegistrar);

        // Acción del botón
        btnRegistrar.addActionListener(e -> {
            try {
                String origen = campoOrigen.getText().trim();
                String destino = campoDestino.getText().trim();
                LocalDateTime llegada = LocalDateTime.parse(campoLlegada.getText().trim());
                LocalDateTime salida = LocalDateTime.parse(campoSalida.getText().trim());

                Terminal terminal = (Terminal) comboTerminal.getSelectedItem();
                Avion avion = (Avion) comboAvion.getSelectedItem();
                Pista pista = (Pista) comboPista.getSelectedItem();
                PuertaEmbarque puerta = (PuertaEmbarque) comboPuerta.getSelectedItem();
                Vuelo.EstadoVuelo estado = (Vuelo.EstadoVuelo) comboEstado.getSelectedItem();
                Vuelo.TipoVuelo tipo = (Vuelo.TipoVuelo) comboTipo.getSelectedItem();
                Vuelo.ClaseVuelo clase = (Vuelo.ClaseVuelo) comboClase.getSelectedItem();

                Vuelo vuelo = new Vuelo(origen, destino, llegada, salida, terminal, avion, pista, puerta, estado, aeropuerto, tipo, clase, aerolinea);

                aeropuerto.addVuelo(vuelo);

                JOptionPane.showMessageDialog(this, "Vuelo registrado exitosamente");

                // Limpiar campos
                campoOrigen.setText("");
                campoDestino.setText("");
                campoLlegada.setText("");
                campoSalida.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en los datos: " + ex.getMessage());
            }
        });
    }
}