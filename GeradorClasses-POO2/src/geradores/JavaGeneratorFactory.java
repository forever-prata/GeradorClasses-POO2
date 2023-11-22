package geradores;

import java.io.IOException;

import jsonRead.LeitorJson;

public class JavaGeneratorFactory extends GeneratorFactory{
    @Override
    public Generator createGenerator(String caminho) throws IOException {
    	LeitorJson l = new LeitorJson();
    	String json = l.lerArquivo(caminho);
        return new JavaGenerator(json);
    }
}
