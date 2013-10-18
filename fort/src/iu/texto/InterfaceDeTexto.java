package iu.texto;

import iu.texto.enums.ItensMenuInicial;
import iu.texto.enums.ItensMenuPrincipal;
import iu.texto.enums.Mensagem;
import logica.Leitor;
import facebook4j.Facebook;

public class InterfaceDeTexto {	
	private Facebook face;
	private Leitor leitor;

	public InterfaceDeTexto(){
		this.leitor = new Leitor();
	}

	public int mostraMenuInicial() {
		System.out.println("*****************************************");
		System.out.println("|\t       MENU INICIAL\t\t|");
		System.out.println("*****************************************\n");
		for(ItensMenuInicial im : ItensMenuInicial.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		return leitor.leInt(Mensagem.MSG_DIGITE_OPCAO.getTexto());
	}
	
	public int mostraMenuPrincipal(Facebook face) {
		if(this.face == null){
			this.face = face;
		}
		System.out.println("*****************************************");
		System.out.println("|\tO QUE DESEJA FAZER?\t\t|");
		System.out.println("*****************************************\n");
		for(ItensMenuPrincipal im : ItensMenuPrincipal.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		return leitor.leInt(Mensagem.MSG_DIGITE_OPCAO.getTexto());
	}
	
	public String getCodigo(){
		return leitor.leString(Mensagem.MSG_ESCREVA_CODIGO.getTexto());
	}

	public void mostraResultado(Mensagem conexaoSucesso, boolean aguardaInteracao) {
		String mensagem = "\n"+conexaoSucesso.getTexto()+"\n";
		if(aguardaInteracao){
			leitor.leString(mensagem+"\nDigite qualquer tecla e aperte [Enter] para continuar.");
		} else{
			System.out.println(mensagem);
		}
	}

	public String novoStatus() {
		return leitor.leString(Mensagem.MSG_NOVO_STATUS.getTexto());
	}
}
