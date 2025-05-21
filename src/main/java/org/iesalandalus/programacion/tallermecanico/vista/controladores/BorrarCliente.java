package org.iesalandalus.programacion.tallermecanico.vista.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.vista.utilidades.Controlador;

public class BorrarCliente extends Controlador{
    @FXML
    private Button btBorrar;

    @FXML
    private Button btCancelar;

    @FXML
    void Borrar(ActionEvent event) {

    }

    @FXML
    void Cerrar(ActionEvent event) {
        getEscenario().close();
    }
}
