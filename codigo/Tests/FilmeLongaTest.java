package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Classes.FilmeLonga;
import Classes.Midia;

public class FilmeLongaTest {

    void testVisualizacaoAoInstanciarMidiaFilmeLonga() {

        Midia filmeLonga = new FilmeLonga("321312", "Filmin", "PT-br", "Ação", "20/01/2044", 180);

        assertEquals(1, filmeLonga.getVisualizacoesMidia());

    }

    @Test
    public void testNumeroDeEpsInvalidoEmMidiaFilmeLonga() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new FilmeLonga("321312", "Filmin", "PT-br", "Ação", "21/02/2005", 0);
        });

    }
}
