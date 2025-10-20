package es.etg.dam;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VideojuegoLuismi {
    private int codigo;
    private String nombre;
    private String plataforma;
    private float precio;
    private int año;
}
