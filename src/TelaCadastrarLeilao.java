import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

public class TelaCadastrarLeilao extends TelaPadrao{
	
	private JButton salvar;
	private JTextField titulo;
	private JFormattedTextField prazo;
	private JFormattedTextField valor;
	private JTextArea areaDeTexto;
	private Usuario usuario;

	public JTextField getTitulo() {
		return titulo;
	}

	public void setTitulo(JTextField titulo) {
		this.titulo = titulo;
	}

	public JFormattedTextField getPrazo() {
		return prazo;
	}

	public void setPrazo(JFormattedTextField prazo) {
		this.prazo = prazo;
	}

	public JFormattedTextField getValor() {
		return valor;
	}

	public void setValor(JFormattedTextField valor) {
		this.valor = valor;
	}

	public JTextArea getAreaDeTexto() {
		return areaDeTexto;
	}

	public void setAreaDeTexto(JTextArea areaDeTexto) {
		this.areaDeTexto = areaDeTexto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}

	public TelaCadastrarLeilao(Usuario usuario) {
		this.usuario = usuario;
		super.setTitle("Cadastrar Leilão");
		adicionarJL();
		adicionarBotoes();
		adicionarJT();
		adicionarJTA();
		setVisible(true);
	}

	private void adicionarJL() {
		
		JLabel textoTitulo = new JLabel("Titulo");
		textoTitulo.setBounds(20, 80, 100, 20);
		textoTitulo.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoTitulo);
	    
	    JLabel textoPrazo = new JLabel("Prazo de encerramento");
	    textoPrazo.setBounds(20, 150, 200, 20);
	    textoPrazo.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoPrazo);
	    
	    JLabel textoValor = new JLabel("Valor da compra sem participar do leilão");
	    textoValor.setBounds(20, 220, 300, 20);
	    textoValor.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoValor);
	    
	    JLabel textoDescricao = new JLabel("Descrição do produto");
	    textoDescricao.setBounds(380, 80, 200, 20);
	    textoDescricao.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoDescricao);
		
	}
	
	private void adicionarBotoes() {
		
		salvar = new JButton("Salvar");
		salvar.setForeground(Color.BLACK);
		salvar.setBounds(150, 300, 100, 35);
		salvar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	    add(salvar);
	    salvar.addActionListener(new OuvinteTelaCadastrarLeilao(this));
	    
	    BotaoCancelar BotaoCancelar = new BotaoCancelar();	     
	    add(BotaoCancelar);
	    BotaoCancelar.addActionListener(new OuvinteTelaCadastrarLeilao(this)); 	
	}
	
	private void adicionarJT() {
		
		MaskFormatter data = null;
		MaskFormatter hr = null;
		try {
			data = new MaskFormatter("##/##/#### ##:##");
		} catch (ParseException e) {
			e.getMessage();
		}
		
		try {
			hr = new MaskFormatter("##:##");
		} catch (ParseException e) {
			e.getMessage();
		}
				
		titulo = new JTextField();
		titulo.setBounds(20, 105, 180, 30);
	    add(titulo);
	    
	    prazo = new JFormattedTextField(data);
	    prazo.setBounds(20, 175, 120, 30);
	    add(prazo);	    
	    
	    valor = new JFormattedTextField();
	    valor.setBounds(20, 245, 50, 30);
	    add(valor);
	}
	
	private void adicionarJTA() {
		areaDeTexto = new JTextArea();
		areaDeTexto.setLineWrap(true);
		areaDeTexto.setWrapStyleWord(true);
		areaDeTexto.setEditable(true);
		JScrollPane scroll = new JScrollPane(areaDeTexto);
		int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scroll.setBounds(350, 110, 220, 160);
		add(scroll);
	}
}
