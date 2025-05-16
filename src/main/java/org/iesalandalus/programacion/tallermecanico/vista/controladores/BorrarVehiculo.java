package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.vista.utilidades.Controlador;
public class BorrarVehiculo extends Controlador{


    @FXML
    private Button btBotonBorrar;
    @FXML
    private Button btCancelar;





    @FXML
    void Cancelar(ActionEvent event) {
        getEscenario().close();
    }
    @FXML
    void Borrar(ActionEvent event) {

    }


}
