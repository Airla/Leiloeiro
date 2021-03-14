import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaEditarDados extends TelaPadrao{
	
	private JTextField nome;
	private JTextField sobrenome;
	private JTextField email;
	private JLabel alterarSenha;
	private JButton salvar;
	private JButton cancelar;
	private JCheckBox masculino;
	private JCheckBox feminino;
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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

	public JButton getSalvar() {
		return salvar;
	}

	public void setSalvar(JButton salvar) {
		this.salvar = salvar;
	}

	public JButton getCancelar() {
		return cancelar;
	}

	public void setCancelar(JButton cancelar) {
		this.cancelar = cancelar;
	}

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
	
	public TelaEditarDados(Usuario usuario) {
		super.setTitle("Editar Dados");
		this.usuario = usuario;
		adicionarJL();
		adicionarTF();
		adicionarBotoes();
		adicionarJLFuncionais();
		adicionarJCheckBox();
		setVisible(true);
	}
	
	private void adicionarJL() {
		JLabel textoNome = new JLabel("Nome");
		textoNome.setBounds(95, 90, 100, 20);
		textoNome.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		textoNome.setForeground(Color.BLACK);
	    add(textoNome);
	    
	    JLabel textoSobrenome = new JLabel("Sobrenome");
	    textoSobrenome.setBounds(50, 140, 100, 20);
	    textoSobrenome.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    textoSobrenome.setForeground(Color.BLACK);
	    add(textoSobrenome);
	    
	    JLabel textoEmail = new JLabel("Email");
		textoEmail.setBounds(95, 180, 100, 20);
		textoEmail.setFont(new Font("Calibri Light", Font.PLAIN, 18));
		textoEmail.setForeground(Color.BLACK);
	    add(textoEmail);
	    
	    JLabel textoSexo = new JLabel("Sexo");
	    textoSexo.setBounds(470, 190, 100, 20);
	    textoSexo.setFont(new Font("Calibri Light", Font.PLAIN, 18));
	    textoSexo.setForeground(Color.BLACK);
	    add(textoSexo);
	}
	
	private void adicionarTF() {
		
		nome = new JTextField(usuario.getNome());
		nome.setBounds(150, 90, 180, 30);
	    add(nome);
	    
	    sobrenome = new JTextField(usuario.getSobrenome());
	    sobrenome.setBounds(150, 130, 180, 30);
	    add(sobrenome);
	    
	    email = new JTextField(usuario.getEmail());
	    email.setBounds(150, 170, 180, 30);
	    add(email);
	}
	
	private void adicionarBotoes() {
		
		salvar = new JButton("Salvar");
		salvar.setForeground(Color.BLACK);
		salvar.setBounds(150, 300, 100, 35);
		salvar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	    add(salvar);
	    salvar.addActionListener(new OuvinteTelaEditarDados(this));
	    
	    BotaoCancelar BotaoCancelar = new BotaoCancelar();	     
	    add(BotaoCancelar);
	    BotaoCancelar.addActionListener(new OuvinteTelaEditarDados(this)); 

	}

	private class OuvinteAlterarSenha implements MouseListener{
		private TelaEditarDados  editarDados;  
		public OuvinteAlterarSenha(TelaEditarDados  editarDados) {
			this.editarDados = editarDados;
		}
		public void mouseClicked(MouseEvent e) {
			TelaAlterarSenha telaAlterarSenha = new TelaAlterarSenha(usuario);
			telaAlterarSenha.setLocationRelativeTo(editarDados);
			editarDados.dispose();
		}
		
		public void mouseEntered(MouseEvent e) {
			alterarSenha.setText("<html><u>Alterar senha</u></html>");
			alterarSenha.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		}
		
		public void mouseExited(MouseEvent e) {
			alterarSenha.setText("Alterar senha");
			alterarSenha.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	private void adicionarJLFuncionais() {
	
		alterarSenha = new JLabel("Alterar senha");
		alterarSenha.setBounds(95, 220, 100, 20);
		alterarSenha.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		alterarSenha.setForeground(Color.BLUE);
	    add(alterarSenha);
	    alterarSenha.addMouseListener(new OuvinteAlterarSenha(this));
	}
	
	private void adicionarJCheckBox() {
		
		masculino = new JCheckBox("M");
		masculino.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		masculino.setBounds(450, 220, 50 ,20);
		add(masculino);
		masculino.addItemListener(new OuvinteTelaEditarDados(this));
		
		feminino = new JCheckBox("F");
		feminino.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		feminino.setBounds(500, 220, 50 ,20);
		add(feminino);
		feminino.addItemListener(new OuvinteTelaEditarDados(this));
		
		if(usuario.getSexo()=='M') {
			masculino.setSelected(true);
		}else {
			feminino.setSelected(true);
		}
	}
}