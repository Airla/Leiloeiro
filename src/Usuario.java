import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String confimarSenha;
	private char sexo;
	
	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	private ArrayList<Leilao> leiloes = new ArrayList();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfimarSenha() {
		return confimarSenha;
	}

	public void setConfimarSenha(String confimarSenha) {
		this.confimarSenha = confimarSenha;
	}

	public Usuario() {
		
	}
	
	public Usuario(String nome, String sobrenome, String email, String senha, String confirmarSenha, char sexo) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.senha = senha;
        this.confimarSenha = confirmarSenha;
        this.sexo = sexo;
    }
	
	
	//isso aqui e pq ta no main, depois apagar
	public Usuario(String email, String senha) {
		this.email= email;
		this.senha = senha;
	}
	
	//teste
	public Usuario(String email) {
		this.email= email;
	}

	public ArrayList<Leilao> getLeiloes() {
		return leiloes;
	}
	public void setLeiloes(ArrayList<Leilao> leiloes) {
		this.leiloes = leiloes;
	}
	
	public void cadastrarNovoLeilao(Leilao leilao) {
		leiloes.add(leilao);
	}

	private ArrayList<LeilaoGanho> leiloesGanhos = new ArrayList();

	public ArrayList<LeilaoGanho> getLeilaoGanho() {
		return leiloesGanhos;
	}

	public void setLeiloesGanhos(ArrayList<LeilaoGanho> leiloeGanho) {
		this.leiloesGanhos = leiloesGanhos;
	}
	
}
