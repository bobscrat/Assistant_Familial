package fr.acdo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorMessages {

	private static final Logger logger = LoggerFactory.getLogger(ErrorMessages.class);

	public void getAll(Class<?> class1, String string) {
		logger.error("ERREUR : Sur " + class1 + " la méthode " + string + " ne renvoit rien. ");
	}

	public void getById(Class<?> class1, Long id, String string) {
		logger.error("ERREUR : Sur " + class1 + " la méthode " + string + " ne renvoit rien pour l'id " + id);
	}

	public void saveInBase(Class<?> class1, String string) {
		logger.error("ERREUR : Sur " + class1 + " la méthode " + string + " ne peut sauvegarder. ");
	}

	public void updateInBase(Class<?> class1, String string) {
		logger.error("ERREUR : Sur " + class1 + " la méthode " + string + " ne peut modifier. ");
	}

	public void deleteInBase(Class<?> class1, String string) {
		logger.error("ERREUR : Sur " + class1 + " la méthode " + string + " ne peut supprimer. ");
	}

}
