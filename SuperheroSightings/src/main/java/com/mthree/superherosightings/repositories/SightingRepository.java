package com.mthree.superherosightings.repositories;

import com.mthree.superherosightings.entities.Sighting;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SightingRepository extends JpaRepository<Sighting, Integer> {

    List<Sighting> findBysightDate(String date);

}
