

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FormataArquivos {

     /**
     * Retorna um ArrayList com os dados de um arquivo CSV selecionado. É uma lista de Arrays, onde cada array possui as posições das coluanas de cada linha.
     * @return ArrayList <String[]>
     * @throws FileNotFoundException Quando o arquivo não é localizado.
     */
    public static ArrayList<String[]> listaDadosArquivo(String arquivo) throws FileNotFoundException {
        File dados = new File(arquivo);
        Scanner leitorDados = new Scanner(dados);
        ArrayList<String[]> lista = new ArrayList<>(600000);
        String [] vetAux;
        while(leitorDados.hasNextLine()){
            vetAux = leitorDados.nextLine().split(";");
            lista.add(vetAux);        }
        leitorDados.close();
        return lista;
    }
    
}
