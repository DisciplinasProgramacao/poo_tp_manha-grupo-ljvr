import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import Classes.Avaliacao;
import Classes.Cliente;
import Classes.FilmeLonga;
import Classes.FiltroMidia;
import Classes.Midia;
import Classes.Plataforma;
import Classes.Serie;
import Utilidades.FormataArquivos;
import Utilidades.UsuarioNaoEncontradoException;

public class App {

    private static Plataforma plataforma = new Plataforma();

    private static Scanner teclado = new Scanner(System.in);

    public static void main(String[] args) {

        limparTela();

        try {
            // carregaDadosIniciais(); Para carregar arquivos originais descomentar essa
            // linha e comnetar a linha abaixo.
            carregaDados();

            if (menuPlataforma() == 1)
                areaDoUsuario();
            else
                areaDaAdm();

            salvaDados();

        } catch (NumberFormatException | InputMismatchException error) {
            limparTela();
            System.out.println("Caractere invalido!");
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("Arquivo de dados não encontrado.");
        }

    }

    // #region area USUARIO
    public static void areaDoUsuario() {

        int opcao = -1;
        Midia midiaSelecionada = null;

        do {
            limparTela();
            boolean resposta = true;
            String op;
            try {
                opcao = menuPrincipalPlataformaUsuario();

                switch (opcao) {
                    case 1: // login
                        Cliente usuarioLogado = menuLoginCliente();
                        if (usuarioLogado == null)
                            throw new UsuarioNaoEncontradoException("Usuario nao encontrado!");
                        do {
                            switch (menuUsuarioLogado(midiaSelecionada)) {
                                case 1: // procurar midia
                                    midiaSelecionada = menuProcurarMidia();
                                    limparTela();
                                    if (midiaSelecionada == null) {
                                        limparTela();
                                        System.out.println("A midia seleciona não foi achada ou e invalida!");
                                        espera();
                                    } else {
                                        limparTela();
                                        System.out.println("Midia: " + midiaSelecionada.getNomeMidia()
                                                + " pronta para ser manuseada!");
                                        espera();
                                    }
                                    break;
                                case 2: // add midia
                                    limparTela();
                                    if (verificaMidia(midiaSelecionada)) {
                                        System.out.println("Midia adicionada com sucesso para assistir mais tarde");
                                        usuarioLogado.adicionarTableMidiasAssistidas(midiaSelecionada);
                                        midiaSelecionada = null;
                                    }
                                    espera();
                                    break;
                                case 3: // add midia assistida
                                    limparTela();
                                    if (verificaMidia(midiaSelecionada)) {
                                        System.out.println("Midia adiciona com sucesso em ja assistidas");
                                        usuarioLogado.adicionarTableMidiasAssistidas(midiaSelecionada);
                                        midiaSelecionada = null;
                                    }
                                    espera();
                                    break;
                                case 4: // avalia midia
                                    if (verificaMidia(midiaSelecionada)) {

                                        if (!usuarioLogado.verificaSeAssistiuMidia(midiaSelecionada)) {
                                            System.out.println("Voce ainda nao assistiu a midia.");
                                            System.out.println(
                                                    "Para avaliar ela deve estar na lista de midias ja assistidas!");
                                            espera();
                                            break;
                                        }

                                        System.out.println("Qual a nota da avaliacao? (1 - 5)");
                                        int nota = Integer.parseInt(teclado.nextLine());
                                        try {
                                            midiaSelecionada.adicionarAvaliacao(new Avaliacao(nota, usuarioLogado));
                                            System.out.println("Avaliação feita com sucesso!");
                                        } catch (IllegalAccessError ex) {
                                            System.out.println(ex.getLocalizedMessage());
                                        } catch (IllegalStateException ex) {
                                            System.out.println(ex.getMessage());
                                        }
                                    }
                                    espera();
                                    break;
                                case 5:
                                    try {
                                        limparTela();
                                        System.out.println("Estao em assistir mais tarde as seguintes midias:\n");
                                        System.out.println(usuarioLogado.relatorioMidiasFuturas());
                                        espera();
                                        espera();
                                    } catch (NoSuchElementException err) {
                                        System.out.println("Voce não possui nenhuma midia para assistir mais tarde!");
                                        espera();
                                        break;
                                    }
                                    break;
                                case 6:
                                    try {
                                        limparTela();
                                        System.out.println("Estao em ja assistidos as seguintes midias:\n");
                                        System.out.println(usuarioLogado.relatorioMidiasAssistidas());
                                        espera();
                                    } catch (NoSuchElementException err) {
                                        System.out.println("Voce nao possui midias na lista de ja assistidas");
                                        espera();
                                    }
                                    break;
                                case 7:
                                    try {
                                        limparTela();
                                        switch (menuDeFiltros()) {
                                            case 1: // nome idioma genero
                                                System.out.println("Digite o nome da midia que deseja procurar:");
                                                op = teclado.nextLine();
                                                if (menuDeFiltrosEscolha() == 1) {
                                                    System.out.println(usuarioLogado.relatorioFiltroFuturassNome(op));
                                                    espera();
                                                } else {
                                                    System.out.println(usuarioLogado.relatorioFiltroAssistidasNome(op));
                                                    espera();
                                                }
                                                break;
                                            case 2:
                                                System.out.println("Digite o idioma da midia que deseja procurar");
                                                op = teclado.nextLine();
                                                if (menuDeFiltrosEscolha() == 1) {
                                                    System.out.println(usuarioLogado.relatorioFiltroFuturassIdioma(op));
                                                    espera();
                                                } else {
                                                    System.out
                                                            .println(usuarioLogado.relatorioFiltroAssistidasIdioma(op));
                                                    espera();
                                                }
                                                break;
                                            case 3:
                                                System.out.println("Digite o genero da midia que deseja procurar");
                                                op = teclado.nextLine();
                                                if (menuDeFiltrosEscolha() == 1) {
                                                    System.out
                                                            .println(usuarioLogado.relatorioFiltroAssistidasGenero(op));
                                                    espera();
                                                } else {
                                                    System.out.println(usuarioLogado.relatorioFiltroFuturassGenero(op));
                                                    espera();
                                                }
                                                break;
                                            default:
                                                System.out.println("Opção invalida!");
                                                espera();
                                                break;
                                        }
                                    } catch (NoSuchElementException err) {
                                        limparTela();
                                        System.out.println("Nenhuma midia encontrada!");
                                        espera();
                                    }
                                    break;
                                case 100:
                                    Midia novaMidia = menuNovaMidia();
                                    plataforma.adicionarMidia(novaMidia.getIdMidia(), novaMidia);
                                    break;
                                case 0:
                                    resposta = false;
                                    break;
                                default:
                                    System.out.println("Opcao invalida");
                            }
                        } while (resposta);
                        break;
                    case 2: // criar conta
                        Cliente clienteCriado = menuCriacaoDeCliente();
                        plataforma.adicionarCliente(clienteCriado);
                        break;
                    case 3:
                        System.out.println(plataforma.relatorioTodasMidias());
                        espera();
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
        System.out.println("2 - Criar usuario");
        System.out.println("3 - Catalogo completo de midias ");
        System.out.println("0 - Sair");
        System.out.println("======================");
        System.out.println("Digite a opção");
        return Integer.parseInt(teclado.nextLine());
    }

    public static Cliente menuCriacaoDeCliente() {
        limparTela();
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

    public static int menuUsuarioLogado(Midia midiaAtual) {
        limparTela();

        if (midiaAtual != null) {
            System.out.println("===============================================");
            System.out.println("A midia selecionada e: " + midiaAtual.getNomeMidia());
            System.out.println("===============================================");
        } else {
            System.out.println("=====================ATENCAO===================");
            System.out.println("Você não tem nenhuma midia para manuseio.");
            System.out.println("Selecione a opção 1 para escolher uma midia.");
            System.out.println("===============================================");
        }

        System.out.println("Selecione a opção:");
        System.out.println("1 - Selecionar midia");
        System.out.println("2 - Adicionar midia em \"assistir mais tarde\"");
        System.out.println("3 - Adicionar midia em \"ja assistidas\"");
        System.out.println("4 - Avaliar midia");
        System.out.println("5 - Ver lista de assistir mais tarde");
        System.out.println("6 - Ver lista de ja assistidas");
        System.out.println("7 - Busca filtrada");
        System.out.println("0 - Sair da conta\n");
        System.out.println("=======Para testes=======");
        System.out.println("| 100 - Adicionar midia |");
        System.out.println("=========================");

        return Integer.parseInt(teclado.nextLine());
    }

    public static Midia menuProcurarMidia() {
        System.out.println("Digite o id da midia que deseja selecionar:");
        return plataforma.encontrarMidia(teclado.nextLine());
    }

    public static boolean verificaMidia(Midia midia) {
        limparTela();
        if (midia == null) {
            System.out.println("Midia invalida, selecione uma midia nas opcoes anteriores antes de prosseguir! ");
            espera();
            return false;
        } else {
            return true;
        }

    }

    public static int menuDeFiltrosEscolha() {
        System.out.println("Em qual lista você quer filtrar? ");
        System.out.println("1 - Assistir mais tarde");
        System.out.println("2 - Ja assistidas");
        return Integer.parseInt(teclado.nextLine());
    }

    // #endregion
    // #region Area ADM
    public static void areaDaAdm() {

        int opcao = -1;
        String op;
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
                    try {
                        System.out.println(plataforma.relatorioTop10MidiasAvaliacao());
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        System.out.println(
                                "Não foi possivel gerar o relatório pois menos de 10 midias foram reproduzidas mais de 100 vezes.");
                    }
                    break;
                case 5:
                    System.out.println(plataforma.relatorioTop10MidiasVisualizacao());
                    break;
                case 6:
                    try {
                        switch (menuDeFiltros()) {
                            case 1: // nome idio gen
                                System.out.println("Digite o nome da midia");
                                op = teclado.nextLine();
                                System.out.println(plataforma.relatorioMidiasFiltradasNome(op));
                                espera();
                                break;
                            case 2:
                                System.out.println("Digite o idioma da midia");
                                op = teclado.nextLine();
                                System.out.println(plataforma.relatorioMidiasFiltradasIdioma(op));
                                espera();
                                break;
                            case 3:
                                System.out.println("Digite o nome da genero");
                                op = teclado.nextLine();
                                System.out.println(plataforma.relatorioMidiasFiltradasgenero(op));
                                espera();
                                break;
                            default:
                                System.out.println("Opcao invalida!");
                                espera();
                                break;

                        }
                    } catch (NoSuchElementException err) {
                        limparTela();
                        System.out.println("Nada foi encontrado!");
                        espera();
                    }
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
        System.out.println("6 - Filtrar midias");
        System.out.println("0 - Sair");
        System.out.println("=======================");
        return Integer.parseInt(teclado.nextLine());
    }

    // # Filme
    // IdFilme;Nome;DataDeLançamento;Duração(min)
    // # Séries
    // IdSerie;Nome;DataDeLançamento

    public static Midia menuNovaMidia() {
        String nome;
        String dataLancamento;
        String id;
        String idioma;
        String genero;

        System.out.println("Insira a opção:");
        System.out.println("1 - Serie");
        System.out.println("2 - Filme longametragem");

        int resp = Integer.parseInt(teclado.nextLine());

        if (resp == 1) {

            System.out.println("Id:");
            id = teclado.nextLine();
            System.out.println("Nome:");
            nome = teclado.nextLine();
            System.out.println("Idioma:");
            idioma = teclado.nextLine();
            System.out.println("Genero");
            genero = teclado.nextLine();
            System.out.println("Data de lancamento:");
            dataLancamento = teclado.nextLine();

            System.out.println("Quantidade de episodios da serie");
            int qtdEp = Integer.parseInt(teclado.nextLine());

            System.out.println("Serie criada com sucesso");
            return new Serie(id, nome, idioma, genero, dataLancamento, qtdEp);

        } else {
            System.out.println("Id:");
            id = teclado.nextLine();
            System.out.println("Nome:");
            nome = teclado.nextLine();
            System.out.println("Idioma:");
            idioma = teclado.nextLine();
            System.out.println("Genero");
            genero = teclado.nextLine();
            System.out.println("Data de lancamento:");
            dataLancamento = teclado.nextLine();

            System.out.println("Duracao em minutos do filme longametragem");
            int duracaoMin = Integer.parseInt(teclado.nextLine());

            System.out.println("Filme criado com sucesso!");
            return new FilmeLonga(id, nome, idioma, genero, dataLancamento, duracaoMin);
        }
    }

    // #endregion
    // #region metodos do sistema

    public static int menuPlataforma() {
        System.out.println("================Bem vindo=============");
        System.out.println("Qual deseja entrar? Insira o numero:");
        System.out.println("1 - Plataforma Stream");
        System.out.println("2 - Relatorios Administrativos");
        System.out.println("======================================");
        System.out.println("\n\nDica: Para uma melhor experiencia deixe o terminal em uma altura grande.");
        return Integer.parseInt(teclado.nextLine());
    }

    public static int menuDeFiltros() {
        System.out.println("Qual seu criterio de filtro?");
        System.out.println("1 - Nome");
        System.out.println("2 - Idioma");
        System.out.println("3 - Genero");
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

    private static void carregaDados() throws ClassNotFoundException, FileNotFoundException, IOException {
        try {
            FileInputStream arquivo = new FileInputStream("Dados/POO_Plataforma.bin");
            ObjectInputStream dados = new ObjectInputStream(arquivo);
            plataforma = (Plataforma) dados.readObject();
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Não foi encontrado o arquivo de dados POO_Plataforma.bin");
        } catch (IOException | ClassNotFoundException ex) {
            throw new ClassNotFoundException("O objeto não foi encontrado no arquivo.");

        }
    }

    private static void salvaDados() {
        try {
            FileOutputStream arquivo = new FileOutputStream("Dados/POO_Plataforma.bin", false);
            ObjectOutputStream dados = new ObjectOutputStream(arquivo);
            dados.writeObject(plataforma);
            System.out.println("\nDados salvos com sucesso.");
        } catch (FileNotFoundException ex) {
            System.out.println("Não foi possivel salvar o arquivo de dados POO_Plataforma.bin");
        } catch (IOException ex) {
            System.out.println("O objeto para salvar é nulo. Processo interrompido.");
        }
    }
    // #endregion
}
