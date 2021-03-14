import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

public class TelaDetalhesDoleilao extends TelaPadrao{
	private Leilao leilao;
	public Leilao getLeilao() {
		return leilao;
	}

	public void setLeilao(Leilao leilao) {
		this.leilao = leilao;
	}

	private Usuario usuario;
	private Usuario fulano;
	private JTextField lance;
	private JButton botaoComprar;
	
	public JTextField getLance() {
		return lance;
	}

	public void setLance(JTextField lance) {
		this.lance = lance;
	}

	public TelaDetalhesDoleilao(Leilao leilao, Usuario fulano, Usuario usuario) {
		this.leilao = leilao;
		this.fulano = fulano;
		this.usuario = usuario;
		setTitle("Detalhes do leilão");
		adicionarJL();
		adicionarJTA();
		adicionarBotoes();
		adicionarJT();
		setVisible(true);
	}
	
	public TelaDetalhesDoleilao(Leilao leilao, Usuario fulano) {
		this.leilao = leilao;
		this.fulano = fulano;
		setTitle("Detalhes do leilão");
		adicionarJL();
		adicionarJTA();
		adicionarBotoes();
		adicionarJT();
		setVisible(true);
	}

	
	private void adicionarJL() {
		JLabel textoDescricao = new JLabel("Descrição do produto");
		textoDescricao.setBounds(380, 80, 200, 20);
		textoDescricao.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoDescricao);
	    
