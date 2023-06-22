package Classes;

import java.io.Serializable;

public class Avaliacao implements Serializable{
	private int nota;
	private Cliente cliente;
	private final int NOTAMAXIMA = 5;
	private static final long serialVersionUID = 1;


	public Avaliacao(int nota, Cliente cliente) {
        validarNota(nota);
        this.nota = nota;
        this.cliente = cliente;
		cliente.registrarAvaliacao();
    }
	
	private void validarNota(int nota) {
        if (nota < 1 || nota > NOTAMAXIMA) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e " + NOTAMAXIMA);
        }
    }	

	public int getNota() {
	    return nota;
	}

	public Cliente getCliente() {
	    return cliente;
	}

	
}


