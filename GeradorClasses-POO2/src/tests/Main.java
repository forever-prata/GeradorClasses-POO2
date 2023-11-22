package tests;

import java.io.IOException;

import geradores.Generator;
import geradores.JavaGenerator;
import jsonRead.LeitorJson;

public class Main {

	public static void main(String[] args) throws IOException {
		LeitorJson l = new LeitorJson();
		Generator g = new JavaGenerator(l.lerArquivo("output/meuarquivo.json"));
		
		g.Gerar();

	}

}
