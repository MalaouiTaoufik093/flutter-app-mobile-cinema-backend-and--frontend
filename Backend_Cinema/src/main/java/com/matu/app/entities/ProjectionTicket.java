package com.matu.app.entities;


import org.springframework.data.rest.core.config.Projection;



@Projection(name = "p2",types = Ticket.class)
public interface ProjectionTicket {
public Integer getId();
public String getNomclient();
public double getPrix();
public int getCodepaiment();
public boolean getReserve();
public Place getPlace();



}
