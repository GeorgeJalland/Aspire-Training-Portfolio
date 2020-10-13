package com.mthree.superherosightings.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "heroes_villains")
public class Superhero_Villain {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int hv_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String super_power;
    @ManyToMany(mappedBy = "members", fetch = FetchType.EAGER)
    private List<Organisation> organisations;

    public void setId(int id) {
        this.hv_id = id;
    }

    public String getSuperPower() {
        return super_power;
    }

    public void setSuperPower(String super_power) {
        this.super_power = super_power;
    }

    public List<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(List<Organisation> organisations) {
        this.organisations = organisations;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return super_power;
    }

    public void setSuperpower(String superpower) {
        this.super_power = superpower;
    }

    public int getId() {
        return hv_id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.hv_id;
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + Objects.hashCode(this.super_power);
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
        final Superhero_Villain other = (Superhero_Villain) obj;
        if (this.hv_id != other.hv_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.super_power, other.super_power)) {
            return false;
        }
        return true;
    }

}
