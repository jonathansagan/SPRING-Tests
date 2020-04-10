package dev;

import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dev.config.DataSourceConfig;
import dev.dao.PlatDaoJdbc;

public class AppJdbc {

	private static final Logger LOGGER = Logger.getLogger(AppJdbc.class.getName());

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				DataSourceConfig.class)) {

			PlatDaoJdbc PlatDaoJdbc = context.getBean(PlatDaoJdbc.class);

			Integer nbElements = PlatDaoJdbc.compter();

			LOGGER.info("NB ELEMENTS=" + nbElements);

		}
	}
}