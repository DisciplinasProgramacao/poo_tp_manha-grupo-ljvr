package Classes;

public class FilmeLonga extends Midia {

    // atributos
    private int duracaoMinFilmeLonga;

    /**
     * Construtor de FilmeLonga.
     * <p>
     * Caso duracaoMin for menor que 1 ira disparar uma IllegalArgumentException
     * 
     * @param idFilmeLonga             int
     * @param nomeFilmeLonga           String
     * @param idiomaFilmeLonga         String
     * @param generoFilmeLonga         String
     * @param dataLancamentoFilmeLonga LocalDate
     * @param duracaoMinFilmeLonga     String
     */
    public FilmeLonga(String idFilmeLonga, String nomeFilmeLonga, String idiomaFilmeLonga, String generoFilmeLonga,
            String dataLancamentoFilmeLonga,
            int duracaoMinFilmeLonga) {

        super(idFilmeLonga, nomeFilmeLonga, idiomaFilmeLonga, generoFilmeLonga, dataLancamentoFilmeLonga);

        if (duracaoMinFilmeLonga > 0)
            this.duracaoMinFilmeLonga = duracaoMinFilmeLonga;
        else
            throw new IllegalArgumentException("A duracao do filme nao pode ser menor que 1");

    }

    // get
    public int getDuracaoMinFilmeLonga() {
        return duracaoMinFilmeLonga;
    }

}
