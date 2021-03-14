import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MinhaData {
	
	public  boolean validaHora(String hora){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		sdf.setLenient(false);
		try{
			sdf.parse(hora);
		}catch(ParseException e){
			return false;
		}
		return true;
	}

}
