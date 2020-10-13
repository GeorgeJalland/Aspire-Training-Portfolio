package com.mthree.superherosightings.repositories;

import com.mthree.superherosightings.entities.Location;
import com.mthree.superherosightings.entities.Organisation;
import com.mthree.superherosightings.entities.Sighting;
import com.mthree.superherosightings.entities.Superhero_Villain;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoriesTest {

    private final SightingRepository sightingRepo;
    private final LocationRepository locationRepo;
    private final SuperheroRepository supersRepo;
    private final OrganisationRepository orgRepo;
    private Superhero_Villain testSuper;
    private final Superhero_Villain testVillain;
    private final Location testLocationHouse;
    private final Location testLocationCinema;
    private final Organisation testOrganisation;
    private final List<Superhero_Villain> testSVList;
    private final Sighting testSightOne;
    private final Sighting testSightTwo;

    @Autowired
    public RepositoriesTest(SightingRepository sightingRepo, LocationRepository locationRepo, SuperheroRepository supersRepo, OrganisationRepository orgRepo) {
        this.sightingRepo = sightingRepo;
        this.locationRepo = locationRepo;
        this.supersRepo = supersRepo;
        this.orgRepo = orgRepo;

        testSuper = new Superhero_Villain();
        testSuper.setName("George");
        testSuper.setDescription("Talented");
        testSuper.setSuperPower("Programming");

        testVillain = new Superhero_Villain();
        testVillain.setName("Phil Book");
        testVillain.setDescription("Alpha");
        testVillain.setSuperPower("Invisibility");

        testSVList = new ArrayList<>();
        testSVList.add(testSuper);
        testSVList.add(testVillain);

        testLocationHouse = new Location();
        testLocationHouse.setName("Grey house");
        testLocationHouse.setAddress("Knipton");
        testLocationHouse.setDescription("Cold");
        testLocationHouse.setLatitude("102345");
        testLocationHouse.setLongitude("233489");

        testLocationCinema = new Location();
        testLocationCinema.setName("Cinema");
        testLocationCinema.setAddress("London, 1SW");
        testLocationCinema.setDescription("self-describing");
        testLocationCinema.setLatitude("123456");
        testLocationCinema.setLongitude("222222");

        testOrganisation = new Organisation();
        testOrganisation.setName("mTree");
        testOrganisation.setAddress("Silicon valley");
        testOrganisation.setDescription("powerful");
        testOrganisation.setMembers(testSVList);

        testSightOne = new Sighting();
        testSightOne.setHero(testSuper);
        testSightOne.setLocation(testLocationHouse);
        testSightOne.setsightDate("1997-03-22");

        testSightTwo = new Sighting();
        testSightTwo.setHero(testSuper);
        testSightTwo.setLocation(testLocationCinema);
        testSightTwo.setsightDate("2005-03-22");
    }

    @BeforeEach
    public void setUp() {
        supersRepo.deleteAll();
        locationRepo.deleteAll();
        sightingRepo.deleteAll();
        orgRepo.deleteAll();
    }

    @Test
    public void testAddGetSuperhero() {
        supersRepo.save(testSuper);
        supersRepo.save(testVillain);

        Superhero_Villain actualSup = supersRepo.findById(testSuper.getId()).get();
        List<Superhero_Villain> expectedList = supersRepo.findAll();

        assertEquals(testSuper, actualSup);
        assertEquals(expectedList, testSVList);
    }

    @Test
    public void testAddGetLocation() {
        locationRepo.save(testLocationHouse);

        Location actualLoc = locationRepo.findById(testLocationHouse.getId()).get();

        assertEquals(testLocationHouse, actualLoc);
    }

    @Test
    public void testAddGetOrganisation() {
        supersRepo.save(testSuper);
        supersRepo.save(testVillain);
        orgRepo.save(testOrganisation);

        Organisation actualOrg = orgRepo.findById(testOrganisation.getId()).get();

        assertEquals(testOrganisation, actualOrg);
    }

    @Test
    public void testAddGetSighting() {
        locationRepo.save(testLocationHouse);
        supersRepo.save(testSuper);
        sightingRepo.save(testSightOne);

        Sighting actualSighting = sightingRepo.findById(testSightOne.getId()).get();

        assertEquals(testSightOne, actualSighting);
    }

    @Test
    public void testGetSupersBySightingLocation() {
        locationRepo.save(testLocationHouse);
        supersRepo.save(testSuper);
        sightingRepo.save(testSightOne);

        List<Superhero_Villain> supersSighted = supersRepo.findSuperSightingByLocation(testLocationHouse.getId());

        assertTrue(supersSighted.contains(testSuper));
        assertEquals(1, supersSighted.size());
    }

    @Test
    public void testGetLocationsOfSuperheroSightings() {
        locationRepo.save(testLocationHouse);
        locationRepo.save(testLocationCinema);
        supersRepo.save(testSuper);
        sightingRepo.save(testSightOne);
        sightingRepo.save(testSightTwo);

        List<Location> superSightingLocations = locationRepo.findSightingLocationsBySuper(testSuper.getId());

        assertTrue(superSightingLocations.contains(testLocationHouse));
        assertTrue(superSightingLocations.contains(testLocationCinema));
        assertEquals(2, superSightingLocations.size());
    }

    @Test
    public void testGetSigtingsByDate() {
        locationRepo.save(testLocationHouse);
        locationRepo.save(testLocationCinema);
        supersRepo.save(testSuper);
        sightingRepo.save(testSightOne);
        sightingRepo.save(testSightTwo);

        List<Sighting> sightingsByDate = sightingRepo.findBysightDate(testSightOne.getsightDate());

        assertTrue(sightingsByDate.contains(testSightOne));
        assertEquals(1, sightingsByDate.size()); //Sightings have different dates
    }

    @Test
    public void testOrganisationMembers() {
        supersRepo.save(testSuper);
        supersRepo.save(testVillain);
        orgRepo.save(testOrganisation);

        List<Superhero_Villain> organisationMembers = testOrganisation.getMembers();

        assertEquals(testSVList, organisationMembers);

        testSuper = supersRepo.findById(testSuper.getId()).get();

        List<Organisation> supersOrganisations = testSuper.getOrganisations();

        assertTrue(supersOrganisations.contains(testOrganisation));
        assertEquals(1, supersOrganisations.size());
    }

}
