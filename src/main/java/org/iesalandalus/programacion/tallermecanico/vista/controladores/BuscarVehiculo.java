package org.iesalandalus.programacion.tallermecanico.vista.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.iesalandalus.programacion.tallermecanico.vista.utilidades.Controlador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BuscarVehiculo extends Controlador{
    @FXML
    private Button btBotonBuscarr;

    @FXML
    private Button btCancelar;
    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    private ListView<String> lvlistado;

    @FXML
    void Cancelar(ActionEvent event) {
        getEscenario().close();
    }
    @FXML
    void initialize(){
        ObservableList<String> items = FXCollections.observableArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader("/datos/mariadb/tallerMecanico.sql"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                items.add(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
            items.add("Error leyendo el archivo.");
        }

        lvlistado.setItems(items);
    }
}
