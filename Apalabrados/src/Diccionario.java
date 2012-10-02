import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Diccionario {
	// crea el diccionario en tabla hash para insercciones y consultas mas rapidas
	//el valor inicial de la tabla es 50 palabras y el factor de carga es el 90%
	// es decir cuando llega al 90% el hashset crece
	private Set <String> miDiccionario = new HashSet <String> (96000,0.9f);

	Diccionario(){
		try{
			//inicio del fichero
			DataInputStream entrada = new DataInputStream(new FileInputStream(Datos.NOMBRE_FICHERO_DICCIONARIO));
			// Creamos el Buffer de Lectura
			BufferedReader buffer = new BufferedReader(new InputStreamReader(entrada));
			String strLinea;
			while ((strLinea = buffer.readLine()) != null)   {
				//añadimos a la tabla hash
				miDiccionario.add(strLinea);
			}
			// Cerramos el archivo
			entrada.close();
		}catch (Exception e){ //Catch de excepciones
			System.err.println("Ocurrio un error: " + e.getMessage());
		}
		System.out.println("en el diccionario hay "+ miDiccionario.size()+" palabras");
	}
	
	/**
	 * 
	 * @param cadena, cadena de entrada a comprobar
	 * @return devuelve true, si la cadena esta en el diccionario, false si no esta en el diccionario.
	 */
	public boolean EsValida(String cadena){
		return miDiccionario.contains(cadena);
	}
}
