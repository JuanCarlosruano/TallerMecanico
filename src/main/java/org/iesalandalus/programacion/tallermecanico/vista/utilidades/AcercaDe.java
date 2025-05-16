package org.iesalandalus.programacion.tallermecanico.vista.utilidades;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

public class AcercaDe extends Controlador {

    @FXML
    private Button btSalir;

    @FXML
    void Salir(ActionEvent event) {
        getEscenario().close();
    }


}
