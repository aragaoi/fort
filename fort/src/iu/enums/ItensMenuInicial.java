package iu.enums;

public enum ItensMenuInicial {
	NOME(-1, "Meu Facebook"),
	STATUS(0, "Atualizar Status"),
	ATUALIZACOES(1, "Ver o Feed de Notícias"),
	LOG_IN(2, "Entrar em minha conta"),
	LOG_OUT(3, "Sair da minha conta"),
	FECHAR(4, "Fechar ForTApp");
	
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
