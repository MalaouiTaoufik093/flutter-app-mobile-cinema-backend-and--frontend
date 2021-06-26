package com.matu.app.Controller;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.matu.app.entities.Filme;
import com.matu.app.entities.Ticket;
import com.matu.app.repository.FilmeRepository;
import com.matu.app.repository.TicketRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@RestController
@CrossOrigin("*")
public class CinemaRestController {
	@Autowired
	FilmeRepository filmerepository;
	@Autowired
	TicketRepository ticketrepository;
	
	
	
	@GetMapping(path = "/imageFilme/{id}",produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] images(@PathVariable (name="id") Integer id) throws IOException {
		Filme filme = filmerepository.findById(id).get();
		String photoname =filme.getPhoto();
		File file=new File(System.getProperty("user.home")+"/cinema/images/"+photoname+".jpg"); 
	    Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
	}
	
	@PostMapping("/payerTicket")
	@Transactional
	public List<Ticket>payer (@RequestBody TicketForm ticketforme) {
		List<Ticket> listTickets= new ArrayList<>();
		ticketforme.getTickets().forEach(idticket->{
			System.out.println(idticket);
			Ticket ticket =ticketrepository.findById(idticket).get();
			    ticket.setNomclient(ticketforme.getNom_client());
			    ticket.setReserve(true);
			    ticket.setCodepaiment(ticketforme.getCodepayement());
			    ticketrepository.save(ticket);
			    listTickets.add(ticket);
			    
		});
		return listTickets;
	} 

}

@Data 
class TicketForm{
private  String nom_client; 
private int codepayement;
private List<Integer> tickets=new ArrayList<>();
}

