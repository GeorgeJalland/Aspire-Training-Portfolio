package com.mthree.superherosightings.repositories;

import com.mthree.superherosightings.entities.Superhero_Villain;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero_Villain, Integer> {

    @Query(value = "SELECT h.* FROM heroes_villains h "
            + "LEFT JOIN sightings s on s.hero_villain_id = h.hv_id"
            + " WHERE s.location_id = ?", nativeQuery = true)
    List<Superhero_Villain> findSuperSightingByLocation(int location_id);
}
