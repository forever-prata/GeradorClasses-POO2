package jsonGen;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonGenerator {

    public static void main(String[] args) {
        Map<String, Object> modeloClasse = new LinkedHashMap<>();
        modeloClasse.put("nome", "Pessoa");
        modeloClasse.put("visibilidade", "public");

        List<Map<String, Object>> atributos = new ArrayList<>();
        adicionarAtributo(atributos, "nome", "String", "private");
        adicionarAtributo(atributos, "idade", "int", "public");
        adicionarAtributo(atributos, "email", "String", "protected");

        modeloClasse.put("atributos", atributos);

        List<Map<String, Object>> funcoes = new ArrayList<>();
        adicionarFuncao(funcoes, "demitir", "boolean", "private");
        adicionarFuncao(funcoes, "explodir", "void", "public");
        adicionarFuncao(funcoes, "matar", "void", "protected");

        modeloClasse.put("funcoes", funcoes);

        String json = convertModeloClasseToJson(modeloClasse);

        String diretorio = "output";

        criarDiretorioSeNaoExistir(diretorio);

        String caminhoArquivo = diretorio + "/meuarquivo.json";

        salvarJsonEmArquivo(json, caminhoArquivo);
    }

    private static String convertModeloClasseToJson(Map<String, Object> modeloClasse) {
        JSONObject jsonClasse = new JSONObject(modeloClasse);

        return jsonClasse.toString(2);
    }

    private static void adicionarAtributo(List<Map<String, Object>> atributos, String nome, String tipo, String visibilidade) {
        Map<String, Object> atributo = new LinkedHashMap<>();
        atributo.put("nome", nome);
        atributo.put("tipo", tipo);
        atributo.put("visibilidade", visibilidade);
        atributos.add(atributo);
    }

    private static void adicionarFuncao(List<Map<String, Object>> funcoes, String nome, String retorno, String visibilidade) {
        Map<String, Object> funcao = new LinkedHashMap<>();
        funcao.put("nome", nome);
        funcao.put("retorno", retorno);
        funcao.put("visibilidade", visibilidade);
        funcoes.add(funcao);
    }

    private static void criarDiretorioSeNaoExistir(String diretorio) {
        Path path = Paths.get(diretorio);

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void salvarJsonEmArquivo(String json, String caminhoArquivo) {
        try (FileWriter fileWriter = new FileWriter(caminhoArquivo)) {
            fileWriter.write(json);
            System.out.println("JSON salvo com sucesso em " + caminhoArquivo);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
