package geradores;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JavaGenerator extends Generator{

	protected JavaGenerator(String json) {
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

            String caminhoCompleto = "output" + File.separator + nomeClasse + ".java";
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
		
		builder.append(visibilidadeClasse);
		builder.append(" class ");
		builder.append(nomeClasse);
		builder.append(" {\n\n");
		
		//Declaracao de atributos
        JSONArray atributosArray = jsonObject.getJSONArray("atributos");
        for (Object atributoObj : atributosArray) {
            if (atributoObj instanceof JSONObject) {
                JSONObject atributo = (JSONObject) atributoObj;
                String nome = atributo.getString("nome");
                String tipo = atributo.getString("tipo");
                String visibilidade = atributo.getString("visibilidade");
                builder.append("\t"+visibilidade+" "+tipo+" "+nome+";\n");
            }
        }
        
       builder.append("\n");
       
       //Getters e Setters
       for (Object atributoObj : atributosArray) {
           if (atributoObj instanceof JSONObject) {
               JSONObject atributo = (JSONObject) atributoObj;
               String nome = atributo.getString("nome");
               String tipo = atributo.getString("tipo");
               String nomeMaiusculo = nome.substring(0, 1).toUpperCase();
               String restoNome = nome.substring(1);
               String nomeFormatado = nomeMaiusculo + restoNome;
               builder.append("\t public "+tipo+" get"+nomeFormatado+"(){\n\t\t return this."+nome+";\n\t}\n\n");
           }
       }
       
       for (Object atributoObj : atributosArray) {
           if (atributoObj instanceof JSONObject) {
               JSONObject atributo = (JSONObject) atributoObj;
               String nome = atributo.getString("nome");
               String tipo = atributo.getString("tipo");
               String nomeMaiusculo = nome.substring(0, 1).toUpperCase();
               String restoNome = nome.substring(1);
               String nomeFormatado = nomeMaiusculo + restoNome;
               builder.append("\t public void set"+nomeFormatado+"("+tipo+" "+nome+"){\n\t\t this."+nome+"= "+nome+";\n\t}\n\n");
           }
       }
       
       //metodo construtor
       builder.append("\t"+visibilidadeClasse+" "+nomeClasse+"(");
           
       int totalAtributos = atributosArray.length();
       int contador = 0;
           
       for (Object atributoObj : atributosArray) {
    	   if (atributoObj instanceof JSONObject) {
    		   JSONObject atributo = (JSONObject) atributoObj;
        	   String nome = atributo.getString("nome");
        	   String tipo = atributo.getString("tipo");
        	   builder.append(tipo + " " + nome);

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
       	       builder.append("\n\t\tthis."+nome+"= "+nome+";");
       	   }
       }
       builder.append("\n\t}\n");
       
       builder.append("\n");
       
       //funcoes
       JSONArray funcoesArray = jsonObject.getJSONArray("funcoes");
       for (Object funcoesObj : funcoesArray) {
           if (funcoesObj instanceof JSONObject) {
               JSONObject funcao = (JSONObject) funcoesObj;
               String nome = funcao.getString("nome");
               String retorno = funcao.getString("retorno");
               String visibilidade = funcao.getString("visibilidade");
               builder.append("\t"+visibilidade+" "+retorno+" "+nome+"(){\n\t\treturn null;\n\t}\n\n");
           }
       }
		builder.append("}");
		return builder.toString();
	}

}
