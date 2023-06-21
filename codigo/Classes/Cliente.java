package Classes;

import java.io.Serializable;
import java.util.HashSet;

public class Cliente implements Serializable{

    private String loginCliente;
    private String senhaCliente;
    private String nomeCliente;
    private HashSet<Midia> tableMidiasFuturas = new HashSet<>();
    private HashSet<Midia> tableMidiasAssistidas = new HashSet<>();
    private int quantidadeAvaliacoesTotal;

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

    // TO DO: verificacoes de inicializacao
    private void inicializar(String nomeCliente, String loginCliente, String senhaCliente) {

        if (!loginCliente.isEmpty()) {
            this.loginCliente = loginCliente;
        } else {
            throw new ExceptionInInitializerError("Login em branco");
        }

        if (!senhaCliente.isEmpty()) {
            this.senhaCliente = senhaCliente;
        } else {
            throw new ExceptionInInitializerError("Senha em branco");
        }

        this.nomeCliente = nomeCliente;
        this.quantidadeAvaliacoesTotal = 0;
    }

    /**
     * Adiciona uma midia a table de midias para Assistir Futuramente de um cliente
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
     * Adiciona uma midia a lista de midia Assistidas de um cliente especifico.
     */
    public void adicionarTableMidiasAssistidas(Midia midia) {
        tableMidiasAssistidas.add(midia);
        midia.registrarVisualizacao();
    }

    public void registrarAvaliacao(){
        quantidadeAvaliacoesTotal++;
    }

    public int getQuantidadeAvaliacoesTotal(){
        return this.quantidadeAvaliacoesTotal;
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

    public int qtdeMidiasAssistidas(){
        return tableMidiasAssistidas.size();
    }

}