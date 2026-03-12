package com.vuelos;

import com.vuelos.entities.Vuelo;
import com.vuelos.utiles.GestorDeVuelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // Requisito: Definir constantes de fecha (pueden ser null y funciona)
    private static final LocalDate FECHA_INICIO = LocalDate.of(2025, 5, 10);
    private static final LocalDate FECHA_FIN = LocalDate.of(2025, 5, 20);

    public static void main(String[] args) {

        List<Vuelo> listaVuelos = new ArrayList<>();

        // Banco de datos (10 vuelos)
        // ENTRA (Límite)
        listaVuelos.add(new Vuelo(1, "H001-V", "Iberia", "Madrid", "New York", LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 11)));
        // FUERA (Antes)
        listaVuelos.add(new Vuelo(2, "H002-V", "Turkish", "Barcelona", "Estambul", LocalDate.of(2025, 5, 2), LocalDate.of(2025, 5, 3)));
        // FUERA (Después)
        listaVuelos.add(new Vuelo(3, "H003-V", "Lufthansa", "Madrid", "Berlin", LocalDate.of(2025, 5, 25), LocalDate.of(2025, 5, 26)));
        // ENTRA (Centro)
        listaVuelos.add(new Vuelo(4, "H004-V", "Air Europa", "Madrid", "Cancún", LocalDate.of(2025, 5, 15), LocalDate.of(2025, 5, 17)));
        // ENTRA (Límite fecha inicio)
        listaVuelos.add(new Vuelo(5, "H005-V", "Iberia", "Sevilla", "Madrid", LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 11)));
        // ENTRA (limite fecha fin)
        listaVuelos.add(new Vuelo(6, "H006-V", "Turkish", "Madrid", "Tokio", LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 21)));
        // FUERA (Por un día)
        listaVuelos.add(new Vuelo(7, "H007-V", "Vueling", "Bilbao", "París", LocalDate.of(2025, 5, 9), LocalDate.of(2025, 5, 9)));
        // ENTRA (Centro)
        listaVuelos.add(new Vuelo(8, "H008-V", "Iberia", "Madrid", "Miami", LocalDate.of(2025, 5, 14), LocalDate.of(2025, 5, 15)));
        // FUERA (Por un día)
        listaVuelos.add(new Vuelo(9, "H009-V", "Ryanair", "Valencia", "Roma", LocalDate.of(2025, 5, 21), LocalDate.of(2025, 5, 21)));
        // ENTRA (Centro)
        listaVuelos.add(new Vuelo(10, "H010-V", "Turkish", "Madrid", "Dubai", LocalDate.of(2025, 5, 11), LocalDate.of(2025, 5, 13)));

        // Filtrado
        List<Vuelo> filtrados = GestorDeVuelos.filtrarVuelos(listaVuelos, FECHA_INICIO, FECHA_FIN);

        // Imprimir resultados
        imprimirVuelos(filtrados);
    }

    private static void imprimirVuelos(List<Vuelo> vuelos) {
        System.out.println("\n>>> CRITERIO: Desde " + FECHA_INICIO + " hasta " + FECHA_FIN);
        String formato = "| %-3s | %-8s | %-15s | %-12s | %-12s | %-12s |%n";

        System.out.println("-----------------------------------------------------------------------------------");
        System.out.printf(formato, "ID", "VUELO", "AEROLINEA", "ORIGEN", "DESTINO", "FECHA SAL.");
        System.out.println("-----------------------------------------------------------------------------------");

        vuelos.forEach(v -> System.out.printf(formato,
                v.getIdVuelo(), v.getNombreVuelo(), v.getEmpresaVuelo(),
                v.getLugarSalida(), v.getLugarLLegada(), v.getFechaSalida()));

        if (vuelos.isEmpty()) System.out.println("|              No se encontraron vuelos para los criterios dados.             |");
        System.out.println("-----------------------------------------------------------------------------------");
    }
}
