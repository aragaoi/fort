package iu.enums;

public enum ItensMenuAmigos {
	NOME(-1, "Amigos"),
	PESQUISAR(0, "Pesquisar"),
	VER_MURAL(1, "Ver o Mural");
	
	private int id;
	private String texto;
	
	private ItensMenuAmigos(int id, String texto){
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
