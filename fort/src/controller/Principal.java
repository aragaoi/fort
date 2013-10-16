package controller;

import iu.texto.InterfaceDeTexto;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import facebook4j.Facebook;
import facebook4j.FacebookException;

import model.Conexao;

public class Principal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		InterfaceDeTexto it = new InterfaceDeTexto();
		
		Conexao conn = new Conexao();
		Facebook face;
		
		Scanner leitor = new Scanner(System.in);
		leitor.useDelimiter(System.getProperty("line.separator"));
		
		leitor.nextInt();
		
		try {
			conn.buscaCodigo();
		System.out.println("Digite o código (code) indicado na página que foi aberta: ");
		String codigo = leitor.next();
		
		face = conn.getConexaoFacebook(codigo);
		
		if(face != null) System.out.println("Conectado!");
		
		leitor.close();
		} catch (IOException | URISyntaxException | FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
