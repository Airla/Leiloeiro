import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class OuvinteTelaCadastrarLeilao implements ActionListener{
	private TelaCadastrarLeilao telaCadastrarLeilao;
	
	public OuvinteTelaCadastrarLeilao(TelaCadastrarLeilao telaCadastrarLeilao) {
		this.telaCadastrarLeilao = telaCadastrarLeilao;
	}
	
	public void actionPerformed(ActionEvent e) {
		Persistencia p = new Persistencia();
		CentralDeInformacoes central = p.recuperarCentral();
		
		String botao = e.getActionCommand();
		
		if(botao.equals("Salvar")) {
			String titulo = telaCadastrarLeilao.getTitulo().getText();
			String prazo= telaCadastrarLeilao.getPrazo().getText();
			float valor = 0;
			String descricao = telaCadastrarLeilao.getAreaDeTexto().getText();
			boolean valorFloat = false;
			
			//validando o valor
				try {
					valor = Float.parseFloat(telaCadastrarLeilao.getValor().getText());					
					valorFloat=true;
				}catch(NumberFormatException erro) {
					valorFloat=false;
				}
			
			//validando o prazo			
			MinhaData minhaData = new MinhaData();
			boolean prazoValido= minhaData.validaHora(prazo);
			Date dataAtual = Calendar.getInstance().getTime();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			Date prazoDate=null;
			try {
				prazoDate = sdf.parse(prazo);
			} catch (ParseException erro) {
			}
			
				if(titulo.equals("") || prazo.equals("")|| descricao.equals("") || telaCadastrarLeilao.getValor().getText().equals("")) {
					JOptionPane.showMessageDialog(telaCadastrarLeilao, "Preencha todas as informações!", "Erro!", JOptionPane.ERROR_MESSAGE);
				}else if(!prazoValido || prazoDate.before(dataAtual)) {
					JOptionPane.showMessageDialog(telaCadastrarLeilao, "Preencha uma data válida!", "Erro!", JOptionPane.ERROR_MESSAGE);
				}else if(!valorFloat) {
					JOptionPane.showMessageDialog(telaCadastrarLeilao, "Preencha o campo de valor com números!", "Erro!", JOptionPane.ERROR_MESSAGE);
				}else {
					Usuario autenticado = central.recuperarUsuario(telaCadastrarLeilao.getUsuario().getEmail(), telaCadastrarLeilao.getUsuario().getSenha());
										
					System.out.println();
					Leilao leilao = new Leilao (titulo, prazoDate, valor, descricao);
					leilao.setNomeVendedor(autenticado.getNome());
					leilao.setEmailVendedor(autenticado.getEmail());
					autenticado.getLeiloes().add(leilao);
					p.salvarCentral(central);
					JOptionPane.showMessageDialog(telaCadastrarLeilao, "Leilão cadastrado!", "Informação", JOptionPane.INFORMATION_MESSAGE);				
					TelaMinhaConta telaMinhaConta = new TelaMinhaConta(autenticado);
					telaMinhaConta.setLocationRelativeTo(telaCadastrarLeilao);
					telaCadastrarLeilao.dispose();
				}
								
		}else if(botao.equals("Cancelar")) {
			TelaMinhaConta minhaConta = new TelaMinhaConta(telaCadastrarLeilao.getUsuario());
			minhaConta.setLocationRelativeTo(telaCadastrarLeilao);
			telaCadastrarLeilao.dispose();
		}
		
	}

}
