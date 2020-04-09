package dev;

import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import dev.ihm.Menu;

/**
 * Création du contexte Spring
 * 
 * @author Jonathan
 *
 */
public class AppSpringXML {
	public static void main(String[] args) {
		// creation contexte Spring config mémoire
		//ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-memoire.xml");
		
		// creation contexte Spring config fichier
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-config-fichier.xml");
		
		// récupération du bean Scanner
		Scanner scanner = context.getBean(Scanner.class);
		
		// récupération du bean Menu
		Menu menu = context.getBean(Menu.class);
		menu.afficher();
		
		// fermeture du Scanner
		context.getBean(Scanner.class).close();
		
		// fermeture du contexte Spring
		context.close();
	}
}