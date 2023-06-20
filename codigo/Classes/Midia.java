package Classes;

import java.util.HashMap;
import java.util.Map;

/**
 * M�todo auxiliar para calcular a m�dia de uma m�dia espec�fica
 */
public double calcularMediaAvaliacoes() {
    if (avaliacoes.isEmpty()) {
        return 0.0;
    }

    	int somaNotas = 0;
    	for (Avaliacao avaliacao : avaliacoes.values()) {
        somaNotas += avaliacao.getNota();
    }

    	return (double) somaNotas / avaliacoes.size();
	}

public abstract class Midia {

    // atributos
    private String idMidia;
    private String nomeMidia;
    private String idiomaMidia;
    private String generoMidia;
    private String dataLancamentoMidia;
    private int visualizacoesMidia = 0;
    private Map<Cliente, Avaliacao> avaliacoes = new HashMap<>();

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
     * Calcula a m�dia individual de cada m�dia que est� dentro da tableMidiasGerais
     */
    public Map<Midia, Double> calcularMediaIndividualPorMidia() {
        return tableMidiasGerais.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, entry -> entry.getValue().calcularMediaAvaliacoes()));
    }
}
	
	/**
	 * M�todo Para calcular a m�dia de uma m�dia espec�fica, recebendo o idMidia como chave
	 */
		
	public Double calcularMediaIndividualPorMidia(String idMidia) {
        Midia midia = tableMidiasGerais.get(idMidia);
        if (midia != null) {
            return midia.calcularMediaAvaliacoes();
        }
        return null;
    }


    /**
     * adiciona uma avalia��o e coment�rio
     */
    public void adicionarAvaliacao(Cliente cliente, int nota) {
        if (avaliacoes.containsKey(cliente)) {
            throw new IllegalStateException("O cliente j� avaliou esta m�dia.");
        }

        Avaliacao avaliacao = new Avaliacao(nota, cliente);
        avaliacoes.put(cliente, avaliacao);
    }

    /**
     * Verifica se o cliente j� avaliou a midia
     */
    public boolean hasAvaliacao(Cliente cliente) {
        return avaliacoes.containsKey(cliente);
    }

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

}
