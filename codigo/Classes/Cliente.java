package Classes;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.NoSuchElementException;

import Utilidades.*;

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

        /**
     * Metodo que retorna um String com a lista de midias na lista de Midias Assistidas do Cliente
     * @return String com lista de Midias;
     * @throws NoSuchElementException Lista vazia retorna exceção.
     */
    public String relatorioMidiasAssistidas() throws NoSuchElementException{
        if(!tableMidiasAssistidas.isEmpty()){
            String report = GeradorString.geraStringRelatorioMidias(tableMidiasAssistidas);
            return report;
        }
        else{
            throw new NoSuchElementException("Lista de Midias assistidas está vazia");
        }
    }

    /**
     * Metodo que retorna um String com a lista de midias na lista de Assistir Futuramente do Cliente
     * @return String com lista de Midias;
     * @throws NoSuchElementException Lista vazia retorna exceção.
     */
    public String relatorioMidiasFuturas() throws NoSuchElementException{
        if(!tableMidiasFuturas.isEmpty()){
            String report = GeradorString.geraStringRelatorioMidias(tableMidiasFuturas);
            return report;
        }
        else{
            throw new NoSuchElementException("Lista de Midias futuras está vazia");
        }
    }

    //// RELATORIO FILTROS //////

    /**
     * Retorna uma String com a lista de midias já assistidas filtradas por nome.
     * @param nome Nome que deseja-se pequisar na lista.
     * @return String com a lista de midias.
     */
    public String relatorioFiltroAssistidasNome(String nome){
        if(!tableMidiasFuturas.isEmpty()){
            Collection<Midia> filtrada = FiltroMidia.buscarNome(tableMidiasAssistidas, nome);
            return GeradorString.geraStringRelatorioMidias(filtrada);
        }
        else{
            throw new NoSuchElementException("Lista de Midias Assistidas está vazia");
        }
    }

    /**
     * Retorna uma String com a lista de midias já assistidas filtradas por idioma.
     * @param idioma Idioma que deseja-se pequisar na lista.
     * @return String com a lista de midias.
     */
    public String relatorioFiltroAssistidasIdioma(String idioma){
        if(!tableMidiasFuturas.isEmpty()){
            Collection<Midia> filtrada = FiltroMidia.buscarIdioma(tableMidiasAssistidas, idioma);
            return GeradorString.geraStringRelatorioMidias(filtrada);
        }
        else{
            throw new NoSuchElementException("Lista de Midias Assistidas está vazia");
        }
    }

    /**
     * Retorna uma String com a lista de midias já assistidas filtradas por genero.
     * @param genero Genero que deseja-se pequisar na lista.
     * @return String com a lista de midias.
     */
    public String relatorioFiltroAssistidasGenero(String genero){
        if(!tableMidiasFuturas.isEmpty()){
            Collection<Midia> filtrada = FiltroMidia.buscarGenero(tableMidiasAssistidas, genero);
            return GeradorString.geraStringRelatorioMidias(filtrada);
        }
        else{
            throw new NoSuchElementException("Lista de Midias Assistidas está vazia");
        }
    }

    /**
     * Retorna uma String com a lista de midias para assistir futuramente filtradas por nome.
     * @param nome Nome que deseja-se pequisar na lista.
     * @return String com a lista de midias.
     */
    public String relatorioFiltroFuturassNome(String nome){
        if(!tableMidiasFuturas.isEmpty()){
            Collection<Midia> filtrada = FiltroMidia.buscarNome(tableMidiasFuturas, nome);
            return GeradorString.geraStringRelatorioMidias(filtrada);
        }
        else{
            throw new NoSuchElementException("Lista de Midias Futuras está vazia");
        }
    }

    /**
     * Retorna uma String com a lista de midias para assistir futuramente por idioma.
     * @param idioma Idioma que deseja-se pequisar na lista.
     * @return String com a lista de midias.
     */
    public String relatorioFiltroFuturassIdioma(String idioma){
        if(!tableMidiasFuturas.isEmpty()){
            Collection<Midia> filtrada = FiltroMidia.buscarIdioma(tableMidiasFuturas, idioma);
            return GeradorString.geraStringRelatorioMidias(filtrada);
        }
        else{
            throw new NoSuchElementException("Lista de Midias Futuras está vazia");
        }
    }

    /**
     * Retorna uma String com a lista de midias para assistir futuramente filtradas por genero.
     * @param genero Genero que deseja-se pequisar na lista.
     * @return String com a lista de midias.
     */
    public String relatorioFiltroFuturassGenero(String genero){
        if(!tableMidiasFuturas.isEmpty()){
            Collection<Midia> filtrada = FiltroMidia.buscarGenero(tableMidiasAssistidas, genero);
            return GeradorString.geraStringRelatorioMidias(filtrada);
        }
        else{
            throw new NoSuchElementException("Lista de Midias Futuras está vazia");
        }
    }

}