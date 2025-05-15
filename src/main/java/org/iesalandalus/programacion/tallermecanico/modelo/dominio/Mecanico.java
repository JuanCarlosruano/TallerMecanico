package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;

import java.time.LocalDate;

public class Mecanico extends Trabajo {
    private static final float FACTOR_HORA=30;
    private static final float FACTOR_PRECIO_MATERIAL = 1.5F;
    private float precioMaterial;
    public Mecanico(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio) {
        super(cliente, vehiculo, fechaInicio);
        precioMaterial = 0;
    }

    public Mecanico(Mecanico mecanico) {
        super(mecanico);
        precioMaterial = mecanico.getPrecioMaterial();
    }

    public float getPrecioMaterial() {
        return precioMaterial;
    }
    public void anadirPrecioMaterial(float precioMaterial) throws TallerMecanicoExcepcion {
        if (estaCerrado()) {
            throw new TallerMecanicoExcepcion("No se puede añadir precio del material, ya que el trabajo mecánico está cerrado.");
        }
        if (precioMaterial <= 0) {
            throw new IllegalArgumentException("El precio del material a añadir debe ser mayor que cero.");
        }
        this.precioMaterial += precioMaterial;
    }



    @Override
    public float getPrecioEspecifico() {
        return ((getHoras() * FACTOR_HORA) + (getPrecioMaterial() * FACTOR_PRECIO_MATERIAL));
    }

    @Override
    public String toString() {
        String resultado = String.format("Mecánico -> %s - %s (%s - %s): %s horas, %.2f € en material", getCliente().toString(), getVehiculo().toString(), getFechaInicio(), (estaCerrado() ? getFechaFin() : ""), getHoras(), precioMaterial);
        if (estaCerrado()) {
            resultado += String.format(", %.2f € total", getPrecio()).replace(".", ",");
        }
        return resultado;
    }


}
