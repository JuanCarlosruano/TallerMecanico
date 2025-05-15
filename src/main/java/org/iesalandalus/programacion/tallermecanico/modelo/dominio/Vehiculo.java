package org.iesalandalus.programacion.tallermecanico.modelo.dominio;

import java.util.Objects;
import java.util.SplittableRandom;

public record Vehiculo(String marca, String modelo, String matricula) {
    private static final String ER_MARCA ="(\\b[A-Z][a-z]*\\b|\\b[A-Z][a-z]+\\s[A-Z][a-z]+\\b|\\b[A-Z]{2,}\\b|\\b[A-Z][a-z]+-[A-Z][a-z]+\\b|\\b[A-Z][a-z]+[A-Z][a-z]+\\b)";
    private static final String ER_MATRICULA ="\\d{4}[^\\W_\\d AEIOUa-z]{3}";
    public Vehiculo{
        validarMarca(marca);
        validarModelo(modelo);
        validarMatricula(matricula);
    }
    private void validarMarca(String marca) {
        Objects.requireNonNull(marca, "La marca no puede ser nula.");
        if (!marca.matches(ER_MARCA)) {
            throw new IllegalArgumentException("La marca no tiene un formato válido.");
        }
    }
    private void validarModelo(String modelo) {
        Objects.requireNonNull(modelo, "El modelo no puede ser nulo.");
        if (modelo.isBlank()) {
            throw new IllegalArgumentException("El modelo no puede estar en blanco.");
        }
    }
    private void validarMatricula(String matricula) {
        Objects.requireNonNull(matricula, "La matrícula no puede ser nula.");
        if (!matricula.matches(ER_MATRICULA)) {
            throw new IllegalArgumentException("La matrícula no tiene un formato válido.");
        }
    }
    public static Vehiculo get(String matricula) {
        return new Vehiculo("Seat", "1", matricula);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return Objects.equals(matricula, vehiculo.matricula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricula);
    }

    @Override
    public String toString() {
        return String.format("%s %s - %s", marca, modelo, matricula);
    }
}
