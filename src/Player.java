import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable {
	private String nome;
	private String login;
	
	private CRClient lookup;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setConn(CRClient lookup) {
		this.lookup = lookup;
	}

	public CRClient getConn() {
		return lookup;
	}
}