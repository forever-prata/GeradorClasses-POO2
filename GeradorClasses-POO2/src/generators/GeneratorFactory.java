package generators;

import java.io.IOException;
/**
 * Classe base de todas as Factory(s).
 */
public abstract class GeneratorFactory {
	/**
	 * Metodo para criar um Generator
	 * 
	 * @param caminhoArquivo String que contem o caminho do arquivo json de entrada
	 * 
	 * @return Generator generico
	 */
	public abstract Generator createGenerator(String caminhoArquivo) throws IOException;
}
