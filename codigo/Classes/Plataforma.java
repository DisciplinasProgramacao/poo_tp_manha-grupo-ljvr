package Classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;

public class Plataforma {

	// atributos
	private ArrayList<Serie> listSeriesGeral;
	/*
	 * 
	 * HashMap não é sincronizado, o que pode tornar sua utilização mais eficiente
	 * em casos em que não há preocupação com múltiplas threads acessando a
	 * estrutura de dados.
	 * 
	 * 
	 */
	// private HashMap<String, Cliente> tableClientes;
	private Hashtable<String, Cliente> tableClientes;
	private Cliente usuarioAtual;

	/**
	 * O construtor da classe Pataforma
	 * 
	 * @param listSeriesGeral ArrayList que recebe como parâmetro os dados da classe
	 *                        Serie,
	 * @param tableClientes   Hashtable e
	 * @param usuarioAtual    que recebem como parâmetros os dados do cliente da
	 *                        Classe Cliente
	 * @return
	 */
	public Plataforma() {
		listSeriesGeral = new ArrayList<Serie>();
		//tableClientes = new HashMap<String, Cliente>();
		tableClientes = new Hashtable<String, Cliente>();
		usuarioAtual = null;
	}

	// métodos de acesso e controle

	public ArrayList<Serie> getListSeriesGeral() {
		return listSeriesGeral;
	}

	public void setListSeriesGeral(ArrayList<Serie> listSeriesGeral) {
		this.listSeriesGeral = listSeriesGeral;
	}

	public Hashtable<String, Cliente> getTableClientes() {
		return tableClientes;
	}

	public void setTableClientes(Hashtable<String, Cliente> tableClientes) {
		this.tableClientes = tableClientes;
	}

	public Cliente getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Cliente usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	/**
	 * Método para adicionar um novo cliente. Adiciona um novo cliente na tabela
	 * hash tableClientes, utilizando o e-mail do cliente como chave
	 * 
	 * @param novoCliente
	 */
	public void adicionarCliente(Cliente novoCliente) {
		tableClientes.put(novoCliente.getEmail(), novoCliente); //falta um get na classe cliente
	}

	/**
	 * O método removerCliente remove um cliente da tabela hash, utilizando o e-mail
	 * como chave
	 * 
	 * @param email
	 */
	public void removerCliente(String email) {
		tableClientes.remove(email);
	}

	/**
	 * O método adicionarSerie adiciona uma nova série no ArrayList listSeriesGeral
	 * 
	 * @param novaSerie
	 */
	public void adicionarSerie(Serie novaSerie) {
		listSeriesGeral.add(novaSerie);
	}

	/**
	 * O método removerSerie remove uma série do ArrayList listSeriesGeral
	 * 
	 * @param serie
	 */
	public void removerSerie(Serie serie) {
		listSeriesGeral.remove(serie);
	}

	/**
	 * O método fazerLogin recebe como parâmetros o e-mail e a senha do cliente e
	 * realiza a validação do login. Caso o e-mail esteja na tabela hash
	 * tableClientes e a senha seja válida, o método define o cliente correspondente
	 * como usuarioAtual e retorna true. Caso contrário, o método retorna false
	 * 
	 * @param email
	 * @param senha
	 * @return
	 */
	public boolean fazerLogin(String email, String senha) {
		if (tableClientes.containsKey(email)) {
			Cliente cliente = tableClientes.get(email);
			if (cliente.getSenha().equals(senha)) { //falta get na classe Cliente
				usuarioAtual = cliente;
				return true;
			}
		}
		return false;
	}

	/**
	 * Métodos a serem implementados futuramente
	 * @author jvasc
	 */

	// public void filtrarListaSerieGeralNome, que recebe como parâmetro o nome da
	// série digitada pelo usuário

	// public void filtrarListaSerieGeralIdioma, que recebe como parâmetro o idioma
	// da série digitada pelo usuário

	// public void filtrarListaSerieGeralGenero, que recebe como parâmetro o gênero
	// da série digitada pelo usuário
}
