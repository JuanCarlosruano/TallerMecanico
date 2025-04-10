package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.time.LocalDate;

public class Revision extends Trabajo {
    private static final float FACTOR_HORA = 35;

    public Revision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);

    }
    public Revision(Revision revision) {
        super(revision);

    }

    public float getPrecioEspecifico() {
        return getHoras() * FACTOR_HORA;
    }

    @Override
    public String toString() {
        String resultado = String.format("Revisión -> %s - %s (%s - %s): %s horas", getCliente().toString(), getVehiculo().toString(), getFechaInicio(), (estaCerrado() ? getFechaFin() : ""), getHoras(), getPrecioEspecifico());
        if (estaCerrado()) {
            resultado += String.format(", %.2f € total", getPrecio()).replace(".", ",");
        }
        return resultado;
    }
}
