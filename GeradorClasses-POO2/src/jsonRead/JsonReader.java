package jsonRead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Classe responsavel por ler arquivos .json
 */
public class JsonReader {
    /**
     * Metodo utilizado para salvar os dados do Json em uma String
     * 
     * @param caminhoArquivo String que contem o caminho do arquivo json
     */
    public String readFile(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }
}

