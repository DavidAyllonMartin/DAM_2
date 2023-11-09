package org.infantaelena.ies.ad.ejercicios2_2;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio224 {
    public static void main(String[] args) {
        try {
            File inputFile = new File("src/main/resources/ad/ejercicios2_2/carrera.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            Vehiculo vehiculo3 = new Vehiculo("Miguel Aventuras", 8, "3H40M20S");
            List<String> conductores3 = new ArrayList<>();
            conductores3.add("Miguel");
            conductores3.add("Ana");
            Participante participante3 = new Participante("MAV", conductores3, vehiculo3);
            participante3.agregarConductor("Sara");

            Element rootElement = doc.getDocumentElement();
            participante3.agregarComoHijo(rootElement, doc);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(inputFile);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Node buscarParticipantePorCodigo(Document doc, String codigo) {
        NodeList participanteList = doc.getElementsByTagName("participante");
        for (int i = 0; i < participanteList.getLength(); i++) {
            Node participanteNode = participanteList.item(i);
            if (participanteNode.getNodeType() == Node.ELEMENT_NODE) {
                Element participanteElement = (Element) participanteNode;
                String codigoParticipante = participanteElement.getElementsByTagName("codigo").item(0).getTextContent();
                if (codigoParticipante.equals(codigo)) {
                    return participanteNode;
                }
            }
        }
        return null;
    }

    public static boolean eliminarParticipantePorCodigo(Document doc, String codigo) {
        boolean isRemoved = false;
        Node participanteNode = buscarParticipantePorCodigo(doc, codigo);
        if (participanteNode != null) {
            Node parentNode = participanteNode.getParentNode();
            parentNode.removeChild(participanteNode);
            isRemoved = true;
        }
        return isRemoved;
    }
}
