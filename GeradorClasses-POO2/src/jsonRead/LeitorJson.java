package jsonRead;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeitorJson {

    public String lerArquivo(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }
}

