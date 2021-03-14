import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaAlterarSenha extends TelaPadrao{

	private Usuario usuario;
	private JPasswordField campoSenhaAntiga;
	private JPasswordField campoSenhaNova;
	private JPasswordField campoConfirmarSenha;
	private JButton salvar;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public JPasswordField getCampoSenhaAntiga() {
		return campoSenhaAntiga;
	}

	public void setCampoSenhaAntiga(JPasswordField campoSenhaAntiga) {
		this.campoSenhaAntiga = campoSenhaAntiga;
	}

	public JPasswordField getCampoSenhaNova() {
		return campoSenhaNova;
	}

	public void setCampoSenhaNova(JPasswordField campoSenhaNova) {
		this.campoSenhaNova = campoSenhaNova;
	}

	public JPasswordField getCampoConfirmarSenha() {
		return campoConfirmarSenha;
	}

	public void setCampoConfirmarSenha(JPasswordField campoConfirmarSenha) {
		this.campoConfirmarSenha = campoConfirmarSenha;
	}

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}

	public TelaAlterarSenha(Usuario usuario) {
		this.usuario = usuario;
		setTitle("Alterar Senha");
		adicionarJL();
		adicionarJPF();
		adicionarBotoes();
		setVisible(true);
	}
	
	private void adicionarJL() {
		JLabel textoSenhaAntiga = new JLabel("Senha antiga");
		textoSenhaAntiga.setBounds(95, 90, 100, 20);
		textoSenhaAntiga.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoSenhaAntiga);
	    
	    JLabel textoNovaSenha = new JLabel("Nova senha");
	    textoNovaSenha.setBounds(100, 140, 100, 20);
	    textoNovaSenha.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoNovaSenha);
	    
	    JLabel textoConfirmarSenha = new JLabel("Confirme a nova senha");
	    textoConfirmarSenha.setBounds(20, 190, 200, 20);
	    textoConfirmarSenha.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    add(textoConfirmarSenha);
	}
	
	private void adicionarJPF() {
		campoSenhaAntiga = new JPasswordField();
		campoSenhaAntiga.setBounds(200, 85, 180, 30);
	    add(campoSenhaAntiga);
	    
	    campoSenhaNova = new JPasswordField();
	    campoSenhaNova.setBounds(200, 135, 180, 30);
	    add(campoSenhaNova);
	    
	    campoConfirmarSenha = new JPasswordField();
	    campoConfirmarSenha.setBounds(200, 185, 180, 30);
	    add(campoConfirmarSenha);
	}

	private void adicionarBotoes() {
		salvar = new JButton("Salvar");
		salvar.setBounds(150, 300, 100, 35);
		salvar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	    add(salvar);
	    salvar.addActionListener(new OuvinteTelaAlterarsenha(this));
	    
	    BotaoCancelar BotaoCancelar = new BotaoCancelar();	     
	    add(BotaoCancelar);
	    BotaoCancelar.addActionListener(new OuvinteTelaAlterarsenha(this));
	}
	
}
