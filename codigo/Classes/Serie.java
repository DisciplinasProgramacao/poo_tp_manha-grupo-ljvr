package Classes;

public class Serie {
    // atributos
    private String nomeSerie;
    private String idiomaSerie;
    private String generoSerie;
    private int visualizacoes = 0;

    /**
     * Construtor de serie. Caso nome, idioma e genero for vazio sera inicializado com o valor null.
     * Sempre que for criado um novo objeto serie será contado uma visualização.
     * @param nome String
     * @param idioma String
     * @param genero String
     */ 
    Serie(String nome, String idioma, String genero){
        
        if(!nome.isEmpty()){
            this.nomeSerie = nome;
        } else {
            this.nomeSerie = null;
        }

        if(!idioma.isEmpty()){
            this.idiomaSerie = idioma;
        }else {
            this.nomeSerie = null;
        }

        if(!genero.isEmpty()){
            this.generoSerie = genero;
        }else {
            this.nomeSerie = null;
        }

        registrarVisualizacao();

    }

    // metodos
    public void registrarVisualizacao(){
        visualizacoes++;
    }

    // getters e setters
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

}
