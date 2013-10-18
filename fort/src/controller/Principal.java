package controller;

import iu.texto.InterfaceDeTexto;
import iu.texto.enums.ItensMenuPrincipal;
import iu.texto.enums.Mensagem;

import java.io.IOException;
import java.net.URISyntaxException;

import model.Conexao;
import facebook4j.Facebook;
import facebook4j.FacebookException;

public class Principal {

	public static Facebook face;
	public static InterfaceDeTexto iU;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		iU = new InterfaceDeTexto();
		int opcao;
		
		while(true){
			opcao = iU.mostraMenuInicial();
			resolveAcaoInicial(opcao);
		}
	}

	private static void resolveAcaoInicial(int opcao) {
		switch (opcao) {
		case 0:
			Mensagem resultado;
			if(face == null){
				resultado = iniciaConexao();
				iU.mostraResultado(resultado, false);
			}else{
				resultado = Mensagem.SUCESSO_CONEXAO;
			}			
			if(resultado.equals(Mensagem.SUCESSO_CONEXAO)){
				menuPrincipal();
			}
			break;
		case 1:
			fechaConexao();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			mostraErro(Mensagem.ERRO_OPCAO_INVALIDA);
		}
	}

	private static void menuPrincipal() {
		int opcao;
		do{
			opcao = iU.mostraMenuPrincipal(face);
			resolveAcao(opcao);
		}while(opcao != ItensMenuPrincipal.MENU_INICIAL.getId());
		
	}

	private static void resolveAcao(int opcao) {
		switch (opcao) {
		case 0:
			String status = iU.novoStatus();
			try {
				face.postStatusMessage(status);
				iU.mostraResultado(Mensagem.SUCESSO_NOVO_STATUS, false);
			} catch (FacebookException e) {
				e.printStackTrace();
				iU.mostraResultado(Mensagem.ERRO_NOVO_STATUS, true);
			}
		case 1:
			break;
		default:
			mostraErro(Mensagem.ERRO_OPCAO_INVALIDA);
		}
	}

	private static void mostraErro(Mensagem resultado) {
		iU.mostraResultado(resultado, true);
		iU.mostraMenuInicial();		
	}

	private static Mensagem iniciaConexao() {
		Mensagem resultado = Mensagem.ERRO_CONEXAO;
		Conexao conn = new Conexao();
		
		try {
			conn.buscaCodigo();
			String codigo = iU.getCodigo();
			
			face = conn.getConexaoFacebook(codigo);
			
			if(face != null){
				resultado = Mensagem.SUCESSO_CONEXAO;
			}
		
		} catch (IOException | URISyntaxException | FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resultado;
	}
	
	private static void fechaConexao() {
		face = null;		
	}

}
