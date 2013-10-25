package iu.texto.enums;

public enum ItensMenuAmigos {
	LISTAR(0, "Mostrar Lista"),
	POSTAR_MURAL(1, "Escrever no Mural"),
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
