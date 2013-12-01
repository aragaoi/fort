package iu.enums;

public enum ItensMenuPrincipal {
	STATUS(0, "Atualizar Status"),
	ATUALIZACOES(1, "Ver o Feed de Notícias"),
	AMIGOS(2, "Amigos"),
	MENU_INICIAL(3, "Menu Inicial");
	
	private int id;
	private String texto;
	
	private ItensMenuPrincipal(int id, String texto){
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
