package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.*;

public class GestorEventos {

    private Map<Evento, List<ReceptorEventos>> receptores;

    public GestorEventos(Evento... eventos){
        receptores = new HashMap<>();
        for (Evento evento : eventos){
            receptores.put(evento, new ArrayList<>());
        }
    }

    public void suscribir(ReceptorEventos receptor, Evento... eventos){
        Objects.requireNonNull(receptor, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos, "El evento no puede ser nulo.");
        for (Evento evento : eventos){
            receptores.get(evento).add(receptor);
        }
    }

    public void desuscribir(ReceptorEventos receptor, Evento... eventos){
        Objects.requireNonNull(receptor, "El receptor de eventos no puede ser nulo.");
        Objects.requireNonNull(eventos, "El evento no puede ser nulo.");
        for (Evento evento : eventos){
            receptores.get(evento).remove(receptor);
        }
    }

    public void notificar(Evento evento){
        Objects.requireNonNull(evento, "El evento no puede ser nulo");
        List<ReceptorEventos> lista = receptores.get(evento);
        if (lista != null){
            for (ReceptorEventos receptor : lista){
                receptor.actualizar(evento);
            }
        }
    }
}
