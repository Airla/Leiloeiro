import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TelaDeCadastro extends TelaPadrao{
	
	private JTextField nome;
	private JTextField sobrenome;
	private JTextField email;
	private JPasswordField senha;
	private JPasswordField confimarSenha;
	private JButton botaoCadastrar;
	private JCheckBox masculino;
	private JCheckBox feminino;

	public JCheckBox getMasculino() {
		return masculino;
	}
	public void setMasculino(JCheckBox masculino) {
		this.masculino = masculino;
	}
	public JCheckBox getFeminino() {
		return feminino;
	}
	public void setFeminino(JCheckBox feminino) {
		this.feminino = feminino;
	}
	public JButton getBotaoCadastrar() {
		return botaoCadastrar;
	}
	public void setBotaoCadastrar(JButton botaoCadastrar) {
		this.botaoCadastrar = botaoCadastrar;
	}
	public JTextField getNome() {
		return nome;
	}
	public void setNome(JTextField nome) {
		this.nome = nome;
	}
	public JTextField getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(JTextField sobrenome) {
		this.sobrenome = sobrenome;
	}
	public JTextField getEmail() {
		return email;
	}
	public void setEmail(JTextField email) {
		this.email = email;
	}
	public JPasswordField getSenha() {
		return senha;
	}
	public void setSenha(JPasswordField senha) {
		this.senha = senha;
	}
	public JPasswordField getConfimarSenha() {
		return confimarSenha;
	}
	public void setConfimarSenha(JPasswordField confimarSenha) {
		this.confimarSenha = confimarSenha;
	}
	
	public TelaDeCadastro() {
		super.setTitle("Cadastre-se");
		adicionarJL();
		adicionarCamposDeTexto();
		adicionarBotoes();
		adicionarJCheckBox();
		setVisible(true);
	}
	
	private void adicionarJL() {
		JLabel textoNome = new JLabel("Nome");
		textoNome.setBounds(95, 80, 100, 20);
		textoNome.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		textoNome.setForeground(Color.BLACK);
	    add(textoNome);
	    
	    JLabel textoSobrenome = new JLabel("Sobrenome");
	    textoSobrenome.setBounds(50, 120, 100, 20);
	    textoSobrenome.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    textoSobrenome.setForeground(Color.BLACK);
	    add(textoSobrenome);
	    
	    JLabel textoEmail = new JLabel("Email");
		textoEmail.setBounds(95, 160, 100, 20);
		textoEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		textoEmail.setForeground(Color.BLACK);
	    add(textoEmail);
	    
	    JLabel textoSenha = new JLabel("Senha");
	    textoSenha.setBounds(95, 200, 100, 20);
	    textoSenha.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    textoSenha.setForeground(Color.BLACK);
	    add(textoSenha);
		
	    JLabel textoConfirmarSenha = new JLabel("Confirmar Senha");
	    textoConfirmarSenha.setBounds(20, 240, 140, 20);
	    textoConfirmarSenha.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    textoConfirmarSenha.setForeground(Color.BLACK);
	    add(textoConfirmarSenha);
	    
	    JLabel textoSexo = new JLabel("Sexo");
	    textoSexo.setBounds(470, 170, 100, 20);
	    textoSexo.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    textoSexo.setForeground(Color.BLACK);
	    add(textoSexo);
	}
	
	private void adicionarCamposDeTexto(){
		nome = new JTextField();
		nome.setBounds(150, 70, 180, 30);
	    add(nome);
	    
	    sobrenome = new JTextField();
	    sobrenome.setBounds(150, 110, 180, 30);
	    add(sobrenome);
	    
	    email = new JTextField();
	    email.setBounds(150, 150, 180, 30);
	    add(email);
	    
	    senha = new JPasswordField();
	    senha.setBounds(150, 190, 180, 30);
	    add(senha);
	    
	    confimarSenha = new JPasswordField();
	    confimarSenha.setBounds(150, 230, 180, 30);
	    add(confimarSenha);
		
	}
	
	private void adicionarBotoes() {
		
		 botaoCadastrar = new JButton("Cadastrar");
		 botaoCadastrar.setForeground(Color.BLACK);
		 botaoCadastrar.setBounds(150, 300, 100, 35);
		 botaoCadastrar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaoCadastrar);
	     botaoCadastrar.addActionListener(new OuvinteTelaDeCadastro(this));

	     BotaoCancelar BotaoCancelar = new BotaoCancelar();
	     add(BotaoCancelar);
	     BotaoCancelar.addActionListener(new OuvinteTelaDeCadastro(this));		
	}
	
	private void adicionarJCheckBox() {
		masculino = new JCheckBox("M", false);
		masculino.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		masculino.setBounds(450, 200, 50 ,20);
		add(masculino);
		masculino.addItemListener(new OuvinteTelaDeCadastro(this));
		
		feminino = new JCheckBox("F", false);
		feminino.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		feminino.setBounds(500, 200, 50 ,20);
		add(feminino);
		feminino.addItemListener(new OuvinteTelaDeCadastro(this));
	}

}
