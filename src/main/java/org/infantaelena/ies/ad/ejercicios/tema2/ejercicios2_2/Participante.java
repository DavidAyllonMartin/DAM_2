package org.infantaelena.ies.ad.ejercicios.tema2.ejercicios2_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class Participante {
    private String codigo;
    private List<String> conductores;
    private Vehiculo vehiculo;

    public Participante(String codigo, List<String> conductores, Vehiculo vehiculo) {
        setCodigo(codigo);
        setConductores(conductores);
        setVehiculo(vehiculo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<String> getConductores() {
        return conductores;
    }

    public void setConductores(List<String> conductores) {
        this.conductores = conductores;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public void agregarConductor(String conductor){
        this.conductores.add(conductor);
    }

    public void agregarComoHijo(Element elemento, Document documento) {
        Element participanteElemento = documento.createElement("participante");
        Element codigoElemento = documento.createElement("codigo");
        codigoElemento.appendChild(documento.createTextNode(this.getCodigo()));

        Element conductoresElemento = documento.createElement("conductores");
        for (String conductor : this.getConductores()) {
            Element conductorElemento = documento.createElement("conductor");
            conductorElemento.appendChild(documento.createTextNode(conductor));
            conductoresElemento.appendChild(conductorElemento);
        }

        Element vehiculoElemento = documento.createElement("vehiculo");
        Element nombreElemento = documento.createElement("nombre");
        nombreElemento.appendChild(documento.createTextNode(this.getVehiculo().getNombre()));
        vehiculoElemento.appendChild(nombreElemento);

        Element numeroElemento = documento.createElement("numero");
        numeroElemento.appendChild(documento.createTextNode(String.valueOf(this.getVehiculo().getNumero())));
        vehiculoElemento.appendChild(numeroElemento);

        Element tiempoElemento = documento.createElement("tiempo");
        tiempoElemento.appendChild(documento.createTextNode(this.getVehiculo().getTiempo()));
        vehiculoElemento.appendChild(tiempoElemento);

        participanteElemento.appendChild(codigoElemento);
        participanteElemento.appendChild(conductoresElemento);
        participanteElemento.appendChild(vehiculoElemento);

        elemento.appendChild(participanteElemento);
    }

    public static void main(String[] args) {
        try {
            File outputFile = new File("src/main/resources/ad/ejercicios2_2/nuevo_carrera.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element carreraElemento = doc.createElement("carrera");
            doc.appendChild(carreraElemento);

            Vehiculo vehiculo1 = new Vehiculo("David Temazos", 13, "1H20M10S");
            List<String> conductores1 = new ArrayList<>();
            conductores1.add("David");
            conductores1.add("Luis");
            Participante participante1 = new Participante("DAM", conductores1, vehiculo1);
            participante1.agregarComoHijo(carreraElemento, doc);

            Vehiculo vehiculo2 = new Vehiculo("Elena Aventuras", 7, "2H30M15S");
            List<String> conductores2 = new ArrayList<>();
            conductores2.add("Elena");
            Participante participante2 = new Participante("EAV", conductores2, vehiculo2);
            participante2.agregarConductor("Alex");
            participante2.agregarComoHijo(carreraElemento, doc);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new FileWriter(outputFile));
            transformer.transform(source, file);
            StreamResult console = new StreamResult(System.out);
            transformer.transform(source, console);

        } catch (ParserConfigurationException | TransformerException | IOException e) {
            e.printStackTrace();
        }
    }
}
