package model;

import iu.enums.Mensagem;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import model.exceptions.ChamadaBrowseFalhouException;
import model.exceptions.ConexaoFalhouException;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationBuilder;

public class Conexao {
	
	private final Configuration conf;
	private final Facebook facebook;	

	public Conexao(){
		ConfigurationBuilder cb = new ConfigurationBuilder()
			.setDebugEnabled(true)
			.setOAuthAppId(Configuracoes.APP_ID)
			.setOAuthAppSecret(Configuracoes.APP_SECRET)
			.setOAuthPermissions(Configuracoes.PERMISSIONS);
		
		this.conf = cb.build();
		this.facebook = new FacebookFactory(conf).getInstance();
	}	
	
	public void buscaCodigo() throws IOException, URISyntaxException, ChamadaBrowseFalhouException{		URI uri = new URI(facebook.getOAuthAuthorizationURL(Configuracoes.CALLBACK_URL));
		if (Desktop.isDesktopSupported()) {
	        Desktop desktop = Desktop.getDesktop();
	        if (desktop.isSupported(Action.BROWSE)){
	            try {
	                desktop.browse(uri);
	                return;
	            } catch (IOException e) {
	                throw e;
	            }
	        } else{
	        	throw new ChamadaBrowseFalhouException(Mensagem.ERRO_FALHA_BROWSE.getTexto());
	        }
	    } else{
	    	throw new ChamadaBrowseFalhouException(Mensagem.ERRO_FALHA_BROWSE.getTexto());
	    }
	}
	
	public Facebook getConexaoFacebook(String codigo) throws ConexaoFalhouException{
		AccessToken token;
		try {
			token = facebook.getOAuthAccessToken(codigo);
			facebook.setOAuthAccessToken(token);
		} catch (FacebookException e) {
			throw new ConexaoFalhouException(Mensagem.ERRO_CONEXAO.getTexto()+" "+e.getMessage());
		}		
		
		if(facebook.getAuthorization().isEnabled()){
			return this.facebook;
		} else{
			throw new ConexaoFalhouException(Mensagem.ERRO_CONEXAO.getTexto());
		}
	}
	
	public String getOAuthAuthorizationURL(){
		return facebook.getOAuthAuthorizationURL(Configuracoes.CALLBACK_URL);
	}
}
