package Classes;

import java.util.HashMap;

public class Plataforma {

	// atributos
	private HashMap<String,Serie> listSeriesGeral;
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
		listSeriesGeral = new HashMap<String,Serie>();
		tableClientes = new HashMap<String, Cliente>();
		usuarioAtual = null;
	}

	// Metodos de acesso e controle
	
	/**
	 * Retorna um Cliente, bunscando o partir do Login.
	 * @param login Login do Usuario.
	 * @return Cliente.
	 */
	public Cliente encontrarCliente(String login) {
		return tableClientes.get(login);
	}

	/**
	 * Retorna uma Midia, bunscando o partir do ID.
	 * @param id ID da Midia.
	 * @return Midia.
	 */
	public Serie encontrarSerie(String id) {
		return listSeriesGeral.get(id);
	}

	public Cliente getUsuarioAtual() {
		return usuarioAtual;
	}

	private void setUsuarioAtual(Cliente usuarioAtual) {
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
	public void adicionarSerie(String id , Serie novaSerie) {
		listSeriesGeral.put(id, novaSerie);
	}

	/**
	 * O metodo removerSerie remove uma serie do ArrayList listSeriesGeral
	 * 
	 * @param serie
	 */
	public void removerSerie(String id) {
		listSeriesGeral.remove(id);
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
				setUsuarioAtual(cliente);
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
