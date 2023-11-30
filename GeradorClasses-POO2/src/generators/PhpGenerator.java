package generators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Classe responsavel por gerar arquivos .php
 */
public class PhpGenerator extends Generator{
    /**
     * Construtor da classe PhpGenerator.
     * 
     * @param json String gerada a partir da leitura do JSON de entrada.
     */
	protected PhpGenerator(String json) {
		super(json);
	}
    /**
     * Metodo chamado pelo usuario para gerar uma classe .php
     */
	public void Generate() {
		String s = this.Format();
		Generate(s);
	}
    /**
     * Metodo chamado internamente para gerar uma classe .php
     * 
     * @param clas String formatada apartir do json de entrada
     */
	private void Generate(String clas) {
	    try {
	        JSONObject jsonObject = new JSONObject(json);
	        
	        File dir = new File("output");
            dir.mkdir();
            
            String className = jsonObject.getString("nome");

            String fullPath = "output" + File.separator + className + ".php";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fullPath));
            writer.write(clas);
            writer.close();
            System.out.println("Arquivo gerado com sucesso: " + fullPath);
	    } catch (JSONException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**
     * Metodo chamado internamente para formatar o json de entrada
     * 
     * @return String completa que representa uma classe .php
     */
	private String Format() {
		JSONObject jsonObject = new JSONObject(json);
		StringBuilder builder = new StringBuilder();
		String classVisibility = jsonObject.getString("visibilidade");
		String className = jsonObject.getString("nome");
		
		builder.append("<?php\n\n");
		builder.append("class "+className+" {\n");
		
		//Declaracao de atributos
        JSONArray attributesArray = jsonObject.getJSONArray("atributos");
        for (Object attributeObj : attributesArray) {
            if (attributeObj instanceof JSONObject) {
                JSONObject attribute = (JSONObject) attributeObj;
                String name = attribute.getString("nome");
                String visibility = attribute.getString("visibilidade");
                builder.append("\t"+visibility+" "+" $"+name+";\n");
            }
        }
        
       builder.append("\n");
       
       //Getters e Setters
       for (Object attributeObj : attributesArray) {
           if (attributeObj instanceof JSONObject) {
               JSONObject attribute = (JSONObject) attributeObj;
               String name = attribute.getString("nome");
               String nameUpper = name.substring(0, 1).toUpperCase();
               String restName = name.substring(1);
               String formattedName = nameUpper + restName;
               builder.append("\tpublic function"+" get"+formattedName+"(){\n\t\t return $this->"+name+";\n\t}\n\n");
           }
       }
       
       for (Object attributeObj : attributesArray) {
           if (attributeObj instanceof JSONObject) {
               JSONObject attribute = (JSONObject) attributeObj;
               String name = attribute.getString("nome");
               String nameUpper = name.substring(0, 1).toUpperCase();
               String restName = name.substring(1);
               String formattedName = nameUpper + restName;
               builder.append("\tpublic function set"+formattedName+"($"+name+"){\n\t\t $this->"+name+"= $"+name+";\n\t}\n\n");
           }
       }
       
       //metodo construtor
       builder.append("\t"+classVisibility+" function __construct(");
           
       int totalAtributes = attributesArray.length();
       int count = 0;
           
       for (Object attributeObj : attributesArray) {
    	   if (attributeObj instanceof JSONObject) {
    		   JSONObject attribute = (JSONObject) attributeObj;
        	   String name = attribute.getString("nome");
        	   builder.append("$" + name);

        	   if (++count < totalAtributes) {
        		   builder.append(", ");
        	   }
        	}
       }
       builder.append("){");
           
       for (Object attributeObj : attributesArray) {
    	   if (attributeObj instanceof JSONObject) {
    		   JSONObject attribute = (JSONObject) attributeObj;
       	       String name = attribute.getString("nome");
       	       builder.append("\n\t\t$this->"+name+"= $"+name+";");
       	   }
       }
       builder.append("\n\t}\n");
       
       builder.append("\n");
       
       //funcoes mudar
       JSONArray functionsArray = jsonObject.getJSONArray("funcoes");
       for (Object functionObj : functionsArray) {
           if (functionObj instanceof JSONObject) {
               JSONObject function = (JSONObject) functionObj;
               String name = function.getString("nome");
               String visibility = function.getString("visibilidade");
               builder.append("\t"+visibility+" function "+name+"(){\n\t\treturn null;\n\t}\n\n");
           }
       }
		builder.append("}\n?>");
		return builder.toString();
	}
}
