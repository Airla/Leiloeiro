import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class OuvinteTelaDeLogin implements ActionListener{

   private TelaDeLogin telaDeLogin;

   public OuvinteTelaDeLogin(TelaDeLogin telaDeLogin) {
       this.telaDeLogin = telaDeLogin;
   }
 
   public void actionPerformed(ActionEvent e) {
	   String botao = e.getActionCommand();
	   
	   if(botao.equals("Entrar")) {
		   String email = telaDeLogin.getCampoEmail().getText();
			String senha = telaDeLogin.getCampoSenha().getText();
			
			Persistencia p = new Persistencia();
			CentralDeInformacoes c = p.recuperarCentral();
			
			Usuario autenticado = c.recuperarUsuario(email, senha);
			
			if(autenticado==null) {
				JOptionPane.showMessageDialog(telaDeLogin, "Preencha todas as informações corretamente!", "Erro!", JOptionPane.ERROR_MESSAGE);
			}
			else {
				TelaMinhaConta telaMinhaConta = new TelaMinhaConta(autenticado);
				telaMinhaConta.setLocationRelativeTo(telaDeLogin);
				telaDeLogin.dispose();
			} 
	   }else if(botao.equals("Cancelar")) {		   
		   TelaLeiloes telaLeiloes = new TelaLeiloes();
		   telaLeiloes.setLocationRelativeTo(telaDeLogin);
		   telaDeLogin.dispose();
	   }
				
	}

}
