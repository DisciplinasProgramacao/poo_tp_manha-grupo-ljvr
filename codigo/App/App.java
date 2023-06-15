import java.io.FileNotFoundException;
import java.util.ArrayList;
import Classes.Cliente;
import Classes.Plataforma;
import Classes.Serie;

public class App {

    public static Plataforma plataforma = new Plataforma();

    public static void main(String[] args) {        
        carregaDadosIniciais();


    }

    private static void carregaDadosIniciais() {
        carregaCliente();
        carregaSeries();
    }

    private static void carregaSeries() {
        try{
            ArrayList <String[]> lista = FormataArquivos.listaDadosArquivo("Dados/POO_Series.csv");
            for(String[] linha:lista){
                Serie serie = new Serie(linha[0], linha[1], "en", " ", linha[2], 1);
                plataforma.adicionarSerie(serie);
            }
        }
        catch (FileNotFoundException ex){
            System.out.println("Arquivo não encontrado.");
        }
        catch (NumberFormatException ex){
            System.out.println("Arquivo de Serie com formato inesperado. Verificar ID.");
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
