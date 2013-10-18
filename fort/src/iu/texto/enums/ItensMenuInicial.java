package iu.texto.enums;

public enum ItensMenuInicial {
	LOG_IN(0, "Entrar em minha conta"),
	STATUS(1, "Atualizar Status"),
	LOG_OUT(2, "Sair da minha conta"),
	FECHAR(3, "Fecha ForTApp");
	
	private int id;
	private String texto;
	
	private ItensMenuInicial(int id, String texto){
		this.id = id;
		this.texto = texto;
	}

	public int getId() {
		return id;
	}

	public String getTexto() {
		return texto;
	}	
}
