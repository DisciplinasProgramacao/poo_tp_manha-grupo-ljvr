package Utilidades;
import java.util.Collection;

import Classes.Midia;

public abstract class GeradorString {

    public static String geraStringRelatorioMidias(Collection<Midia> lista){
        return lista.stream()
                        .map(m -> m.toString())
                       .reduce((m1,m2)-> m1.concat("\n".concat(m2))).get();
    }
    
}
