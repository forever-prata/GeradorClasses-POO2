package example;

import java.io.IOException;

import geradores.*;

public class Main {

	public static void main(String[] args) throws IOException {
        String caminho = "output/example.json";
        GeneratorFactory factory = new PythonGeneratorFactory();
        Generator generator = factory.createGenerator(caminho);
        
        generator.Gerar();
        //
	}
}
