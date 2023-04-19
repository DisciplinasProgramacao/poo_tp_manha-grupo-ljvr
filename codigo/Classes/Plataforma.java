package Classes;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.HashMap;

public class Plataforma {

	// atributos
	private ArrayList<Serie> listSeriesGeral;
	/*
	 * 
	 * HashMap n�o � sincronizado, o que pode tornar sua utiliza��o mais eficiente
	 * em casos em que n�o h� preocupa��o com m�ltiplas threads acessando a
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
	 * @param listSeriesGeral ArrayList que recebe como par�metro os dados da classe
	 *                        Serie,
	 * @param tableClientes   Hashtable e
	 * @param usuarioAtual    que recebem como par�metros os dados do cliente da
	 *                        Classe Cliente
	 * @return
	 */
	public Plataforma() {
		listSeriesGeral = new ArrayList<Serie>();
		//tableClientes = new HashMap<String, Cliente>();
		tableClientes = new Hashtable<String, Cliente>();
		usuarioAtual = null;
	}

	// m�todos de acesso e controle

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
	 * M�todo para adicionar um novo cliente. Adiciona um novo cliente na tabela
	 * hash tableClientes, utilizando o e-mail do cliente como chave
	 * 
	 * @param novoCliente
	 */
	public void adicionarCliente(Cliente novoCliente) {
		tableClientes.put(novoCliente.getEmail(), novoCliente); //falta um get na classe cliente
	}

	/**
	 * O m�todo removerCliente remove um cliente da tabela hash, utilizando o e-mail
	 * como chave
	 * 
	 * @param email
	 */
	public void removerCliente(String email) {
		tableClientes.remove(email);
	}

	/**
	 * O m�todo adicionarSerie adiciona uma nova s�rie no ArrayList listSeriesGeral
	 * 
	 * @param novaSerie
	 */
	public void adicionarSerie(Serie novaSerie) {
		listSeriesGeral.add(novaSerie);
	}

	/**
	 * O m�todo removerSerie remove uma s�rie do ArrayList listSeriesGeral
	 * 
	 * @param serie
	 */
	public void removerSerie(Serie serie) {
		listSeriesGeral.remove(serie);
	}

	/**
	 * O m�todo fazerLogin recebe como par�metros o e-mail e a senha do cliente e
	 * realiza a valida��o do login. Caso o e-mail esteja na tabela hash
	 * tableClientes e a senha seja v�lida, o m�todo define o cliente correspondente
	 * como usuarioAtual e retorna true. Caso contr�rio, o m�todo retorna false
	 * 
	 * @param email
	 * @param senha
	 * @return
	 */
	public boolean fazerLogin(String email, String senha) {
		if (tableClientes.containsKey(email)) {
			Cliente cliente = tableClientes.get(email);
			if (cliente.getSenha() == senha.hashCode()) { //falta get na classe Cliente
				usuarioAtual = cliente;
				return true;
			}
		}
		return false;
	}

	/**
	 * M�todos a serem implementados futuramente
	 * @author jvasc
	 */

	// public void filtrarListaSerieGeralNome, que recebe como par�metro o nome da
	// s�rie digitada pelo usu�rio

	// public void filtrarListaSerieGeralIdioma, que recebe como par�metro o idioma
	// da s�rie digitada pelo usu�rio

	// public void filtrarListaSerieGeralGenero, que recebe como par�metro o g�nero
	// da s�rie digitada pelo usu�rio
}
