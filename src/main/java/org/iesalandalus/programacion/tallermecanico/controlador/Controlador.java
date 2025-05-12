package org.iesalandalus.programacion.tallermecanico.controlador;

import org.iesalandalus.programacion.tallermecanico.modelo.FabricaModelo;
import org.iesalandalus.programacion.tallermecanico.modelo.Modelo;
import org.iesalandalus.programacion.tallermecanico.modelo.negocio.FabricaFuenteDatos;
import org.iesalandalus.programacion.tallermecanico.vista.FabricaVista;
import org.iesalandalus.programacion.tallermecanico.vista.Vista;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;

public class Controlador implements IControlador {

    private Modelo modelo;
    private Vista vista;

    public Controlador(FabricaModelo fabricaModelo, FabricaFuenteDatos fabricaFuenteDatos, FabricaVista fabricaVista) {
        modelo = fabricaModelo.crear(fabricaFuenteDatos);
        vista = fabricaVista.crear();
        this.vista.getGestorEventos().suscribir(this, Evento.values());
    }

    @Override
    public void comenzar(){
        modelo.comenzar();
        vista.comenzar();
    }

    @Override
    public void terminar(){
        modelo.terminar();
        vista.terminar();
    }

    @Override
    public void actualizar(Evento evento){
        try {
            String resultado = "";
            switch (evento) {
                case INSERTAR_CLIENTE -> {
                    modelo.insertar(vista.leerCliente());
                    resultado = "Cliente insertado correctamente";
                }
                case BUSCAR_CLIENTE -> {
                    vista.mostrarCliente(modelo.buscar(vista.leerClienteDni()));
                    resultado = "Cliente buscado correctamente";
                }
                case BORRAR_CLIENTES -> {
                    modelo.borrar(vista.leerCliente());
                    resultado = "Cliente borrado correctamente";
                }
                case LISTAR_CLIENTES -> {
                    vista.mostrarClientes(modelo.getClientes());
                    resultado = "Clientes listados correctamente";
                }
                case MODIFICAR_CLIENTE -> {
                    modelo.modificar(vista.leerCliente(), vista.leerNuevoNombre(), vista.leerNuevoTelefono());
                    resultado = "Cliente modificado correctamente";
                }
                case INSERTAR_VEHICULO -> {
                    modelo.insertar(vista.leerVehiculo());
                    resultado = "Vehículo insertado con éxito";
                }
                case BUSCAR_VEHICULO -> {
                    vista.mostrarVehiculo(modelo.buscar(vista.leerVehiculo()));
                    resultado = "Vehículo buscado correctamente";
                }
                case BORRAR_VEHICULO -> {
                    modelo.borrar(vista.leerVehiculo());
                    resultado = "Vehículo borrado correctamente";
                }
                case LISTAR_VEHICULOS -> {
                    vista.mostrarVehiculos(modelo.getVehiculos());
                    resultado = "Vehículos listados correctamente";
                }
                case INSERTAR_REVISION -> {
                    modelo.insertar(vista.leerRevision());
                    resultado = "Revisión insertada correctamente";
                }
                case INSERTAR_MECANICO -> {
                    modelo.insertar(vista.leerMecanico());
                    resultado = "Trabajo mecánico insertado correctamente";
                }
                case BUSCAR_TRABAJO -> {
                    vista.mostrarTrabajo(modelo.buscar(vista.leerTrabajoVehiculo()));
                    resultado = "Trabajo buscado correctamente";
                }
                case BORRAR_TRABAJO -> {
                    modelo.borrar(vista.leerTrabajoVehiculo());
                    resultado = "Trabajo borrado correctamente";
                }
                case LISTAR_TRABAJOS -> {
                    vista.mostrarTrabajos(modelo.getTrabajos());
                    resultado = "Trabajos listados correctamente";
                }
                case LISTAR_TRABAJOS_CLIENTE -> {
                    vista.mostrarTrabajos(modelo.getTrabajos(vista.leerCliente()));
                }
                case LISTAR_TRABAJOS_VEHICULO -> {
                    vista.mostrarTrabajos(modelo.getTrabajos(vista.leerVehiculo()));
                }
                case ANADIR_HORAS_TRABAJO -> {
                    modelo.anadirHoras(vista.leerTrabajoVehiculo(), vista.leerHoras());
                    resultado = "Horas añadidas correctamente";
                }
                case ANADIR_PRECIO_MATERIAL_TRABAJO -> {
                    modelo.anadirPrecioMaterial(vista.leerTrabajoVehiculo(), vista.leerPrecioMaterial());
                    resultado = "Precio material añadido correctamente";
                }
                case CERRAR_TRABAJO -> {
                    modelo.cerrar(vista.leerTrabajoVehiculo(), vista.leerFechaCierre());
                    resultado = "Trabajo cerrado correctamente";
                }
                case MOSTRAR_ESTADISTICAS_MENSUALES -> {
                    vista.mostrarEstadisticasMensuales(modelo.getEstadisticasMensuales(vista.leerMes()));
                }
                case SALIR -> {
                    modelo.terminar();
                    vista.terminar();
                }
            }
            if (!resultado.isBlank()){
                vista.notificarResultado(evento, resultado, true);
            }
        } catch (Exception e){
            vista.notificarResultado(evento, e.getMessage(), false);
        }
    }
}
