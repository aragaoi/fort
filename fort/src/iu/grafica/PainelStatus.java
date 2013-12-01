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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import model.exceptions.OperacaoFalhouException;
import controller.Controlador;

public class PainelStatus extends JPanel implements ActionListener {
	private static final long serialVersionUID = -8672908736920957551L;
	
	private Controlador controlador;
	private TelaPrincipal telaPrincipal;
	
	private JTextArea campoStatus;
	private JScrollPane painelTexto;
	private JButton botaoPostar;
	
	public PainelStatus(Controlador controlador, TelaPrincipal telaPrincipal){
		this.telaPrincipal = telaPrincipal;
		this.controlador = controlador;
		this.setMinimumSize(this.telaPrincipal.getSize());
		defineComponentes();
		posicionaComponentes();
		this.setVisible(true);
	}

	private void defineComponentes() {		
		this.campoStatus = new JTextArea("No que você está pensando?");
		this.painelTexto = new JScrollPane(campoStatus);
		this.painelTexto.setMaximumSize(new Dimension(this.telaPrincipal.getWidth(), 150));
		this.botaoPostar = new JButton("Atualizar Status");
		this.botaoPostar.addActionListener(this);
	}
	
	private void posicionaComponentes() {
		GroupLayout gl = new GroupLayout(this);
		gl.setAutoCreateGaps(true);
		gl.setAutoCreateContainerGaps(true);
		this.setLayout(gl);
		{
			SequentialGroup sg = gl.createSequentialGroup();
			ParallelGroup pg = gl.createParallelGroup(Alignment.CENTER, true);
			pg.addComponent(this.painelTexto, Alignment.CENTER);
			pg.addComponent(this.botaoPostar, Alignment.CENTER);
			sg.addGroup(pg);
			gl.setHorizontalGroup(sg);
		}
		{
			SequentialGroup sg = gl.createSequentialGroup();
			ParallelGroup pg = gl.createParallelGroup(Alignment.CENTER, true);
			sg.addComponent(painelTexto);
			sg.addComponent(botaoPostar);
			pg.addGroup(Alignment.CENTER, sg);
			gl.setVerticalGroup(pg);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			controlador.atualizaStatus(campoStatus.getText());
			this.telaPrincipal.mostraMensagem(Mensagem.SUCESSO_NOVO_STATUS.getTexto(), false);			
		} catch (OperacaoFalhouException e1) {
			this.telaPrincipal.mostraMensagem(Mensagem.ERRO_NOVO_STATUS.getTexto(), true);
		}
	}
}
