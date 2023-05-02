import java.util.StringTokenizer;

class Formatacao {

    // possivel implementacao de realocacao de capacidade automatica caso necessario

    /**
     * Recebe uma linha(String) contendo delimitadores (;) e os retorna em um unico
     * array
     * separado pelos delimitadores
     * 
     * @param linha String "Delim1;Delim2;Delim3"
     * @return String Array[3]
     */
    public static String[] separadorPtVirgula(String linha) {

        String[] array = new String[3];
        int posicao = 0;

        StringTokenizer st = new StringTokenizer(linha, ";");

        while (st.hasMoreTokens()) {
            array[posicao] = st.nextToken();
            posicao++;
        }

        return array;

    }
}