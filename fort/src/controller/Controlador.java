package controller;

import iu.texto.InterfaceDeTexto;
import iu.texto.enums.ItensMenuPrincipal;
import iu.texto.enums.Mensagem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.Amigo;
import model.Conexao;
import model.exceptions.ChamadaBrowseFalhouException;
import model.exceptions.OpcaoInvalidaException;
import model.exceptions.OperacaoFalhouException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Friend;
import facebook4j.Post;
import facebook4j.PostUpdate;
import facebook4j.ResponseList;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;

public class Controlador {
	
	private Facebook face;
	private InterfaceDeTexto iU;
	private List<Friend> listAmigos;
	private Gson gson;
	
	public Controlador(){}
	
	public void start(){
		this.gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		try {
			iU = new InterfaceDeTexto();
			int opcao;		
			while(true){
					opcao = iU.mostraMenuInicial();
					resolveAcaoInicial(opcao);
			}
		} catch (Exception e) {
			mostraErro(e.getMessage());
		}
	}
	
	private void resolveAcaoInicial(int opcao) throws Exception {
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

	private void resolveAcaoPrincipal(int opcao) throws OperacaoFalhouException, OpcaoInvalidaException, FacebookException, IOException, JSONException {
		switch (opcao) {
		case 0:
			atualizaStatus();
			break;
		case 1:
			verAtualizacoes();
			break;
		case 2:
			mostraMenuAmigos();
			break;
		case 3:
			break;
		default:
			throw new OpcaoInvalidaException(Mensagem.ERRO_OPCAO_INVALIDA.getTexto());
		}
	}	

	private void mostraMenuAmigos() throws OperacaoFalhouException, OpcaoInvalidaException, FacebookException, IOException, JSONException {
		int opcao;
		do{
			opcao = iU.mostraMenuAmigos();
			resolveAcaoAmigos(opcao);
		}while(opcao != ItensMenuPrincipal.MENU_INICIAL.getId());		
	}

	private void resolveAcaoAmigos(int opcao) throws OperacaoFalhouException, OpcaoInvalidaException, FacebookException, IOException, JSONException {
		Friend amigo;
		switch (opcao) {
		case 0:
			pesquisaAmigos();
			break;
		case 1:
			amigo = iU.getAmigoSelecionado();
			verMural(amigo);
			break;
		case 2:
			break;
		default:
			throw new OpcaoInvalidaException(Mensagem.ERRO_OPCAO_INVALIDA.getTexto());
		}		
	}

	private void verMural(Friend amigo) throws OperacaoFalhouException, OpcaoInvalidaException {
		if(amigo != null){
			try {
				PostUpdate post = new PostUpdate(iU.escreverNoMural(amigo));
				face.postFeed(amigo.getId(), post);
				mostraSucesso(Mensagem.SUCESSO_POST_MURAL.getTexto().replace("#nome", amigo.getName()));
			} catch (FacebookException e) {
				throw new OperacaoFalhouException(Mensagem.ERRO_POST_MURAL.getTexto()+" "+e.getMessage());
			}
		}else{
			throw new OpcaoInvalidaException(Mensagem.ERRO_AMIGO_SELECIONADO.getTexto());
		}
	}

	private void pesquisaAmigos() throws FacebookException, OperacaoFalhouException, JSONException {
		listAmigos = ordenaAmigos(face.getFriends());
		String pesquisa = iU.pesquisaAmigos();
		JSONArray resultado = face.executeFQL("SELECT name, uid FROM user WHERE name >= '"+pesquisa+"' AND name <= '"+pesquisa+"z' AND uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) order by name asc");
		System.out.println(face.getOAuthAccessToken().getToken());
		List<Amigo> amigos = jsonParaFriend(resultado);		
		String idAmigo = iU.mostraResultadoPesquisa(amigos);
		if(!idAmigo.equals("0")){
			List<Friend> lista = face.getBelongsFriend(idAmigo);
			if(!lista.isEmpty()){
				iU.setAmigoSelecionado(lista.get(0));
			} else{
				throw new OperacaoFalhouException(Mensagem.ERRO_PESQUISA_AMIGO.getTexto());
			}
		}	
	}

	private List<Amigo> jsonParaFriend(JSONArray resultado) throws JSONException {
		List<Amigo> lista = new ArrayList<>();
		for(int i = 0; i < resultado.length(); i++){
			lista.add(gson.fromJson(resultado.get(i).toString(), Amigo.class));
		}
		return lista;
	}

	private void atualizaStatus() throws OperacaoFalhouException {
		try {
			String status = iU.novoStatus(face.getName());
			face.postStatusMessage(status);
			mostraSucesso(Mensagem.SUCESSO_NOVO_STATUS.getTexto());
		} catch (FacebookException e) {
			throw new OperacaoFalhouException(Mensagem.ERRO_NOVO_STATUS.getTexto()+" "+e.getMessage());
		}		
	}
	
	private void verAtualizacoes() throws OperacaoFalhouException {
		try {
			ResponseList<Post> atualizacoes = face.getHome();
			iU.mostraAtualizacoesStatus(atualizacoes);
		} catch (FacebookException e) {
			throw new OperacaoFalhouException(Mensagem.ERRO_ATUALIZACOES.getTexto()+" "+e.getMessage());
		}		
	}

	private void iniciaConexao() throws Exception {
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

	private void iniciaConexaoManual() throws Exception {
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
	
	private void menuPrincipal() throws OperacaoFalhouException, OpcaoInvalidaException, FacebookException, IOException, JSONException {
		int opcao;
		do{
			opcao = iU.mostraMenuPrincipal(face);
			resolveAcaoPrincipal(opcao);
		}while(opcao != ItensMenuPrincipal.MENU_INICIAL.getId());		
	}
	
	private void mostraErro(String erro) {
		iU.mostraResultado(erro, true);
	}
	
	private void mostraSucesso(String sucesso) {
		iU.mostraResultado(sucesso, false);	
	}
	
	private String extraiCodigo(String url) {
		String codigo;
		codigo = url.substring(url.indexOf("code=")+5, url.indexOf("#_=_"));
		return codigo;
	}
	
	private List<Friend> ordenaAmigos(List<Friend> amigos) {		
		List<Friend> amigosOrdenados = new ArrayList<Friend>();
		Collections.sort(amigos, new Comparator<Friend>() {
			@Override
			public int compare(Friend o1, Friend o2) {
				return o1.getName().toUpperCase().compareTo(o2.getName().toUpperCase());
			}			
		});
		for(Friend f : amigos){
			amigosOrdenados.add(f);
		}		
		return amigosOrdenados;
	}	

	private void fechaConexao() {
		face = null;		
	}
}
