import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class TelaLeiloesDeFulano extends TelaPadrao{
	private Usuario usuario;
	private Usuario fulano;
	private JTable tabela;
	private DefaultTableModel modelo;

	public TelaLeiloesDeFulano(Usuario usuario, Usuario fulano) {
		this.usuario = usuario;
		this.fulano = fulano;
		setTitle("Leilões de "+fulano.getNome());
		adicionarBotoes();
		adicionarTabela(this);
		setVisible(true);
	}

	private class OuvintebotaoVoltar implements ActionListener{
		TelaLeiloesDeFulano telaLeiloesDeFulano;
		public OuvintebotaoVoltar(TelaLeiloesDeFulano telaLeiloesDeFulano){
			this.telaLeiloesDeFulano = telaLeiloesDeFulano;
		}
		
		public void actionPerformed(ActionEvent arg0) {
			TelaListaDeUsuarios telaListaDeUsuarios = new TelaListaDeUsuarios(usuario);
			telaListaDeUsuarios.setLocationRelativeTo(telaLeiloesDeFulano);
			telaLeiloesDeFulano.dispose();
		}
	}
	
	private class OuvintebotaoDetalharLeilao implements ActionListener{
		TelaLeiloesDeFulano telaLeiloesDeFulano;
		public OuvintebotaoDetalharLeilao(TelaLeiloesDeFulano telaLeiloesDeFulano){
			this.telaLeiloesDeFulano = telaLeiloesDeFulano;
		}
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada =tabela.getSelectedRow();
			if(tabela.getSelectedRow()!=-1) {
				Object obj = tabela.getValueAt(tabela.getSelectedRow(),
						0);
				String str=String.valueOf(obj);
				Persistencia p = new Persistencia();
				CentralDeInformacoes c = p.recuperarCentral();				
				Usuario autenticado = c.recuperarOutroUsuario(fulano.getEmail());
				for (Leilao leilao : autenticado.getLeiloes()) {
					if(leilao.getTitulo().equals(str)) {				
						TelaDetalhesDoleilao telaDetalhesDoleilao = new TelaDetalhesDoleilao(leilao, autenticado, usuario);
						telaDetalhesDoleilao.setLocationRelativeTo(telaLeiloesDeFulano);
						telaLeiloesDeFulano.dispose();
					}
				}					
			}
		}		
	}
	private void adicionarBotoes() {
		 JButton botaoDetalharLeilao = new JButton("Detalhar leilão");
		 botaoDetalharLeilao.setBounds(150, 300, 100, 35);
		 botaoDetalharLeilao.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaoDetalharLeilao);
	     
	     botaoDetalharLeilao.addActionListener(new OuvintebotaoDetalharLeilao(this));
	    
	     BotaoVoltar botaoVoltar = new BotaoVoltar();
	     add(botaoVoltar);
	     botaoVoltar.addActionListener(new OuvintebotaoVoltar(this));
	}
	
	private void adicionarTabela(JFrame frame) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    modelo = new DefaultTableModel() {
	    	public boolean isCellEditable(int row, int column) {
			       //all cells false
			       return false;
			    }
	    };
		modelo.addColumn("Título");
		modelo.addColumn("Data de Encerramento");
		modelo.addColumn("Valor atual do leilão");
		modelo.addColumn("Valor da compra");
		
		tabela = new JTable(modelo);
		
				for (Leilao leilao : fulano.getLeiloes()) {
					Date dataAtual = Calendar.getInstance().getTime();
					sdf.format(dataAtual);
					Date prazo = leilao.getPrazo();
					
					if(!(prazo.getTime()<dataAtual.getTime()) && leilao.isVendido()==false) {
						Object[] linha = new Object[]{
								leilao.getTitulo(),								
							    sdf.format(leilao.getPrazo()),
								leilao.getLance(),
								leilao.getValor()
								};
								modelo.addRow(linha);
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
