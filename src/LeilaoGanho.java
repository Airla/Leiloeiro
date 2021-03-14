import java.util.ArrayList;
import java.util.Date;

public class LeilaoGanho {
	
	private String titulo;
	private Date dataDaCompra;
	private float valorDaCompra;
	private String emailDoVendedor;
	private Usuario usuario;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getDataDaCompra() {
		return dataDaCompra;
	}
	public void setDataDaCompra(Date dataDaCompra) {
		this.dataDaCompra = dataDaCompra;
	}
	public float getValorDaCompra() {
		return valorDaCompra;
	}
	public void setValorDaCompra(float valorDaCompra) {
		this.valorDaCompra = valorDaCompra;
	}
	public String getEmailDoVendedor() {
		return emailDoVendedor;
	}
	public void setEmailDoVendedor(String emailDoVendedor) {
		this.emailDoVendedor = emailDoVendedor;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public LeilaoGanho(String titulo, Date dataDaCompra, float valorDaCompra, String emailDoVendedor) {
		this.titulo = titulo;
		this.dataDaCompra = dataDaCompra;
		this.valorDaCompra = valorDaCompra;
		this.emailDoVendedor = emailDoVendedor;
	}

}
