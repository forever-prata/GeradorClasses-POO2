package geradores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PhpGenerator extends Generator{

	protected PhpGenerator(String json) {
		super(json);
	}
	
	public void Gerar() {
		String s = this.formatar();
		Gerar(s);
	}
	
	private void Gerar(String clas) {
	    try {
	        JSONObject jsonObject = new JSONObject(json);
	        
	        File pasta = new File("output");
            pasta.mkdir();
            
            String nomeClasse = jsonObject.getString("nome");

            String caminhoCompleto = "output" + File.separator + nomeClasse + ".php";
            BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto));
            writer.write(clas);
            writer.close();
            System.out.println("Arquivo gerado com sucesso: " + caminhoCompleto);
	    } catch (JSONException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private String formatar() {
		JSONObject jsonObject = new JSONObject(json);
		StringBuilder builder = new StringBuilder();
		String visibilidadeClasse = jsonObject.getString("visibilidade");
		String nomeClasse = jsonObject.getString("nome");
		
		builder.append("<?php\n\n");
		builder.append("class "+nomeClasse+" {\n");
		
		//Declaracao de atributos
        JSONArray atributosArray = jsonObject.getJSONArray("atributos");
        for (Object atributoObj : atributosArray) {
            if (atributoObj instanceof JSONObject) {
                JSONObject atributo = (JSONObject) atributoObj;
                String nome = atributo.getString("nome");
                String visibilidade = atributo.getString("visibilidade");
                builder.append("\t"+visibilidade+" "+" $"+nome+";\n");
            }
        }
        
       builder.append("\n");
       
       //Getters e Setters
       for (Object atributoObj : atributosArray) {
           if (atributoObj instanceof JSONObject) {
               JSONObject atributo = (JSONObject) atributoObj;
               String nome = atributo.getString("nome");
               String nomeMaiusculo = nome.substring(0, 1).toUpperCase();
               String restoNome = nome.substring(1);
               String nomeFormatado = nomeMaiusculo + restoNome;
               builder.append("\tpublic function"+" get"+nomeFormatado+"(){\n\t\t return $this->"+nome+";\n\t}\n\n");
           }
       }
       
       for (Object atributoObj : atributosArray) {
           if (atributoObj instanceof JSONObject) {
               JSONObject atributo = (JSONObject) atributoObj;
               String nome = atributo.getString("nome");
               String nomeMaiusculo = nome.substring(0, 1).toUpperCase();
               String restoNome = nome.substring(1);
               String nomeFormatado = nomeMaiusculo + restoNome;
               builder.append("\tpublic function set"+nomeFormatado+"($"+nome+"){\n\t\t $this->"+nome+"= $"+nome+";\n\t}\n\n");
           }
       }
       
       //metodo construtor
       builder.append("\t"+visibilidadeClasse+" function __construct(");
           
       int totalAtributos = atributosArray.length();
       int contador = 0;
           
       for (Object atributoObj : atributosArray) {
    	   if (atributoObj instanceof JSONObject) {
    		   JSONObject atributo = (JSONObject) atributoObj;
        	   String nome = atributo.getString("nome");
        	   builder.append("$" + nome);

        	   if (++contador < totalAtributos) {
        		   builder.append(", ");
        	   }
        	}
       }
       builder.append("){");
           
       for (Object atributoObj : atributosArray) {
    	   if (atributoObj instanceof JSONObject) {
    		   JSONObject atributo = (JSONObject) atributoObj;
       	       String nome = atributo.getString("nome");
       	       builder.append("\n\t\t$this->"+nome+"= $"+nome+";");
       	   }
       }
       builder.append("\n\t}\n");
       
       builder.append("\n");
       
       //funcoes mudar
       JSONArray funcoesArray = jsonObject.getJSONArray("funcoes");
       for (Object funcoesObj : funcoesArray) {
           if (funcoesObj instanceof JSONObject) {
               JSONObject funcao = (JSONObject) funcoesObj;
               String nome = funcao.getString("nome");
               String visibilidade = funcao.getString("visibilidade");
               builder.append("\t"+visibilidade+" function "+nome+"(){\n\t\treturn null;\n\t}\n\n");
           }
       }
		builder.append("}\n?>");
		return builder.toString();
	}
}
