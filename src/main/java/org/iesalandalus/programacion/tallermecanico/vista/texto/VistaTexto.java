package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento.*;

public class VistaTexto implements Vista {

    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            ejecutar(evento);
        }while (evento != SALIR);
    }

    @Override
    public void terminar(){
        System.out.println("Se va a cerrar el programa");
    }

    private void ejecutar(Evento evento) {
        Consola.mostrarCabecera(evento.toString());
        getGestorEventos().notificar(evento);
    }

    @Override
    public GestorEventos getGestorEventos(){
        return gestorEventos;
    }

    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito){
        if (exito){
            System.out.println(texto);
        } else {
            System.out.printf("ERROR: %s", texto);
        }
    }

    @Override
    public Cliente leerCliente() {
        return new Cliente(leerNuevoNombre(), Consola.leerCadena("Introduce le DNI del cliente: "), leerNuevoTelefono());
    }

    @Override
    public Cliente leerClienteDni() {
        return Cliente.get(Consola.leerCadena("Introduce el dni del cliente: "));
    }

    @Override
    public String leerNuevoNombre() {
        return Consola.leerCadena("Introduce el nombre: ");
    }

    @Override
    public String leerNuevoTelefono() {
        return Consola.leerCadena("Introduce el teléfono: ");
    }

    @Override
    public Vehiculo leerVehiculo() {
        return new Vehiculo(Consola.leerCadena("Introduce la marca del vehículo: "), Consola.leerCadena("Introduce el modelo del vehículo: "), Consola.leerCadena("Introduce la matrícula del vehículo: "));
    }

    @Override
    public Vehiculo leerVehiculoMatricula() {
        String matricula;
        System.out.print("Introduce la matrícula del vehículo: ");
        matricula = Entrada.cadena();
        return new Vehiculo("Seat", "Panda", matricula);
    }

    @Override
    public Trabajo leerRevision() {
        return new Revision(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha inicio:"));
    }

    @Override
    public Trabajo leerMecanico(){
        return new Mecanico(leerClienteDni(), leerVehiculoMatricula(), Consola.leerFecha("Introduce la fecha inicio: "));
    }

    @Override
    public Trabajo leerTrabajoVehiculo(){
        return Trabajo.get(leerVehiculoMatricula());
    }

    @Override
    public int leerHoras() {
        return Consola.leerEntero("Introduce la cantidad de horas: ");
    }

    @Override
    public float leerPrecioMaterial() {
        return Consola.leerReal("Introduce el precio del material: ");
    }

    @Override
    public LocalDate leerFechaCierre() {
        return Consola.leerFecha("Introduce la fecha de cierre: ");
    }

    @Override
    public LocalDate leerMes() {
        return Consola.leerFecha("Introduce la fecha para las estadísticas");
    }

    @Override
    public void mostrarCliente(Cliente cliente){
        System.out.println(cliente);
    }

    @Override
    public void mostrarVehiculo(Vehiculo vehiculo){
        System.out.println(vehiculo);
    }

    @Override
    public void mostrarTrabajo(Trabajo trabajo){
        System.out.println(trabajo);
    }

    @Override
    public void mostrarClientes(List<Cliente> clientes){
        if (!clientes.isEmpty()){
            clientes.sort(Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni));
            for (Cliente cliente : clientes){
                System.out.println(cliente);
            }
        }else {
            System.out.println("No hay clientes que mostrar.");
        }

    }

    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos){
        if (!vehiculos.isEmpty()){
            vehiculos.sort(Comparator.comparing(Vehiculo::marca).thenComparing(Vehiculo::matricula));
            for (Vehiculo vehiculo : vehiculos){
                System.out.println(vehiculo);
            }
        } else {
            System.out.println("No hay vehículos para mostrar.");
        }

    }

    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos){
        if (!trabajos.isEmpty()){
            Comparator<Cliente> comparadorCliente = Comparator.comparing(Cliente::getNombre).thenComparing(Cliente::getDni);
            trabajos.sort(Comparator.comparing(Trabajo::getFechaInicio).thenComparing(Trabajo::getCliente, comparadorCliente));
            for (Trabajo trabajo : trabajos){
                System.out.println(trabajo);
            }
        } else {
            System.out.println("No hay trabajos para mostrar");
        }
    }

    @Override
    public void mostrarEstadisticasMensuales(Map<TipoTrabajo, Integer> estadisticas){
        System.out.printf("Tipos de trabajos realizados este mes:  %s%n", estadisticas);
    }
}
