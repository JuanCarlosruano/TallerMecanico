package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

public enum TipoTrabajo {

    MECANICO("Mecánico"),
    REVISION("Revisión");

    private String nombre;

    private TipoTrabajo(String nombre){
        this.nombre = nombre;
    }

    public static TipoTrabajo get(Trabajo trabajo){
        TipoTrabajo resultado = null;
        if (trabajo instanceof Revision){
            resultado = REVISION;
        }
        if (trabajo instanceof  Mecanico){
            resultado = MECANICO;
        }
        return resultado;
    }
}
