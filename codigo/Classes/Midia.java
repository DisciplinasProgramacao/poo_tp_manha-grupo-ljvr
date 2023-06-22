package Classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public abstract class Midia implements Serializable {

    // atributos
    private String idMidia;
    private String nomeMidia;
    private String idiomaMidia;
    private String generoMidia;
    private String dataLancamentoMidia;
    private int visualizacoesMidia = 0;
    private Map<Cliente, Avaliacao> avaliacoes = new HashMap<>();
    private static final long serialVersionUID = 1;


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
     * @param idMidia        String
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
        
	/**
     * Retorna a media da avaliação de uma Midia. Se não houver avaliações, será retornado 0.
     * @return Retorna um valor double, a media das notas.
     */
    public double calcularMediaAvaliacoes() {
        if (avaliacoes.isEmpty()) {
            return 0.0;
        }

        int somaNotas = avaliacoes.values().stream()
                .mapToInt(Avaliacao::getNota)
                .sum();

        return (double) somaNotas / avaliacoes.size();
    }

    /**
     * Adiciona uma avaliacao a midia e verifica se o cliente ja avaliou a midia. Caso ja tenha avaliado sera lancada uma mensagem de procedimento ilegal.
     *
     * @param cliente O cliente que realiza a avaliação.
     * @param avaliacao    A avaliacao a ser atribuida a midia (entre 1 e 5).
     */
    public void adicionarAvaliacao(Avaliacao avaliacao) throws IllegalStateException,IllegalAccessError {
        if (avaliacoes.containsKey(avaliacao.getCliente())) {
            throw new IllegalStateException("O cliente ja avaliou esta media.");
        }
        if (avaliacao.getCliente().verificaSeAssistiuMidia(this)){ 
            avaliacoes.put(avaliacao.getCliente(), avaliacao);
        }
        else{
            throw new IllegalAccessError("O cliente não assitiu a Midia, não é possível avaliar");
        }
    }

    
    /**
     * Retorna a avaliação de um cliente. Retorna nulo se o cliente não tiver avaliado a midia.
     * @param cliente Cliente
     * @return Avaliacao;
     */
    public Avaliacao getAvaliacao(Cliente cliente) {
        return avaliacoes.get(cliente);
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

    @Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("=====================\n");
        sb.append("ID: '").append(idMidia).append('\'');
	    sb.append(", Nome: '").append(nomeMidia).append('\'');
	    sb.append(", Gênero: '").append(generoMidia).append('\'');
	    sb.append(", Idioma: '").append(idiomaMidia).append('\'');
	    sb.append(", Data de lançamento: '").append(dataLancamentoMidia).append('\'');
	    sb.append(", Visualizações: ").append(visualizacoesMidia).append("\n");
	    sb.append("=====================\n");
	    return sb.toString();
	}

    

}
