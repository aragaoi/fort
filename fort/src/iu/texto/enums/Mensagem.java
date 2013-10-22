package iu.texto.enums;

public enum Mensagem {

	SUCESSO("Sucesso!"),
	SUCESSO_CONEXAO("Conex�o efetuada com sucesso!"),
	SUCESSO_NOVO_STATUS("Seu status foi atualizado!"),
	
	ERRO("Ocorreu um erro!"),
	ERRO_CONEXAO("A Conex�o falhou!"),
	ERRO_OPCAO_INVALIDA("N�o existe op��o de menu com o n�mero digitado."),
	ERRO_NOVO_STATUS("Que pena! Houve uma falha e seu status n�o foi atualizado!"),
	
	MSG_DIGITE_OPCAO("  Digite sua op��o e aperte [Enter]: "),
	MSG_ESCREVA_CODIGO("Digite o c�digo (code) indicado na p�gina que foi aberta e aperte [Enter]: "),
	MSG_NOVO_STATUS("- ATUALIZACAO DE STATUS -\n\nNo que voc� est� pensando? "),
	MSG_TECLA_CONTINUAR("Digite qualquer tecla e aperte [Enter] para continuar."),
	
	MENU_INICIAL("MENU INICIAL"),
	MENU_PRINCIPAL("MENU_PRINCIPAL");
	
	private String texto;
	
	private Mensagem(String texto){
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

}
