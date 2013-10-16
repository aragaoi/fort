package iu.texto.enums;

public enum ItensMenuPrincipal {
	STATUS(0, "Atualizar Status"),
	MENU_INICIAL(1, "Menu Inicial");
	
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
