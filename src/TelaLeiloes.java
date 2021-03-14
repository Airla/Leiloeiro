import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class TelaLeiloes extends TelaPadrao{
	private JLabel fazerLogin;
	private DefaultTableModel modelo;
	private JTable tabela;
	private Persistencia p = new Persistencia();
	private CentralDeInformacoes central = p.recuperarCentral();
	private Date dataAtual = Calendar.getInstance().getTime();
	public TelaLeiloes() {
		super.setTitle("Leilões");
		adicionarJLF();
		adicionarBotoes();
		adicionarLeilaoVencidoAosLeiloesGanhos();
		adicionarTabela(this);	
		setVisible(true);
	}
	
	private class OuvintefazerLogin implements MouseListener{
		
		private TelaLeiloes telaLeiloes;
		   public OuvintefazerLogin(TelaLeiloes telaLeiloes) {
		       this.telaLeiloes = telaLeiloes;
		   }
		   
		public void mouseClicked(MouseEvent e) {
			TelaDeLogin telaDeLogin = new TelaDeLogin();
			telaDeLogin.setLocationRelativeTo(telaLeiloes);
			telaLeiloes.dispose();
		}
		
		public void mouseEntered(MouseEvent e) {
			fazerLogin.setText("<html><u>Fazer login</u></html>");
			fazerLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
		}
		
		public void mouseExited(MouseEvent e) {
			fazerLogin.setText("Fazer login");
			fazerLogin.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
			
		}

		public void mousePressed(MouseEvent arg0) {
			
			
		}

		public void mouseReleased(MouseEvent arg0) {
			
		}
		
	}
	
	private void adicionarJLF() {
		 fazerLogin = new JLabel("Fazer login");
		 fazerLogin.setBounds(450, 50, 80, 20);
		 fazerLogin.setFont(new Font("Calibri Light", Font.ITALIC, 15));
		 fazerLogin.setForeground(Color.BLUE);
	     add(fazerLogin);
	     fazerLogin.addMouseListener(new OuvintefazerLogin(this));
	     
	}
	
	private class OuvintebotaoDetalharLeilao implements ActionListener{
		TelaLeiloes telaLeiloes;
		public OuvintebotaoDetalharLeilao(TelaLeiloes telaLeiloes){
			this.telaLeiloes = telaLeiloes;
		}
		public void actionPerformed(ActionEvent e) {
			int linhaSelecionada =tabela.getSelectedRow();
			if(tabela.getSelectedRow()!=-1) {
				Object obj = tabela.getValueAt(tabela.getSelectedRow(),
						0);
				String str=String.valueOf(obj);
				Object objLeilao = tabela.getValueAt(tabela.getSelectedRow(),
						1);
				String strLeilao=String.valueOf(objLeilao);
				Persistencia p = new Persistencia();
				CentralDeInformacoes c = p.recuperarCentral();				
				Usuario autenticado = c.recuperarUsuarioLeilao(str);
				for (Leilao leilao : autenticado.getLeiloes()) {
					if(leilao.getTitulo().equals(strLeilao)) {
						TelaDetalhesDoleilao telaDetalhesDoleilao = new TelaDetalhesDoleilao(leilao, autenticado);
						telaDetalhesDoleilao.setLocationRelativeTo(telaLeiloes);
						telaLeiloes.dispose();
					}
				}					
			}
		}		
	}
	
	private void adicionarBotoes() {
		
		JButton botaodetalhar = new JButton("Detalhar leilão");
		botaodetalhar.setBounds(420, 300, 100, 35);
		botaodetalhar.setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	     add(botaodetalhar);
	     
	     botaodetalhar.addActionListener(new OuvintebotaoDetalharLeilao(this));
	}
	
	private void adicionarTabela(JFrame frame) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			
			modelo = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
				       //all cells false
				       return false;
				    }
			};
			modelo.addColumn("Vendedor");
			modelo.addColumn("Título");
			modelo.addColumn("Encerramento");
			modelo.addColumn("Valor atual");
			modelo.addColumn("Valor da compra");
			
			tabela = new JTable(modelo);
			for (Usuario usuario : central.getUsuariosCadastrados()) {				
			for (Leilao leilao : usuario.getLeiloes()) {				
				Date prazo = leilao.getPrazo();
				if((prazo.getTime()>dataAtual.getTime()) && leilao.isVendido()==false) {									
							Object[] linha = new Object[]{
									leilao.getNomeVendedor(),
									leilao.getTitulo(),
									sdf.format(leilao.getPrazo()),
									leilao.getLance(),
									leilao.getValor()
								};
								modelo.addRow(linha);											
				}				
			}
		}
			try {				
				tabela.addRowSelectionInterval(0, 0);
				
			}catch(Exception erro) {
	
			}
			
			JScrollPane scroll = new JScrollPane(tabela);
			scroll.setBounds(0, 100, 630, 150);
			int policy = scroll.getVerticalScrollBarPolicy(); scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
			scroll.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			frame.add(scroll);
		}		
	
	private void adicionarLeilaoVencidoAosLeiloesGanhos() {
		
		for (Usuario usuario : central.getUsuariosCadastrados()) {				
			for (Leilao leilao : usuario.getLeiloes()) {
				if(!leilao.isAdicionadoNosLeiloesGanhos() && (leilao.getPrazo().getTime()<dataAtual.getTime())  && leilao.getValorDaCompra()!=0) {
					leilao.setAdicionadoNosLeiloesGanhos(true);
					leilao.setVendido(true);
					LeilaoGanho leilaoGanho = new LeilaoGanho(leilao.getTitulo(), leilao.getDataDaCompra(), leilao.getValorDaCompra(), leilao.getEmailVendedor());
					Usuario comprador = central.recuperarOutroUsuario(leilao.getComprador());
					comprador.getLeilaoGanho().add(leilaoGanho);
				    p.salvarCentral(central);
				}				
			}
		}
	}
	
	public static void main(String[] args) {
		new TelaLeiloes();
	}
}
