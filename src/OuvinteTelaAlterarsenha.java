import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class OuvinteTelaAlterarsenha implements ActionListener{

	private TelaAlterarSenha alterarSenha;	
	public OuvinteTelaAlterarsenha(TelaAlterarSenha alterarSenha) {
		this.alterarSenha = alterarSenha;
	}
	
	public void actionPerformed(ActionEvent e) {
		String botao = e.getActionCommand();
		
		if(botao.equals("Salvar")) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes central = p.recuperarCentral();
			
			String senhaAtiga = alterarSenha.getCampoSenhaAntiga().getText();
			String novaSenha = alterarSenha.getCampoSenhaNova().getText();			
			String confirNovaSenha = alterarSenha.getCampoConfirmarSenha().getText();						
			 
			if(senhaAtiga.equals("") || novaSenha.equals("") || confirNovaSenha.equals("")) {
				JOptionPane.showMessageDialog(alterarSenha, "Preencha todas as informações!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}else if (!senhaAtiga.equals(alterarSenha.getUsuario().getSenha())){ 		    
				JOptionPane.showMessageDialog(alterarSenha, "Senha incorreta!", "Erro!", JOptionPane.ERROR_MESSAGE); 
	    	}else if (!novaSenha.equals(confirNovaSenha)){ 		    
				JOptionPane.showMessageDialog(alterarSenha, "A nova senha e a confirmação da senha não coincidem!", "Erro!", JOptionPane.ERROR_MESSAGE); 
	    	}else {
			
	    		Usuario autenticado = central.recuperarUsuario(alterarSenha.getUsuario().getEmail(), alterarSenha.getUsuario().getSenha());
				autenticado.setSenha(novaSenha);
				p.salvarCentral(central);
	    		JOptionPane.showMessageDialog(alterarSenha, "Senha Alterada!");
				TelaMinhaConta telaMinhaConta = new TelaMinhaConta(autenticado);
				telaMinhaConta.setLocationRelativeTo(alterarSenha);
				alterarSenha.dispose();
			}		
		
		}else if(botao.equals("Cancelar")) {			
			TelaEditarDados telaEditarDados = new TelaEditarDados(alterarSenha.getUsuario());
			telaEditarDados.setLocationRelativeTo(alterarSenha);
			alterarSenha.dispose();
		}	    	
	}

}
