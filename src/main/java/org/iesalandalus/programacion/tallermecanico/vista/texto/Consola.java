package org.iesalandalus.programacion.tallermecanico.vista.texto;


import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.utilidades.Entrada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Consola {

    private static final String CADENA_FORMATO_FECHA = "dd/MM/yyyy";

    private Consola(){}

    static void mostrarCabecera(String mensaje){
        System.out.println(mensaje);
        for (int i = 0; i < mensaje.length(); i++){
            System.out.print("-");
        }
        System.out.println();
    }

    static void mostrarMenu(){
        mostrarCabecera("Gestión de un taller mecánico");
        for (Evento evento : Evento.values()){
            System.out.println(evento.toString());
        }
    }

    static Evento elegirOpcion(){
        int opcion;
        do {
            opcion = leerEntero("Introduce el número de opción: ");
        }while (!Evento.esValida(opcion));
        return Evento.get(opcion);
    }

    static float leerReal(String mensaje){
        System.out.print(mensaje);
        return Entrada.real();
    }

    static int leerEntero(String mensaje){
        System.out.print(mensaje);
        return Entrada.entero();
    }

    static String leerCadena(String mensaje){
        System.out.print(mensaje);
        return Entrada.cadena();
    }

    static LocalDate leerFecha(String mensaje){
        LocalDate fecha;
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern(CADENA_FORMATO_FECHA);
        String entrada;
        do {
            System.out.print(mensaje);
            entrada = Entrada.cadena();
            fecha = LocalDate.parse(entrada, formatoFecha);
            System.out.println();
        }while (entrada.matches(CADENA_FORMATO_FECHA));
        return fecha;
    }

}
