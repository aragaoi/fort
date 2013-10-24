package model.exceptions;

@SuppressWarnings("serial")
public class ConexaoFalhouException extends Exception {

	public ConexaoFalhouException(String mensagem) {
		super(mensagem);
	}
}
