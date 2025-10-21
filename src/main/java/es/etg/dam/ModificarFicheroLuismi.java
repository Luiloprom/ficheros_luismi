package es.etg.dam;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class ModificarFicheroLuismi {
    private static final String FILE_NAME = "src/main/resources/videojuegosLuismi.dat";

    // Método para modificar un videojuego por su código
    public static void modificarRegistro(int codigoBuscado, String nuevoNombre, String nuevaPlataforma,
            float nuevoPrecio, int nuevoAño) { // Le pasamos el codigo del videojuego a modificar y los datos del nuevo
                                               // videojuego
        List<VideojuegoLuismi> lista = new ArrayList<>(); // Creamos una lista de videojuegos
        boolean encontrado = false; // Inicializamos comprobacion en false

        try (RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "r")) { // Recoremos todos los videojuegos en modo
                                                                            // lectura con acceso aleatorio
            while (raf.getFilePointer() < raf.length()) { // Vamos guardando todos los datos en variables
                int codigo = raf.readInt();
                String nombre = raf.readUTF();
                String plataforma = raf.readUTF();
                float precio = raf.readFloat();
                int año = raf.readInt();

                if (codigo == codigoBuscado) { // Si el codigo a modificar existe entonces asignamos los nuevos datos a
                                               // las variables del videojuego a modificar
                    nombre = nuevoNombre;
                    plataforma = nuevaPlataforma;
                    precio = nuevoPrecio;
                    año = nuevoAño;
                    encontrado = true;
                }
                lista.add(new VideojuegoLuismi(codigo, nombre, plataforma, precio, año)); // Añadimos el videojuego otra
                                                                                          // vez a la lista, ya sea el
                                                                                          // nuevo o el antiguo si no se
                                                                                          // a encontrado coincidencia
            }
        } catch (IOException e) { // Si ocurre un error con el Random
            System.out.println("A ocurrido un error con el acceso aleatorio");
            return; // Para que el programa siga ejecutandose aun con el error
        }

        if (!encontrado) { // Si encontrado no es true entonces no lo a encontrado y mostramos esto
            System.out.println("No se encontró el código: " + codigoBuscado);
            return; // Para que el programa siga ejecutandose aun con el error
        }

        // Reescribir todo el fichero con la lista actualizada
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(FILE_NAME))) {
            for (VideojuegoLuismi v : lista) { // Volvemos a escribir todos los videojuegos y si se a modificado alguno
                                               // ya estara dentro de la lista y se sobreescribira
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
