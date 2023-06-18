package Classes;

public abstract class Midia {

    // atributos
    private String idMidia;
    private String nomeMidia;
    private String idiomaMidia;
    private String generoMidia;
    private String dataLancamentoMidia;
    private int visualizacoesMidia = 0;

    /**
     * Construtor de midia.
     * <p>
     * Caso nome, idioma ou genero for vazio sera disparado uma
     * IllegalArgumentException.
     * 
     * Caso o id seja menor que 0 ira disparar uma IllegalArgumentException.
     * 
     * <p>
     * Sempre que for instanciado será contado uma visualização.
     * 
     * @param idMidia        int
     * @param nome           String
     * @param idioma         String
     * @param genero         String
     * @param dataLancamento String
     * 
     */
    public Midia(String idMidia, String nome, String idioma, String genero, String dataLancamento) {

        // TO DO: rever esse construtor ao defininir como sera as datas

        if (!nome.isEmpty()) {
            this.nomeMidia = nome;
        } else {
            throw new IllegalArgumentException("O nome atribuido a midia e vazio");
        }

        if (!idioma.isEmpty()) {
            this.idiomaMidia = idioma;
        } else {
            throw new IllegalArgumentException("O idioma atribuido a midia e vazio");
        }

        if (!genero.isEmpty()) {
            this.generoMidia = genero;
        } else {
            throw new IllegalArgumentException("O genero atribuido a midia e vazio");
        }

        if (idMidia != "0")
            this.idMidia = idMidia;
        else
            throw new IllegalArgumentException("O Id atribuido a midia e invalido");

        this.dataLancamentoMidia = dataLancamento;

        registrarVisualizacao();

    }

    // metodos
    public void registrarVisualizacao() {
        visualizacoesMidia++;
    }

    // getters
    public String getNomeMidia() {
        return nomeMidia;
    }

    public String getIdiomaMidia() {
        return idiomaMidia;
    }

    public String getGeneroMidia() {
        return generoMidia;
    }

    public int getVisualizacoesMidia() {
        return visualizacoesMidia;
    }

    public String getIdMidia() {
        return idMidia;
    }

    public String getDataLancamentoMidia() {
        return dataLancamentoMidia;
    }

}
