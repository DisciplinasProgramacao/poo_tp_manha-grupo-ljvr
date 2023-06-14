package Classes;

import java.util.ArrayList;
import java.util.HashMap;

public class Plataforma {

	// atributos
	private ArrayList<Serie> listSeriesGeral;
	private HashMap<String, Cliente> tableClientes;
	private Cliente usuarioAtual;

	/**
	 * O construtor da classe Pataforma
	 * 
	 * @param listSeriesGeral ArrayList que recebe como parametro os dados da classe Serie
	 *                       
	 * @param tableClientes   Hashtable recebe como parametros os dados do cliente da Classe Cliente
	 *                     
	 * @param usuarioAtual    
	 * @return
	 */
	public Plataforma() {
		listSeriesGeral = new ArrayList<Serie>();
		tableClientes = new HashMap<String, Cliente>();
		usuarioAtual = null;
	}

	// Metodos de acesso e controle

	public ArrayList<Serie> getListSeriesGeral() {
		return listSeriesGeral;
	}

	public void setListSeriesGeral(ArrayList<Serie> listSeriesGeral) {
		this.listSeriesGeral = listSeriesGeral;
	}

	public HashMap<String, Cliente> getTableClientes() {
		return tableClientes;
	}

	public void setTableClientes(HashMap<String, Cliente> tableClientes) {
		this.tableClientes = tableClientes;
	}

	public Cliente getUsuarioAtual() {
		return usuarioAtual;
	}

	public void setUsuarioAtual(Cliente usuarioAtual) {
		this.usuarioAtual = usuarioAtual;
	}

	/**
	 * Metodo para adicionar um novo cliente. Adiciona um novo cliente na tabela
	 * HashMap Clientes, utilizando o e-mail do cliente como chave
	 * 
	 * @param novoCliente
	 */
	public void adicionarCliente(Cliente novoCliente) {
		tableClientes.put(novoCliente.getLogin(), novoCliente); 
	}

	/**
	 * O metodo removerCliente remove um cliente da tabela hashMap, utilizando o login
	 * como chave
	 * 
	 * @param email
	 */
	public void removerCliente(String login) {
		tableClientes.remove(login);
	}

	/**
	 * O metodo adicionarSerie adiciona uma nova serie no ArrayList listSeriesGeral
	 * 
	 * @param novaSerie
	 */
	public void adicionarSerie(Serie novaSerie) {
		listSeriesGeral.add(novaSerie);
	}

	/**
	 * O metodo removerSerie remove uma serie do ArrayList listSeriesGeral
	 * 
	 * @param serie
	 */
	public void removerSerie(Serie serie) {
		listSeriesGeral.remove(serie);
	}

	/**
	 * O metodo fazerLogin recebe como parametros o e-mail e a senha do cliente e
	 * realiza a validacao do login. Caso o e-mail esteja na tabela hash
	 * tableClientes e a senha seja valida, o metodo define o cliente correspondente
	 * como usuarioAtual e retorna true. Caso contrario, o metodo retorna false
	 * 
	 * @param email
	 * @param senha
	 * @return
	 */
	public boolean fazerLogin(String email, String senha) {
		if (tableClientes.containsKey(email)) {
			Cliente cliente = tableClientes.get(email);
			if (cliente.getSenha() == senha.hashCode()) { 
				usuarioAtual = cliente;
				return true;
			}
		}
		return false;
	}
	
	// a fazer

	/**
	 * Metodo filtrarListaSerieGeralNome que recebe como parametro o nome da serie digitada pelo usuario 
	 * @author jvasc
	 */

	public void filtrarListaSerieGeralNome() {
		
	}
	
	/**
	 * Metodo filtrarListaSerieGeralIdioma recebe como parametro o idioma digitado pelo usuario
	 */
	public void filtrarListaSerieGeralIdioma() {
		
	}
	
	/**
	 * Metodo filtrarListaSerieGeralIdioma recebe como parametro o idioma digitado pelo usuario
	 */
	public void filtrarListaSerieGeralGenero() {
		
	}
}
