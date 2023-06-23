package Classes;

import java.util.Collection;

public abstract class FiltroMidia {

    /**
     * Metodo especializado em filtro de midias com base no nome da Midia.
     * @param lista Collection de Midias
     * @param nome Nome a ser filtrado. Verifica as midias que CONTEM o texto pesquisado.
     * @return Collection de Midias com os resultados encontrados
     */
    public static Collection<Midia> buscarNome(Collection<Midia> lista, String nome) {
        return lista.stream()
                    .filter(m -> m.getNomeMidia().contains(nome))
                    .toList();
    }

    /**
     * Metodo especializado em filtro de midias com base no idioma da Midia.
     * @param lista Collection de Midias
     * @param nome Genero a ser filtrado. Verifica as midias que CONTEM o texto pesquisado.
     * @return Collection de Midias com os resultados encontrados
     */
    public static Collection<Midia> buscarIdioma(Collection<Midia> lista, String idioma) {
         return lista.stream()
                    .filter(m -> m.getIdiomaMidia().contains(idioma))
                    .toList();
    }

    /**
     * Metodo especializado em filtro de midias com base no genero da Midia.
     * @param lista Collection de Midias
     * @param nome Genero a ser filtrado. Verifica as midias que CONTEM o texto pesquisado.
     * @return Collection de Midias com os resultados encontrados
     */
    public static Collection<Midia> buscarGenero(Collection<Midia> lista, String genero) {
         return lista.stream()
                    .filter(m -> m.getGeneroMidia().contains(genero))
                    .toList();
    }

}
