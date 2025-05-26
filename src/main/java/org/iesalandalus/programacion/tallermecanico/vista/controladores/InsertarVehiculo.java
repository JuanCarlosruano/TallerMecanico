package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.VistaVentanas;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.utilidades.*;

public class InsertarVehiculo extends Controlador {

    @FXML
    private Button btCancelar;

    @FXML
    private Button btInsertar;


    @FXML
    void insertar(ActionEvent event) {

    }

    @FXML
    void cancelar(ActionEvent event) {
        getEscenario().close();
    }

    @FXML
    void initialize() {
    }
    @FXML
    private TextField tfMarca;

    @FXML
    private TextField tfMatricula;

    @FXML
    private TextField tfModelo;
    @FXML
    void cerrar() {
        getEscenario().close();
    }
    @FXML
    void aceptar() {
        VistaVentanas.getInstancia().getGestorEventos().notificar(Evento.INSERTAR_VEHICULO);
        getEscenario().close();
    }
    public Vehiculo getVehiculo() {
        String marca = tfMarca.getText();
        String modelo = tfModelo.getText();
        String matricula = tfMatricula.getText();
        return new Vehiculo(marca, modelo, matricula);
    }

    public void limpiar() {
        Controles.limpiarCamposTexto(tfMarca, tfModelo, tfMatricula);
    }

}

