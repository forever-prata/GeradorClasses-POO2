package geradores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PythonGenerator extends Generator{

	protected PythonGenerator(String json) {
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

            String caminhoCompleto = "output" + File.separator + nomeClasse + ".py";
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
		//Visibilidade é descartada no cabecalho
		JSONObject jsonObject = new JSONObject(json);
		StringBuilder builder = new StringBuilder();
		String nomeClasse = jsonObject.getString("nome");
		
		builder.append("class ");
		builder.append(nomeClasse);
		builder.append(":\n\n");
		
        JSONArray atributosArray = jsonObject.getJSONArray("atributos");
        
        //Construtor
        builder.append("\tdef __init__ (self, ");
        
        int totalAtributos = atributosArray.length();
        int contador = 0;
            
        for (Object atributoObj : atributosArray) {
     	   if (atributoObj instanceof JSONObject) {
     		   JSONObject atributo = (JSONObject) atributoObj;
         	   String nome = atributo.getString("nome");
         	   builder.append(nome+"=None");

         	   if (++contador < totalAtributos) {
         		   builder.append(", ");
         	   }
         	}
        }
        builder.append("):\n");
        
        for (Object atributoObj : atributosArray) {
      	   if (atributoObj instanceof JSONObject) {
      		   JSONObject atributo = (JSONObject) atributoObj;
          	   String nome = atributo.getString("nome");
	           String visibilidade = atributo.getString("visibilidade");
	           String visibilidadeTok = "";
	           switch (visibilidade) {
			   case "private": {
				   visibilidadeTok = "__";
				   break;
			   }case "protected":{
				   visibilidadeTok = "_";
				   break;
			   }case "public":{
				   visibilidadeTok = "";
				   break;
			   }
	           }
          	   builder.append("\t\tself."+visibilidadeTok+nome+"="+nome+"\n");
          	}
         }
        
        builder.append("\n");
        //Getters e Setters
        for (Object atributoObj : atributosArray) {
            if (atributoObj instanceof JSONObject) {
                JSONObject atributo = (JSONObject) atributoObj;
                String nome = atributo.getString("nome");
                String visibilidade = atributo.getString("visibilidade");
                String visibilidadeTok = "";
                switch (visibilidade) {
				case "private": {
					visibilidadeTok = "__";
					break;
				}case "protected":{
					visibilidadeTok = "_";
					break;
				}case "public":{
					visibilidadeTok = "";
					break;
				}
                }
                builder.append("\tdef get_"+nome+"(self):\n\t\t return self."+visibilidadeTok+nome+"\n\n");
            }
        }
        
        
        for (Object atributoObj : atributosArray) {
            if (atributoObj instanceof JSONObject) {
                JSONObject atributo = (JSONObject) atributoObj;
                String visibilidade = atributo.getString("visibilidade");
                String visibilidadeTok = "";
                switch (visibilidade) {
				case "private": {
					visibilidadeTok = "__";
					break;
				}case "protected":{
					visibilidadeTok = "_";
					break;
				}case "public":{
					visibilidadeTok = "";
					break;
				}
                }
                String nome = atributo.getString("nome");
                builder.append("\tdef set_"+nome+"(self, "+nome+"):\n\t\tself."+visibilidadeTok+nome+" = "+nome+"\n\n");
            }
        }
        
        //Funcoes
        JSONArray funcoesArray = jsonObject.getJSONArray("funcoes");
        for (Object funcoesObj : funcoesArray) {
            if (funcoesObj instanceof JSONObject) {
                JSONObject funcao = (JSONObject) funcoesObj;
                String nome = funcao.getString("nome");
                String visibilidade = funcao.getString("visibilidade");
                String visibilidadeTok = "";
                switch (visibilidade) {
				case "private": {
					visibilidadeTok = "__";
					break;
				}case "protected":{
					visibilidadeTok = "_";
					break;
				}case "public":{
					visibilidadeTok = "";
					break;
				}
                }
                builder.append("\tdef "+visibilidadeTok+nome+"(self):\n\t\treturn None\n\n");
            }
        }
        
		return builder.toString();
	}
}
