import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class TelaListaDeUsuarios extends TelaPadrao{
	
	private Usuario usuario;
	private JTable tabela;
	private DefaultTableModel modelo;
	
	public TelaListaDeUsuarios(Usuario usuario) {
		this.usuario = usuario;
		super.setTitle("Lista de Usuários Cadastrados");
		adicionarBotoes();
		adicionarTabela(this);
		setVisible(true);
	}
	
	private class OuvintebotaoVoltar implements ActionListener{
		private TelaListaDeUsuarios telaListaDeUsuarios;
		private OuvintebotaoVoltar(TelaListaDeUsuarios telaListaDeUsuarios) {
			this.telaListaDeUsuarios = telaListaDeUsuarios;
		}
		public void actionPerformed(ActionEvent arg0) {
			TelaMinhaConta minhaConta = new TelaMinhaConta(usuario);
			minhaConta.setLocationRelativeTo(telaListaDeUsuarios);
			telaListaDeUsuarios.dispose();
			
		}
		
	}
	
	private class OuvintebotaoListarLeiloes implements ActionListener{
		private TelaListaDeUsuarios telaListaDeUsuarios;
		private OuvintebotaoListarLeiloes(TelaListaDeUsuarios telaListaDeUsuarios) {
			this.telaListaDeUsuarios = telaListaDeUsuarios;
		}
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada =tabela.getSelectedRow();
			if(tabela.getSelectedRow()!=-1) {
				Object obj = tabela.getValueAt(tabela.getSelectedRow(),
						1);
				String str=String.valueOf(obj);
				Persistencia p = new Persistencia();
				CentralDeInformacoes c = p.recuperarCentral();				
				Usuario autenticado = c.recuperarOutroUsuario(str);
				Date dataAtual = Calendar.getInstance().getTime();
				int tamanho = 0;
				for (Leilao l : autenticado.getLeiloes()) {
					if(l.getPrazo().after(dataAtual) && !l.isVendido()){
						tamanho++;
					}
				}
				 if(tamanho>0) {
					 TelaLeiloesDeFulano telaLeiloesDeFulano = new TelaLeiloesDeFulano(usuario, autenticado);
					 telaLeiloesDeFulano.setLocationRelativeTo(telaListaDeUsuarios);
					 telaListaDeUsuarios.dispose(); 
				 }else {
					 JOptionPane.showMessageDialog(telaListaDeUsuarios, "Este usuario não possui lelões cadastrados!", null, JOptionPane.ERROR_MESSAGE);
				 }
				
			}
		}
		
	}
	private void adicionarBotoes() {				

		 JButton botaoListarLeiloes = new JButton("Listar Leilões");
		 botaoListarLeiloes.setBounds(150, 300, 100, 35);
		 botaoListarLeiloes.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaoListarLeiloes);
	     
	     botaoListarLeiloes.addActionListener(new OuvintebotaoListarLeiloes(this));
	    
	     BotaoVoltar botaoVoltar = new BotaoVoltar();
	     add(botaoVoltar);
	     botaoVoltar.addActionListener(new OuvintebotaoVoltar(this));		
	}
	
	private void adicionarTabela(JFrame frame) {

	    modelo = new DefaultTableModel() {
	    	public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
	    };
		modelo.addColumn("Nome");
		modelo.addColumn("Email");
		
		tabela = new JTable(modelo);
		
		Persistencia p = new Persistencia();
		CentralDeInformacoes central = p.recuperarCentral();
		
		for (Usuario u : central.getUsuariosCadastrados()) {
			Object[] linha = new Object[]{
				u.getNome(),
				u.getEmail()
			};
			modelo.addRow(linha);
		}
		
		tabela.addRowSelectionInterval(0, 0);
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 80, 630, 200);
		int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		frame.add(scroll);
	}
}
