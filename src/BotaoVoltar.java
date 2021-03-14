import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class BotaoVoltar extends JButton{
	
	public BotaoVoltar() {
		super("Voltar");
		setForeground(Color.BLACK);
	    setBounds(350, 300, 100, 35);
	    setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	}

}
