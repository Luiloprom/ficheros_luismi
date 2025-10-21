package es.etg.dam;

import java.io.RandomAccessFile;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearFicheroXMLLuismi {
    // Rutas archivos
    private static final String DAT_FILE = "src/main/resources/videojuegosLuismi.dat";
    private static final String XML_FILE = "src/main/resources/videojuegosLuismi.xml";

    public static String crearFicheroXML() { // Método para crear fichero XML
        String resultado; // Para devolver algun resultado al App

        try (RandomAccessFile raf = new RandomAccessFile(DAT_FILE, "r")) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();

            Element root = doc.createElement("Videojuegos");
            doc.appendChild(root);

            while (raf.getFilePointer() < raf.length()) { // Leemos todo el dat con el random
                int codigo = raf.readInt();
                String nombre = raf.readUTF();
                String plataforma = raf.readUTF();
                float precio = raf.readFloat();
                int año = raf.readInt();

                Element videojuego = doc.createElement("Videojuego");

                // He creado un método para no repetir tanto código al crear los elementos
                crearElemento(doc, videojuego, "Codigo", String.valueOf(codigo));
                crearElemento(doc, videojuego, "Nombre", nombre);
                crearElemento(doc, videojuego, "Plataforma", plataforma);
                crearElemento(doc, videojuego, "Precio", String.valueOf(precio));
                crearElemento(doc, videojuego, "Año", String.valueOf(año));

                root.appendChild(videojuego);
            }

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(doc), new StreamResult(XML_FILE));

            resultado = "Fichero videojuegos.xml creado correctamente en resources.";
        } catch (Exception e) {
            resultado = "Error al crear el fichero XML";
        }
        return resultado;
    }

    // Método para crear elementos
    private static void crearElemento(Document doc, Element padre, String nombreElemento, String valor) {
        Element elemento = doc.createElement(nombreElemento);
        elemento.setTextContent(valor);
        padre.appendChild(elemento);
    }

}
