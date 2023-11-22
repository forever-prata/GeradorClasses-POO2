package geradores;

public abstract class Generator {
	protected String json;

	public Generator(String json) {
		this.setJson(json);
	}
	
	public void Gerar() {
		System.out.println("Gerador Genérico");
	}
	
	public void Gerar(String string) {
		System.out.println("Gerador Genérico");
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
