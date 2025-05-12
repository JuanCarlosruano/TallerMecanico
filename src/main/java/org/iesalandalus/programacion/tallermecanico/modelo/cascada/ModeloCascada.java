package org.iesalandalus.programacion.tallermecanico.modelo.cascada;

import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ModeloCascada implements Modelo {

    private IClientes clientes;
    private IVehiculos vehiculos;
    private ITrabajos trabajos;

    public ModeloCascada(FabricaFuenteDatos fabricaFuenteDatos){
        Objects.requireNonNull(fabricaFuenteDatos, "La factoría de la fuente de datos no puede ser nula.");
        IFuenteDatos fuenteDatos = fabricaFuenteDatos.crear();
        clientes = fuenteDatos.crearClientes();
        vehiculos = fuenteDatos.crearVehiculos();
        trabajos = fuenteDatos.crearTrabajos();
    }

    @Override
    public void comenzar(){
        System.out.println("Modelo comenzado.");
    }

    @Override
    public void terminar(){
        System.out.println("El modelo ha terminado.");
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion {
        clientes.insertar(new Cliente(cliente));
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        vehiculos.insertar(vehiculo);
    }

    @Override
    public void insertar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        Cliente cliente = clientes.buscar(trabajo.getCliente());
        Vehiculo vehiculo = vehiculos.buscar(trabajo.getVehiculo());
        Trabajo resultado = null;
        if (trabajo instanceof Revision revision){
            resultado = new Revision(cliente, vehiculo, trabajo.getFechaInicio());
        }
        if (trabajo instanceof Mecanico mecanico){
            resultado = new Mecanico(cliente, vehiculo, trabajo.getFechaInicio());
        }
        trabajos.insertar(resultado);
    }

    @Override
    public Cliente buscar(Cliente cliente){
        return (clientes.buscar(cliente) != null ? new Cliente(cliente) : null);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo){
        return (vehiculos.buscar(vehiculo) != null ? vehiculo : null);
    }

    @Override
    public Trabajo buscar(Trabajo trabajo){
        return (trabajos.buscar(trabajo) != null ? Trabajo.copiar(trabajo) : null);
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion {
        return clientes.modificar(cliente, nombre, telefono);
    }

    @Override
    public Trabajo anadirHoras(Trabajo trabajo, int horas) throws TallerMecanicoExcepcion {
        return trabajos.anadirHoras(trabajo, horas);
    }

    @Override
    public Trabajo anadirPrecioMaterial(Trabajo trabajo, float precioMaterial) throws TallerMecanicoExcepcion {
        return trabajos.anadirPrecioMaterial(trabajo, precioMaterial);
    }

    @Override
    public Trabajo cerrar(Trabajo trabajo, LocalDate fechaFin) throws TallerMecanicoExcepcion {
        return trabajos.cerrar(trabajo, fechaFin);
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajosCliente = trabajos.get(cliente);
        for (Trabajo trabajoCliente : trabajosCliente){
            trabajos.borrar(trabajoCliente);
        }
        clientes.borrar(cliente);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        List<Trabajo> trabajosVehiculo = trabajos.get(vehiculo);
        for (Trabajo trabajoVehiculo : trabajosVehiculo){
            trabajos.borrar(trabajoVehiculo);
        }
        vehiculos.borrar(vehiculo);
    }
    @Override
    public void borrar(Trabajo trabajo) throws TallerMecanicoExcepcion {
        trabajos.borrar(trabajo);
    }

    @Override
    public List<Cliente> getClientes(){
        List<Cliente> resultado = new ArrayList<>();
        for (Cliente cliente : clientes.get()){
            resultado.add(new Cliente(cliente));
        }
        return resultado;
    }

    @Override
    public List<Vehiculo> getVehiculos(){
        return new ArrayList<>(vehiculos.get());
    }

    @Override
    public List<Trabajo> getTrabajos(){
        List<Trabajo> resultado = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get()){
            resultado.add(Trabajo.copiar(trabajo));
        }
        return resultado;
    }

    @Override
    public List<Trabajo> getTrabajos(Cliente cliente){
        List<Trabajo> resultado = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(cliente)){
            resultado.add(Trabajo.copiar(trabajo));
        }
        return resultado;
    }

    @Override
    public List<Trabajo> getTrabajos(Vehiculo vehiculo){
        List<Trabajo> resultado = new ArrayList<>();
        for (Trabajo trabajo : trabajos.get(vehiculo)){
            resultado.add(Trabajo.copiar(trabajo));
        }
        return resultado;
    }

    public Map<TipoTrabajo, Integer> getEstadisticasMensuales(LocalDate mes){
        return trabajos.getEstadisticasMensuales(mes);
    }
}
