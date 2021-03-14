import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TelaRedefinirSenha extends TelaPadrao{
	
	private JTextField campoRedefinirSenha;
	private JButton botaoRedefinirSenha;
	
	public JButton getBotaoRedefinirSenha() {
		return botaoRedefinirSenha;
	}

	public void setBotaoRedefinirSenha(JButton botaoRedefinirSenha) {
		this.botaoRedefinirSenha = botaoRedefinirSenha;
	}

	public JTextField getCampoRedefinirSenha() {
		return campoRedefinirSenha;
	}

	public void setCampoRedefinirSenha(JTextField campoRedefinirSenha) {
		this.campoRedefinirSenha = campoRedefinirSenha;
	}

	public TelaRedefinirSenha() {
		super.setTitle("Redefinir Senha");
		adicionarJL();
		adicionarCamposDeTexto();
		adicionarBotoes();
		setVisible(true);
	}
	
	private void adicionarJL() {
		JLabel textoCentral = new JLabel("Podemos ajudar você a redefinir a senha usando o endreço de Email");
		textoCentral.setBounds(20, 110, 500, 20);
		textoCentral.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		textoCentral.setForeground(Color.BLACK);
	    add(textoCentral);
	    
	    JLabel textoCentral2 = new JLabel("vinculado à conta.");
		textoCentral2.setBounds(20, 135, 500, 20);
		textoCentral2.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		textoCentral2.setForeground(Color.BLACK);
	    add(textoCentral2);
	    
	    JLabel textoEmail = new JLabel("Email");
		textoEmail.setBounds(20, 210, 40, 20);
		textoEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		textoEmail.setForeground(Color.BLACK);
	    add(textoEmail);
	}
	
	private void adicionarCamposDeTexto() {
	     
		campoRedefinirSenha = new JTextField();
		campoRedefinirSenha.setBounds(70, 200, 180, 35);
	    add(campoRedefinirSenha);
	}
	
	
	private class Ouvinte implements ActionListener{
		TelaRedefinirSenha telaRedefinirSenha;
		public Ouvinte(TelaRedefinirSenha telaRedefinirSenha) {
			this.telaRedefinirSenha = telaRedefinirSenha;
		}

		public void actionPerformed(ActionEvent e) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes central = p.recuperarCentral();
			Usuario autenticado = central.recuperarOutroUsuario(campoRedefinirSenha.getText());
			
			if(campoRedefinirSenha.getText().equals("")) {
				JOptionPane.showMessageDialog(telaRedefinirSenha, "Preencha o campo com o seu email!", null, JOptionPane.ERROR_MESSAGE);				
			}else if(autenticado==null) {
				JOptionPane.showMessageDialog(telaRedefinirSenha, "Não encontramos nenhuma conta cadastrada\n com esse endereço de email!", null, JOptionPane.ERROR_MESSAGE);								
			}else {
				String[] carct ={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

			    String msg="";


			    for (int x=0; x<10; x++){
			        int j = (int) (Math.random()*carct.length);
			        msg += carct[j];
			    }
			    
			
			JavaMail email = new JavaMail();
			email.enviarEmail("leiloes2018","monteiroleiloes2018@gmail.com",autenticado.getEmail(), "Sua nova senha é: "+msg,"Recupereção de senha");
			JOptionPane.showMessageDialog(telaRedefinirSenha, "Sua nova senha foi enviar para seu endereço de email!", null, JOptionPane.INFORMATION_MESSAGE);
			autenticado.setSenha(msg);
			p.salvarCentral(central);
			TelaDeLogin telaDeLogin = new TelaDeLogin();
			telaDeLogin.setLocationRelativeTo(telaRedefinirSenha);
			telaRedefinirSenha.dispose();
			}			 
			
		}
		
	}
	
	private class OuvinteBotaoVoltar implements ActionListener{
		private TelaRedefinirSenha telaRedefinirSenha;
		
		public OuvinteBotaoVoltar(TelaRedefinirSenha telaRedefinirSenha) {
		       this.telaRedefinirSenha = telaRedefinirSenha;
		   }
		
		public void actionPerformed(ActionEvent arg0) {
			TelaDeLogin telaDeLogin = new TelaDeLogin();
			telaDeLogin.setLocationRelativeTo(telaRedefinirSenha);
			telaRedefinirSenha.dispose();
		}

	}
	private void adicionarBotoes() {
		
		 botaoRedefinirSenha = new JButton("Redefinir Senha");
		 botaoRedefinirSenha.setBounds(150, 300, 100, 35);
		 botaoRedefinirSenha.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaoRedefinirSenha);
	     botaoRedefinirSenha.addActionListener(new Ouvinte(this));

	     BotaoVoltar botaoVoltar = new BotaoVoltar();
	     add(botaoVoltar);
	     botaoVoltar.addActionListener(new OuvinteBotaoVoltar(this));
		
	}

}
