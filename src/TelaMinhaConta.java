import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class TelaMinhaConta extends TelaPadrao{
	
	private Usuario usuario;
	private  JLabel listarUsuarios;
	private JLabel cadastrar;
	private JLabel leiloesFinalizados;
	private JLabel leiloesGanhos;
	private JLabel leiloesQueCadastrei;
	private JLabel leiloesQueGanhei;
	private JLabel excluirLeilao;
	private JLabel editarDados;
	private Persistencia p = new Persistencia();
	private CentralDeInformacoes central = p.recuperarCentral();
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TelaMinhaConta(Usuario usuario) {
		super.setTitle("Minha Conta");
		this.usuario = usuario;
		adicioarJL();
		adicioarJLFuncionais();
		adicionarBarraDeMenu();
		setVisible(true);
	}

	private void adicioarJL() {
		JLabel nomeNaConta = new JLabel(usuario.getNome()+" "+usuario.getSobrenome());
		nomeNaConta.setBounds(130, 80, 400, 30);
		nomeNaConta.setFont(new Font("Segoe Print", Font.PLAIN, 25));
		nomeNaConta.setForeground(Color.BLACK);
	    add(nomeNaConta);
	    
	    if(usuario.getSexo()=='F') {
	    ImageIcon f = new ImageIcon(getClass().getResource("f.png"));
	    	JLabel imagemF = new JLabel(f);
	    	imagemF.setBounds(40, 40, 80, 80);
		    add(imagemF);
	    }else if(usuario.getSexo()=='M') {
	    ImageIcon m = new ImageIcon(getClass().getResource("m.png"));
	    	JLabel imagemM = new JLabel(m);
	    	imagemM.setBounds(40, 40, 80, 80);
		    add(imagemM);
	    }
	    
		JLabel textoNome = new JLabel("Usuários cadastrados");
		textoNome.setBounds(40, 145, 180, 20);
		textoNome.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		textoNome.setForeground(Color.BLACK);
	    add(textoNome);
	    
	    JLabel textoNovoLeilão = new JLabel("Novo leilão");
	    textoNovoLeilão.setBounds(40, 205, 180, 20);
	    textoNovoLeilão.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    textoNovoLeilão.setForeground(Color.BLACK);
	    add(textoNovoLeilão);
	    
	    JLabel textoLeiloesFinalizados = new JLabel("Leilões recentemente finalizados");
	    textoLeiloesFinalizados.setBounds(40, 265, 220, 20);
	    textoLeiloesFinalizados.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    textoLeiloesFinalizados.setForeground(Color.BLACK);
	    add(textoLeiloesFinalizados);
	    
	    JLabel textoLeiloesGanhos = new JLabel("Leilões ganhos");
	    textoLeiloesGanhos.setBounds(270, 145, 350, 20);
	    textoLeiloesGanhos.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    textoLeiloesGanhos.setForeground(Color.BLACK);
	    add(textoLeiloesGanhos);
	    
	    JLabel textoRelatorioLeiloesCadastrados = new JLabel("Gerar relatório com os leilões cadastrados por mim");
	    textoRelatorioLeiloesCadastrados.setBounds(270, 205, 350, 20);
	    textoRelatorioLeiloesCadastrados.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    textoRelatorioLeiloesCadastrados.setForeground(Color.BLACK);
	    add(textoRelatorioLeiloesCadastrados);
	    
	    JLabel textoRelatorioLeiloesGanhos = new JLabel("Gerar relatório com todos os leilões que ganhei");
	    textoRelatorioLeiloesGanhos.setBounds(270, 265, 350, 20);
	    textoRelatorioLeiloesGanhos.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    textoRelatorioLeiloesGanhos.setForeground(Color.BLACK);
	    add(textoRelatorioLeiloesGanhos);
		
	}
	
	private class OuvinteBotaoSair implements ActionListener{

		private TelaMinhaConta telaMinhaConta;
		
		public OuvinteBotaoSair(TelaMinhaConta telaMinhaConta) {
			this.telaMinhaConta = telaMinhaConta;
		}
		
		public void actionPerformed(ActionEvent e) {
			TelaLeiloes telaLeiloes = new TelaLeiloes();
			telaLeiloes.setLocationRelativeTo(telaMinhaConta);
			telaMinhaConta.dispose();			
		}		
	}
	
	private class OuvinteEditarDados implements ActionListener{

		private TelaMinhaConta telaMinhaConta;
		
		public OuvinteEditarDados(TelaMinhaConta telaMinhaConta) {
			this.telaMinhaConta = telaMinhaConta;
		}
		
		public void actionPerformed(ActionEvent e) {
			TelaEditarDados telaEditarDados = new TelaEditarDados(usuario);	
			telaEditarDados.setLocationRelativeTo(telaMinhaConta);
			telaMinhaConta.dispose();
		}		
	}
	
	Cursor mao = new Cursor(Cursor.HAND_CURSOR);
	Cursor seta = new Cursor(Cursor.CROSSHAIR_CURSOR);
	
	private class OuvinteListarUsuarios implements MouseListener{
		
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteListarUsuarios(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }
		   
		public void mouseClicked(MouseEvent e) {			
			TelaListaDeUsuarios telaListaDeUsuarios = new TelaListaDeUsuarios(usuario);
			telaListaDeUsuarios.setLocationRelativeTo(telaMinhaConta);
			telaMinhaConta.dispose();
			
		}
		
		public void mouseEntered(MouseEvent e) {
			listarUsuarios.setText("<html><u>Listar</u></html>");
			listarUsuarios.setCursor(mao);
			
		}
		
		public void mouseExited(MouseEvent e) {
			listarUsuarios.setText("Listar");
			listarUsuarios.setCursor(seta);
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteCadastrar implements MouseListener{
		
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteCadastrar(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			TelaCadastrarLeilao telaCadastrarLeilao = new TelaCadastrarLeilao(usuario);
			telaCadastrarLeilao.setLocationRelativeTo(telaMinhaConta);
			telaMinhaConta.dispose();			
		}
		
		public void mouseEntered(MouseEvent e) {
			cadastrar.setText("<html><u>Cadastrar</u></html>");
			cadastrar.setCursor(mao);		
		}
		
		public void mouseExited(MouseEvent e) {
			cadastrar.setText("Cadastrar");
			cadastrar.setCursor(seta);			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteLeiloesFinalizados implements MouseListener{
		
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteLeiloesFinalizados(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			
			Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
			Date dataAtual = Calendar.getInstance().getTime();
			int tamanho = 0;
			for (Leilao l : u.getLeiloes()) {
				if(l.getPrazo().before(dataAtual) || l.isVendido()){
					tamanho++;
				}
			}
			
			if(tamanho>0) {
				TelaLeiloesFinalizados telaLeiloesFinalizados = new TelaLeiloesFinalizados(usuario);
				telaLeiloesFinalizados.setLocationRelativeTo(telaMinhaConta);
				telaMinhaConta.dispose();
			}else{
				JOptionPane.showMessageDialog(telaMinhaConta, "Você não possui lelões finalizados!", null, JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		public void mouseEntered(MouseEvent e) {
			leiloesFinalizados.setText("<html><u>Listar</u></html>");
			leiloesFinalizados.setCursor(mao);
			
		}
		
		public void mouseExited(MouseEvent e) {
			leiloesFinalizados.setText("Listar");
			leiloesFinalizados.setCursor(seta);
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteLeiloesGanhos implements MouseListener{
		
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteLeiloesGanhos(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
			int tamanho = u.getLeilaoGanho().size();
			
			if(tamanho>0) {
				TelaLeiloesGanhos telaLeiloesGanhos = new TelaLeiloesGanhos(usuario);
				telaLeiloesGanhos.setLocationRelativeTo(telaMinhaConta);
				telaMinhaConta.dispose();
			}else {
				JOptionPane.showMessageDialog(telaMinhaConta, "Você ainda não possui leilões ganhos!", null, JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		public void mouseEntered(MouseEvent e) {
			leiloesGanhos.setText("<html><u>Listar</u></html>");
			leiloesGanhos.setCursor(mao);
			
		}
		
		public void mouseExited(MouseEvent e) {
			leiloesGanhos.setText("Listar");
			leiloesGanhos.setCursor(seta);
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteLeiloesQueCadastrei implements MouseListener{
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteLeiloesQueCadastrei(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			//fazer download do relatorio
			 Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
			 int tamanho = u.getLeiloes().size();
			 if(tamanho>0) {
				 GeradorDeRelatorios relatorio = new GeradorDeRelatorios();
					String nomePDF =  "Leilões cadastrados por "+usuario.getNome()+" "+usuario.getSobrenome();
					relatorio.gerarRelatoriosLeiloesCadastradosPorMim(usuario, nomePDF);
					JOptionPane.showMessageDialog(telaMinhaConta, "Relatório gerado!", null, JOptionPane.INFORMATION_MESSAGE); 
			 }else {
				 JOptionPane.showMessageDialog(telaMinhaConta, "Você ainda não possui lelões cadastrados!", null, JOptionPane.ERROR_MESSAGE);
			 }
			
		}
		
		public void mouseEntered(MouseEvent e) {
			leiloesQueCadastrei.setText("<html><u>Solicitar pdf</u></html>");
			leiloesQueCadastrei.setCursor(mao);
			
		}
		
		public void mouseExited(MouseEvent e) {
			leiloesQueCadastrei.setText("Solicitar pdf");
			leiloesQueCadastrei.setCursor(seta);
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteleiloesQueGanhei implements MouseListener{
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteleiloesQueGanhei(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }   
		public void mouseClicked(MouseEvent e) {
			//faz download do relatorio leiloes que ganhei
			 Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
			 int tamanho = u.getLeilaoGanho().size();
			 if(tamanho>0) {
				 GeradorDeRelatorios relatorio = new GeradorDeRelatorios();
				 String nomePDF =  "Leilões ganhos por "+usuario.getNome()+" "+usuario.getSobrenome();
					relatorio.gerarRelatoriosLeiloesQueGanhei(usuario, nomePDF);
					JOptionPane.showMessageDialog(telaMinhaConta, "Relatório gerado!", null, JOptionPane.INFORMATION_MESSAGE); 
			 }else {
				 JOptionPane.showMessageDialog(telaMinhaConta, "Você ainda não possui lelões ganhos!", null, JOptionPane.ERROR_MESSAGE);
			 }
		}
		
		public void mouseEntered(MouseEvent e) {
			leiloesQueGanhei.setText("<html><u>Solicitar pdf</u></html>");
			leiloesQueGanhei.setCursor(mao);
			
		}
		
		public void mouseExited(MouseEvent e) {
			leiloesQueGanhei.setText("Solicitar pdf");
			leiloesQueGanhei.setCursor(seta);
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteExcluirLeilao implements MouseListener{
		
		private TelaMinhaConta telaMinhaConta;
		   public OuvinteExcluirLeilao(TelaMinhaConta telaMinhaConta) {
		       this.telaMinhaConta = telaMinhaConta;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
			Date dataAtual = Calendar.getInstance().getTime();
			int tamanho = 0;
			for (Leilao l : u.getLeiloes()) {
				if(l.getPrazo().after(dataAtual) && !l.isVendido()){
					tamanho++;
				}
			}
			
			if(tamanho>0) {
				TelaExcluirLeilão TelaExcluirLeilão = new TelaExcluirLeilão(usuario);
				TelaExcluirLeilão.setLocationRelativeTo(telaMinhaConta);
				telaMinhaConta.dispose();
			}else{
				JOptionPane.showMessageDialog(telaMinhaConta, "Você não possui lelões para excluir!", null, JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public void mouseEntered(MouseEvent e) {
			excluirLeilao.setText("<html><u>Excluir Leilão</u></html>");
			excluirLeilao.setCursor(mao);
			
		}
		
		public void mouseExited(MouseEvent e) {
			excluirLeilao.setText("Excluir Leilão");
			excluirLeilao.setCursor(seta);
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private void adicioarJLFuncionais() {
	    
	    listarUsuarios = new JLabel("Listar");
	    listarUsuarios.setBounds(40, 170, 50, 20);
	    listarUsuarios.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    listarUsuarios.setForeground(Color.BLUE);
	    add(listarUsuarios);
	    listarUsuarios.addMouseListener(new OuvinteListarUsuarios(this));
	    
	    cadastrar = new JLabel("Cadastrar");
		cadastrar.setBounds(40, 230, 100, 20);
		cadastrar.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		cadastrar.setForeground(Color.BLUE);
	    add(cadastrar);
	    cadastrar.addMouseListener(new OuvinteCadastrar(this));
	    
	    leiloesFinalizados = new JLabel("Listar");
	    leiloesFinalizados.setBounds(40, 290, 50, 20);
	    leiloesFinalizados.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    leiloesFinalizados.setForeground(Color.BLUE);
	    add(leiloesFinalizados);
	    leiloesFinalizados.addMouseListener(new OuvinteLeiloesFinalizados(this));
	    
	    leiloesGanhos = new JLabel("Listar");
	    leiloesGanhos.setBounds(270, 170, 50, 20);
	    leiloesGanhos.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    leiloesGanhos.setForeground(Color.BLUE);
	    add(leiloesGanhos);
	    leiloesGanhos.addMouseListener(new OuvinteLeiloesGanhos(this));
	    
	    leiloesQueCadastrei = new JLabel("Solicitar pdf");
	    leiloesQueCadastrei.setBounds(270, 230, 200, 20);
	    leiloesQueCadastrei.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    leiloesQueCadastrei.setForeground(Color.BLUE);
	    add(leiloesQueCadastrei);
	    leiloesQueCadastrei.addMouseListener(new OuvinteLeiloesQueCadastrei(this));
	    
	    leiloesQueGanhei = new JLabel("Solicitar pdf");
	    leiloesQueGanhei.setBounds(270, 290, 200, 20);
	    leiloesQueGanhei.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    leiloesQueGanhei.setForeground(Color.BLUE);
	    add(leiloesQueGanhei);
	    leiloesQueGanhei.addMouseListener(new OuvinteleiloesQueGanhei(this));
	    
	    excluirLeilao = new JLabel("Excluir Leilão");
	    excluirLeilao.setBounds(270, 325, 200, 20);
	    excluirLeilao.setFont(new Font("Calibri Light", Font.PLAIN, 16));
	    excluirLeilao.setForeground(Color.BLUE);
	    add(excluirLeilao);
	    excluirLeilao.addMouseListener(new OuvinteExcluirLeilao(this));
	    
	
	}
	
	private void adicionarBarraDeMenu() {
		JMenuBar barra = new JMenuBar();
		
		ImageIcon configuracoes = new ImageIcon(getClass().getResource("conf.png"));
		
		JMenu config = new JMenu();
		config.setIcon(configuracoes);
		barra.add(config);
		
		JMenuItem editarDados = new JMenuItem("Editar dados");
		editarDados.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		config.add(editarDados);
		editarDados.addActionListener(new OuvinteEditarDados(this));
		JMenuItem sair = new JMenuItem("Sair");
		sair.setFont(new Font("Calibri Light", Font.PLAIN, 14));
		sair.setMnemonic('s');
		config.add(sair);
		sair.addActionListener(new OuvinteBotaoSair(this));
		
		setJMenuBar(barra);
	}

}
