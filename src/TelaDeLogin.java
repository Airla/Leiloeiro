import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaDeLogin extends TelaPadrao{
	
	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JLabel cadastrese;
	private  JLabel esqueceuSenha;
	
	public JLabel getCadastrese() {
		return cadastrese;
	}

	public void setCadastrese(JLabel cadastrese) {
		this.cadastrese = cadastrese;
	}

	public JLabel getEsqueceuSenha() {
		return esqueceuSenha;
	}

	public void setEsqueceuSenha(JLabel esqueceuSenha) {
		this.esqueceuSenha = esqueceuSenha;
	}

	public JTextField getCampoEmail() {
		return campoEmail;
	}

	public void setCampoEmail(JTextField campoEmail) {
		this.campoEmail = campoEmail;
	}

	public JPasswordField getCampoSenha() {
		return campoSenha;
	}

	public void setCampoSenha(JPasswordField campoSenha) {
		this.campoSenha = campoSenha;
	}

	public TelaDeLogin() {
		super.setTitle("Tela de Login");
		adicionarJLabel();
		adicionarCamposDeTexto();
		adicionarBotoes();
		adicionarJLFucionais();
		setVisible(true);
	}
	
	public void adicionarJLabel() {
		
		 JLabel textoEmail = new JLabel("Email");
		 textoEmail.setBounds(20, 120, 40, 20);
		 textoEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		 textoEmail.setForeground(Color.BLACK);
	     add(textoEmail);
	     
	     JLabel textoSenha = new JLabel("Senha");
	     textoSenha.setBounds(20, 180, 50, 20);
	     textoSenha.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	     textoSenha.setForeground(Color.BLACK);
	     add(textoSenha);
	     
	     JLabel textoNaoTemConta = new JLabel("Não tem conta?");
	     textoNaoTemConta.setBounds(350, 125, 100, 20);
	     textoNaoTemConta.setFont(new Font("Calibri Light", Font.PLAIN, 15));
	     textoNaoTemConta.setForeground(Color.BLACK);
	     add(textoNaoTemConta);
     
	}
	
	private void adicionarCamposDeTexto() {
		 campoEmail = new JTextField();
	     campoEmail.setBounds(90, 110, 180, 35);
	     add(campoEmail);
	     
	     campoSenha = new JPasswordField();
	     campoSenha.setBounds(90, 170, 180, 35);
	     add(campoSenha);
	}
	
	private void adicionarBotoes() {
		
		 JButton botaoEntrar = new JButton("Entrar");
	     botaoEntrar.setBounds(150, 300, 100, 35);
	     botaoEntrar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaoEntrar);
	     
	     botaoEntrar.addActionListener(new OuvinteTelaDeLogin(this));

	     BotaoCancelar BotaoCancelar = new BotaoCancelar();	     
	     add(BotaoCancelar);
	     BotaoCancelar.addActionListener(new OuvinteTelaDeLogin(this));
		
	}
	
	private class OuvinteCadastrar implements MouseListener{
	
		private TelaDeLogin telaDeLogin;
		   public OuvinteCadastrar(TelaDeLogin telaDeLogin) {
		       this.telaDeLogin = telaDeLogin;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			TelaDeCadastro  telaDeCadastro = new TelaDeCadastro();
			telaDeCadastro.setLocationRelativeTo(telaDeLogin);
			telaDeLogin.dispose();
			
		}
		
		public void mouseEntered(MouseEvent e) {
			cadastrese.setText("<html><u>Cadastre-se</u></html>");
			cadastrese.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		}
		
		public void mouseExited(MouseEvent e) {
			cadastrese.setText("Cadastre-se");
			cadastrese.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private class OuvinteEsqueceuSenha implements MouseListener{

		private TelaDeLogin telaDeLogin;
		   public OuvinteEsqueceuSenha(TelaDeLogin telaDeLogin) {
		       this.telaDeLogin = telaDeLogin;
		   }
		public void mouseClicked(MouseEvent e) {
			TelaRedefinirSenha telaRedefinirSenha = new TelaRedefinirSenha();
			telaRedefinirSenha.setLocationRelativeTo(telaDeLogin);
			telaDeLogin.dispose();
			
		}

		public void mouseEntered(MouseEvent e) {
			esqueceuSenha.setText("<html><u>Esqueceu a senha?</u></html>");
			esqueceuSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
		
		public void mouseExited(MouseEvent e) {
			esqueceuSenha.setText("Esqueceu a senha?");
			esqueceuSenha.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			
		}

		public void mousePressed(MouseEvent arg0) {
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private void adicionarJLFucionais() {
		
		 cadastrese = new JLabel("Cadastre-se");
		 cadastrese.setBounds(450, 125, 100, 20);
		 cadastrese.setFont(new Font("Calibri Light", Font.ITALIC, 15));
		 cadastrese.setForeground(Color.BLUE);
	     add(cadastrese);
	     cadastrese.addMouseListener(new OuvinteCadastrar(this));
	     
	     esqueceuSenha = new JLabel("Esqueceu a senha?");
	     esqueceuSenha.setBounds(350, 185, 150, 20);
	     esqueceuSenha.setFont(new Font("Calibri Light", Font.ITALIC, 15));
	     esqueceuSenha.setForeground(Color.BLUE);
		 	
		 add(esqueceuSenha);
		 esqueceuSenha.addMouseListener(new OuvinteEsqueceuSenha(this));
		 
	}

}
