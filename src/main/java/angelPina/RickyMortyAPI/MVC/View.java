package angelPina.RickyMortyAPI.MVC;

import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import angelPina.RickyMortyAPI.MVC.Model.Personaje;

/**
 * Clase que se encarga de la interacción con el usuario
 * @autor Angel Pina Gonzalez
 * 
 * @param scanner Scanner para leer datos del usuario
 */
public class View {
	static Scanner scanner;
	/**
	 * Muestra los datos del NodeList dado en formato amigable
	 * 
	 * @param nodeList NodeList con los datos
	 */
	public static void mostrarXML(NodeList nodeList) {
		boolean first = true;
		
		if (nodeList != null) {
	        for (int i = 0; i < nodeList.getLength(); i++) {
	            Element element = (Element) nodeList.item(i);
	            switch (element.getNodeName()) {
	                case "id":
	                    System.out.println("id: " + element.getTextContent());
	                    break;
	                case "name":
	                    System.out.println("name: " + element.getTextContent());
	                    break;
	                case "episode":
	                    System.out.println("episode: " + element.getTextContent());
	                    break;
	                case "characters":
						if (first) {
	                        System.out.println("characters: ");
	                        first = false;
	                    }
						System.out.println("\t" + element.getTextContent());
	                    break;
	                case "air_date":
	                    System.out.println("air_date: " + element.getTextContent());
	                    break;
	                case "created":
	                    System.out.println("created: " + element.getTextContent());
	                    break;
	                case "url":
	                    System.out.println("url: " + element.getTextContent());
	                    break;
	            }
	        }
	    }
	}
	
	/**
	 * Muestra el menú
	 * 
	 * @return String con la opción elegida
	 */
	public static String mostrarMenu() {
		scanner = new Scanner(System.in);
		
		System.out.print(
				"==== Menú ====\n" +
				"1. Conversor XML\n" +
				"2. Mostrar XML\n" +
				"3. Obtener Personaje\n" +
				"4. Guardar Personajes\n" +
				"5. Mostrar Personajes\n" +
				"6. Localización del Personaje\n" +
				"7. Salir\n" +
				"Seleccione una opción: "
		);
		return scanner.nextLine();
	}

	/**
	 * Muestra los datos de un personaje y su localización en formato amigable
	 * 
	 * @param JO JSONObject con los datos del personaje
	 */
	public static void mostrarLocalizado(JSONObject jO) {
		JSONObject location = (JSONObject) jO.get("location");
		System.out.println("name: " + jO.get("name") + ",");
		System.out.println("type: " + jO.get("type") + ",");
		
		System.out.println("location {");
		System.out.println("\tid: " + location.get("id") + ",");
		System.out.println("\tname: " + location.get("name") + ",");
		System.out.println("\ttype: " + location.get("type") + ",");
		System.out.println("\tdimension: " + location.get("dimension") + ",");
		System.out.println("\tresidents [");
		((JSONArray) location.get("residents")).forEach(jA -> {
			System.out.println("\t\t" + jA);
		});
		System.out.println("\t],");
		System.out.println("\turl: " + jO.get("url") + ",");
		System.out.println("\tcreated: " + jO.get("created"));
		System.out.println("}");
	}
	
	/**
	 * Muestra los datos de una lista de personajes
	 * 
	 * @param lP Lista de personajes a mostrar
	 */
	public static void mostrarDatos(List<Personaje> lP) {
		lP.forEach(System.out::println);
	}

	/**
	 * Pide un dato al usuario hasta que sea un entero
	 * 
	 * @param dato String con el dato a pedir
	 * @return int con el dato pedido
	 */
	public static int peticionDato(String dato) {
		scanner = new Scanner(System.in);
		do {
			try {
				System.out.print("Id del " + dato + ": ");
				return Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {}
		} while(true);
	}
}
