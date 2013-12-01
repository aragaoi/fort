package iu.grafica;

import javax.swing.JPanel;

import controller.Controlador;

public class PainelPrincipal extends JPanel {
	private static final long serialVersionUID = 6519932308619023443L;

	private Controlador controlador;
	
	public PainelPrincipal(Controlador controlador){
		this.controlador = controlador;
		this.setVisible(true);
	}
}
