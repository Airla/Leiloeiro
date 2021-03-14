import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class TelaExcluirLeilão extends TelaPadrao{
	
	private Usuario usuario;
	private JTable tabela;
	private DefaultTableModel modelo;
	Persistencia p = new Persistencia();
	CentralDeInformacoes central = p.recuperarCentral();
	
	public TelaExcluirLeilão(Usuario usuario) {
		this.usuario = usuario;
		super.setTitle("Excluir Leilão");
		adicionarBotoes();
		adicionarTabela(this);
		setVisible(true);
	}
	
	private class OuvintebotaoVoltar implements ActionListener{
		TelaExcluirLeilão telaExcluirLeilão;
		public OuvintebotaoVoltar(TelaExcluirLeilão telaExcluirLeilão){
			this.telaExcluirLeilão = telaExcluirLeilão;
		}
		
		
		public void actionPerformed(ActionEvent arg0) {
			TelaMinhaConta minhaConta = new TelaMinhaConta(usuario);
			minhaConta.setLocationRelativeTo(telaExcluirLeilão);
			telaExcluirLeilão.dispose();
		}		
	}
	
	private class OuvintebotaoExcluir implements ActionListener{
		TelaExcluirLeilão telaExcluirLeilão;
		public OuvintebotaoExcluir(TelaExcluirLeilão telaExcluirLeilão){
			this.telaExcluirLeilão = telaExcluirLeilão;
		}
		
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada =tabela.getSelectedRow();
			if(tabela.getSelectedRow()!=-1) {
				int apagar = JOptionPane.showConfirmDialog(telaExcluirLeilão, "Tem certeza que deseja excluir este leilão?", null, JOptionPane.YES_NO_OPTION);
				if(apagar==JOptionPane.YES_OPTION) {
					Usuario autenticado = central.recuperarUsuario(usuario.getEmail(), usuario.getSenha());
					autenticado.getLeiloes().remove(linhaSelecionada);
					p.salvarCentral(central);
					modelo.removeRow(linhaSelecionada);
					JOptionPane.showMessageDialog(telaExcluirLeilão, "Leilão excluido!", null, JOptionPane.INFORMATION_MESSAGE);
				}			
			}
		}		
	}
	
	private void adicionarBotoes() {				

		 JButton botaoExcluir = new JButton("Excluir");
		 botaoExcluir.setBounds(150, 300, 100, 35);
		 botaoExcluir.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaoExcluir);
	     
	     botaoExcluir.addActionListener(new OuvintebotaoExcluir(this));
	    
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
				modelo.addColumn("Leilões Ativos");
				
				tabela = new JTable(modelo);

					for (Usuario u : central.getUsuariosCadastrados()) {
						if(u.getEmail().equals(usuario.getEmail())) {
							for (Leilao leilao : u.getLeiloes()) {
								Date dataAtual = Calendar.getInstance().getTime();
							    Date prazo = leilao.getPrazo();
							    
							    if(prazo.after(dataAtual) && leilao.isVendido()==false) {
							    	Object[] linha = new Object[]{
											leilao.getTitulo()
										};
										modelo.addRow(linha);
							    }
							}
						}
			}
		try {
			tabela.addRowSelectionInterval(0, 0);
		}catch(Exception erro) {
			
		}
		
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 80, 630, 200);
		int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		frame.add(scroll);
	}

}
