package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Classes.FilmeLonga;

public class FilmeLongaTest {
    void testVisualizacaoAoInstanciarSerie() {

        LocalDate dataExemplo = LocalDate.of(2050, 10, 5);
        FilmeLonga filmeLonga = new FilmeLonga(321312, "Filmin", "PT-br", "Ação", dataExemplo, 180);

        assertEquals(1, filmeLonga.getVisualizacoes());

    }

    @Test
    public void testNumeroDeEpsInvalido() {

        LocalDate dataExemplo = LocalDate.of(2050, 10, 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FilmeLonga(321312, "Filmin", "PT-br", "Ação", dataExemplo, 0);
        });

    }
}
