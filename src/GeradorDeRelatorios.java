import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {
	
	public void gerarRelatoriosLeiloesCadastradosPorMim(Usuario u, String nomepdf) {
		
		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream(nomepdf));
			doc.open();

			 Persistencia p = new Persistencia();
			 CentralDeInformacoes central = p.recuperarCentral();
			Usuario usuario = central.recuperarOutroUsuario(u.getEmail());
			Font font = new Font(FontFamily.COURIER, 20, Font.BOLD);
			
			for (Leilao l : usuario.getLeiloes()) {				 				
			String	paragrafo = "\nTitulo do leilão: "+l.getTitulo()+
						"\nDescricão: "+l.getDescricao()+
						"\nValor do leilão: "+l.getValor()+
						"\nData de encerramento: "+stf.format(l.getPrazo())+"\n";
				Paragraph pa = new Paragraph(paragrafo);
				doc.add(pa);				
			}			
			
			doc.close();
			
		} catch (FileNotFoundException | DocumentException e) {
		}		
	}
	
	public void gerarRelatoriosLeiloesQueGanhei(Usuario u, String nomepdf) {
		
		Document doc = new Document(PageSize.A4, 72, 72, 72, 72);
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			
			PdfWriter.getInstance(doc, new FileOutputStream(nomepdf));
			doc.open();

			Persistencia p = new Persistencia();
			CentralDeInformacoes central = p.recuperarCentral();
			Usuario usuario = central.recuperarOutroUsuario(u.getEmail());
			Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
			
			for (LeilaoGanho l : usuario.getLeilaoGanho()) {				 				
			String	paragrafo = "\nTitulo do leilão: "+l.getTitulo()+
						"\nData da compra: "+stf.format(l.getDataDaCompra())+
						"\nalor da compra: "+l.getValorDaCompra()+
						"\nEmail do vendedor: "+l.getEmailDoVendedor()+"\n";
				Paragraph pa = new Paragraph(paragrafo);
				doc.add(pa);				
			}			
			
			doc.close();
			
		} catch (FileNotFoundException | DocumentException e) {
		}		
	}
}
