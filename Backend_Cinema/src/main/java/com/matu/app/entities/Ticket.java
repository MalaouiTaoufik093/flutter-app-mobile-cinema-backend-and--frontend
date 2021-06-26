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
public class Ticket {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
private String nomclient;
private double prix;
private int  codepaiment;
private boolean reserve;
@ManyToOne
private Place place;
@ManyToOne
@JsonProperty(access = Access.WRITE_ONLY)
private Projection projection;
}
