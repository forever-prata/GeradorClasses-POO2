package generators;

public abstract class Generator {
	protected String json;

	public Generator(String json) {
		this.setJson(json);
	}
	
	public void Generate() {
		Generate("");
	}
	
	private void Generate(String string) {
		System.out.println("Gerador Genérico");
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
}
