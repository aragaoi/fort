package iu.texto.enums;

public enum Mensagem {

	SUCESSO("Sucesso!"),
	SUCESSO_CONEXAO("Conex�o efetuada com sucesso!"),
	SUCESSO_NOVO_STATUS("Seu status foi atualizado!"),
	
	ERRO("Ocorreu um erro!"),
	ERRO_CONEXAO("A Conex�o falhou!"),
	ERRO_OPCAO_INVALIDA("N�o existe op��o de menu com o n�mero digitado."),
	ERRO_NOVO_STATUS("Que pena! Houve uma falha e seu status n�o foi atualizado!"),
	ERRO_FALHA_BROWSE("Falha ao abrir a URL no navegador padr�o."),
	ERRO_ATUALIZACOES("Falha ao buscar as atualiza��es."),
	
	MSG_DIGITE_OPCAO("  Digite sua op��o e aperte [Enter]: "),
	MSG_URL_NAVEGADOR("Acesse este endere�o atrav�s do seu navegador de internet: "),
	MSG_ESCREVA_CODIGO_MANUAL("Um novo endere�o (URL) foi gerado na barra de endere�os do seu navegador.\nCole-o aqui e aperte [Enter]: "),
	MSG_ESCREVA_CODIGO("Cole aqui o endere�o (URL) da p�gina que foi aberta em seu navegador e aperte [Enter]: "),
	MSG_NOVO_STATUS("- ATUALIZACAO DE STATUS -\n\nNo que voc� est� pensando "),
	MSG_TECLA_CONTINUAR("Digite qualquer tecla e aperte [Enter] para continuar."),
	
	MENU_INICIAL("MENU INICIAL"),
	MENU_PRINCIPAL("O QUE DESEJA FAZER?");
	
	private String texto;
	
	private Mensagem(String texto){
		this.texto = texto;
	}

	public String getTexto() {
		return texto;
	}

}
