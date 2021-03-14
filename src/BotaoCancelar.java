import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

public class BotaoCancelar extends JButton{
	
	public BotaoCancelar() {
		super("Cancelar");
		setForeground(Color.BLACK);
	    setBounds(350, 300, 100, 35);
	    setFont(new Font("Arial Narrow", Font.PLAIN, 12));
	    
	}
}
