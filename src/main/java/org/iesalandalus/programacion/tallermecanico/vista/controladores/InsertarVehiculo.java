package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
;
import org.iesalandalus.programacion.tallermecanico.vista.VistaVentanas;
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

}
