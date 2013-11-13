package iu.texto;

import iu.texto.enums.ItensMenuAmigos;
import iu.texto.enums.ItensMenuInicial;
import iu.texto.enums.ItensMenuPrincipal;
import iu.texto.enums.Mensagem;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import logica.Leitor;
import model.Amigo;
import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.Friend;
import facebook4j.Post;
import facebook4j.ResponseList;

public class InterfaceDeTexto {	
	private Facebook face;
	private Leitor leitor;
	private Friend amigoSelecionado;

	public InterfaceDeTexto() throws IOException{
		this.leitor = new Leitor();
	}

	public int mostraMenuInicial() {
		System.out.println("*****************************************");
		System.out.println("|\t       "+Mensagem.MENU_INICIAL.getTexto()+"\t\t|");
		System.out.println("*****************************************\n");
		for(ItensMenuInicial im : ItensMenuInicial.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		return leOpcao();
	}

	public int mostraMenuPrincipal(Facebook face) {
		if(this.face == null){
			this.face = face;
		}
		System.out.println("*****************************************");
		System.out.println("|\t    "+Mensagem.MENU_PRINCIPAL.getTexto()+"    \t|");
		System.out.println("*****************************************\n");
		for(ItensMenuPrincipal im : ItensMenuPrincipal.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		return leOpcao();
	}
	
	public int mostraMenuAmigos() {
		System.out.println("*****************************************");
		System.out.println("|\t    "+Mensagem.MENU_AMIGOS.getTexto()+"    \t|");
		System.out.println("*****************************************\n");
		if(this.amigoSelecionado != null){
			System.out.println("\nAmigo selecionado: "+this.amigoSelecionado.getName()+"\n");
		}else{
			System.out.println("\nNenhum amigo selecionado.\n");
		}
		for(ItensMenuAmigos im : ItensMenuAmigos.values()){
			System.out.println("\t"+ im.getId() +" - "+ im.getTexto());
		}
		System.out.println("\n*****************************************\n");
		return leOpcao();
	}

	public String novoStatus(String name) {
		return leitor.leString(Mensagem.MSG_NOVO_STATUS.getTexto()+name+"?");
	}
	
	public void mostraAtualizacoesStatus(ResponseList<Post> atualizacoes) {
		for(int i = atualizacoes.size()-1; i > 0; i--){
			Post post = atualizacoes.get(i);
			if(post.getType().toUpperCase().equals("STATUS")){				
				mostraNome(post);
				mostraHistoria(post);
				mostraDescricao(post);
				mostraMensagem(post);
				mostraData(post);
				mostraComentarios(post);
				linhaSeparadora();
			}
		}		
	}

	public String escreverNoMural(Friend amigo) {
		return leitor.leString(Mensagem.MSG_POST_MURAL.getTexto().replace("#nome", amigo.getName()));
	}
	
	private void linhaSeparadora() {
		System.out.println("----------");
		System.out.println();
	}

	private void mostraData(Post post) {
		SimpleDateFormat formatador = new SimpleDateFormat("d MMM yyyy HH:mm:ss");
		Date dataPost = new Date(post.getCreatedTime().getTime());
		String dataStr;
		long segundos = (new Date().getTime() - dataPost.getTime()) / 1000;
		if(segundos < 60){
			dataStr = " * há "+segundos+"segundo"+resolvePlural(segundos);			
		} else if(emMinutos(segundos) < 60){
			dataStr = " * há "+emMinutos(segundos)+" minuto"+resolvePlural(emMinutos(segundos));
		} else if(emHoras(segundos) < 24){
			dataStr = " * há "+emHoras(segundos)+" hora"+resolvePlural(emHoras(segundos));
		} else {
			dataStr = formatador.format(dataPost);
		}		
		System.out.println(dataStr);
	}

	private String resolvePlural(long numero) {
		if(numero == 1){
			return "";
		}else{
			return "s";
		}
	}

	private long emMinutos(long segundos) {
		return (long) segundos / 60;
	}
	
	private long emHoras(long segundos) {
		return (long) segundos / 3600;
	}

	private void mostraComentarios(Post post) {
		if(post.getComments() != null && post.getComments().size() > 0){
			System.out.println("\n\tComentários:");
			for(Comment c : post.getComments()){
				System.out.println("\t"+c.getFrom().getName()+": "+c.getMessage().replace("\n", "\n\t\t"));
			}			
		}
	}

	private void mostraMensagem(Post post) {
		if(post.getMessage() != null){
			System.out.println(post.getMessage());
		}
	}

	private void mostraHistoria(Post post) {
		if(post.getStory() != null){
			System.out.println(post.getStory());
		}
	}

	private void mostraNome(Post post) {
		System.out.println("-> "+post.getFrom().getName());
	}

	private void mostraDescricao(Post post) {
		if(post.getDescription() != null){
			System.out.println("\t"+post.getDescription());
		}
	}

	public String leURLCodigo(){
		return leitor.leString(Mensagem.MSG_ESCREVA_CODIGO.getTexto());
	}
	
	public String leURLCodigoManual(){
		return leitor.leString(Mensagem.MSG_ESCREVA_CODIGO_MANUAL.getTexto());
	}

	public void mostraURLGeraCodigo(String url) {
		leitor.leString(Mensagem.MSG_URL_NAVEGADOR.getTexto() + url);
	}
	
	private int leOpcao() {
		return leitor.leInt(Mensagem.MSG_DIGITE_OPCAO.getTexto());
	}
	
	public void mostraResultado(String mensagem, boolean aguardaInteracao) {
		mensagem = "\n"+mensagem+"\n";
		if(aguardaInteracao){
			leitor.leString(mensagem+"\n"+Mensagem.MSG_TECLA_CONTINUAR.getTexto()+"");
		} else{
			System.out.println(mensagem);
		}
	}

	public Friend getAmigoSelecionado() {
		return this.amigoSelecionado;
	}

	public void setAmigoSelecionado(Friend amigoSelecionado) {
		if(amigoSelecionado != null)
			this.amigoSelecionado = amigoSelecionado;
	}

	public String selecionaAmigo() {
		return leitor.leString(Mensagem.MSG_SELECIONA_AMIGO.getTexto());		
	}

	public String pesquisaAmigos() {		
		System.out.println(" - "+Mensagem.MENU_AMIGOS.getTexto()+" - ");
		return leitor.leString(Mensagem.MSG_PESQUISA_AMIGO.getTexto());		
	}

	public String mostraResultadoPesquisa(List<Amigo> amigos) {
		System.out.println("\n - "+Mensagem.MSG_RESULTADO_PESQUISA_AMIGO.getTexto());
		long i = 1;
		for(Amigo a : amigos){
			System.out.println("\t"+i+" - "+a.getName());
			i++;
		}
		System.out.println();
		Long opcao = leitor.leLong(Mensagem.MSG_SELECIONA_AMIGO.getTexto());
		if(opcao != 0 && amigos.size() >= opcao){
			return amigos.get(new Long(opcao-1L).intValue()).getUid();
		}else{
			return "0";
		}
	}
}
