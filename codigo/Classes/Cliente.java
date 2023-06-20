package Classes;

import java.util.HashSet;

public class Cliente {

    private String loginCliente;
    private String senhaCliente;
    private String nomeCliente;
    private HashSet<Midia> tableMidiasFuturas = new HashSet<>();
    private HashSet<Midia> tableMidiasAssistidas = new HashSet<>();
    private Map<String, Avaliacao> avaliacoes;

    /**
     * Cria o Objeto Cliente recebendo os dados do usuario.
     * 
     * Se os dados e-mail, senha, senha forem inseridos invalidos <- TO DO
     * 
     * @param nomeCliente
     * @param loginCliente
     * @param senhaCliente
     */
    public Cliente(String nomeCliente, String loginCliente, String senhaCliente) {
        inicializar(nomeCliente, loginCliente, senhaCliente);
    }
    
    public Cliente(String login, String senha) {
        this.login = login;
        this.senha = senha;
        this.avaliacoes = new HashMap<>();
    }

    // TO DO: verificacoes de inicializacao
    private void inicializar(String nomeCliente, String loginCliente, String senhaCliente) {

        if (loginCliente.isEmpty()) {
            this.loginCliente = loginCliente;
        } else {
            this.loginCliente = loginCliente;
        }

        if (loginCliente.isEmpty()) {
            this.senhaCliente = senhaCliente;
        } else {
            this.senhaCliente = senhaCliente;
        }

        this.nomeCliente = nomeCliente;
    }

    /**
     * Adiciona uma midia Ã  table de midias para Assistir Futuramente de um cliente
     * especifico.
     * 
     * @param Midia
     */
    public void adicionarTableMidiasFuturas(Midia midia) {
        tableMidiasFuturas.add(midia);
    }

    /**
     * Remove uma midia especifica da table de midia para Assistir Futuramente de
     * um cliente.
     * 
     * @param midia
     */
    public void removerTableMidiasFuturas(Midia midia) {
        tableMidiasFuturas.remove(midia);
    }

    /**
     * Adiciona uma midia Ã  lista de midia Assistidas de um cliente especifico.
     */
    public void adicionarTableMidiasAssistidas(Midia midia) {
        tableMidiasAssistidas.add(midia);
    }
    
    
    public void adicionarAvaliacao(Midia midia, int nota, String comentario) {
        if (avaliacoes.containsKey(midia.getId())) {
            throw new IllegalStateException("Você já avaliou esta mídia.");
        }

        Avaliacao avaliacao = new Avaliacao(this, nota, comentario);
        avaliacoes.put(midia.getId(), avaliacao);
    }

    public boolean hasAvaliacao(Midia midia) {
        return avaliacoes.containsKey(midia.getId());
    }

    public boolean isEspecialista() {
        int contadorAvaliacoesMesAnterior = 0;
        // Lógica para contar as avaliações do mês anterior
        // ...

        return contadorAvaliacoesMesAnterior >= 5;
    }
    

    public String getLoginCliente() {
        return this.loginCliente;
    }

    public int getSenhaCliente() {
        return this.senhaCliente.hashCode();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

}