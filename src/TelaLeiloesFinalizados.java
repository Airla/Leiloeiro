import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class TelaLeiloesFinalizados extends TelaPadrao{
	private DefaultTableModel modelo;
	private JTable tabela;
	private Usuario usuario;
	private JTextArea msg;
	
	public TelaLeiloesFinalizados(Usuario usuario) {
		this.usuario = usuario;
		super.setTitle("Leilões Recentemente Finalizados");
		adicionarTabela(this);
		adicionarBotoes();
		adicionarTexto();
		adicionarJL();
		setVisible(true);
	}

	private void adicionarTabela(JFrame frame) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
		};
		modelo.addColumn("Título");
		modelo.addColumn("Data de compra");
		modelo.addColumn("Valor da compra");
		modelo.addColumn("Email do comprador");

		tabela = new JTable(modelo);
		
		Persistencia p = new Persistencia();
		CentralDeInformacoes central = p.recuperarCentral();
		Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
		Date dataAtual = Calendar.getInstance().getTime();
		sdf.format(dataAtual);		
		
			for (Leilao leilao : u.getLeiloes()) {				    			    
				if(leilao.isVendido()) {
				    Object[] linha = new Object[]{
						leilao.getTitulo(),
						sdf.format(leilao.getDataDaCompra()),
						leilao.getValorDaCompra(),
						leilao.getComprador()
					};
					modelo.addRow(linha);	
			}
}
		try {
			tabela.addRowSelectionInterval(0, 0);
		}catch(Exception erro) {

		}
		JScrollPane scroll = new JScrollPane(tabela);
		scroll.setBounds(0, 100, 630, 100);
		int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		frame.add(scroll);
	}
	
	private class OuvinteBotaoVoltar implements ActionListener{
		private TelaLeiloesFinalizados telaLeiloesFinalizados;
		private OuvinteBotaoVoltar(TelaLeiloesFinalizados telaLeiloesFinalizados){
			this.telaLeiloesFinalizados = telaLeiloesFinalizados;
		}
		public void actionPerformed(ActionEvent arg0) {
			Persistencia p = new Persistencia();
			CentralDeInformacoes central = p.recuperarCentral();
			Usuario u = central.recuperarOutroUsuario(usuario.getEmail());
			TelaMinhaConta telaMinhaConta = new TelaMinhaConta (u);
			telaMinhaConta.setLocationRelativeTo(telaLeiloesFinalizados);
			telaLeiloesFinalizados.dispose();
		}
		
	}
	
	private class OuvinteEnviar implements ActionListener{
		private TelaLeiloesFinalizados telaLeiloesFinalizados;
		private OuvinteEnviar(TelaLeiloesFinalizados telaLeiloesFinalizados){
			this.telaLeiloesFinalizados = telaLeiloesFinalizados;
		}
		public void actionPerformed(ActionEvent arg0) {
			int linhaSelecionada =tabela.getSelectedRow();
			if(tabela.getSelectedRow()!=-1) {
				if(msg.getText().equals("")) {
					JOptionPane.showMessageDialog(telaLeiloesFinalizados, "Não é possível enviar mensagens vazias!", null, JOptionPane.ERROR_MESSAGE);						
				}else {
					Object obj = tabela.getValueAt(tabela.getSelectedRow(),
							3);
					String emailDestinatario=String.valueOf(obj);
					JavaMail email = new JavaMail();
					email.enviarEmail(usuario.getSenha(),usuario.getEmail(),emailDestinatario, msg.getText(),"Mensagem do vendedor "+usuario.getNome());
					JOptionPane.showMessageDialog(telaLeiloesFinalizados, "Sua mensagem foi enviada!", null, JOptionPane.INFORMATION_MESSAGE);															
					}
				}			
		}		
	}
	
	private void adicionarBotoes() {
		 BotaoVoltar botaoVoltar = new BotaoVoltar();
		 add(botaoVoltar);
		 botaoVoltar.addActionListener(new OuvinteBotaoVoltar(this));
		 
		 JButton enviar = new JButton("Enviar");
		 enviar.setBounds(20, 320, 80, 30);
		 enviar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
		 add(enviar);
		 enviar.addActionListener(new OuvinteEnviar(this));
	}

	private void adicionarJL() {
		JLabel label = new JLabel("Selecione o comprador que deseja enviar a mensagem");
		label.setFont(new Font("Calibri Light", Font.PLAIN, 15));
		label.setBounds(20, 200, 400, 30);
		add(label);
		
	}
	
	private void adicionarTexto() {
		msg = new JTextArea();
		msg.setLineWrap(true);
		msg.setWrapStyleWord(true);
		msg.setEditable(true);
		JScrollPane scroll = new JScrollPane(msg);
		int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
		scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		scroll.setBounds(20, 230, 220, 80);
		add(scroll);
	}
}
