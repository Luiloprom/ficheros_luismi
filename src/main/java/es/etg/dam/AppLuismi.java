package es.etg.dam;

import java.io.File;

import javax.swing.JOptionPane;

public class AppLuismi {
    private static final String RESOURCES_PATH = "src/main/resources";

    public static void main(String[] args) {
        borrarArchivosResources();

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
                break; // usuario canceló

            switch (input) {
                case "1" -> {
                    JOptionPane.showMessageDialog(null, CrearFicheroLuismi.crearFichero());
                }
                case "2" -> {
                    String codigoStr = JOptionPane.showInputDialog("Introduce código del videojuego a modificar:");
                    String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
                    String nuevaPlataforma = JOptionPane.showInputDialog("Nueva plataforma:");
                    String precioStr = JOptionPane.showInputDialog("Nuevo precio:");
                    String añoStr = JOptionPane.showInputDialog("Nuevo año:");
                    try {
                        int codigo = Integer.parseInt(codigoStr);
                        float precio = Float.parseFloat(precioStr);
                        int año = Integer.parseInt(añoStr);
                        ModificarFicheroLuismi.modificarRegistro(codigo, nuevoNombre, nuevaPlataforma, precio, año);
                        JOptionPane.showMessageDialog(null, "Modificación finalizada.");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Datos numéricos no válidos.");
                    }
                }
                case "3" -> {
                    JOptionPane.showMessageDialog(null, CrearFicheroXMLLuismi.crearFicheroXML());
                }
                case "4" -> {
                    LeerFicheroXMLLuismi.leerDOM();
                    JOptionPane.showMessageDialog(null, "Lectura con DOM finalizada. Revisa consola.");
                }
                case "5" -> {
                    LeerFicheroXMLLuismi.leerSAX();
                    JOptionPane.showMessageDialog(null, "Lectura con SAX finalizada. Revisa consola.");
                }
                case "6" -> {
                    TransformarXSLLuismi.transformarXMLaHTML();
                    JOptionPane.showMessageDialog(null, "Transformación a HTML finalizada.");
                }
                case "7" -> {
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    System.exit(0);
                }
                default -> JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }
    }

    // Metodo para limpiar los archivos generados anteriormente
    public static void borrarArchivosResources() {
        File carpeta = new File(RESOURCES_PATH);
        if (!carpeta.exists() || !carpeta.isDirectory()) {
            System.out.println("La carpeta resources no existe o no es un directorio.");
            return;
        }
        File[] archivos = carpeta.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isFile() && !archivo.getName().endsWith("xsl")) {
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
