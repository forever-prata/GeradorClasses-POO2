package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import geradores.JavaGenerator;
import jsonRead.LeitorJson;

class JavaGeneratorTests {
	
	  @Test
	  void testGerar() throws IOException {
		  LeitorJson leitor = new LeitorJson();
	      String json = leitor.lerArquivo("output/meuarquivo.json");
	      JavaGenerator generator = new JavaGenerator(json);
	      generator.Gerar();
	      String expectedFilePath = "output/Pessoa.java";
	      assertTrue(new File(expectedFilePath).exists());
	  }
}
