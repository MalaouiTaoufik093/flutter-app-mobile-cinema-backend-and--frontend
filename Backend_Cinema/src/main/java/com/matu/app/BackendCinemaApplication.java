package com.matu.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.matu.app.entities.Filme;
import com.matu.app.entities.Salle;
import com.matu.app.entities.Ticket;
import com.matu.app.services.IcenemeService;

@SpringBootApplication
public class BackendCinemaApplication  implements CommandLineRunner{
@Autowired
private IcenemeService cinemaservice;
@Autowired
private RepositoryRestConfiguration restconfigurer;
	
	public static void main(String[] args) {
		SpringApplication.run(BackendCinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restconfigurer.exposeIdsFor(Filme.class,Salle.class,Ticket.class);
		cinemaservice.initvilles();
		cinemaservice.inicinemas();
		cinemaservice.initsalles();
		cinemaservice.initplaces();
		cinemaservice.initseances();
		cinemaservice.initcategories();
		cinemaservice.initfilms();
		cinemaservice.initprojections();
		cinemaservice.inittickets();
	}

}
