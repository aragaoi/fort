package iu.grafica;

import iu.enums.Mensagem;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Conexao;

import controller.Controlador;
import facebook4j.FacebookException;

public class PainelLogin extends JPanel implements ActionListener{
	private static final long serialVersionUID = -5646328994846834199L;
	private TelaPrincipal telaPrincipal;
	private Controlador controlador;
	
	private JLabel statusConexao;
	private JLabel codigo;
	private JTextField campoCode;
	private JButton botaoLogar;
	
	public PainelLogin(Controlador controlador, TelaPrincipal telaPrincipal){
		this.telaPrincipal = telaPrincipal;
		this.controlador = controlador;
		defineComponentes();
		posicionaComponentes();
		inicia();
	}

	private void inicia() {
		try {
			this.controlador.abreBrowser();
		} catch (Exception e) {
			this.telaPrincipal.mostraMensagem(Mensagem.ERRO_FALHA_BROWSE.getTexto(), true);
		}
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
		this.codigo = new JLabel("Código:");
		this.campoCode = new JTextField(Mensagem.MSG_ESCREVA_CODIGO.getTexto());
		this.campoCode.setSize(new Dimension(this.telaPrincipal.getWidth(), 20));
		this.botaoLogar = new JButton(Mensagem.BT_CONECTAR.getTexto());
		this.botaoLogar.addActionListener(this);
	}
	
	private void posicionaComponentes() {
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		this.setLayout(gl);
		{
			SequentialGroup sg = gl.createSequentialGroup();
			ParallelGroup pg = gl.createParallelGroup(Alignment.CENTER, true);
			pg.addComponent(this.statusConexao, Alignment.TRAILING);
			pg.addComponent(this.codigo, Alignment.LEADING);
			pg.addComponent(this.campoCode, Alignment.CENTER);
			pg.addComponent(this.botaoLogar, Alignment.CENTER);
			sg.addGroup(pg);
			gl.setHorizontalGroup(sg);
		}
		{
			SequentialGroup sg = gl.createSequentialGroup();
			ParallelGroup pg = gl.createParallelGroup(Alignment.CENTER, true);
			sg.addComponent(statusConexao);
			sg.addComponent(codigo);
			sg.addComponent(campoCode, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE);
			sg.addComponent(botaoLogar);
			pg.addGroup(Alignment.CENTER, sg);
			gl.setVerticalGroup(pg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent paramActionEvent) {
		try {
			controlador.iniciaConexao(campoCode.getText());
			this.telaPrincipal.mostraMensagem(Mensagem.SUCESSO_CONEXAO.getTexto(), false);
		} catch (Exception e) {
			this.telaPrincipal.mostraMensagem(Mensagem.ERRO_CONEXAO.getTexto(), true);
		}
		
	}

}
