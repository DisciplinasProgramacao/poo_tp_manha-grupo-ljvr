package Utilidades;
public class UsuarioNaoEncontradoException extends RuntimeException {

    public UsuarioNaoEncontradoException(String msgErro) {
        super(msgErro);
    }
}
