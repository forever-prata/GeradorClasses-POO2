public class Pessoa {

	private String nome;
	public int idade;
	protected String email;

	public String getNome(){
		 return this.nome;
	}

	public int getIdade(){
		 return this.idade;
	}

	public String getEmail(){
		 return this.email;
	}

	public void setNome(String nome){
		 this.nome= nome;
	}

	public void setIdade(int idade){
		 this.idade= idade;
	}

	public void setEmail(String email){
		 this.email= email;
	}

	public Pessoa(String nome, int idade, String email){
		this.nome= nome;
		this.idade= idade;
		this.email= email;
	}

	private boolean demitir(){
		return null;
	}

	public void explodir(){
		return null;
	}

	protected void matar(){
		return null;
	}

}