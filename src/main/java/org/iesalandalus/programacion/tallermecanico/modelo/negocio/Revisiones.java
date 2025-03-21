package org.iesalandalus.programacion.tallermecanico.modelo.negocio;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Revision;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Revisiones {
    private List<Revision> coleccionRevisiones;
    public Revisiones (){
        coleccionRevisiones = new ArrayList<>();
    }
    public List<Revision> get(){
        return coleccionRevisiones;

    }
    public List<Revision> get(Cliente cliente) {
        List<Revision> aux = new ArrayList<>();
        for (Revision r : coleccionRevisiones){
            if (r.getCliente().equals(cliente)){
                aux.add(r);
            }
        }
        return aux;
    }

    public List<Revision> get(Vehiculo vehiculo) {
        List<Revision> aux = new ArrayList<>();
        for (Revision r : coleccionRevisiones){
            if (r.getVehiculo().equals(vehiculo)){
                aux.add(r);
            }
        }
        return aux;
    }
    public void insertar(Revision revision) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No se puede insertar una revisión nula.");
        comprobarRevision(revision.getCliente(), revision.getVehiculo(), revision.getFechaInicio());
        this.coleccionRevisiones.add(revision);
    }
    private void comprobarRevision(Cliente cliente, Vehiculo vehiculo, LocalDate fechaRevision) throws TallerMecanicoExcepcion{
       for (Revision revision : coleccionRevisiones){
           if (!revision.estaCerrada()) {
               if (revision.getCliente().equals(cliente)) {
                   throw new TallerMecanicoExcepcion("El cliente tiene otra revisión en curso.");
               } else if (revision.getVehiculo().equals(vehiculo)) {
                   throw new TallerMecanicoExcepcion("El vehículo está actualmente en revisión.");

               }
           } else  {
               if (revision.getCliente().equals(cliente) && !fechaRevision.isAfter(revision.getFechaFin())){
                   throw new TallerMecanicoExcepcion("El cliente tiene una revisión posterior.");
               } else if (revision.getVehiculo().equals(vehiculo) && !fechaRevision.isAfter(revision.getFechaFin())){
                   throw new TallerMecanicoExcepcion("El vehículo tiene una revisión posterior.");
               }
           }
       }


    }
    public Revision anadirHoras(Revision revision, int horas) throws TallerMecanicoExcepcion {
        getRevision(revision).anadirHoras(horas);
        return getRevision(revision);

    }
    private Revision getRevision (Revision revision) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(revision, "No puedo operar sobre una revisión nula.");
        for (Revision revision1 : coleccionRevisiones){
            if (revision1.equals(revision)){
                return revision1;
            }
        }
        throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
    }
    public Revision anadirPrecioMaterial(Revision revision, float precioMaterial) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"La revision no puede ser nula");
        getRevision(revision).anadirPrecioMaterial(precioMaterial);
        return getRevision(revision);

    }
    public Revision cerrar (Revision revision, LocalDate fechaFin) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(revision,"No puedo operar sobre una revisión nula.");
        getRevision(revision).cerrar(fechaFin);
        return getRevision(revision);
    }
    public Revision buscar (Revision revision){
        Objects.requireNonNull(revision,"No se puede buscar una revisión nula.");
        Revision revision1 = null;
        if (coleccionRevisiones.contains(revision)){
          revision1 = revision;

        }
        return revision1;
    }
    public void borrar (Revision revision) throws TallerMecanicoExcepcion{
            Objects.requireNonNull(revision, "No se puede borrar una revisión nula.");
            if (!coleccionRevisiones.contains(revision)) {
                throw new TallerMecanicoExcepcion("No existe ninguna revisión igual.");
            }
            coleccionRevisiones.remove(revision);
        }
}





