package org.iesalandalus.programacion.tallermecanico.vista.controladores;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.iesalandalus.programacion.tallermecanico.vista.VistaVentanas;
import org.iesalandalus.programacion.tallermecanico.vista.eventos.Evento;
import org.iesalandalus.programacion.tallermecanico.vista.utilidades.Controlador;

public class BuscarCliente extends Controlador{

    @FXML
    private Button btBuscar;

    @FXML
    private Button btCerrar;

    @FXML
    void Buscar(ActionEvent event) {
        VistaVentanas.getInstancia().getGestorEventos().notificar(Evento.BUSCAR_CLIENTE);
    }

    @FXML
    void Cerrar(ActionEvent event) {
        getEscenario().close();
    }
}
