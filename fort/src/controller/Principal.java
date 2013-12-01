package controller;

import iu.grafica.TelaPrincipal;

public class Principal {
	
	public static void main(String[] args) {		
		Controlador controlador = new Controlador();
		TelaPrincipal telaPrincipal = new TelaPrincipal(controlador);
		while(true){
			telaPrincipal.start();
		}
	}
}
