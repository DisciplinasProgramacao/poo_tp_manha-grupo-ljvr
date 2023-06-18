package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Classes.Serie;

public class SerieTest {

    @Test
    void testVisualizacaoAoInstanciarSerie() {
        LocalDate dataExemplo = LocalDate.of(2050, 10, 5);
        Serie serie1 = new Serie(321312, "Serie1", "PT-br", "Ação", dataExemplo, 10);

        assertEquals(1, serie1.getVisualizacoesMidia());

    }

    @Test
    public void testNumeroDeEpsInvalido() {
        LocalDate dataExemplo = LocalDate.of(2050, 10, 5);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Serie(321312, "Serie1", "PT-br", "Ação", dataExemplo, 0);
            ;
        });
    }

}
