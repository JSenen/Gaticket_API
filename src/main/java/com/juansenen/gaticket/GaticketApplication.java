package com.juansenen.gaticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** API para el control http de la aplicacion gaticket. Apliación de gestión de tickets de incidencia
 * por parte de un equipo de Tecnologias de la Informacion
 * @author Juan Senen
 * @version 1.0*/
@SpringBootApplication
public class GaticketApplication {

	public static void main(String[] args) {
		SpringApplication.run(GaticketApplication.class, args);
	}

}
