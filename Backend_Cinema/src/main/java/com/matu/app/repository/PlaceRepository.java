package com.matu.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.app.entities.Place;
import com.matu.app.entities.Ville;
@RepositoryRestResource
@CrossOrigin("*")
public interface PlaceRepository extends JpaRepository<Place, Integer> {

}
