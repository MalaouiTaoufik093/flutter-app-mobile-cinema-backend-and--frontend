package com.matu.app.entities;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1",types = com.matu.app.entities.Projection.class)
public interface ProjectionProj {
public Integer getId();
public double getprix();
public Date getDateprojection();
public Salle getSalle();
public Filme getFilme();
public Seance getSeance();
public Collection<Ticket> gettickets();
}
