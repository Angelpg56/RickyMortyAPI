package angelPina.RickyMortyAPI.MVC.Model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * Clase de Personaje
 * @autor Angel Pina Gonzalez
 * 
 * @param id Identificador del personaje
 * @param name Nombre del personaje
 * @param status Estado del personaje
 * @param species Especie del personaje
 * @param type Tipo del personaje
 * @param gender Genero del personaje
 * @param origin Origen del personaje
 * @param location Localización del personaje
 * @param image Imagen del personaje
 * @param episode Episodios en los que aparece el personaje
 * @param url URL del personaje
 * @param created Fecha de creación del personaje
 */
public class Personaje implements Serializable {
	private static final long serialVersionUID = 1L;
	int id;
	String name;
	String status;
	String species;
	String type;
	String gender;
	Map<String, String> origin;
	Map<String, String> location;
	String image;
	String[] episode;
	String url;
	String created;
	
	/**
     * Constructor de la clase Personaje
     * 
	 * @param id Identificador del personaje
	 * @param name Nombre del personaje
	 * @param status Estado del personaje
	 * @param species Especie del personaje
	 * @param type Tipo del personaje
	 * @param gender Genero del personaje
	 * @param origin Origen del personaje
	 * @param location Localización del personaje
	 * @param image Imagen del personaje
	 * @param episode Episodios en los que aparece el personaje
	 * @param url URL del personaje
	 * @param created Fecha de creación del personaje
     */
	public Personaje(int id, String name, String status, String species, String type, String gender, JSONObject origin, JSONObject location, String image, String[] episodes, String url, String created) {
	    this.id = id;
	    this.name = name;
	    this.status = status;
	    this.species = species;
	    this.type = type;
	    this.gender = gender;
		if (origin != null) {
		    this.origin = jsonToMap(origin);
		}
		if (location != null) {
		    this.location = jsonToMap(location);
		}
	    this.image = image;
	    this.episode = episodes;
	    this.url = url;
	    this.created = created;
	}

	/**
	 * Método que devuelve el identificador
	 * @return int con el identificador
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método que sobre escribe el método toString
	 * 
	 * @return String con el contenido del personaje
	 */
	@Override
	public String toString() {
		return "id " + this.id + 
				"=\n\tname= " + this.name +
				",\n\tstatus=" + this.status+ 
				",\n\tspecies=" + this.species + 
				",\n\ttype=" + this.type + 
				",\n\tgender=" + this.gender + 
				",\n\torigin=" + mapToString(this.origin) + 
				",\n\tlocation=" + mapToString(this.location) + 
				",\n\timage=" + this.image + 
				",\n\tepisode=" + Arrays.toString(this.episode) + 
				",\n\turl=" + this.url +
				",\n\tcreated=" + this.created;
	}
	
	/**
	 * Método que convierte un JSONObject en un Map
	 * 
	 * @param jO JSONObject a convertir
	 * @return Map con los datos del JSONObject
	 */
	public static Map<String, String> jsonToMap(JSONObject jO) {
        Map<String, String> map = new HashMap<>();
        jO.keys().forEachRemaining(key -> map.put(key, jO.get(key).toString()));
        return map;
    }
	
	/**
	 * Método que convierte un Map en un String
	 * 
	 * @param map Map a convert
	 * @return String con el contenido del Map
	 */
	private String mapToString(Map<String, String> map) {
		if (map == null || map.isEmpty())
			return "{}";
		StringBuilder sb = new StringBuilder();
		map.forEach((k, v) -> sb.append("\n\t\t" + k + ": " + v + ","));
		return sb.toString();
	}
}
