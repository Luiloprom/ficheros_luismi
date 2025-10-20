package es.etg.dam;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ModificarFicheroLuismi {
    private static final String FILE_NAME = "src/main/resources/videojuegosLuismi.dat";

    // Método para modificar un videojuego por código
    public static void modificarRegistro(int codigoBuscado, String nuevoNombre, String nuevaPlataforma,
            float nuevoPrecio, int nuevoAño) {
        List<VideojuegoLuismi> lista = new ArrayList<>();
        boolean encontrado = false;

        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "r")) {
            while (raf.getFilePointer() < raf.length()) {
                int codigo = raf.readInt();
                String nombre = raf.readUTF();
                String plataforma = raf.readUTF();
                float precio = raf.readFloat();
                int año = raf.readInt();

                if (codigo == codigoBuscado) {
                    nombre = nuevoNombre;
                    plataforma = nuevaPlataforma;
                    precio = nuevoPrecio;
                    año = nuevoAño;
                    encontrado = true;
                }
                lista.add(new VideojuegoLuismi(codigo, nombre, plataforma, precio, año));
            }
        } catch (IOException e) {
            System.out.println("A ocurrido un error con el acceso aleatorio");
            return;
        }

        if (!encontrado) {
            System.out.println("No se encontró el código: " + codigoBuscado);
            return;
        }

        // Reescribir todo el fichero con la lista actualizada
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (VideojuegoLuismi v : lista) {
                dos.writeInt(v.getCodigo());
                dos.writeUTF(v.getNombre());
                dos.writeUTF(v.getPlataforma());
                dos.writeFloat(v.getPrecio());
                dos.writeInt(v.getAño());
            }
            System.out.println("Registro modificado correctamente.");
        } catch (IOException e) {
            System.out.println("Error al escribir la modificacion");
        }
    }
}
