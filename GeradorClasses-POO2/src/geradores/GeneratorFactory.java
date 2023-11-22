package geradores;

import java.io.IOException;

public abstract class GeneratorFactory {
	public abstract Generator createGenerator(String json) throws IOException;
}
