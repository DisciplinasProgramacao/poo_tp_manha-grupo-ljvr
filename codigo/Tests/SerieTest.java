package Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import Classes.Midia;
import Classes.Serie;

public class SerieTest {

    @Test
    void testVisualizacaoAoInstanciarMidiaSerie() {
        Midia serie = new Serie("225", "Mundo Louco", "Pt-br", "Humor e piadas", "23/11/2002", 55);

        assertEquals(1, serie.getVisualizacoesMidia());

    }

    @Test
    public void testNumeroDeEpsInvalidoAoInstanciarMidiaSerie() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Serie("321312", "Serie1", "PT-br", "Ação", "22/01/2020", 0);
            ;
        });
    }

}
