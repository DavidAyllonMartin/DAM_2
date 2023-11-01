package org.infantaelena.ies.ad.ejercicios2_1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class ejercicio2_1_1 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/main/resources/ad/ejercicios2_1/carrera.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = null;
            try {
                builder = builderFactory.newDocumentBuilder();
            } catch (ParserConfigurationException ex) {
                ex.printStackTrace();
            }
            Document doc = builder.parse(inputFile);
            doc.getDocumentElement().normalize();

            nombresPilotos(doc).forEach(System.out::println);

        } catch (IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public static List<String> nombresPilotos(Document doc){
        List<String> pilotos = new ArrayList<>();
        NodeList participanteList = doc.getElementsByTagName("participante");

        for (int i = 0; i < participanteList.getLength(); i++) {
            Node participanteNode = participanteList.item(i);

            if (participanteNode.getNodeType() == Node.ELEMENT_NODE) {
                Element participanteElement = (Element) participanteNode;

                NodeList conductorList = participanteElement.getElementsByTagName("conductor");
                NodeList vehiculoList = participanteElement.getElementsByTagName("vehiculo");

                String numeroVehiculo = vehiculoList.item(0).getChildNodes().item(3).getTextContent();

                for (int j = 0; j < conductorList.getLength(); j++) {
                    Node conductorNode = conductorList.item(j);

                    if (conductorNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element conductorElement = (Element) conductorNode;
                        NodeList nombreList = conductorElement.getElementsByTagName("nombre");

                        for (int k = 0; k < nombreList.getLength(); k++) {
                            String nombrePiloto = nombreList.item(k).getTextContent();
                            int numeroOrden = k + 1;
                            pilotos.add(numeroVehiculo + "-" + numeroOrden + "-" + nombrePiloto);
                        }
                    }
                }
            }
        }
        return pilotos;
    }
}

