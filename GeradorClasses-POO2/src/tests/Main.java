package tests;

import java.io.IOException;

import geradores.Generator;
import geradores.JavaGeneratorFactory;
import geradores.GeneratorFactory;

public class Main {

	public static void main(String[] args) throws IOException {
        String caminho = "output/meuarquivo.json";
        GeneratorFactory factory = new JavaGeneratorFactory();
        Generator generator = factory.createGenerator(caminho);
        
        generator.Gerar();
	}
}
