package org.iesalandalus.programacion.tallermecanico.vista.texto;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.*;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.GestorEventos;
import static  org.iesalandalus.programacion.tallermecanico.vista.texto.Consola.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


public class VistaTexto implements org.iesalandalus.programacion.tallermecanico.vista.Vista {
    private final GestorEventos gestorEventos = new GestorEventos(Evento.values());

    @Override
    public GestorEventos getGestorEventos() {
        return gestorEventos;
    }

    @Override
    public void comenzar() {
        Evento evento;
        do {
            Consola.mostrarMenu();
            evento = Consola.elegirOpcion();
            ejecutar(evento);
        } while (evento != Evento.SALIR);
    }
    @Override
    public void terminar() {
        System.out.println("Hasta luego");

    }
    private void ejecutar(Evento opcion) {
        Consola.mostrarCabecera(opcion.toString());
        gestorEventos.notificar(opcion);


    }
    @Override
    public Cliente leerCliente() {
        String nombre = leerCadena("Introduce el nombre: ");
        String dni = leerCadena("Introduce el DNI: ");
        String telefono = leerCadena("Introduce el teléfono: ");
        return new Cliente(nombre, dni, telefono);
    }
    @Override
    public Cliente leerClienteDni() {
        return Cliente.get(leerCadena("Introduce el DNI: "));
    }
    @Override
    public String leerNuevoNombre() {
        return leerCadena("Introduce el nuevo nombre: ");
    }
    @Override
    public String leerNuevoTelefono() {
        return leerCadena("Introduce el nuevo teléfono");
    }
    @Override
    public Vehiculo leerVehiculo() {
        String marca = leerCadena("Introduce la marca: ");
        String modelo = leerCadena("Introduce el modelo: ");
        String matricula = leerCadena("Introduce la matrícula: ");
        return new Vehiculo(marca, modelo, matricula);
    }
    @Override
    public Vehiculo leerVehiculoMatricula() {
        return Vehiculo.get(leerCadena("Introduce la matrícula: "));

    }
    @Override
    public Trabajo leerRevision() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio");
        return new Revision(cliente, vehiculo, fechaInicio);
    }
    @Override
    public Trabajo leerMecanico() {
        Cliente cliente = leerClienteDni();
        Vehiculo vehiculo = leerVehiculoMatricula();
        LocalDate fechaInicio = leerFecha("Introduce la fecha de inicio");
        return new Mecanico(cliente, vehiculo, fechaInicio);
    }
    @Override
    public Trabajo leerTrabajoVehiculo() {
        Vehiculo vehiculo = leerVehiculoMatricula();
        return Trabajo.get(vehiculo);
    }
    @Override
    public int leerHoras() {
        return leerEntero("Intoduce las horas a añadir: ");
    }
    @Override
    public float leerPrecioMaterial() {
        return leerReal("Introduce el precio del material a añadir: ");

    }
    @Override
    public LocalDate leerFechaCierre() {
        return leerFecha("Introduce la fecha de cierre");
    }
    @Override
    public void notificarResultado(Evento evento, String texto, boolean exito) {
        if (exito) {
            System.out.println(texto);
        } else {
            System.out.printf("Error %s", texto);
        }

    }
    @Override
    public void mostrarCliente(Cliente cliente) {
        System.out.println(cliente);
    }
    @Override
    public void mostrarVehiculo(Vehiculo vehiculo) {
        System.out.println(vehiculo);
    }
    @Override
    public void mostrarTrabajo(Trabajo trabajo) {
        System.out.println(trabajo);
    }
    @Override
    public void mostrarClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("La lista esta vacía.");
        } else {
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
        }
    }
    @Override
    public void mostrarVehiculos(List<Vehiculo> vehiculos) {
        if (vehiculos.isEmpty()) {
            System.out.println("La lista esta vacía.");
        } else {
            for (Vehiculo vehiculo : vehiculos) {
                System.out.println(vehiculo);
            }
        }
    }
    @Override
    public void mostrarTrabajos(List<Trabajo> trabajos) {
        if (trabajos.isEmpty()) {
            System.out.println("La lista esta vacía.");
        } else {
            for (Trabajo trabajo : trabajos) {
                System.out.println(trabajo);
            }
        }
    }


}

