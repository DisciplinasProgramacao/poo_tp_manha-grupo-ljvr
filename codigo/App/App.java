import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Classes.Cliente;
import Classes.FilmeLonga;
import Classes.Midia;
import Classes.Plataforma;
import Classes.Serie;

public class App {

    public static Plataforma plataforma = new Plataforma();

    static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        // variaveis da main
        Cliente clienteAtual;
        int opcao = -1;

        do {
            try {
                opcao = menuPrincipalPlataforma();

                switch (opcao) {
                    case 1:
                        Cliente usuarioLogado = menuLoginCliente();
                        if (usuarioLogado == null)
                            throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
                        else
                            System.out.println("AQUI ACHOU O USUARIO, FALTA MANUSEAR!!");
                    case 2:
                        System.out.println("Vai mostrar catalogo");
                        System.out.println("Vai mostrar catalogo");
                        System.out.println("Vai mostrar catalogo");
                        System.out.println("Vai mostrar catalogo");
                        break;
                    case 3:
                        Cliente clienteCriado = menuCriacaoDeCliente();
                        plataforma.adicionarCliente(clienteCriado);
                        break;
                    default:
                        if (opcao != 0) {
                            System.out.println("Opção invalida!");
                            espera();
                            break;
                        } else {
                            System.out.println("Até a próxima!");
                            espera();
                            break;
                        }
                }
            } catch (NumberFormatException erro) {
                limparTela();
                System.out.println("Caractere invalido!");
                espera();
            } catch (UsuarioNaoEncontradoException erro) {
                limparTela();
                System.out.println("Usuario não encontrado!");
                espera();
            }

        } while (opcao != 0);

        carregaDadosIniciais();
    }

    public static int menuPrincipalPlataforma() {
        System.out.println("=====Menu inicial=====");
        System.out.println("1 - Login");
        System.out.println("2 - Mostrar catalogo");
        System.out.println("3 - Criar usuario");
        System.out.println("0 - Sair");
        System.out.println("======================");
        System.out.println("Digite a opção");
        return Integer.parseInt(teclado.nextLine());
    }

    public static Cliente menuCriacaoDeCliente() {

        System.out.println("=====Bem vindo novo cliente=====");
        System.out.println("Insira seu novo login: ");
        String login = teclado.nextLine();
        System.out.println("Insira seu nome: ");
        String nome = teclado.nextLine();
        System.out.println("Insira sua senha: ");
        String senha = teclado.nextLine();
        System.out.println("================================");
        System.out.println("Usuario criado com sucesso!");

        espera();

        return new Cliente(nome, login, senha);
    }

    public static Cliente menuLoginCliente() {
        System.out.println("=====Bem vindo cliente=====");
        System.out.println("Digite seu login");
        String login = teclado.nextLine();
        System.out.println("Digite sua senha");
        String senha = teclado.nextLine(); // ainda sem verificacao com senha <- lembrar disso
        System.out.println("===========================");

        return plataforma.encontrarCliente(login);
    }

    /**
     * Espera o usuario
     */
    public static void espera() {
        System.out.println("Pressione enter para continuar");
        teclado.nextLine();
    }

    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     *
     * @author João Caram Santos - Professor PUC Minas
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // #region carga de dados

    private static void carregaDadosIniciais() {
        carregaCliente();
        carregaFilmes();
        carregaSeries();
        carregaAudiencia();
        System.out.println();
    }

    /**
     * Carrega todos os Cliente no sistema se existir o arquivo
     * Dados/POO_Audiencia.csv
     * Em caso de não encontrar o arquivo, o metodo informa na tela e encerra a
     * inicialização do sistema
     */
    private static void carregaAudiencia() {
        try {
            ArrayList<String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Audiencia.csv");
            for (String[] linha : lista) {
                Cliente cliente = plataforma.encontrarCliente(linha[0]);
                Midia serie = plataforma.encontrarMidia(linha[2]);
                if (linha[1].equals("A")) {
                    cliente.adicionarTableMidiasAssistidas(serie);
                } else if (linha[1].equals("F")) {
                    cliente.adicionarTableMidiasFuturas(serie);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Formato do arquivo de Audiencia invalido.");
        }
    }

    /**
     * Carrega todas as Filmes no sistema se existir o arquivo Dados/POO_Filmes.csv
     * Em caso de não encontrar o arquivo, o metodo informa na tela e encerra a
     * inicialização do sistema
     */
    private static void carregaFilmes() {
        try {
            ArrayList<String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Filmes.csv");
            for (String[] linha : lista) {
                int duracaoFilme = Integer.parseInt(linha[3]);
                FilmeLonga serie = new FilmeLonga(linha[0], linha[1], "en", " ", linha[2], duracaoFilme);
                plataforma.adicionarMidia(linha[0], serie);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado.");
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
            System.out.println("Formato do arquivo de Filmes invalido.");
        }
    }

    /**
     * Carrega todas as Series no sistema se existir o arquivo Dados/POO_Series.csv
     * Em caso de não encontrar o arquivo, o metodo informa na tela e encerra a
     * inicialização do sistema
     */
    private static void carregaSeries() {
        try {
            ArrayList<String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Series.csv");
            for (String[] linha : lista) {
                Serie serie = new Serie(linha[0], linha[1], "en", " ", linha[2], 1);
                plataforma.adicionarMidia(linha[0], serie);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Formato do arquivo de Serie invalido.");
        }
    }

    /**
     * Carrega todos os Cliente no sistema se existir o arquivo
     * Dados/POO_Espectadores.csv
     * Em caso de não encontrar o arquivo, o metodo informa na tela e encerra a
     * inicialização do sistema
     */
    private static void carregaCliente() {
        try {
            ArrayList<String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Espectadores.csv");
            for (String[] linha : lista) {
                Cliente cliente = new Cliente(linha[0], linha[1], linha[2]);
                plataforma.adicionarCliente(cliente);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Formato do arquivo de Cliente invalido.");
        }
    }
    // #endregion
}
