package Classes;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

// TO DO: rever funcionamento da classe antes da implementação final e remover code smell antes de publicar o app

public class Plataforma implements Serializable{

	// atributos
	private HashMap<String, Midia> tableMidiasGerais;
	private HashMap<String, Cliente> tableClientes;
	private static final long serialVersionUID = 1;
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
	}

	// metodo de login
	// TO DO: rever funcionamento do metodo fazerLogin


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
		tableClientes.put(novoCliente.getLoginCliente(), novoCliente);
	}

	/**
	 * Adiciona uma nova midia em tableMidiaGerais
	 * 
	 * @param novaMidia
	 */
	public void adicionarMidia(String id, Midia novaMidia) {
		tableMidiasGerais.put(id, novaMidia);
	}
	
	// #####Relatorios

	/**
	 * Relatorio que retorna o nome do usuario com maior número de midias assistidas.
	 * @return Retorna uma String com o nome um texto padrão, contendo o nome do usuario e a quantidade de midias assistidas.
	 */
	public String relatorioClienteMaisMidias(){
		Cliente  cliente = tableClientes.values().stream()
										.max((c1,c2) -> (c1.qtdeMidiasAssistidas()>c2.qtdeMidiasAssistidas()) ?1:-1).get();

		return "O cliente com mais midias assistidas é o " + cliente.getNomeCliente() + " com " + cliente.qtdeMidiasAssistidas() +
		" midias assistidas.";
	}

	public String relatorioClienteMaisAvaliacoes(){
		Cliente  cliente = tableClientes.values().stream()
										.max((c1,c2) -> (c1.getQuantidadeAvaliacoesTotal()>c2.getQuantidadeAvaliacoesTotal()) ?1:-1).get();

		return "O cliente com mais midias avaliadas é o " + cliente.getNomeCliente() + " com " + cliente.getQuantidadeAvaliacoesTotal() +
		" midias avaliadas.";
	}
	/**
	 * Retorna a porcentagem de usuarios que tem pelo menos 15 avaliações na plataforma.
	 * @return Retorna uma String com o nome um texto padrão, com a informação da porcentagem.
	 */
	public String relatorioPorcentagemClientes15Avaliacoes(){
		double  qtdeCliente15Avaliacoes = tableClientes.values().stream()
										.filter(c -> c.getQuantidadeAvaliacoesTotal()>=15)
										.count();
		double porcentagem = Double.parseDouble(String.format("%.3f",(qtdeCliente15Avaliacoes / tableClientes.size()) *100).replace(",","."));
		return "A porcentagem de midias que tem mais de 15 avaliaçõe é: " + porcentagem + "%";
	}

	/**
	 * Gera relatorio com a lista das 10 midias mais bem avaliadas, que foram assistidas ao menos 100 vezes.
	 * @return String com a lista de midias.
	 * @throws ArrayIndexOutOfBoundsException Retonra exceção caso a quantidade de midias assistidas mais de 100 vezes seja menor que 10.
	 */
	public String relatorioTop10MidiasAvaliacao()throws ArrayIndexOutOfBoundsException{
		List<Midia> listaOrdenada = tableMidiasGerais.values().stream()
									.filter(c-> c.getVisualizacoesMidia()>=100)
									.sorted((c1,c2)-> (c1.calcularMediaAvaliacoes()<c2.calcularMediaAvaliacoes())?1:-1)
									.toList();

		StringBuilder report = new StringBuilder("=== LISTA DAS 10 MIDIAS MAIS BEM AVALIADAS ===\n");

		for (int i = 0 ; i<10;i++){
			report.append("" + (i+1) +" - " + listaOrdenada.get(i).getNomeMidia() + "\n");
		}

		return report.toString();
		
	}
	/**
	 * Gera relatorio com a lista das 10 midias mais assistidas.
	 * @return String com a lista de midias.
	 * @throws ArrayIndexOutOfBoundsException Retonra exceção caso a quantidade de midias seja menor que 10.
	 */
	public String relatorioTop10MidiasVisualizacao()throws ArrayIndexOutOfBoundsException{
		List<Midia> listaOrdenada = tableMidiasGerais.values().stream()
									.sorted((c1,c2)-> (c1.getVisualizacoesMidia()<c2.getVisualizacoesMidia())?1:-1)
									.toList();

		StringBuilder report = new StringBuilder("=== LISTA DAS 10 MIDIAS MAIS ASSISTIDAS ===\n");

		for (int i = 0 ; i<10;i++){
			report.append("" + (i+1) +" - " + listaOrdenada.get(i).getNomeMidia() + "\n");
		}

		return report.toString();
		
	}


}