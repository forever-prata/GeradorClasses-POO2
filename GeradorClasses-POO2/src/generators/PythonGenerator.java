package generators;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Classe responsavel por gerar arquivos .py
 */
public class PythonGenerator extends Generator{
    /**
     * Construtor da classe PythonGenerator.
     * 
     * @param json String gerada a partir da leitura do JSON de entrada.
     */
	protected PythonGenerator(String json) {
		super(json);
	}
    /**
     * Metodo chamado pelo usuario para gerar uma classe .py
     */
	public void Generate() {
		String s = this.Format();
		Generate(s);
	}
    /**
     * Metodo chamado internamente para gerar uma classe .py
     * 
     * @param clas String formatada apartir do json de entrada
     */
	private void Generate(String clas) {
	    try {
	        JSONObject jsonObject = new JSONObject(json);
	        
	        File dir = new File("output");
	        dir.mkdir();
            
            String className = jsonObject.getString("nome");

            String fullPath = "output" + File.separator + className + ".py";
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
     * @return String completa que representa uma classe .py
     */
	private String Format() {
		//Visibilidade e descartada no cabecalho
		JSONObject jsonObject = new JSONObject(json);
		StringBuilder builder = new StringBuilder();
		String className = jsonObject.getString("nome");
		
		builder.append("class ");
		builder.append(className);
		builder.append(":\n\n");
		
        JSONArray attributesArray = jsonObject.getJSONArray("atributos");
        
        //Construtor
        builder.append("\tdef __init__ (self, ");
        
        int totalAtributes = attributesArray.length();
        int count = 0;
            
        for (Object attributeObj : attributesArray) {
     	   if (attributeObj instanceof JSONObject) {
     		   JSONObject attribute = (JSONObject) attributeObj;
         	   String name = attribute.getString("nome");
         	   builder.append(name+"=None");

         	   if (++count < totalAtributes) {
         		   builder.append(", ");
         	   }
         	}
        }
        builder.append("):\n");
        
        for (Object attributeObj : attributesArray) {
      	   if (attributeObj instanceof JSONObject) {
      		   JSONObject attribute = (JSONObject) attributeObj;
          	   String name = attribute.getString("nome");
	           String visibility = attribute.getString("visibilidade");
	           String visibilityTok = "";
	           switch (visibility) {
			   case "private": {
				   visibilityTok = "__";
				   break;
			   }case "protected":{
				   visibilityTok = "_";
				   break;
			   }case "public":{
				   visibilityTok = "";
				   break;
			   }
	           }
          	   builder.append("\t\tself."+visibilityTok+name+"="+name+"\n");
          	}
         }
        
        builder.append("\n");
        //Getters e Setters
        for (Object attributeObj : attributesArray) {
            if (attributeObj instanceof JSONObject) {
                JSONObject attribute = (JSONObject) attributeObj;
                String name = attribute.getString("nome");
                String visibility = attribute.getString("visibilidade");
                String visibilityTok = "";
                switch (visibility) {
				case "private": {
					visibilityTok = "__";
					break;
				}case "protected":{
					visibilityTok = "_";
					break;
				}case "public":{
					visibilityTok = "";
					break;
				}
                }
                builder.append("\tdef get_"+name+"(self):\n\t\t return self."+visibilityTok+name+"\n\n");
            }
        }
        
        
        for (Object attributeObj : attributesArray) {
            if (attributeObj instanceof JSONObject) {
                JSONObject attribute = (JSONObject) attributeObj;
                String visibility = attribute.getString("visibilidade");
                String visibilityTok = "";
                switch (visibility) {
				case "private": {
					visibilityTok = "__";
					break;
				}case "protected":{
					visibilityTok = "_";
					break;
				}case "public":{
					visibilityTok = "";
					break;
				}
                }
                String name = attribute.getString("nome");
                builder.append("\tdef set_"+name+"(self, "+name+"):\n\t\tself."+visibilityTok+name+" = "+name+"\n\n");
            }
        }
        
        //Funcoes
        JSONArray functionsArray = jsonObject.getJSONArray("funcoes");
        for (Object functionObj : functionsArray) {
            if (functionObj instanceof JSONObject) {
                JSONObject function = (JSONObject) functionObj;
                String name = function.getString("nome");
                String visibilidade = function.getString("visibilidade");
                String visibilityTok = "";
                switch (visibilidade) {
				case "private": {
					visibilityTok = "__";
					break;
				}case "protected":{
					visibilityTok = "_";
					break;
				}case "public":{
					visibilityTok = "";
					break;
				}
                }
                builder.append("\tdef "+visibilityTok+name+"(self):\n\t\treturn None\n\n");
            }
        }
        
		return builder.toString();
	}
}
