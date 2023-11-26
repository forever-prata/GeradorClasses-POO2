package example;

import java.io.IOException;

import generators.*;

public class Main {

	public static void main(String[] args) throws IOException {
        String path = "output/example.json";
        GeneratorFactory factory = new PythonGeneratorFactory();
        Generator generator = factory.createGenerator(path);
        
        generator.Generate();
	}
}
