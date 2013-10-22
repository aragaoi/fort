package iu.texto.enums;

public enum Mensagem {

	SUCESSO("Sucesso!"),
	SUCESSO_CONEXAO("Conexão efetuada com sucesso!"),
	SUCESSO_NOVO_STATUS("Seu status foi atualizado!"),
	
	ERRO("Ocorreu um erro!"),
	ERRO_CONEXAO("A Conexão falhou!"),
	ERRO_OPCAO_INVALIDA("Não existe opção de menu com o número digitado."),
	ERRO_NOVO_STATUS("Que pena! Houve uma falha e seu status não foi atualizado!"),
	
	MSG_DIGITE_OPCAO("  Digite sua opção e aperte [Enter]: "),
	MSG_ESCREVA_CODIGO("Digite o código (code) indicado na página que foi aberta e aperte [Enter]: "),
	MSG_NOVO_STATUS("- ATUALIZACAO DE STATUS -\n\nNo que você está pensando? "),
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
