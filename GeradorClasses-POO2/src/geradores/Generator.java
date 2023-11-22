package geradores;

public abstract class Generator {
	protected String json;

	public Generator(String json) {
		this.setJson(json);
	}
	
	public void Gerar() {
		System.out.println("Gerador Gen�rico");
	}
	
	public void Gerar(String string) {
		System.out.println("Gerador Gen�rico");
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
