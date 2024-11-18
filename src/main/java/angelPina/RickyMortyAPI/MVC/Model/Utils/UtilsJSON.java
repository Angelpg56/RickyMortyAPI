package angelPina.RickyMortyAPI.MVC.Model.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;

/**
 * Clase que se encarga de la obtención de datos en formato JSON desde la api
 * @autor Angel Pina Gonzalez
 */
public class UtilsJSON {
	/**
	 * Método que obtiene un JSONObject desde la api
	 * @param url URL de la api
	 * @param resource Recurso de la api a buscar (character, location or episode)
	 * @return JSONObject con los datos obtenidos
	 * @throws IOException
	 */
	public JSONObject getJSONObject(String url, String resource) throws IOException {
		InputStream is = null;
		JSONObject jo = null;
		
		if(resource != null)
			url += resource;
		
		try {
			is = new URL(url).openStream();
	    	BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
	    	StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		    	sb.append((char) cp);
		    }
		    String jsonText =  sb.toString();
	    	
	      	jo = new JSONObject(jsonText);
	      	is.close();
		} catch (IOException e) {
			System.out.println("Error: Recurso no encontrado: " + e.getMessage());
		}
		return jo;
	}
}
