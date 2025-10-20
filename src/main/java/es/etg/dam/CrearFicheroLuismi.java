package es.etg.dam;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrearFicheroLuismi {
    private static final String FILE_NAME = "src/main/resources/videojuegosLuismi.dat";

    public static String crearFichero() {
        String resultado;
        List<VideojuegoLuismi> lista = new ArrayList<>();
        lista.add(new VideojuegoLuismi(1001, "Minecraft", "PC", 26.95f, 2011));
        lista.add(new VideojuegoLuismi(1002, "The Witcher 3", "PC", 39.99f, 2015));
        lista.add(new VideojuegoLuismi(1003, "Halo Infinite", "Xbox", 59.99f, 2021));
        lista.add(new VideojuegoLuismi(1004, "FIFA 23", "PS5", 49.99f, 2022));
        lista.add(new VideojuegoLuismi(1005, "Among Us", "PC", 5.00f, 2018));

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (VideojuegoLuismi v : lista) {
                dos.writeInt(v.getCodigo());
                dos.writeUTF(v.getNombre());
                dos.writeUTF(v.getPlataforma());
                dos.writeFloat(v.getPrecio());
                dos.writeInt(v.getAño());
            }
            resultado = "Fichero creado correctamente.";
        } catch (IOException e) {
            resultado = "Error al crear el fichero: ";
        }
        return resultado;
    }
}
