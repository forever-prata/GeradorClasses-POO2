package generators;

import java.io.IOException;

import jsonRead.JsonReader;

public class PhpGeneratorFactory extends GeneratorFactory{

	@Override
    public Generator createGenerator(String caminhoArquivo) throws IOException {
    	JsonReader l = new JsonReader();
    	String json = l.readFile(caminhoArquivo);
        return new PhpGenerator(json);
    }

}
