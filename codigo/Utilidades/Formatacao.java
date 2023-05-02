import java.util.StringTokenizer;

class Formatacao {

    // possivel implementacao de realocacao de capacidade automatica e troca de
    // delimitador caso necessario

    /**
     * Pega uma linha de arquivo String com até 5 delimitadores ( ; )
     * partindo a linha e a transformando em um unico Array.
     * Caso a a linha do arquivo seja menor que 5 delimitadores as unidades
     * sem preenchimento serão null.
     * 
     * @param linha String "Delim1;Delim2..."
     * @return String[5]
     */
    public static String[] separadorPtVirgula(String linha) {

        String[] array = new String[5];
        int posicao = 0;

        StringTokenizer st = new StringTokenizer(linha, ";");

        while (st.hasMoreTokens()) {
            array[posicao] = st.nextToken();
            posicao++;
        }

        return array;

    }
}