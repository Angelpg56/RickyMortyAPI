package angelPina.RickyMortyAPI.MVC;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.json.JSONObject;
import org.w3c.dom.NodeList;

import angelPina.RickyMortyAPI.MVC.Model.Personaje;
import angelPina.RickyMortyAPI.MVC.Model.Utils.*;

/**
 * Clase que se encarga de la interacción del programa
 * @autor Angel Pina Gonzalez
 * 
 * @param url Ruta de la API
 * @param utilsJSON Clase UtilsJSON
 * @param utilsXML Clase UtilsXML
 * @param utilsIO Clase UtilsIO
 */
public class Controller {
	static String url;
	static UtilsJSON utilsJSON;
	static UtilsXML utilsXML;
	static UtilsIO utilsIO;

	/**
	 * Constructor que guarda las rutas a usar e inicializa las clases Utils
	 * 
	 * @param url Ruta de la API
	 * @param utilsJSON Clase UtilsJSON
	 * @param utilsXML Clase UtilsXML
	 * @param utilsIO Clase UtilsIO
	 */
	public Controller() {
		this.url = "https://rickandmortyapi.com/api/";

		utilsJSON = new UtilsJSON();
		utilsXML = new UtilsXML();
		utilsIO = new UtilsIO();
	}
	
	/**
	 * Método que ejecuta el programa
	 */
	public static void programaRyM() {
		String opcion;

		do {
			// Muestra  el menú y espera la eleccion del usuario
			opcion =  View.mostrarMenu();

			try {
				switch (opcion) {
				
					// Obtiene capitulo, lo Convierte a XML y lo guarda en resultado.xml
				case "1":	
					ConversorXML(View.peticionDato("episodio"));
					break;
					
					// Mostrar XML del archivo resultado.xml
				case "2":	
					View.mostrarXML(LectorXML());
					break;
					
					// Obtener Personaje y guardarlo en una lista interna
				case "3":	
					switch(ListarPersonaje(View.peticionDato("personaje"))) {
						// Si el personaje existe y no esta añadido a la lista
					case 1:		
						System.out.println("Personaje añadido correctamente");
						break;
						// Si el personaje existe pero esta añadido a la lista
					case 0:		
						System.out.println("El personaje ya existe en la lista");
						break;
						// Si el personaje no existe
					case -1:	
						System.out.println("Hubo un problema al añadir el personaje");
					}
					break;
					
					// Guardar Personajes de la lista interna a personajes.dat
				case "4":	
					System.out.println(
							GuardarPersonajes() ? 
									"Personajes guarddados correctamente" : 
										"Hubo un porblema al guardar los personajes"
							);
					break;
					
					// Mostrar Personajes en el archivo personajes.dat
				case "5":	
					View.mostrarDatos(MostrarPersonajes());
					break;
					
					// Localizar Personaje y mostar sus datos mas los de su localización
				case "6":	
					JSONObject personajeJO = LocalizarPersonaje(View.peticionDato("personaje"));
					if (personajeJO != null)
						View.mostrarLocalizado(personajeJO);
					break;
					
					// Salir del programa
				case "7":	
					System.out.println("Saliendo del programa...");
					break;
				default:
					System.out.println("Opción no válida. Intente de nuevo.");
				}
			} catch (IOException e) {
				System.out.println("Error durante la accion: " + e.getMessage());
			}
		} while (!opcion.equals("7"));
	}

	/**
	 * Manda a la clase UtilsJSON la petición de un episodio y lo guarda en un archivo XML llamando a la clase UtilsXML
	 * 
	 * @param episodio int con el número del episodio
	 * @throws IOException
	 */
	public static void ConversorXML(int episodio) throws IOException {
		JSONObject episodioJO = utilsJSON.getJSONObject(url, "episode/" + episodio);
		if (episodioJO != null)
			utilsXML.saveXML(episodioJO);
	}
	
	
	/**
	 * Pide a la clase UtilsXML que lea el archivo XML y devuelve su NodeList
	 * 
	 * @return NodeList con los elementos del archivo
	 */
	public static NodeList LectorXML() {
		return utilsXML.readXML();
	}

	/**
	 * Manda a la clase UtilsJSON la petición de un personaje y lo guarda en una lista interna llamando a la clase UtilsIO
	 * 
	 * @param personaje int con el número del personaje
	 * @return JSONObject con la información del personaje
	 * @throws IOException
	 */
	public static JSONObject ObtenerPersonaje(int personaje) throws IOException {
		return utilsJSON.getJSONObject(url, "character/" + personaje);
	}
	
	/**
	 * Llama a la clase ObtenerPersonaje y si devuelve un JSONObject, lo convierte a
	 * Personaje y lo manda a la clase UtilsIO para guardarlo en la lista interna
	 * devolviendo un int según el resultado
	 * 
	 * @param personaje int con el número del personaje
	 * @return int con el resultado de la operación, 1 si se añade, 0 si ya esta en la lista, -1 si no existe
	 * @throws IOException
	 */
	public static int ListarPersonaje(int personaje) throws IOException {
		JSONObject personajeJO = ObtenerPersonaje(personaje);
		return personajeJO != null 
				? utilsIO.anyadirPersonaje(jSONObjectToPersonaje(personajeJO))
						? 1 
						: 0 
				: -1;
	}
	
	/**
	 * Manda a la clase UtilsIO la petición de guardar los personajes en un archivo
	 * llamando a su método serializarP
	 * 
	 * @return boolean con el resultado de la operación true si se guarda, false si no
	 */
	public static boolean GuardarPersonajes() {
		return utilsIO.serializarP();
	}
	
	/**
	 * Manda a la clase UtilsIO la petición de listar los personajes en la lista
	 * interna
	 * 
	 * @return List con los personajes
	 */
	public static List<Personaje> MostrarPersonajes() {
		return utilsIO.ListarPersonajes();
	}

	/**
	 * Manda a la clase UtilsJSON la petición de un personaje y si devuelve un
	 * JSONObject con la información del personaje y su localización
	 * 
	 * @param personaje int con el número del personaje
	 * @return JSONObject con la información del personaje y su localización
	 * @throws IOException
	 */
	public static JSONObject LocalizarPersonaje(int personaje) throws IOException {
		// Busca un personaje.
		JSONObject personajeJO =  utilsJSON.getJSONObject(url, "character/" + personaje);
		
		if (personajeJO != null) {
			// Sustituye el contenido de "location" por su contenido en la api
			personajeJO.put("location", utilsJSON.getJSONObject(
					((JSONObject) personajeJO.get("location")).get("url").toString(), 
					null
			));
		}
		return personajeJO;
	}

	/**
	 * Convierte un JSONObject a un Personaje
	 * @param jO
	 * @return
	 */
	public static Personaje jSONObjectToPersonaje(JSONObject jO) {
		return new Personaje(
				jO.getInt("id"), jO.getString("name"), jO.getString("status"),
				jO.getString("species"), jO.getString("type"),
			    jO.getString("gender"), jO.getJSONObject("origin"),
			    jO.getJSONObject("location"), jO.getString("image"),
			    jO.getJSONArray("episode").toList().toArray(new String[0]),
			    jO.getString("url"), jO.getString("created")
		);
	}
}
