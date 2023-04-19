package Classes;
import java.util.HashSet;
import java.util.Random;

public class Cliente{

    private String email,senha,nome;
    private HashSet<Serie> listaSeriesFuturas = new HashSet<>();
    private HashSet<Serie> listaSeriesAssistidas = new HashSet<>();

    /**
     * Cria o Objeto Cliente recebendo os dados do usuario.
     * Se o dado nome receber qualquer valor invalido ou em branco, será mantido em branco.
     * Se os dados e-mail ou senha forem inseridos invalidos, seraão criados aleatorios para o cliente.
     * @param nome
     * @param email
     * @param senha
     */
    public Cliente (String nome, String email, String senha){
        inicializar (nome,email,senha);
    }

    private void inicializar (String nome, String email, String senha){
        
        if(email.isEmpty() || !email.contains("@")){
            this.email = gerarEmail();
        }
        else{
            this.email = email;
        }

        if(senha.isEmpty()){
          this.senha = gerarSenha();  
        }
        else{
            this.senha = senha;
        }

        this.nome = nome;
    }
    
    private String gerarEmail() {
        Random nRandom = new Random(1);
        return (Integer.toString(nRandom.nextInt(10000000)) + "@plataforma.com");
    }

    private String gerarSenha() {
        Random nRandom = new Random(1);
        return Integer.toString(nRandom.nextInt(10000000));
    }
    
    /**
     * Adiciona uma série à lista de Series para Assistir Futuramente de um cliente especifico.
     * @param serie
     */
    public void adicionarListaSeriesFuturas(Serie serie){
        listaSeriesFuturas.add(serie);
    }

    /**
     * Remove uma série especifica da lista de Series para Assistir Futuramente de um cliente.
     * @param serie
     */
    public void removerListaSeriesFuturas(Serie serie){
        listaSeriesFuturas.remove(serie);
    }

    /**
     * Adiciona uma série à lista de Series Assistidas de um cliente especifico.
     */
    public void adicionarListaSeriesAssistidas (Serie serie){
        listaSeriesAssistidas.add(serie);
    }

    public void filtrarSeriesFuturasNome (String nome){

    }

    public void filtrarSeriesFuturasIdioma (String idioma){
        
    }

    public void filtrarSeriesFuturasGenero (String genero){
        
    }

    public void filtrarSeriesAssistidassNome (String nome){

    }

    public void filtrarSeriesAssistidassIdioma (String idioma){
        
    }

    public void filtrarSeriesAssistidassGenero (String genero){
        
    }

}