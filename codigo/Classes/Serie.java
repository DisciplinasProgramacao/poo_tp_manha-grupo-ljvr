package Classes;

public class Serie extends Midia {

    // atributos
    private int quantidadeEpSerie;

    /**
     * Construtor de Serie
     * <p>
     * Caso a quantidadeEp for menor que 1 ira disparar uma IllegalArgumentException
     * 
     * @param idSerie             int
     * @param nomeSerie           String
     * @param idiomaSerie         String
     * @param generoSerie         String
     * @param dataLancamentoSerie LocalDate
     * @param quantidadeEpSerie   int
     */
    public Serie(String idSerie, String nomeSerie, String idiomaSerie, String generoSerie, String dataLancamentoSerie,
            int quantidadeEpSerie) {

        super(idSerie, nomeSerie, idiomaSerie, generoSerie, dataLancamentoSerie);

        if (quantidadeEpSerie > 0)
            this.quantidadeEpSerie = quantidadeEpSerie;
        else
            throw new IllegalArgumentException("A quantidade de episodios da serie e menor que 1");

    }

    // get and set
    public int getQuantidadeEpSerie() {
        return quantidadeEpSerie;
    }
    
}
