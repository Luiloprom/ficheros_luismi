package es.etg.dam;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class TransformarXSLLuismi {
    public static void transformarXMLaHTML() {
        // Rutas archivos
        String xmlFile = "src/main/resources/videojuegosLuismi.xml";
        String xsltFile = "src/main/resources/videojuegosLuismi.xsl";
        String htmlFile = "src/main/resources/videojuegosLuismi.html";

        try {
            TransformerFactory factory = TransformerFactory.newInstance();
            Source xslt = new StreamSource(new File(xsltFile));
            Transformer transformer = factory.newTransformer(xslt);

            Source text = new StreamSource(new File(xmlFile));
            transformer.transform(text, new StreamResult(new File(htmlFile)));

            System.out.println("Transformación realizada: videojuegos.html creado en resources.");
        } catch (TransformerException e) {
            System.out.println("A ocurrido un error");
        }
    }
}
