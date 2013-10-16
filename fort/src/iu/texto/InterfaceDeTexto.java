package iu.texto;

import iu.texto.enums.ItensMenuInicial;
import iu.texto.enums.ItensMenuPrincipal;

public class InterfaceDeTexto {

	public InterfaceDeTexto(){
		mostraMenuInicial();
	}

	private void mostraMenuInicial() {
		System.out.println("*****************************************");
		System.out.println("|\t       MENU INICIAL\t\t|");
		System.out.println("*****************************************\n");
		for(ItensMenuInicial im : ItensMenuInicial.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		System.out.print("  Digite sua opção e aperte [Enter]: ");
	}
	
	private void mostraMenuPrincipal() {
		System.out.println("*****************************************");
		System.out.println("|\t       O QUE DESEJA FAZER?\t\t|");
		System.out.println("*****************************************\n");
		for(ItensMenuPrincipal im : ItensMenuPrincipal.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		System.out.print("  Digite sua opção e aperte [Enter]: ");
	}
}
