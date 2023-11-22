package jsonRead;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LeitorJson {

    public String lerArquivo(String caminhoArquivo) throws IOException {
        return new String(Files.readAllBytes(Paths.get(caminhoArquivo)));
    }

    public static void mostrarDadosDoJson(String json) {
        JSONObject jsonObject = new JSONObject(json);

        String nomeClasse = jsonObject.getString("nome");
        System.out.println("Nome da Classe: " + nomeClasse);

        System.out.println("Atributos:");
        JSONArray atributosArray = jsonObject.getJSONArray("atributos");
        for (Object atributoObj : atributosArray) {
            if (atributoObj instanceof JSONObject) {
                JSONObject atributo = (JSONObject) atributoObj;
                String nome = atributo.getString("nome");
                String tipo = atributo.getString("tipo");
                String visibilidade = atributo.getString("visibilidade");
                System.out.println(" - Nome: " + nome + ", Tipo: " + tipo + ", Visibilidade: " + visibilidade);
            }
        }

        System.out.println("Funções:");
        JSONArray funcoesArray = jsonObject.getJSONArray("funcoes");
        for (Object funcaoObj : funcoesArray) {
            if (funcaoObj instanceof JSONObject) {
                JSONObject funcao = (JSONObject) funcaoObj;
                String nome = funcao.getString("nome");
                String retorno = funcao.getString("retorno");
                String visibilidade = funcao.getString("visibilidade");
                System.out.println(" - Nome: " + nome + ", Retorno: " + retorno + ", Visibilidade: " + visibilidade);
            }
        }
    }
}

