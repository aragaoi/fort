package iu.grafica;

import iu.enums.ItensMenuAmigos;
import iu.enums.ItensMenuInicial;
import iu.enums.Mensagem;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controller.Controlador;

public class TelaPrincipal extends JFrame implements ActionListener{
	private static final long serialVersionUID = -3351844292133410611L;

	private Controlador controlador;
	
	public TelaPrincipal(Controlador controlador){
		super("ForTApp");
		this.controlador = controlador;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		montaMenu();
		setContentPane(new PainelPrincipal(controlador));
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
		setMinimumSize(new Dimension(tela.width/2, tela.height/2));
		pack();
		setLocationRelativeTo(null);
	}
	
	private void montaMenu() {
		setJMenuBar(new JMenuBar());
		JMenu menu;
		if(ItensMenuInicial.values().length > 0){
			menu = new JMenu();
			for(ItensMenuInicial item : ItensMenuInicial.values()){
				if(item.equals(ItensMenuInicial.NOME)){
					menu.setText(item.getTexto());
				} else {
					JMenuItem menuItem = new JMenuItem(item.getTexto());
					menuItem.addActionListener(this);
					menu.add(menuItem);
				}				
			}
			getJMenuBar().add(menu);
		}
		if(ItensMenuAmigos.values().length > 0){
			menu = new JMenu();
			for(ItensMenuAmigos item : ItensMenuAmigos.values()){
				if(item.equals(ItensMenuAmigos.NOME)){
					menu.setText(item.getTexto());
				} else {
					JMenuItem menuItem = new JMenuItem(item.getTexto());
					menuItem.addActionListener(this);
					menu.add(menuItem);
				}
			}
			getJMenuBar().add(menu);			
		}		
	}

	public void mostraMensagem(String mensagem, boolean erro){
		if(erro){
			JOptionPane.showMessageDialog(null, mensagem, "ERRO: ", JOptionPane.ERROR_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, mensagem, "AVISO: ", JOptionPane.WARNING_MESSAGE);			
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String source = ((JMenuItem) e.getSource()).getText();
		if(source.equals(ItensMenuInicial.STATUS.getTexto())){
			setContentPane(new PainelStatus(controlador, this));
		} else if(source.equals(ItensMenuInicial.ATUALIZACOES.getTexto())){
			
		} else if(source.equals(ItensMenuInicial.LOG_IN.getTexto())){
			setContentPane(new PainelLogin(controlador, this));
		} else if(source.equals(ItensMenuInicial.LOG_OUT.getTexto())){
			
		} else if(source.equals(ItensMenuInicial.FECHAR.getTexto())){
			System.exit(0);
		}
	}	
	
	public void start(){
		this.setVisible(true);
	}
}
