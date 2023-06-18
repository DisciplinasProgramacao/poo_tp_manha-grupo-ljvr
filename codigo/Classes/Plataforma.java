package Classes;

import java.util.HashMap;

// TO DO: rever funcionamento da classe antes da implementação final e remover code smell antes de publicar o app

public class Plataforma {

	// atributos
	private HashMap<String, Midia> tableMidiasGerais;
	private HashMap<String, Cliente> tableClientes;
	private Cliente usuarioAtual;

	/**
	 * O construtor da classe Pataforma
	 * 
	 * @param tableMidiasGerais HashMap que recebe como parametros os dados da
	 *                          classe Midia, com a chave sendo uma String
	 * 
	 * @param tableClientes     Hashtable que recebe como parametros os dados da
	 *                          da classe Cliente, com a chave sendo uma String
	 * 
	 * @param usuarioAtual      Usuario atual sendo manuseado pela plataforma
	 * 
	 */
	public Plataforma() {
		tableMidiasGerais = new HashMap<String, Midia>();
		tableClientes = new HashMap<String, Cliente>();
		usuarioAtual = null;
	}

	// metodo de login
	// TO DO: rever funcionamento do metodo fazerLogin

	/**
	 * O metodo fazerLogin recebe como parametros o e-mail e a senha do cliente e
	 * realiza a validacao do login. Caso o e-mail esteja na tabela hash
	 * tableClientes e a senha seja valida, o metodo define o cliente
	 * correspondente
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
				this.usuarioAtual = cliente;
				return true;
			}
		}
		return false;
	}

	// metodos de acesso

	/**
	 * Retorna uma Midia, bunscando o partir do ID.
	 * 
	 * @param id ID da Midia.
	 * @return Midia.
	 */
	public Midia encontrarMidia(String id) {
		return tableMidiasGerais.get(id);
	}

	/**
	 * Retorna um Cliente, bunscando o partir do Login.
	 * 
	 * @param login Login do Usuario.
	 * @return Cliente.
	 */
	public Cliente encontrarCliente(String login) {
		return tableClientes.get(login);
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
	 * Adiciona uma nova midia em tableMidiaGerais
	 * 
	 * @param novaMidia
	 */
	public void adicionarMidia(String id, Midia novaMidia) {
		tableMidiasGerais.put(id, novaMidia);
	}

}
