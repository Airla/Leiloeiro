import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * Classe utilizada para criar e recuperar arquivos XML atrav�s da biblioteca XStream.
 */
public class Persistencia {
	
	private XStream xstream = new XStream (new DomDriver());
	
	private File arquivo = new File ("central.xml");
	/**
	 * M�todo salvarCentral,
	 * Voc� deve utiliza-lo para salvar os dados da classe CentralDeInforma��es.
	 *@param central central da Central de Informacoes.
	 */
	public void salvarCentral(CentralDeInformacoes central) {
		
		String xml = xstream.toXML(central);
		
		try {
			if(!arquivo.exists())
				arquivo.createNewFile();
			PrintWriter gravar = new PrintWriter(arquivo);
			gravar.print(xml);
			gravar.close();
			
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * M�todo recuperarCentral,
	 * Voc� deve utiliza-lo para recuperar os dados da Central De Informa��es.
	 * @return CentralDeInformacoes
	 */
	public CentralDeInformacoes recuperarCentral() {
		
		
		try {
			if(arquivo.exists()) {
				FileInputStream fis = new FileInputStream(arquivo);
				return (CentralDeInformacoes) xstream.fromXML(fis);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new CentralDeInformacoes();
			
		}
	}


