package dev;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.AppConfig;
import dev.ihm.Menu;

/**
 * @author Jonathan Sagan
 *
 */
public class AppSpringJava {

	public static void main(String[] args) {
		// Création du contexte Spring à partir d'une configuration Java
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		menu.afficher();

		// fermeture du contexte Spring
		context.close();
	}

}
