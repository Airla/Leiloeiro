import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Leilao {

	private String descricao;
	private float valor;
	private Date prazo;
	private String titulo;
	private float lance=0;
	private String emailVendedor;
	private String nomeVendedor;
	private String nomeComprador;
	private String comprador;
	private boolean vendido=false;
	private Date dataDaCompra;
	private float valorDaCompra=0;
	private boolean adicionadoNosLeiloesGanhos=false;
	private ArrayList<Float> historicoLancesBemSucedidos = new ArrayList<Float>();
	
	public String getNomeVendedor() {
		return nomeVendedor;
	}
	public void setNomeVendedor(String nomeVendedor) {
		this.nomeVendedor = nomeVendedor;
	}
	public String getEmailVendedor() {
		return emailVendedor;
	}
	public void setEmailVendedor(String emailVendedor) {
		this.emailVendedor = emailVendedor;
	}
	public boolean isAdicionadoNosLeiloesGanhos() {
		return adicionadoNosLeiloesGanhos;
	}
	public void setAdicionadoNosLeiloesGanhos(boolean adicionadoNosLeiloesGanhos) {
		this.adicionadoNosLeiloesGanhos = adicionadoNosLeiloesGanhos;
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
	public boolean isVendido() {
		return vendido;
	}
	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	
	public float getLance() {
		return lance;
	}
	public void setLance(float lance) {
		this.lance = lance;
	}
	public Date getPrazo() {
		return prazo;
	}
	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public Leilao(String titulo, Date prazo, float valor, String descricao) {
		this.titulo = titulo;
		this.prazo = prazo;
		this.valor = valor;
		this.descricao = descricao;
		
	}
	
	public Leilao( String descricao, float valor) {
		this.descricao = descricao;
		this.valor = valor;				
	}
	
	private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	private ArrayList<Leilao> leiloes = new ArrayList<Leilao>();
	
	public ArrayList<Leilao> getLeiloes() {
		return leiloes;
	}
	public void setLeiloes(ArrayList<Leilao> leiloes) {
		this.leiloes = leiloes;
	}	
	
	public void cadastrarNovoLeilao(Leilao leilao) {
		leiloes.add(leilao);
	}

	public ArrayList<Float> getLanceBemSucedido() {
		return historicoLancesBemSucedidos;
	}
	public void setLanceBemSucedido(ArrayList<Float> lanceBemSucedido) {
		this.historicoLancesBemSucedidos = lanceBemSucedido;
	}
	public String getNomeComprador() {
		return nomeComprador;
	}
	public void setNomeComprador(String nomeComprador) {
		this.nomeComprador = nomeComprador;
	}

}
