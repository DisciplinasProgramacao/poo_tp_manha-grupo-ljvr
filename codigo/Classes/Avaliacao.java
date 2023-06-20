package Classes;

public class Avaliacao {
	private double nota;
	private Cliente cliente;

	public Avaliacao(double nota, Cliente cliente) {
	   this.nota = nota;
	   this.cliente = cliente;
	   //TO do validar dados;
	 }
	

	public double getNota() {
	    return nota;
	}

	public Cliente getCliente() {
	    return cliente;
	}

	
}


