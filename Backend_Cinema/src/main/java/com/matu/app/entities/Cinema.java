package com.matu.app.entities;

import java.util.Collection;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Cinema {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String name;
private double ltitude,longitude,altitude;
private int nbsalles;
@OneToMany (mappedBy = "cinema")
private Collection<Salle> salles;
@ManyToOne
private Ville ville;
}
