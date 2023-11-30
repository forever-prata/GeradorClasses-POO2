package generators;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import jsonRead.JsonReader;
/**
 * Classe para testar a classe PhpGenerator
 */
class PhpGeneratorTests {
	/**
	 * teste que verifica se um arquivo Pessoa.php e gerado apartir de um json valido
	 */
	  @Test
	  void testGerar() throws IOException {
		  JsonReader leitor = new JsonReader();
	      String json = leitor.readFile("output/example.json");
	      Generator generator = new PhpGenerator(json);
	      generator.Generate();
	      String expectedFilePath = "output/Pessoa.php";
	      assertTrue(new File(expectedFilePath).exists());
	  }
		/**
		 * teste que verifica se um arquivo Pessoa.php e gerado apartir de um json invalido
		 */
	  @Test
	  void testGerarInvalidJsonPath() {
	      String invalidJsonPath = "invalidpath.json";

	      assertThrows(IOException.class, () -> {
	          JsonReader leitor = new JsonReader();
	          String json = leitor.readFile(invalidJsonPath);

	          Generator generator = new PhpGenerator(json);
	          generator.Generate();
	      }, "IOException should be thrown for an invalid JSON file path.");
	  }

}
