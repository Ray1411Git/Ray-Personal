package com.vuelos.utiles;

import com.vuelos.entities.Vuelo;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GestorDeVuelos {

    public static List<Vuelo> filtrarVuelos(List<Vuelo> vuelos, LocalDate inicio, LocalDate fin) {
        return vuelos.stream()
                // "Filtra si cumple con la fecha de inicio"
                .filter(v -> cumpleFechaInicio(v, inicio))
                // "Filtra si cumple con la fecha de fin"
                .filter(v -> cumpleFechaFin(v, fin))
                // Ordenación
                .sorted(Comparator.comparing(Vuelo::getFechaSalida))
                .collect(Collectors.toList());
    }

    // Metodo para mira la fecha de inicio
    private static boolean cumpleFechaInicio(Vuelo v, LocalDate inicio) {
        return Optional.ofNullable(inicio)
                .map(fecha -> !v.getFechaSalida().isBefore(fecha))
                .orElse(true);
    }

    // Metodo para mira la fecha de fin
    private static boolean cumpleFechaFin(Vuelo v, LocalDate fin) {
        return Optional.ofNullable(fin)
                .map(fecha -> !v.getFechaSalida().isAfter(fecha))
                .orElse(true);
    }
}
