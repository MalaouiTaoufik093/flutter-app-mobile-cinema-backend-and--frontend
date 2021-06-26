package com.matu.app.entities;

import java.sql.Date;
import java.util.Collection;
import java.util.Locale.Category;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Filme {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String titre,realisateur,description,photo;
private Date date_sortie;
private double duree;
@OneToMany(mappedBy = "filme")
@JsonProperty(access = Access.WRITE_ONLY)
private Collection<Projection> projections;
@ManyToOne
private Categorie categorie;
}
