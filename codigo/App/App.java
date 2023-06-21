import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Classes.Cliente;
import Classes.FilmeLonga;
import Classes.Midia;
import Classes.Plataforma;
import Classes.Serie;

public class App {

    private static Plataforma plataforma = new Plataforma();

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        limparTela();
        carregaDadosIniciais();

        try {

            if (menuPlataforma() == 1)
                areaDoUsuario();
            else
                areaDaAdm();

        } catch (NumberFormatException | InputMismatchException error) {
            limparTela();
            System.out.println("Caractere invalido!");
        }
        carregaDadosIniciais();
    }

    // #region area USUARIO
    public static void areaDoUsuario() {

        int opcao = -1;

        do {
            Midia midiaSelecionada = null;
            limparTela();
            boolean resposta = true;
            try {
                opcao = menuPrincipalPlataformaUsuario();

                switch (opcao) {
                    case 1: // login
                        Cliente usuarioLogado = menuLoginCliente();
                        if (usuarioLogado == null)
                            throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
                        do {
                            switch (menuUsuarioLogado()) {
                                case 1: // procurar midia
                                    midiaSelecionada = menuProcurarMidia();
                                    if (midiaSelecionada == null) {
                                        limparTela();
                                        System.out.println("A midia seleciona não foi achada ou e invalida!");
                                        espera();
                                    }
                                    break;
                                case 2: // add midia
                                    verificaMidia(midiaSelecionada);
                                    System.out.println("Midia adicionada com sucesso para assistir mais tarde");
                                    usuarioLogado.adicionarTableMidiasAssistidas(midiaSelecionada);
                                    break;
                                case 3: // add midia assistida
                                    verificaMidia(midiaSelecionada);
                                    System.out.println("Midia adiciona com sucesso em ja assistidas");
                                    usuarioLogado.adicionarTableMidiasAssistidas(midiaSelecionada);
                                    break;
                                case 0:
                                    resposta = false;
                                    break;
                                default:
                                    System.out.println("Opcao invalida");
                            }
                        } while (resposta);
                        break;
                    case 2: // mostrar catalogo
                        System.out.println("Vai mostrar catalogo");
                        break;
                    case 3: // criar conta
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
            } catch (NumberFormatException | InputMismatchException erro) {
                limparTela();
                System.out.println("Caractere invalido!");
                espera();
            } catch (UsuarioNaoEncontradoException erro) {
                limparTela();
                System.out.println("Usuario não encontrado!");
                espera();
            }

        } while (opcao != 0);
    }

    public static int menuPrincipalPlataformaUsuario() {
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
        String senha = teclado.nextLine();
        System.out.println("===========================");

        return plataforma.encontrarCliente(login);
    }

    public static int menuUsuarioLogado() {
        System.out.println("Selecione a opção:");
        System.out.println("1 - Selecionar midia");
        System.out.println("2 - Adicionar midia em \"assistir mais tarde\"");
        System.out.println("3 - Adicionar midias em \"ja assistidas manualmente\"");
        System.out.println("0 - Sair da conta");

        return Integer.parseInt(teclado.nextLine());
    }

    public static Midia menuProcurarMidia() {
        System.out.println("Digite o id da midia que deseja selecionar:");
        return plataforma.encontrarMidia(teclado.nextLine());
    }

    public static void verificaMidia(Midia midia) {
        limparTela();
        if (midia == null) {
            System.out.println("Midia invalida, selecione uma midia nas opcoes anteriores! ");
        }
        espera();
    }

    // #endregion
    // #region Area ADM
    public static void areaDaAdm() {

        int opcao = -1;

        do {
            limparTela();
            opcao = menuPrincialAdm();

            switch (opcao) {
                case 1:
                    System.out.println(plataforma.relatorioClienteMaisMidias());
                    break;
                case 2:
                    System.out.println(plataforma.relatorioClienteMaisAvaliacoes());
                    break;
                case 3:
                    System.out.println(plataforma.relatorioPorcentagemClientes15Avaliacoes());
                    break;
                case 4:
                    try{
                        System.out.println(plataforma.relatorioTop10MidiasAvaliacao());
                    }
                    catch (ArrayIndexOutOfBoundsException ex){
                        System.out.println("Não foi possivel gerar o relatório pois menos de 10 midias foram reproduzidas mais de 100 vezes.");
                    }
                    break;
                case 5:
                    System.out.println(plataforma.relatorioTop10MidiasVisualizacao());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Nenhuma opção valida!");
            }
            espera();

        } while (opcao != 0);
    }

    public static int menuPrincialAdm() {
        System.out.println("=====Bem vindo aos Relatórios=====");
        System.out.println("  Area de relatorios ");
        System.out.println("1 - Cliente com mais midias assistidas");
        System.out.println("2 - Cliente com mais midias avaliadas");
        System.out.println("3 - Porcentagem de midias que tem mais de 15 avaliacoes");
        System.out.println("4 - TOP10 Midias mais bem avaliadas");
        System.out.println("5 - TOP10 Midias mais assistidas");
        System.out.println("0 - Sair");


        System.out.println("=======================");
        return Integer.parseInt(teclado.nextLine());
    }
    // #endregion

    // #region metodos do sistema

    public static int menuPlataforma() {
        System.out.println("=====Bem vindo=====");
        System.out.println("Insira:");
        System.out.println("1 - Plataforma Stream");
        System.out.println("2 - Relatórios");
        return Integer.parseInt(teclado.nextLine());
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
    // #endregion
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
