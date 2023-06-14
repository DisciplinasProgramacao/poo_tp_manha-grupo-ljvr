import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Classes.Cliente;
import Classes.Plataforma;

public class App {

    public static Plataforma plataforma = new Plataforma();

    public static void main(String[] args) {        
        carregaDados();


    }

    private static void carregaDados() {
       carregaCliente();
       System.out.println();
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
