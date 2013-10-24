package controller;

import iu.texto.InterfaceDeTexto;
import iu.texto.enums.ItensMenuPrincipal;
import iu.texto.enums.Mensagem;
import model.Conexao;
import model.exceptions.ChamadaBrowseFalhouException;
import model.exceptions.OpcaoInvalidaException;
import model.exceptions.OperacaoFalhouException;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;

public class Principal {

	public static Facebook face;
	public static InterfaceDeTexto iU;
	
	public static void main(String[] args) {
		iU = new InterfaceDeTexto();
		int opcao;
		
		while(true){
			opcao = iU.mostraMenuInicial();
			try {
				resolveAcaoInicial(opcao);
			} catch (Exception e) {
				mostraErro(e.getMessage());
			}
		}
	}

	private static void resolveAcaoInicial(int opcao) throws Exception {
		switch (opcao) {
		case 0:
			iniciaConexao();
			break;
		case 1:
			fechaConexao();
			break;
		case 2:
			System.exit(0);
			break;
		default:
			mostraErro(Mensagem.ERRO_OPCAO_INVALIDA.getTexto());
		}
	}	

	private static void resolveAcao(int opcao) throws OperacaoFalhouException, OpcaoInvalidaException {
		switch (opcao) {
		case 0:
			atualizaStatus();
			break;
		case 1:
			verAtualizacoes();
			break;
		case 2:
			break;
		default:
			throw new OpcaoInvalidaException(Mensagem.ERRO_OPCAO_INVALIDA.getTexto());
		}
	}	

	private static void atualizaStatus() throws OperacaoFalhouException {
		try {
			String status = iU.novoStatus(face.getName());
			face.postStatusMessage(status);
			mostraSucesso(Mensagem.SUCESSO_NOVO_STATUS.getTexto());
		} catch (FacebookException e) {
			throw new OperacaoFalhouException(Mensagem.ERRO_NOVO_STATUS.getTexto()+" "+e.getMessage());
		}		
	}
	
	private static void verAtualizacoes() throws OperacaoFalhouException {
		try {
			ResponseList<Post> atualizacoes = face.getHome();
			iU.mostraAtualizacoesStatus(atualizacoes);
		} catch (FacebookException e) {
			throw new OperacaoFalhouException(Mensagem.ERRO_ATUALIZACOES.getTexto()+" "+e.getMessage());
		}		
	}

	private static void iniciaConexao() throws Exception {
		Conexao con;	
		
		try {
			if(face == null){
				con = new Conexao();
				con.buscaCodigo();
				String url = iU.leURLCodigo();
				
				face = con.getConexaoFacebook(extraiCodigo(url));
			}				
				mostraSucesso(Mensagem.SUCESSO_CONEXAO.getTexto());	
				menuPrincipal();
		} catch (Exception e) {
			if(e instanceof ChamadaBrowseFalhouException){
				iniciaConexaoManual();
			} else{
				throw e;
			}
		}	
	}

	private static void iniciaConexaoManual() throws Exception {
		Conexao con;	
		
		try {
			if(face == null){
				con = new Conexao();
				iU.mostraURLGeraCodigo(con.getOAuthAuthorizationURL());
				String codigo = iU.leURLCodigoManual();
				
				face = con.getConexaoFacebook(codigo);
			}				
				mostraSucesso(Mensagem.SUCESSO_CONEXAO.getTexto());	
				menuPrincipal();
		} catch (Exception e) {
			throw e;
		}			
	}
	
	private static void menuPrincipal() throws OperacaoFalhouException, OpcaoInvalidaException {
		int opcao;
		do{
			opcao = iU.mostraMenuPrincipal(face);
			resolveAcao(opcao);
		}while(opcao != ItensMenuPrincipal.MENU_INICIAL.getId());		
	}
	
	private static void mostraErro(String erro) {
		iU.mostraResultado(erro, true);
	}
	
	private static void mostraSucesso(String sucesso) {
		iU.mostraResultado(sucesso, false);	
	}
	
	private static String extraiCodigo(String url) {
		String codigo;
		codigo = url.substring(url.indexOf("code=")+5, url.indexOf("#_=_"));
		return codigo;
	}

	private static void fechaConexao() {
		face = null;		
	}
}
