package com.mthree.superherosightings.entities;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sightings")
public class Sighting {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int sighting_id;
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    @ManyToOne
    @JoinColumn(name = "hero_villain_id")
    private Superhero_Villain hero;
    @Column(name = "sight_date", nullable = false)
    private String sightDate;

    public void setId(int id) {
        this.sighting_id = id;
    }

    public int getId() {
        return sighting_id;
    }

    public Location getLocation() {
        return location;
    }

    public Superhero_Villain getHero() {
        return hero;
    }

    public String getsightDate() {
        return sightDate;
    }

    public void setsightDate(String sightDate) {
        this.sightDate = sightDate;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setHero(Superhero_Villain hero) {
        this.hero = hero;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.sighting_id;
        hash = 71 * hash + Objects.hashCode(this.location);
        hash = 71 * hash + Objects.hashCode(this.hero);
        hash = 71 * hash + Objects.hashCode(this.sightDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sighting_id != other.sighting_id) {
            return false;
        }
        if (!Objects.equals(this.sightDate, other.sightDate)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.hero, other.hero)) {
            return false;
        }
        return true;
    }

}
