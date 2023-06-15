package Classes;

public class Serie extends Midia {

    // atributos
    private int quantidadeEp;

    /**
     * Construtor de Serie
     * <p>
     * Caso a quantidadeEp for menor que 1 ira disparar uma IllegalArgumentException
     * 
     * @param idMidia        int
     * @param nome           String
     * @param idioma         String
     * @param genero         String
     * @param dataLancamento LocalDate
     * @param quantidadeEp   int
     */
    public Serie(String idMidia, String nome, String idioma, String genero, String dataLancamento, int quantidadeEp) {

        super(idMidia, nome, idioma, genero, dataLancamento);

        if (quantidadeEp > 0)
            this.quantidadeEp = quantidadeEp;
        else
            throw new IllegalArgumentException("A quantidade de episodios da serie e menor que 1");

    }

    // get and set

    public int getQuantidadeEp() {
        return quantidadeEp;
    }

    // set pode ser necessario caso a serie atualize o numero de ep
    public void setQuantidadeEp(int quantidadeEp) {
        this.quantidadeEp = quantidadeEp;
    }

}
