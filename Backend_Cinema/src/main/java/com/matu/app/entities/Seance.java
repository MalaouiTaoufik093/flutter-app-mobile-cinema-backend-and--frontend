package com.matu.app.entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

import javax.annotation.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ManyToAny;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Seance {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@Temporal(TemporalType.TIME)
private java.util.Date heurdebut;


}
