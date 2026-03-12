package com.vuelos.entities;

import lombok.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vuelo {
    private int idVuelo;
    private String nombreVuelo;
    private String empresaVuelo;
    private String lugarSalida;
    private String lugarLLegada;
    private LocalDate fechaSalida;
    private LocalDate fechaLlegada;
}
