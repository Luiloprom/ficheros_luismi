package es.etg.dam;

import lombok.AllArgsConstructor;
import lombok.Data;

// Uso lombok para que me genere el constructor y los getter y setter automaticamente
@Data
@AllArgsConstructor
public class VideojuegoLuismi {
    private int codigo;
    private String nombre;
    private String plataforma;
    private float precio;
    private int año;
}
