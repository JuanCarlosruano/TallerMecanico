package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import javax.management.ObjectName;
import java.util.*;

public class GestorEventos {
    Map<Evento, List<ReceptorEventos>> receptores = new EnumMap<>(Evento.class);
    public GestorEventos(Evento... eventos){
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            receptores.put(evento, new ArrayList<>());
        }
    }
    public void suscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> usuarios = receptores.get(evento);
            usuarios.add(receptor);
        }
    }
    public void desuscribir(ReceptorEventos receptor, Evento... eventos) {
        Objects.requireNonNull(receptor, "El receptor no puede ser nulo.");
        Objects.requireNonNull(eventos, "Los eventos no pueden ser nulos.");
        for (Evento evento : eventos) {
            List<ReceptorEventos> usuarios = receptores.get(evento);
            usuarios.remove(receptor);
        }
    }
    public void notificar(Evento evento) {
        Objects.requireNonNull(evento,"El evento no puede ser nulo.");
        List<ReceptorEventos> lista = receptores.get(evento);
        for (ReceptorEventos receptor : lista) {
            receptor.actualizar(evento);
        }
    }
}
