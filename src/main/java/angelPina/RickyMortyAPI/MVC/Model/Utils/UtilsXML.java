package angelPina.RickyMortyAPI.MVC.Model.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.XML;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Clase que se encarga del manejo de archivos XML
 * 
 * @autor Angel Pina Gonzalez
 * @param save Ruta donde se guardará el archivo XML
 */
public class UtilsXML {
	private String save;

	/**
	 * Constructor que guarda la ruta donde se guardará el archivo XML
	 */
	public UtilsXML() {
		this.save = "saves/resultado.xml";
	}

	/**
	 * Guarda un JSONObject en un archivo XML
	 * @param jO JSONObject a guardar
	 */
	public void saveXML(JSONObject jO) {
		File file = new File(this.save);
		
        String xmlContent = "<html>" + XML.toString(jO) + "</html>";
        try (FileWriter fileWriter = new FileWriter(this.save)) {
            fileWriter.write(xmlContent);
            System.out.println("Episodio agregado al XML correctamente");
        } catch (IOException e) {}
    }

	/**
	 * Lee un archivo XML y devuelve un NodeList con los elementos
	 * 
	 * @return NodeList con los elementos
	 */
	public NodeList readXML() {
		NodeList nL = null;
        try {
            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Leer el archivo XML
            File xmlFile = new File(this.save);
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            // Obtener todos los elementos
            nL = doc.getElementsByTagName("*");
        } catch (Exception e) {
			System.out.println("Fichero no encontrado");
        }
		
		return nL;
    }
}
