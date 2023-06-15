package Classes;

public class FilmeLonga extends Midia {

    // atributos
    private int duracaoMin;

    /**
     * Construtor de FilmeLonga.
     * <p>
     * Caso duracaoMin for menor que 1 ira disparar uma IllegalArgumentException
     * 
     * @param idMidia        int
     * @param nome           String
     * @param idioma         String
     * @param genero         String
     * @param dataLancamento LocalDate
     * @param duracaoMin     String
     */
    public FilmeLonga(String idMidia, String nome, String idioma, String genero, String dataLancamento,
            int duracaoMin) {

        super(idMidia, nome, idioma, genero, dataLancamento);

        if (duracaoMin > 0)
            this.duracaoMin = duracaoMin;
        else
            throw new IllegalArgumentException("A duracao do filme nao pode ser menor que 1");

    }

    // get
    public int getDuracaoMin() {
        return duracaoMin;
    }

}