	    JLabel textolanceAtual = new JLabel("Lance atual");
	    textolanceAtual.setBounds(20, 80, 100, 20);
	    textolanceAtual.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textolanceAtual);
	    
	    JLabel textoDarlance = new JLabel("Dar lance");
	    textoDarlance.setBounds(20, 120, 100, 20);
	    textoDarlance.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoDarlance);
	    
	    JLabel textoCamprar = new JLabel("Camprar pelo valor de:");
	    textoCamprar.setBounds(20, 190, 200, 20);
	    textoCamprar.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoCamprar);
	    
	    JLabel textoRs = new JLabel("R$");
	    textoRs.setBounds(20, 220, 200, 20);
	    textoRs.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoRs);
	}
	
	private void adicionarJTA() {
		JTextArea areaDeTexto = new JTextArea();
		areaDeTexto.setLineWrap(true);
		areaDeTexto.setWrapStyleWord(true);
		areaDeTexto.setEditable(true);
		areaDeTexto.setText(leilao.getDescricao());
		areaDeTexto.setEditable(false);
		JScrollPane scroll = new JScrollPane(areaDeTexto);
		int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scroll.setBounds(350, 110, 220, 160);
		add(scroll);
	}
	
	private class OuvinteBotaoCancelar implements ActionListener{
		private TelaDetalhesDoleilao  telaDetalhesDoleilao;
		public OuvinteBotaoCancelar( TelaDetalhesDoleilao  telaDetalhesDoleilao) {
			this.telaDetalhesDoleilao = telaDetalhesDoleilao;
		}
		public void actionPerformed(ActionEvent arg0) {			
			if(usuario==null) {
				TelaLeiloes telaLeiloes = new TelaLeiloes();
				telaLeiloes.setLocationRelativeTo(telaDetalhesDoleilao);
				telaDetalhesDoleilao.dispose();
			}else {
				TelaLeiloesDeFulano telaLeiloesDeFulano = new TelaLeiloesDeFulano(usuario, fulano);
				telaLeiloesDeFulano.setLocationRelativeTo(telaDetalhesDoleilao);
				telaDetalhesDoleilao.dispose();
			}
		}
		
	}
	
	private class OuvinteBotaoEnviar implements ActionListener{

		private TelaDetalhesDoleilao  telaDetalhesDoleilao;
		public OuvinteBotaoEnviar( TelaDetalhesDoleilao  telaDetalhesDoleilao) {
			this.telaDetalhesDoleilao = telaDetalhesDoleilao;
		}
		public void actionPerformed(ActionEvent arg0) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes c = p.recuperarCentral();				
			Usuario autenticado = c.recuperarOutroUsuario(fulano.getEmail());
			float lance =0;
			boolean lanceFloat=false;
			try {
				lance = Float.parseFloat(getLance().getText());					
				lanceFloat=true;
			}catch(NumberFormatException erro) {
				lanceFloat=false;
			}
			
			if(lanceFloat==false) {
				JOptionPane.showMessageDialog(telaDetalhesDoleilao, "Digite um lance válido!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else if(lance<leilao.getLance()){
				JOptionPane.showMessageDialog(telaDetalhesDoleilao, "Seu lance é inferior ao lance atual!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else {
				if(lance>leilao.getLance() && lance<=leilao.getValor()) {
					if(lance==leilao.getValor()) {
						for (Leilao l : autenticado.getLeiloes()) {
							if(l.getTitulo().equals(leilao.getTitulo())) {
								l.setVendido(true);
								l.setLance(lance);
								l.setComprador(usuario.getEmail());
								l.setNomeComprador(usuario.getNome()+" "+usuario.getSobrenome());
								l.setDataDaCompra(Calendar.getInstance().getTime());
								l.setValorDaCompra(l.getValor());
								l.setAdicionadoNosLeiloesGanhos(true);
								p.salvarCentral(c);
								Usuario comprador = c.recuperarOutroUsuario(usuario.getEmail());
								LeilaoGanho leilaoGanhos = new LeilaoGanho(l.getTitulo(), l.getDataDaCompra(), l.getValorDaCompra(), autenticado.getEmail());
								comprador.getLeilaoGanho().add(leilaoGanhos);
							    p.salvarCentral(c);
								JOptionPane.showMessageDialog(telaDetalhesDoleilao, "Parabens você venceu o leilão!", null, JOptionPane.INFORMATION_MESSAGE);
								TelaLeiloesDeFulano telaLeiloesDeFulano = new TelaLeiloesDeFulano(usuario, autenticado);
								telaDetalhesDoleilao.dispose();
							}
						}						
						
					}else {
						for (Leilao l : autenticado.getLeiloes()) {
							if(l.getTitulo().equals(leilao.getTitulo())) {
								l.setLance(lance);
								l.setComprador(usuario.getEmail());
								l.setNomeComprador(usuario.getNome()+" "+usuario.getSobrenome());
								l.setDataDaCompra(Calendar.getInstance().getTime());
								l.setValorDaCompra(l.getLance());
								l.getLanceBemSucedido().add(l.getLance());								
								p.salvarCentral(c);
								JOptionPane.showMessageDialog(telaDetalhesDoleilao, "Lance efetuado!", null, JOptionPane.INFORMATION_MESSAGE);
								TelaLeiloesDeFulano telaLeiloesDeFulano = new TelaLeiloesDeFulano(usuario, autenticado);
								telaDetalhesDoleilao.dispose();
							}
						}												
					}					
				}
			}					
		}
		
	}
	
	private class OuvinteBotaoComprar implements ActionListener{

		private TelaDetalhesDoleilao  telaDetalhesDoleilao;
		public OuvinteBotaoComprar( TelaDetalhesDoleilao  telaDetalhesDoleilao) {
			this.telaDetalhesDoleilao = telaDetalhesDoleilao;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes c = p.recuperarCentral();				
			Usuario autenticado = c.recuperarOutroUsuario(fulano.getEmail());
			
			for (Leilao l : autenticado.getLeiloes()) {
				if(l.getTitulo().equals(leilao.getTitulo())) {
					l.setVendido(true);
					l.setComprador(usuario.getEmail());
					l.setNomeComprador(usuario.getNome()+" "+usuario.getSobrenome());
					l.setDataDaCompra(Calendar.getInstance().getTime());
					l.setValorDaCompra(l.getValor());
					l.setAdicionadoNosLeiloesGanhos(true);
					p.salvarCentral(c);
					Usuario comprador = c.recuperarOutroUsuario(usuario.getEmail());
					LeilaoGanho leilaoGanhos = new LeilaoGanho(l.getTitulo(), l.getDataDaCompra(), l.getValorDaCompra(), autenticado.getEmail());
					comprador.getLeilaoGanho().add(leilaoGanhos);
				    p.salvarCentral(c);
					JOptionPane.showMessageDialog(telaDetalhesDoleilao, "Parabens você venceu o leilão!", null, JOptionPane.INFORMATION_MESSAGE);
					TelaLeiloesDeFulano telaLeiloesDeFulano = new TelaLeiloesDeFulano(usuario, autenticado);
					telaDetalhesDoleilao.dispose();
					}				
				}
			}	
		}
	
	
	private void adicionarBotoes() {
		 botaoComprar = new JButton("Comprar");
		 botaoComprar.setBounds(150, 215, 80, 30);
		 botaoComprar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));	    
	     if(usuario==null) {	    	
		    }else if(usuario!=null) {
		    	if(usuario.getEmail().equals(fulano.getEmail())) {
		    		
		    	}else {
				    add(botaoComprar);			 
			    }
		    }
	     
	     botaoComprar.addActionListener(new OuvinteBotaoComprar(this));
	     
	     JButton botaoEnviar = new JButton("Enviar");
	     botaoEnviar.setBounds(210, 145, 80, 30);
	     botaoEnviar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     if(usuario==null) {	    	
		    }else if(usuario!=null) {
		    	if(usuario.getEmail().equals(fulano.getEmail())) {
		    		
		    	}else {
		    		add(botaoEnviar);			    
		    }
		    }
	     botaoEnviar.addActionListener(new OuvinteBotaoEnviar(this));
	     
	     BotaoCancelar BotaoCancelar = new BotaoCancelar();
	     add(BotaoCancelar);
	     BotaoCancelar.addActionListener(new OuvinteBotaoCancelar(this));
	}
	
	private void adicionarJT() {
				
		lance = new JTextField();
		lance.setBounds(20, 145, 180, 30);
		 if(usuario==null) {
			 lance.setEditable(false);
			 lance.setToolTipText("Você precisa estar logado pra dar um lance!");
		    }
		 if(usuario!=null) {
			 if(usuario.getEmail().equals(fulano.getEmail())) {
				 lance.setEditable(false);
				 lance.setToolTipText("Você não pode dar um lance no seu leilão!");
			 }
		 }
	    add(lance);
	    
	    JTextField lanceAtual = new JTextField();
	    lanceAtual.setBounds(110, 75, 80, 30);
	    lanceAtual.setText(Float.toString(leilao.getLance()));
	    lanceAtual.setEditable(false);
	    add(lanceAtual);
	    
	    JTextField comprar = new JTextField();
	    comprar.setBounds(50, 215, 80, 30);
	    comprar.setText(Float.toString(leilao.getValor()));
	    comprar.setEditable(false);
	    if(usuario==null) {
	    	comprar.setToolTipText("Você precisa estar logado pra comprar!");
		    }
	    if(usuario!=null) {
			 if(usuario.getEmail().equals(fulano.getEmail())) {
				comprar.setToolTipText("Você não pode comprar o seu leilão seu leilão!");
			}
		}
		
	    add(comprar);
	    
	}
}
