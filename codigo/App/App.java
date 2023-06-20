import java.io.FileNotFoundException;
import java.util.ArrayList;
import Classes.*;

public class App {

    public static Plataforma plataforma = new Plataforma();

    public static void main(String[] args) {        
        carregaDadosIniciais();


    }

    private static void carregaDadosIniciais() {
        carregaCliente();
        carregaSeries();
        carregaAudiencia();
        System.out.println();
    }

    private static void carregaAudiencia() {
        try{
            ArrayList <String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Audiencia.csv");
            for(String[] linha:lista){
                Cliente cliente = plataforma.encontrarCliente(linha[0]);
                Midia serie = plataforma.encontrarMidia(linha[2]);
                if(linha[1].equals("A")){
                    cliente.adicionarTableMidiasAssistidas(serie);
                }
                else if (linha[1].equals("F")){
                    cliente.adicionarTableMidiasFuturas(serie);
                }
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("Arquivo não encontrado.");
        }
    }

    private static void carregaSeries() {
        try{
            ArrayList <String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Series.csv");
            for(String[] linha:lista){
                Serie serie = new Serie(linha[0], linha[1], "en", " ", linha[2], 1);
                plataforma.adicionarMidia(linha[0],serie);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("Arquivo não encontrado.");
        }
    }

    /**
     * Carrega todos os Cliente no sistema se existir o arquivo Dados/POO_Espectadores.csv
     */
    private static void carregaCliente() {
        try{
            ArrayList <String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Espectadores.csv");
            for(String[] linha:lista){
                Cliente cliente = new Cliente(linha[0], linha[1], linha[2]);
                plataforma.adicionarCliente(cliente);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Arquivo não encontrado.");
        }
    }
}
