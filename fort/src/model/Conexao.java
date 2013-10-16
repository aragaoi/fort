package model;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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
			.setOAuthAppId(FacebookConstantes.APP_ID)
			.setOAuthAppSecret(FacebookConstantes.APP_SECRET)
			.setOAuthPermissions(FacebookConstantes.PERMISSIONS);
		
		this.conf = cb.build();
		this.facebook = new FacebookFactory(conf).getInstance();
	}	
	
	public void buscaCodigo() throws IOException, URISyntaxException{		URI uri = new URI(facebook.getOAuthAuthorizationURL(FacebookConstantes.CALLBACK_URL));;		Desktop.getDesktop().browse(uri);
	}
	
	public Facebook getConexaoFacebook(String codigo) throws FacebookException{
		AccessToken token = facebook.getOAuthAccessToken(codigo);
		facebook.setOAuthAccessToken(token);
			
		if(facebook.getAuthorization().isEnabled()){
			return this.facebook;
		}
		return null;
	}
}
