package org.iesalandalus.programacion.tallermecanico.modelo.negocio.ficheros;

import org.iesalandalus.programacion.tallermecanico.modelo.TallerMecanicoExcepcion;
import org.iesalandalus.programacion.tallermecanico.modelo.dominio.Cliente;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Clientes implements org.iesalandalus.programacion.tallermecanico.modelo.negocio.IClientes {

    private static final String FICHERO_CLIENTES = String.format("%s%s%s", "datos", File.separator, "clientes.xml");
    private static final String RAIZ = "clientes";
    private static final String CLIENTE = "cliente";
    private static final String NOMBRE = "nombre";
    private static final String DNI = "dni";
    private static final String TELEFONO = "telefono";

    private static Clientes instancia;
    private final List<Cliente> coleccionClientes;

    private Clientes(){
        coleccionClientes = new ArrayList<>();
    }

    static Clientes getInstancia(){
        if (instancia == null){
            instancia = new Clientes();
        }
        return instancia;
    }

    @Override
    public void comenzar(){
        Document documentoXml = UtilidadesXml.leerDocumentoXml(FICHERO_CLIENTES);
        if (documentoXml != null){
            procesarDocumentoXml(documentoXml);
            System.out.printf("Fichero %s leído correctamente.%n", FICHERO_CLIENTES);
        }
    }

    private void procesarDocumentoXml(Document documentoXml){
        NodeList clientes = documentoXml.getElementsByTagName(CLIENTE);
        for (int i = 0; i < clientes.getLength(); i++){
            Node cliente = clientes.item(i);
            try {
                if (cliente.getNodeType() == Node.ELEMENT_NODE){
                    insertar(getCliente((Element) cliente));
                }
            } catch (TallerMecanicoExcepcion | IllegalArgumentException | NullPointerException e){
                System.out.printf("Error al leer el  elemento cliente %d. ---> %s%n", i, e.getMessage());
            }
        }
    }

    private Cliente getCliente(Element elemento){
        String nombre = elemento.getAttribute(NOMBRE);
        String dni = elemento.getAttribute(DNI);
        String telefono = elemento.getAttribute(TELEFONO);
        return new Cliente(nombre, dni, telefono);
    }

    @Override
    public void terminar(){
        Document documentoXml = crearDocumentoXml();
        UtilidadesXml.escribirDocumentoXml(documentoXml, FICHERO_CLIENTES);
    }

    private Document crearDocumentoXml(){
        DocumentBuilder constructor = UtilidadesXml.crearConstructorDocumentoXml();
        Document documentoXml = null;
        if (constructor != null){
            documentoXml = constructor.newDocument();
            documentoXml.appendChild(documentoXml.createElement(RAIZ));
            for (Cliente cliente : coleccionClientes){
                Element elementoCliente = documentoXml.createElement(CLIENTE);
                elementoCliente.setAttribute(NOMBRE, cliente.getNombre());
                elementoCliente.setAttribute(DNI, cliente.getDni());
                elementoCliente.setAttribute(TELEFONO, cliente.getTelefono());
                documentoXml.getDocumentElement().appendChild(elementoCliente);
            }
        }
        return documentoXml;
    }

    private Element getElemento(Document documentoXml, Cliente cliente){
        NodeList elementos = documentoXml.getElementsByTagName(CLIENTE);
        Element resultado = null;
        for (int i = 0; i < elementos.getLength(); i++){
            Node elemento = elementos.item(i);
            if (elemento.getNodeType() == Node.ELEMENT_NODE){
                Element elementoCliente = (Element) elemento;
                if (elementoCliente.getAttribute(DNI).equals(cliente.getDni())){
                    resultado = elementoCliente;
                }
            }
        }
        return resultado;
    }

    @Override
    public List<Cliente> get(){
        return new ArrayList<>(coleccionClientes);
    }

    @Override
    public void insertar(Cliente cliente) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente, "No se puede insertar un cliente nulo.");
        if (coleccionClientes.contains(cliente)){
            throw new TallerMecanicoExcepcion("Ya existe un cliente con ese DNI.");
        }
        coleccionClientes.add(cliente);
    }

    @Override
    public Cliente modificar(Cliente cliente, String nombre, String telefono) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente, "No se puede modificar un cliente nulo.");
        Cliente clienteLista = buscar(cliente);
        if (clienteLista == null){
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
        if (nombre != null && !nombre.isBlank()){
            clienteLista.setNombre(nombre);
        }
        if (telefono != null && !telefono.isBlank()){
            clienteLista.setTelefono(telefono);
        }
        return clienteLista;
    }

    @Override
    public Cliente buscar(Cliente cliente){
        Objects.requireNonNull(cliente, "No se puede buscar un cliente nulo.");
        int indice = coleccionClientes.indexOf(cliente);
        return (coleccionClientes.contains(cliente) ? coleccionClientes.get(indice) : null);
    }

    @Override
    public void borrar(Cliente cliente) throws TallerMecanicoExcepcion{
        Objects.requireNonNull(cliente, "No se puede borrar un cliente nulo.");
        if (!coleccionClientes.contains(cliente)){
            throw new TallerMecanicoExcepcion("No existe ningún cliente con ese DNI.");
        }
        coleccionClientes.remove(cliente);
    }
}
