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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Ville {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String name;
private double latitude,longitude,altitude;
@OneToMany(mappedBy = "ville")
private Collection<Cinema> cinemas;
}
