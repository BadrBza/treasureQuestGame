
package treasurequest.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;

/**
 * Convertit le contenu d'un fichier en un tableau de caractères.
 * 
 * @author Nicolas Hendrikx
 */
public final class CharArrayFileReader {

	/**
	 * Analyse le contenu du fichier {@code filePathStr} et le retourne sous forme d'une table de caractères.
	 * 
	 * <p>
	 * Attention, le tableau retourné est en escalier : la ligne i peut avoir un nombre de colonnes différent de la ligne j si i != j.
	 * Ignore les lignes commençant par une '#' : elle font office de commentaire. Ignore également les lignes vides.
	 * </p>
	 * <p>
	 * Vous pouvez appeler cette méthode et fournir des chemins relatifs à la racine du projet. Par exemple, l'appel
	 * {@code parseFile("resources/maps/map-sample.txt")} lira le contenu du fichier map-sample.txt, disponible dans les ressources du projet.
	 * </p>
	 * @param filePathStr le chemin vers le fichier sous forme de String.
	 * @return le contenu du fichier sous forme d'une table de caractères.
	 * @throws un exception d'exécution {@code RuntimeException} si l'argument ne correspond pas à un chemin de fichier.
	 * */
	public static char[][] parseFile(String filePathStr) {
		Path filePath = Paths.get(filePathStr);
		
		try(var reader = Files.newBufferedReader(filePath)) {
			return reader
				.lines()
				.filter(line -> !line.startsWith("#"))
				.filter(line -> !line.isBlank())
				.map(line -> line.toCharArray())
				.collect(Collectors.toList())
				.toArray(new char[0][]);
		} catch(IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}

}
