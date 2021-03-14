import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class JavaMail {
	
	public void enviarEmail( String senha, String remetente, String destinario, String msg, String assunto){
		Properties props = new Properties();
        /** Parâmetros de conexão com servidor Gmail */
        
        props.put("mail.smtp.user", remetente); 
        props.put("mail.smtp.host", "smtp.gmail.com"); 
        props.put("mail.smtp.port", "25"); 
        props.put("mail.debug", "true"); 
        props.put("mail.smtp.auth", "true"); 
        props.put("mail.smtp.starttls.enable","true"); 
        props.put("mail.smtp.EnableSSL.enable","true");

        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");   
        props.setProperty("mail.smtp.socketFactory.fallback", "false");   
        props.setProperty("mail.smtp.port", "465");   
        props.setProperty("mail.smtp.socketFactory.port", "465");

        Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                         protected PasswordAuthentication getPasswordAuthentication() 
                         {
                               return new PasswordAuthentication(remetente, senha);
                         }
                    });

        /** Ativa Debug para sessão */
        session.setDebug(true);

        try {

              Message message = new MimeMessage(session);
              message.setFrom(new InternetAddress(remetente)); //Remetente

              Address[] toUser = InternetAddress //Destinatário(s)
                         .parse(destinario);  

              message.setRecipients(Message.RecipientType.TO, toUser);
              message.setSubject(assunto);//Assunto
              message.setText(msg);
              /**Método para enviar a mensagem criada*/
              Transport.send(message);

//              System.out.println("Feito!!!");

         } catch (MessagingException e) {
        	 JOptionPane.showMessageDialog(null, "Ocorreu um erro inesperado!", null, JOptionPane.ERROR_MESSAGE);
 			
        }
	}
	
	public static void main(String[] args) {
		
		new JavaMail().enviarEmail("onedirection123", "airlavcds@gmail.com", "airlavcds@gmail.com", "oi", "Java mail");
	}
	

}
