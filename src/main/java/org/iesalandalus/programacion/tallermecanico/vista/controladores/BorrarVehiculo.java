package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.tallermecanico.vista.VistaVentanas;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.utilidades.Controlador;
public class BorrarVehiculo extends Controlador{


    @FXML
    private Button btBotonBorrar;
    @FXML
    private Button btCancelar;
    @FXML
    private TextField tfMatricula;




    @FXML
    void cancelar(ActionEvent event) {
        getEscenario().close();
    }
    @FXML
    void borrar(ActionEvent event) {
        VistaVentanas.getInstancia().getGestorEventos().notificar(Evento.BORRAR_VEHICULO);
        getEscenario().close();

    }
    public Vehiculo getVehiculo(){
        String matricula = tfMatricula.getText();
        return Vehiculo.get(matricula);
    }


}
