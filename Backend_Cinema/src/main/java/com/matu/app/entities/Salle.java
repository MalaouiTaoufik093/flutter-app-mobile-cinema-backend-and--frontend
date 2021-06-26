package com.matu.app.entities;

import java.util.Collection;

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
public class Salle {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String name;
private int nbplace;
@ManyToOne
@JsonProperty(access = Access.WRITE_ONLY)
private Cinema cinema;
@OneToMany(mappedBy = "salle")
@JsonProperty(access = Access.WRITE_ONLY)
private Collection<Place> places;
@OneToMany(mappedBy = "salle")
@JsonProperty(access = Access.WRITE_ONLY)
private Collection<Projection> projections;




}
