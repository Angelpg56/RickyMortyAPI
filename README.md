Este es un programa con un menú con opciones donde se obtenga la siguiente información:

1. Conversor XML. Pedir un episodio por pantalla (un número entero). A partir del resultado, 
  genera un fichero de texto llamado resultado.xml. Debe estar en formato xml válido (puedes 
  comprobarlo si lo abres con un navegador y sale bien formado).
2. Mostrar XML. Leer el contenido de resultado.xml (del punto 1) y mostrar la información por 
  pantalla en un formato amigable para el usuario. 
3. Obtener Personaje. Pedir un personaje por pantalla (un número entero). Guardar el resultado 
  a una clase Personaje.class y añadirlo en una Lista de Personajes. El programa comprobará 
  que no existe dicho personaje previamente antes de añadirlo para evitar duplicados.
4. Guardar Personajes. Serializa los Personajes almacenados en un archivo personajes.dat.
5. Mostrar Personajes. Mostrará los datos de los personajes Des-serializados del archivo personajes.dat 
  al entrar ADEMÁS de los añadidos al abrir el programa* y durante su ejecución(ver nota 1).
    *Al abrir el programa se Des-serializarán los datos que haya en el fichero personajes.dat y se 
    rellenará la Lista de Personajes con los datos almacenados.
6. Localización del Personaje. Pedir un personaje por pantalla. Mostrar el nombre, tipo y dimensión 
  de su localización. Es decir, deberá consultar el JSON localización dentro del Personaje y obtener 
  los datos de este parámetro.
7. Salir.
