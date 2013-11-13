package iu.texto.enums;

public enum ItensMenuAmigos {
	PESQUISAR(0, "Pesquisar"),
	VER_MURAL(1, "Ver o Mural"),
	VOLTAR(2, "Voltar ao Menu Anterior");
	
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
