package es.etg.dam;

import java.io.File;

import javax.swing.JOptionPane;

public class AppLuismi {
    // Ruta del resources
    private static final String RESOURCES_PATH = "src/main/resources";

    public static void main(String[] args) {
        borrarArchivosResources(); // Método para limpiar todos los archivos del resources menos el xsl

        // Menu para mostrarlo con JOptionPane
        String menu = """
                Selecciona una opcion:
                1. Crear fichero videojuegos.dat
                2. Modificar videojuego
                3. Crear fichero videojuegos.xml desde .dat
                4. Leer XML con DOM
                5. Leer XML con SAX
                6. Transformar XML a HTML
                7. Salir""";

        while (true) {
            String input = JOptionPane.showInputDialog(menu);
            if (input == null)
                break; // Por si el usuario cancela
            switch (input) {
                case "1" -> { // CrearFichero, Método para crear el fichero .dat
                    JOptionPane.showMessageDialog(null, CrearFicheroLuismi.crearFichero());
                }
                case "2" -> { // ModificarFichero. Método para modificar un videojuego
                    String codigoStr = JOptionPane.showInputDialog("Introduce codigo del videojuego a modificar:");
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    String nuevaPlataforma = JOptionPane.showInputDialog("Nueva plataforma:");
                    String precioStr = JOptionPane.showInputDialog("Nuevo precio:");
                    String añoStr = JOptionPane.showInputDialog("Nuevo año:");
                    try {
                        int codigo = Integer.parseInt(codigoStr);
                        float precio = Float.parseFloat(precioStr);
                        int año = Integer.parseInt(añoStr);
                        ModificarFicheroLuismi.modificarRegistro(codigo, nuevoNombre, nuevaPlataforma, precio, año);
                        JOptionPane.showMessageDialog(null, "Modificacion finalizada.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Datos numericos no validos.");
                    }
                }
                case "3" -> { // CrearFicheroXML. Método para crear el fichero XML
                    JOptionPane.showMessageDialog(null, CrearFicheroXMLLuismi.crearFicheroXML());
                }
                case "4" -> { // Método para leer con DOM
                    LeerFicheroXMLLuismi.leerDOM();
                    JOptionPane.showMessageDialog(null, "Lectura con DOM finalizada. Revisa consola.");
                }
                case "5" -> { // Método para leer con SAX
                    LeerFicheroXMLLuismi.leerSAX();
                    JOptionPane.showMessageDialog(null, "Lectura con SAX finalizada. Revisa consola.");
                }
                case "6" -> { // Método para generar HTML
                    TransformarXSLLuismi.transformarXMLaHTML();
                    JOptionPane.showMessageDialog(null, "Transformacion a HTML finalizada.");
                }
                case "7" -> { // Método para salir
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    System.exit(0);
                } // Por si seleciona una opción no válida
                default -> JOptionPane.showMessageDialog(null, "Opcion no valida");
            }
        }
    }

    // Metodo para limpiar los archivos generados anteriormente
    public static void borrarArchivosResources() {
        File carpeta = new File(RESOURCES_PATH);
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && !archivo.getName().endsWith("xsl")) { // Me aseguro de que no elimine el archivo
                                                                              // xsl
                    if (archivo.delete()) {
                        System.out.println("Borrado: " + archivo.getName());
                    } else {
                        System.out.println("No se pudo borrar: " + archivo.getName());
                    }
                }
            }
        }
    }
}
