package org.iesalandalus.programacion.tallermecanico.vista.eventos;

import java.util.HashMap;
import java.util.Map;

public enum Evento {
    INSERTAR_CLIENTE(10, "Insertar cliente"),
    BUSCAR_CLIENTE(11, "Buscar cliente"),
    BORRAR_CLIENTES(12, "Borrar clientes"),
    LISTAR_CLIENTES(13, "Listar clientes"),
    MODIFICAR_CLIENTE(14, "Modificar cliente"),
    INSERTAR_VEHICULO(21, "Insertar vehículo"),
    BUSCAR_VEHICULO(22, "Buscar vehículo"),
    BORRAR_VEHICULO(23, "Borrar vehículo"),
    LISTAR_VEHICULOS(24, "Listar vehículos"),
    INSERTAR_REVISION(31, "Insertar revisión"),
    INSERTAR_MECANICO(32, "Insertar mecánico"),
    BUSCAR_TRABAJO(33, "Buscar trabajo"),
    BORRAR_TRABAJO(34, "Borrar trabajo"),
    LISTAR_TRABAJOS(35, "Listar trabajos"),
    LISTAR_TRABAJOS_CLIENTE(36, "Listar trabajos de un cliente"),
    LISTAR_TRABAJOS_VEHICULO(37, "Listar trabajos de un vehículo"),
    ANADIR_HORAS_TRABAJO(38, "Añadir horas a un trabajo"),
    ANADIR_PRECIO_MATERIAL_TRABAJO(39, "Añadir precio material a un trabajo"),
    CERRAR_TRABAJO(40, "Cerrar trabajo"),
    MOSTRAR_ESTADISTICAS_MENSUALES(41, "Mostrar estadísticas mensuales"),
    SALIR(0, "Salir");

    private final int codigo;
    private final String mensaje;
    private static final Map<Integer, Evento> eventos = new HashMap<>();
    static {
        for (Evento evento : values()){
            eventos.put(evento.codigo, evento);
        }
    }

    private Evento(int numeroOpcion, String mensaje){
        this.codigo = numeroOpcion;
        this.mensaje = mensaje;
    }

    public static boolean esValida(int numeroOpcion){
        return (eventos.containsKey(numeroOpcion));
    }

    public static Evento get(int numeroOpcion){
        if (!esValida(numeroOpcion)){
            throw new IllegalArgumentException("El número no está entre las opciones.");
        }
        return eventos.get(numeroOpcion);
    }

    @Override
    public String toString() {
        return String.format("%s - %s", codigo, mensaje);
    }
}
