package com.mthree.superherosightings.repositories;

import com.mthree.superherosightings.entities.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {

    @Query(value = "SELECT l.* FROM location l "
            + "LEFT JOIN sightings s on s.location_id = l.location_id"
            + " WHERE s.hero_villain_id = ?", nativeQuery = true)
    List<Location> findSightingLocationsBySuper(int hero_id);

}
