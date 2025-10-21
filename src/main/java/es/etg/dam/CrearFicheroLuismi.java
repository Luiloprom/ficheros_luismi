package es.etg.dam;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrearFicheroLuismi {
    private static final String FILE_NAME = "src/main/resources/videojuegosLuismi.dat"; // Ruta y nombre del archivo
                                                                                        // .dat que se va a generar

    public static String crearFichero() { // Método para crear ficheros .dat
        String resultado;
        List<VideojuegoLuismi> lista = new ArrayList<>(); // Creamos una lista de videojuegos y los añadimos
        lista.add(new VideojuegoLuismi(1001, "Minecraft", "PC", 26.95f, 2011));
        lista.add(new VideojuegoLuismi(1002, "The Witcher 3", "PC", 39.99f, 2015));
        lista.add(new VideojuegoLuismi(1003, "Halo Infinite", "Xbox", 59.99f, 2021));
        lista.add(new VideojuegoLuismi(1004, "FIFA 23", "PS5", 49.99f, 2022));
        lista.add(new VideojuegoLuismi(1005, "Among Us", "PC", 5.00f, 2018));

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (VideojuegoLuismi v : lista) { // Recorremos toda nuestra lista de videojuegos y los vamos escribiendo
                                               // uno a uno.
                dos.writeInt(v.getCodigo());
                dos.writeUTF(v.getNombre());
                dos.writeUTF(v.getPlataforma());
                dos.writeFloat(v.getPrecio());
                dos.writeInt(v.getAño());
            }
            resultado = "Fichero creado correctamente."; // Resultado si el fichero se crea correctamente
        } catch (IOException e) {
            resultado = "Error al crear el fichero: "; // Resultado si falla cualquier cosa
        }
        return resultado; // Devolvemos el resultado como String para mostrarlo por pantalla en el App
    }
}
