package Classes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class Cliente implements Serializable{

    private String loginCliente;
    private String senhaCliente;
    private String nomeCliente;
    private HashSet<Midia> tableMidiasFuturas = new HashSet<>();
    private HashSet<Midia> tableMidiasAssistidas = new HashSet<>();
    private int quantidadeAvaliacoesTotal;
    private static final long serialVersionUID = 1;


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
        if(!tableMidiasAssistidas.contains(midia)){
            tableMidiasAssistidas.add(midia);
            midia.registrarVisualizacao();
        }
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

    public boolean verificaSeAssistiuMidia(Midia midia){
        return tableMidiasAssistidas.contains(midia);
    }

    public String relatorioMidiasAssistidas() throws NoSuchElementException{
        if(!tableMidiasAssistidas.isEmpty()){
            String report = geraStringRelatorioMidias(tableMidiasAssistidas);
            return report;
        }
        else{
            throw new NoSuchElementException("Lista de Midias assistidas está vazia");
        }
    }

    public String relatorioMidiasFuturas() throws NoSuchElementException{
        if(!tableMidiasFuturas.isEmpty()){
            String report = geraStringRelatorioMidias(tableMidiasFuturas);
            return report;
        }
        else{
            throw new NoSuchElementException("Lista de Midias futuras está vazia");
        }
    }

    private String geraStringRelatorioMidias(HashSet<Midia> lista){
        return lista.stream()
                        .map(m -> m.toString())
                       .reduce((m1,m2)-> m1.concat("\n".concat(m2))).get();
    }

}