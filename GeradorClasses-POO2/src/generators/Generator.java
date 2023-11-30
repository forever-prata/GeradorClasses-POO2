package generators;
/**
 * Classe base de todos os geradores.
 */
public abstract class Generator {

    /**
     * Json gerado a partir da leitura do JSON de entrada.
     */
	protected String json;

    /**
     * Construtor da classe Generator.
     * 
     * @param json String gerada a partir da leitura do JSON de entrada.
     */
	public Generator(String json) {
		this.setJson(json);
	}
	
    /**
     * Metodo chamado pelo usuario
     */
	public void Generate() {
		Generate(Format());
	}
	
    /**
     * Metodo chamado internamente com json formatado
     * 
     * @param string String formatada apartir do json de entrada
     */
	private void Generate(String string) {
		System.out.println("Gerador Generico");
	}
	
    /**
     * Metodo utilizado para formatar o conteudo do json, retornando uma grande String que sera escrita em um arquivo gerado
     * 
     * @return String completa que representa uma classe
     */
	private String Format() {
		return null;
	}

    /**
     * Obtem a string JSON.
     * 
     * @return A string JSON.
     */
	public String getJson() {
		return json;
	}

    /**
     * Define a string JSON.
     * 
     * @param json A string JSON a ser definida.
     */
	public void setJson(String json) {
		this.json = json;
	}
}
