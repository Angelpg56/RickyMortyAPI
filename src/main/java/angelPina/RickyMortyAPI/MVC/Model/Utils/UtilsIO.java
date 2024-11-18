package angelPina.RickyMortyAPI.MVC.Model.Utils;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import angelPina.RickyMortyAPI.MVC.Model.Personaje;

/**
 * Clase que se encarga del manejo de archivos binarios
 * 
 * @autor Angel Pina Gonzalez
 * @param save String de la Ruta donde se guardará el archivo personajes.dat
 * @param personajes Lista de personajes
 */
public class UtilsIO {
	private List<Personaje> personajes;
	private String save;
	
	/**
	 * Constructor que guarda la ruta donde se guardará el archivo personajes.dat
	 */
	public UtilsIO() {
		this.save = "saves/";
		personajes = new ArrayList<>();
		desSerializarP();
	}
	
	/**
	 * Método que añade un personaje a la lista personajes si no esta ya en ella
	 * @param p Personaje a añadir
	 * @return boolean true si se ha añadido, false si ya existía
	 */
	public boolean anyadirPersonaje(Personaje p) {
		boolean existe;
		if(personajes.stream().anyMatch(pl -> pl.getId() == p.getId())) {
			existe = false;
		} else {
			personajes.add(p);
			existe = true;
		}
		
		return existe;
	}

	/**
	 * Método que deserializa un archivo personajes.dat y añade los personajes a la lista personajes
	 */
	public void desSerializarP() {
		ObjectInputStream ficheroObjetos = null;
		
		try {
			File file = new File(this.save + "personajes.dat");
			
			// Si el archivo no existe, lo crea
	        if (!file.exists()) {
	            try {
					file.createNewFile();
					System.out.println("Archivo 'personajes.dat' creado en " + this.save);
				} catch (IOException e) {}
	        } else {
	        	System.out.println("Leyendo archivo " + this.save + "personajes.dat");
				ficheroObjetos = 
						new ObjectInputStream(
								new FileInputStream(
										new File(this.save + "personajes.dat")));
				// Lee el archivo hasta el final y añade cada personaje a la lista
				while (true) {
		                Personaje p = (Personaje) ficheroObjetos.readObject();
		                anyadirPersonaje(p);
				}
	        }
        } catch (EOFException e) {
			System.out.println("Archivo leido correctamente");
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (Exception e) {
			//System.out.println("Error al leer el archivo " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (ficheroObjetos != null)
				try {
					ficheroObjetos.close();
				} catch (IOException e) {}
		}
		
		if (this.personajes == null || this.personajes.isEmpty()) {
			System.out.println("Archivo vacio");
		} else {
			System.out.println(this.personajes.size() + " personajes leidos");
		}

	}
	
	/**
	 * Método que serializa la lista personajes en un archivo personajes.dat
	 * @param <T> Tipo de objeto a serializar
	 * @return boolean true si se ha guardado, false si no hay personajes en la lista o ha habido un error
	 */
	public <T> boolean serializarP() {
	    ObjectOutputStream ficheroObjetos = null;
	    boolean guardado = false;
		if (!personajes.isEmpty()) {
		    try {
		        File file = new File(this.save + "personajes.dat");
		        
		        // Si el archivo no existe, lo crea
		        if (!file.exists()) {
		            file.createNewFile();
		        }
		        ficheroObjetos = new ObjectOutputStream(new FileOutputStream(file));
		        // Escribe cada personaje serializado al archivo
		        for (Personaje a : personajes) {
		            ficheroObjetos.writeObject(a);
		        }
		        guardado =  true;
		    } catch (IOException e) {
		        e.printStackTrace();
		        guardado = false;
		    } finally {
		        if (ficheroObjetos != null) {
		            try {
		                ficheroObjetos.close();
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
		    }
		} else {
			System.out.println("Obtén personajes (opcion 3) para guardarlos en el archivo");
		}
	    return guardado;
	}

	/**
	 * Método que devuelve la lista personajes
	 * @return List<Personaje> Lista de personajes
	 */
	public List<Personaje> ListarPersonajes() {
		return personajes;
	}
}
