package org.infantaelena.ies.ad.ejercicios;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class Examen2022 {
    public static void main(String[] args) {
        try {
            File precipitacionesXML = new File("src/main/resources/precipitaciones.xml");
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            Document precipitacionesDocument = builder.parse(precipitacionesXML);
            precipitacionesDocument.getDocumentElement().normalize();

            NodeList poblacionNodeList = precipitacionesDocument.getElementsByTagName("poblacion");
            Element poblacion = null;
            for (int i = 0; i < poblacionNodeList.getLength(); i++) {
                poblacion = (Element) poblacionNodeList.item(i);
                String nombre = poblacion.getAttribute("nombre");
                if (nombre.equals("Delgado")){
                    break;
                }
            }
            Node parent = poblacion.getParentNode();
            parent.removeChild(poblacion);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(precipitacionesDocument);
            StreamResult streamResult = new StreamResult(precipitacionesXML);
            transformer.transform(source, streamResult);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
