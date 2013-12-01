package iu.grafica;

import facebook4j.FacebookException;
import iu.enums.Mensagem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controlador;

public class PainelLogin extends JPanel implements ActionListener{
	private static final long serialVersionUID = -5646328994846834199L;
	private TelaPrincipal telaPrincipal;
	private Controlador controlador;
	
	private JLabel statusConexao;
	private JTextField campoCode;
	private JButton botaoLogar;
	
	public PainelLogin(Controlador controlador, TelaPrincipal telaPrincipal){
		this.telaPrincipal = telaPrincipal;
		this.controlador = controlador;
		defineComponentes();
		posicionaComponentes();
	}

	private void defineComponentes() {
		String usuario = null;
		try {
			usuario = controlador.getUsuarioConectado();
		} catch (IllegalStateException | FacebookException e) {}
		String status = usuario == null 
				? Mensagem.MSG_NAO_CONECTADO.getTexto()
				: Mensagem.MSG_CONECTADO.getTexto().replace("#nome", usuario);
		this.statusConexao = new JLabel(status);
		this.campoCode = new JTextField(Mensagem.MSG_ESCREVA_CODIGO.getTexto());
		this.botaoLogar = new JButton(Mensagem.BT_CONECTAR.getTexto());
	}
	
	private void posicionaComponentes() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		// TODO Auto-generated method stub
		
	}

}
