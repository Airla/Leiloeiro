import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class OuvinteTelaDeCadastro implements ActionListener, ItemListener{
	TelaDeCadastro telaDeCadastro;
	
	public OuvinteTelaDeCadastro(TelaDeCadastro telaDeCadastro) {
		this.telaDeCadastro = telaDeCadastro;
	}
	
	public void actionPerformed(ActionEvent e){
		String botao = e.getActionCommand();
		if(botao.equals("Cadastrar")) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes central = p.recuperarCentral();
			
			String nome = telaDeCadastro.getNome().getText();
			String sobrenome = telaDeCadastro.getSobrenome().getText();
			String email = telaDeCadastro.getEmail().getText();
			String senha = telaDeCadastro.getSenha().getText();
			String confirmeSenha = telaDeCadastro.getConfimarSenha().getText();			
			char sexo =' ';
			
			if(telaDeCadastro.getMasculino().isSelected()) {
				sexo='M';
			}else if(telaDeCadastro.getFeminino().isSelected()) {
				sexo='F';
			}
			
			//para validar o email
			 String regex = "[a-z._-]+@[a-z.]+.com";
			 Pattern pattern = Pattern.compile(regex); 
			 String source = email;
			 Matcher matcher = pattern.matcher(source);
			 boolean emailValido = false;
			 if (matcher.find() && matcher.group().equals(source)){ 		    
				 emailValido=true;
		    	}
			 		
			boolean usuarioDuplicado = central.verificarSeJaUsuarioExiste(email);
			 
			if(nome.equals("") || sobrenome.equals("") || email.equals("") || senha.equals("") || confirmeSenha.equals("") || sexo==' ') {
				JOptionPane.showMessageDialog(telaDeCadastro, "Preencha todas as informações!", "Erro!", JOptionPane.ERROR_MESSAGE);
			} else if(!nome.matches("[A-Z][a-z]{1,}")){
				JOptionPane.showMessageDialog(telaDeCadastro, "Nome inválido!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else if(!sobrenome.matches("[A-Z][a-z]{1,}")){
				JOptionPane.showMessageDialog(telaDeCadastro, "Sobreome inválido!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else if (!emailValido){ 		    
				JOptionPane.showMessageDialog(telaDeCadastro, "Email inválido!", "Erro!", JOptionPane.ERROR_MESSAGE); 
	    	}else if (!senha.equals(confirmeSenha)){ 		    
				JOptionPane.showMessageDialog(telaDeCadastro, "A senha e a confirmação da senha não coincidem!", "Erro!", JOptionPane.ERROR_MESSAGE); 
	    	}else if (usuarioDuplicado){ 		    
				JOptionPane.showMessageDialog(telaDeCadastro, "Já existe um usuário com este email, por favor insira outro!", "Erro!", JOptionPane.ERROR_MESSAGE); 
	    	}else if(senha.length()<8){
	    		JOptionPane.showMessageDialog(telaDeCadastro, "A senha precisa ter no mínimo 8 digitos!", "Erro!", JOptionPane.ERROR_MESSAGE);
	    	}else {
			
				Usuario usuario = new Usuario(nome, sobrenome, email, senha, confirmeSenha, sexo);				
				central.adicionarUsuario(usuario);
				p.salvarCentral(central);
				JOptionPane.showMessageDialog(telaDeCadastro, "Usuario cadastrado", null, JOptionPane.INFORMATION_MESSAGE);
				TelaDeLogin telaDeLogin = new TelaDeLogin();
				telaDeLogin.getCampoEmail().setText(usuario.getEmail());
				telaDeLogin.setLocationRelativeTo(telaDeCadastro);
				telaDeCadastro.dispose();
			}		
			
		}else if(botao.equals("Cancelar")) {		
			TelaDeLogin telaDeLogin = new TelaDeLogin();
			telaDeLogin.setLocationRelativeTo(telaDeCadastro);
			telaDeCadastro.dispose();
		}		
	}

	public void itemStateChanged(ItemEvent e) {
		
		if(telaDeCadastro.getMasculino().isSelected()) {
			telaDeCadastro.getFeminino().setEnabled(false);
		}else if(telaDeCadastro.getFeminino().isSelected()) {
			telaDeCadastro.getMasculino().setEnabled(false);
		}
		
		if(!telaDeCadastro.getMasculino().isSelected()) {
			telaDeCadastro.getFeminino().setEnabled(true);
		}
		
		if(!telaDeCadastro.getFeminino().isSelected()) {
			telaDeCadastro.getMasculino().setEnabled(true);
		}		
	}
}
