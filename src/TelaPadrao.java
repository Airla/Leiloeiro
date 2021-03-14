import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TelaPadrao extends JFrame{
	//* Tela padrao
	 public TelaPadrao() {
	        setSize(640, 420);
	        setDefaultCloseOperation(EXIT_ON_CLOSE);
	        setResizable(false);
	        setLocationRelativeTo(null);
	        setLayout(null);
	        ImageIcon imagemTituloJanela = new ImageIcon("icone.png");
	        setIconImage(imagemTituloJanela.getImage());
	        JLabel texto = new JLabel("Leiloeiro");
		    texto.setBounds(250, 15, 150, 30);
		    texto.setFont(new Font("Segoe Print", Font.BOLD, 25));
		    texto.setForeground(Color.BLACK);
		    add(texto);
	    }
}
