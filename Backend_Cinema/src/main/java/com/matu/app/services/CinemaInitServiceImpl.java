package com.matu.app.services;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.matu.app.entities.Categorie;
import com.matu.app.entities.Cinema;
import com.matu.app.entities.Filme;
import com.matu.app.entities.Place;
import com.matu.app.entities.Projection;
import com.matu.app.entities.Salle;
import com.matu.app.entities.Seance;
import com.matu.app.entities.Ticket;
import com.matu.app.entities.Ville;
import com.matu.app.repository.CategorieRepository;
import com.matu.app.repository.CinemaRepository;
import com.matu.app.repository.FilmeRepository;
import com.matu.app.repository.PlaceRepository;
import com.matu.app.repository.ProjectionRepository;
import com.matu.app.repository.SalleRepository;
import com.matu.app.repository.SeanceRepository;
import com.matu.app.repository.TicketRepository;
import com.matu.app.repository.VilleRepository;

import antlr.collections.List;

@Service
@Transactional
public class CinemaInitServiceImpl  implements IcenemeService{

	@Autowired
	private VilleRepository villerepository;
	@Autowired
	private CinemaRepository cinemarepository;
	@Autowired
	private SalleRepository sallerepository;
	@Autowired
	private PlaceRepository placerepository;
	@Autowired
	private ProjectionRepository projectionrepository;
	@Autowired
	private SeanceRepository seancerepository;
	@Autowired
	private FilmeRepository filmerepository;
	@Autowired
	private TicketRepository ticketrepository;
	@Autowired
	private CategorieRepository categorierepository;
	
	
	@Override
	public void initvilles() {
	Stream.of("Casablanca","Marrakech","Rabat","Tanger").forEach(namevalue->{
		Ville ville=new Ville();
		ville.setName(namevalue);
		villerepository.save(ville);
	});
		
	}

	@Override
	public void inicinemas() {
		villerepository.findAll().forEach(v->{
			Stream.of("Megarama","Imax","Founoune","Chahrazad","Doualize")
			.forEach(namecinema->{
				Cinema cinema =new Cinema();
				cinema.setName(namecinema);
				cinema.setNbsalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemarepository.save(cinema);
			});
		});
		
	}

	@Override
	public void initsalles() {
		cinemarepository.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNbsalles();i++) {
				Salle salle =new Salle();
				salle.setName("Salle "+(i+1));
				salle.setCinema(cinema);
				salle.setNbplace(15+(int)(Math.random()*10));
			    sallerepository.save(salle);
			    
			}
		});
		
	}

	@Override
	public void initplaces() {
		sallerepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNbplace();i++) {
				Place place = new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placerepository.save(place);
			}
		});		
	}

	@Override
	public void initseances() {
		DateFormat df =new SimpleDateFormat("HH:mm");
      Stream.of("12:00","15:00","19:00","21:00").forEach(s->{
    	  Seance seance = new Seance();
    	  try {
			seance.setHeurdebut(df.parse(s));
			seancerepository.save(seance);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      });		
	}	
	
	@Override
	public void initcategories() {
		Stream.of("Action","Fiction","Drama").forEach(f->{
			Categorie categorie=new Categorie();
			categorie.setName(f);
            categorierepository.save(categorie);
		});
	}

	
	
	@Override
	public void initfilms() {
		double[] durees =new double[] {1,1.5,2,2.5,3};
		java.util.List<Categorie> categories = categorierepository.findAll();
		Stream.of("12 Homes en colaire","Forset Gump","Green Book","La Ligne Verte","Le Parin","Le Seigneur des anneaux").forEach(titrefilme->{
			Filme filme = new Filme();
			filme.setTitre(titrefilme);
			filme.setDuree(durees[new Random().nextInt(durees.length)]);		
			filme.setPhoto(titrefilme.replaceAll(" ", ""));
			filme.setCategorie(categories.get(new Random().nextInt(categories.size())));
			filmerepository.save(filme);
		});
	}

	@Override
	public void initprojections() {
		double[] prix = new double[] {100,200,300,400,500};
		java.util.List<Filme> filmes = filmerepository.findAll();
		villerepository.findAll().forEach(ville->{
			ville.getCinemas().forEach(cinema->{
				cinema.getSalles().forEach(salle->{
					int index = new Random().nextInt(filmes.size());
					Filme filme = filmes.get(index);
						seancerepository.findAll().forEach(seance->{
							Projection projection=new Projection();
							projection.setDateprojection(new java.util.Date());
						    projection.setFilme(filme);
						    projection.setPrix(prix[new Random().nextInt(prix.length)]);
						    projection.setSalle(salle);
						    projection.setSeance(seance);
						    projectionrepository.save(projection);
						});
					
				});
			});
		});
		
	}

	@Override
	public void inittickets() {
		projectionrepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket=new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReserve(false);
				ticketrepository.save(ticket);
			});
		});
		
		
	}

}
