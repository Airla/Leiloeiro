import java.util.ArrayList;

public class CentralDeInformacoes {
	
	private ArrayList<Usuario> usuariosCadastrados = new ArrayList();
	
	public void adicionarUsuario(Usuario usuario) {
		usuariosCadastrados.add(usuario);
	}
	
	public ArrayList<Usuario> getUsuariosCadastrados() {
		return usuariosCadastrados;
	}
	
	public void setUsuariosCadastrados(ArrayList<Usuario> usuariosCadastrados) {
		this.usuariosCadastrados = usuariosCadastrados;
	}
	
	public Usuario recuperarUsuario(String email, String senha) {
		Usuario usuarioRecuperado = null;
		for (Usuario u : usuariosCadastrados) {
			if(u.getEmail().equals(email) && u.getSenha().equals(senha)) {
				usuarioRecuperado = u;
			}
		}
		
		return usuarioRecuperado;
	}
	
	public Usuario recuperarOutroUsuario(String email) {
		Usuario usuarioRecuperado = null;
		for (Usuario u : usuariosCadastrados) {
			if(u.getEmail().equals(email)) {
				usuarioRecuperado = u;
			}
		}
		
		return usuarioRecuperado;
	}
	
	
	public Usuario recuperarUsuarioLeilao(String nome) {
		Usuario usuarioRecuperado = null;
		for (Usuario u : usuariosCadastrados) {
			if(u.getNome().equals(nome)) {
				usuarioRecuperado = u;
			}
		}
		
		return usuarioRecuperado;
	}
	
	public boolean verificarSeJaUsuarioExiste(String email) {
		for (Usuario u : usuariosCadastrados) {
			if(u.getEmail().equals(email)) {
				return true;
			}
		}
		return false;
	}
}
