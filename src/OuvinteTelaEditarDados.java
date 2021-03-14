import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class OuvinteTelaEditarDados implements ActionListener, ItemListener{
	private TelaEditarDados telaEditarDados;	
	public OuvinteTelaEditarDados(TelaEditarDados telaEditarDados) {
		this.telaEditarDados = telaEditarDados;
	}
	
	public void actionPerformed(ActionEvent e) {
		String botao = e.getActionCommand();
		
		if(botao.equals("Salvar")) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes central = p.recuperarCentral();
			
			String nome = telaEditarDados.getNome().getText();
			String sobrenome = telaEditarDados.getSobrenome().getText();
			String email = telaEditarDados.getEmail().getText();			
			char sexo =' ';
			if(telaEditarDados.getMasculino().isSelected()) {
				sexo='M';
			}else if(telaEditarDados.getFeminino().isSelected()) {
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
			 
			if(nome.equals("") || sobrenome.equals("") || email.equals("") ||  sexo==' ') {
				JOptionPane.showMessageDialog(telaEditarDados, "Preencha todas as informações!", "Erro!", JOptionPane.ERROR_MESSAGE);
			} else if(!nome.matches("[A-Z][a-z]{1,}")){
				JOptionPane.showMessageDialog(telaEditarDados, "Nome inválido!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else if(!sobrenome.matches("[A-Z][a-z]{1,}")){
				JOptionPane.showMessageDialog(telaEditarDados, "Sobreome inválido!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else if (!emailValido){ 		    
				JOptionPane.showMessageDialog(telaEditarDados, "Email inválido!", "Erro!", JOptionPane.ERROR_MESSAGE); 
	    	}else if (!telaEditarDados.getUsuario().getEmail().equals(email) && usuarioDuplicado){ 		    		    
					JOptionPane.showMessageDialog(telaEditarDados, "Já existe um usuário com este email, por favor insira outro!", "Erro!", JOptionPane.ERROR_MESSAGE); 		    	
	    	}else {
			
	    		Usuario autenticado = central.recuperarUsuario(telaEditarDados.getUsuario().getEmail(), telaEditarDados.getUsuario().getSenha());
				autenticado.setNome(nome);
				autenticado.setSobrenome(sobrenome);
				autenticado.setEmail(email);
				autenticado.setSexo(sexo);
				p.salvarCentral(central);
				JOptionPane.showMessageDialog(telaEditarDados, "Dados atualizados", null, JOptionPane.INFORMATION_MESSAGE);
				TelaMinhaConta telaMinhaConta = new TelaMinhaConta(autenticado);
				telaMinhaConta.setLocationRelativeTo(telaEditarDados);
				telaEditarDados.dispose();
			}		
		
		}else if(botao.equals("Cancelar")) {		
			TelaMinhaConta telaMinhaConta = new TelaMinhaConta(telaEditarDados.getUsuario());
			telaMinhaConta.setLocationRelativeTo(telaEditarDados);
			telaEditarDados.dispose();
		}	    	
	}

	public void itemStateChanged(ItemEvent e) {		
		
		if(telaEditarDados.getMasculino().isSelected()) {
			telaEditarDados.getFeminino().setEnabled(false);
		}else if(telaEditarDados.getFeminino().isSelected()) {
			telaEditarDados.getMasculino().setEnabled(false);
		}
		
		if(!telaEditarDados.getMasculino().isSelected()) {
			telaEditarDados.getFeminino().setEnabled(true);
		}
		
		if(!telaEditarDados.getFeminino().isSelected()) {
			telaEditarDados.getMasculino().setEnabled(true);
		}		
	}
}