package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Vehiculo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vehiculos implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.IVehiculos {

    private static final String FICHERO_VEHICULOS = String.format("%s%s%s", "datos", File.separator, "vehiculos.xml");
    private static final String VEHICULO = "vehiculo";
    private static final String RAIZ = "vehiculos";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String MATRICULA = "matricula";

    private static Vehiculos instancia;
    private final List<Vehiculo> coleccionVehiculos;

    private Vehiculos(){
        coleccionVehiculos = new ArrayList<>();
    }

    static Vehiculos getInstancia(){
        if (instancia == null){
            instancia = new Vehiculos();
        }
        return instancia;
    }

    @Override
    public void comenzar(){
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_VEHICULOS);
        if (documentoXml != null){
            procesarDocumentoXml(documentoXml);
            System.out.printf("Fichero %s leído correctamente.%n", FICHERO_VEHICULOS);
        }
    }

    private void procesarDocumentoXml(Document documentoXml){
        NodeList vehiculos = documentoXml.getElementsByTagName(VEHICULO);
        for (int i = 0; i < vehiculos.getLength(); i++){
            Node vehiculo = vehiculos.item(i);
            try {
                if (vehiculo.getNodeType() == Node.ELEMENT_NODE){
                    insertar(getVehiculo((Element) vehiculo));
                }
            } catch (TallerMecanicoExcepcion | IllegalArgumentException | NullPointerException e){
                System.out.printf("Error al leer el  elemento vehículo %d. ---> %s%n", i, e.getMessage());
            }
        }
    }

    private Vehiculo getVehiculo(Element elemento){
        String marca = elemento.getAttribute(MARCA);
        String modelo = elemento.getAttribute(MODELO);
        String matricula = elemento.getAttribute(MATRICULA);
        return new Vehiculo(marca, modelo, matricula);
    }

    @Override
    public void terminar(){
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_VEHICULOS);
    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Vehiculo vehiculo : coleccionVehiculos){
                Element elementoVehiculo = documentoXml.createElement(VEHICULO);
                elementoVehiculo.setAttribute(MARCA, vehiculo.marca());
                elementoVehiculo.setAttribute(MATRICULA, vehiculo.matricula());
                elementoVehiculo.setAttribute(MODELO, vehiculo.modelo());
                documentoXml.getDocumentElement().appendChild(elementoVehiculo);
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Vehiculo vehiculo){
        NodeList elementos = documentoXml.getElementsByTagName(VEHICULO);
        Element resultado = null;
        for (int i = 0; i < elementos.getLength(); i++){
            Node elemento = elementos.item(i);
            if (elemento.getNodeType() == Node.ELEMENT_NODE){
                Element elementoVehiculo = (Element) elemento;
                if (elementoVehiculo.getAttribute(MATRICULA).equals(vehiculo.matricula())){
                    resultado = elementoVehiculo;
                }
            }
        }
        return resultado;
    }

    @Override
    public List<Vehiculo> get(){
        return new ArrayList<>(coleccionVehiculos);
    }

    @Override
    public void insertar(Vehiculo vehiculo) throws TallerMecanicoExcepcion {
        Objects.requireNonNull(vehiculo, "No se puede insertar un vehículo nulo.");
        if (coleccionVehiculos.contains(vehiculo)){
            throw new TallerMecanicoExcepcion("Ya existe un vehículo con esa matrícula.");
        }
        coleccionVehiculos.add(vehiculo);
    }

    @Override
    public Vehiculo buscar(Vehiculo vehiculo){
        Objects.requireNonNull(vehiculo, "No se puede buscar un vehículo nulo.");
        int indice = coleccionVehiculos.indexOf(vehiculo);
        return (coleccionVehiculos.contains(vehiculo) ? coleccionVehiculos.get(indice) : null);
    }

    @Override
    public void borrar(Vehiculo vehiculo) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(vehiculo, "No se puede borrar un vehículo nulo.");
        if (!coleccionVehiculos.contains(vehiculo)){
            throw new TallerMecanicoExcepcion("No existe ningún vehículo con esa matrícula.");
        }
        coleccionVehiculos.remove(vehiculo);
    }
}
