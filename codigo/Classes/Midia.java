package Classes;

import java.time.LocalDate;

public abstract class Midia {

    // atributos
    private int idMidia;
    private String nomeSerie;
    private String idiomaSerie;
    private String generoSerie;
    private LocalDate dataLancamento;
    private int visualizacoes = 0;

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
     * @param dataLancamento LocalDate
     * 
     */
    public Midia(int idMidia, String nome, String idioma, String genero, LocalDate dataLancamento) {

        // TO DO: rever esse construtor ao defininir como sera as datas

        if (!nome.isEmpty()) {
            this.nomeSerie = nome;
        } else {
            throw new IllegalArgumentException("O nome atribuido a midia e vazio");
        }

        if (!idioma.isEmpty()) {
            this.idiomaSerie = idioma;
        } else {
            throw new IllegalArgumentException("O idioma atribuido a midia e vazio");
        }

        if (!genero.isEmpty()) {
            this.generoSerie = genero;
        } else {
            throw new IllegalArgumentException("O genero atribuido a midia e vazio");
        }

        if (idMidia >= 0)
            this.idMidia = idMidia;
        else
            throw new IllegalArgumentException("O Id atribuido a midia e invalido");

        this.dataLancamento = dataLancamento;

        registrarVisualizacao();

    }

    // metodos
    public void registrarVisualizacao() {
        visualizacoes++;
    }

    // getters
    public String getNomeSerie() {
        return nomeSerie;
    }

    public String getIdiomaSerie() {
        return idiomaSerie;
    }

    public String getGeneroSerie() {
        return generoSerie;
    }

    public int getVisualizacoes() {
        return visualizacoes;
    }

    public int getIdMidia() {
        return idMidia;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

}