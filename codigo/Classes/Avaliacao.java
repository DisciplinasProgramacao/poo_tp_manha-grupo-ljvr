package Classes;

public class Avaliacao {
	private int nota;
	private Cliente cliente;
	private final int NOTAMAXINA = 5;

	public Avaliacao(int nota, Cliente cliente) {
        validarNota(nota);
        this.nota = nota;
        this.cliente = cliente;
		cliente.registrarAvaliacao();
    }
	
	private void validarNota(int nota) {
        if (nota < 1 || nota > NOTAMAXINA) {
            throw new IllegalArgumentException("A nota deve estar entre 1 e " + NOTAMAXINA);
        }
    }	

	public int getNota() {
	    return nota;
	}

	public Cliente getCliente() {
	    return cliente;
	}

	
}


