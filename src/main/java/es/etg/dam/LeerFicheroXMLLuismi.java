package es.etg.dam;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class LeerFicheroXMLLuismi {
    private static final String XML_FILE = "src/main/resources/videojuegosLuismi.xml";

    // Lectura usando DOM
    public static void leerDOM() {
        try {
            File inputFile = new File(XML_FILE);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList lista = doc.getElementsByTagName("Videojuego"); // Cogemos los hijos de videojuego que son los
                                                                     // videojuegos
            System.out.println("----- Lectura con DOM -----");
            for (int i = 0; i < lista.getLength(); i++) { // Recoremos la lista de nodos y los vamos mostrando por
                                                          // pantalla
                Node node = lista.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    System.out.printf("Codigo: %s, Nombre: %s, Plataforma: %s, Precio: %s, Año: %s%n",
                            e.getElementsByTagName("Codigo").item(0).getTextContent(),
                            e.getElementsByTagName("Nombre").item(0).getTextContent(),
                            e.getElementsByTagName("Plataforma").item(0).getTextContent(),
                            e.getElementsByTagName("Precio").item(0).getTextContent(),
                            e.getElementsByTagName("Año").item(0).getTextContent());
                }
            }
            System.out.println();
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println("A ocurrido un error al escribir con DOM");
        }
    }

    // Lectura usando SAX
    public static void leerSAX() {
        try {
            System.out.println("----- Lectura con SAX -----");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {
                String ultimoElemento = "";

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) {
                    ultimoElemento = qName;
                }

                @Override
                public void characters(char ch[], int start, int length) {
                    String value = new String(ch, start, length);
                    switch (ultimoElemento) {
                        case "Codigo" -> System.out.print("Codigo: " + value + ", ");
                        case "Nombre" -> System.out.print("Nombre: " + value + ", ");
                        case "Plataforma" -> System.out.print("Plataforma: " + value + ", ");
                        case "Precio" -> System.out.print("Precio: " + value + ", ");
                        case "Año" -> System.out.println("Año: " + value);
                    }
                    ultimoElemento = "";
                }

            };

            saxParser.parse(XML_FILE, handler);
            System.out.println();
        } catch (IOException | ParserConfigurationException | SAXException e) {
            System.out.println("A ocurrido un error al leer el fichero con SAX");
        }
    }
}
