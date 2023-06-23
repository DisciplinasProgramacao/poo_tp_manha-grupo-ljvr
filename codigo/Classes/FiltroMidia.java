package Classes;

import java.util.Collection;

public abstract class FiltroMidia {

    public static Collection<Midia> buscarNome(Collection<Midia> lista, String nome) {
        return lista.stream()
                    .filter(m -> m.getNomeMidia().contains(nome))
                    .toList();
    }

    public static Collection<Midia> buscarIdioma(Collection<Midia> lista, String idioma) {
         return lista.stream()
                    .filter(m -> m.getIdiomaMidia().contains(idioma))
                    .toList();
    }

    public static Collection<Midia> buscarGenero(Collection<Midia> lista, String genero) {
         return lista.stream()
                    .filter(m -> m.getGeneroMidia().contains(genero))
                    .toList();
    }

}
