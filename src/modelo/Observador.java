package modelo;

import modelo.Notificacion;

public interface Observador {
    void recibirNotificacion(Notificacion notificacion);
}
