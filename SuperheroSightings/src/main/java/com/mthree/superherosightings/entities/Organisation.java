package com.mthree.superherosightings.entities;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "organisation")
public class Organisation {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int org_id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String address;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "hero_villain_organisation",
            joinColumns = {
                @JoinColumn(name = "org_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "hv_id")})
    private List<Superhero_Villain> members;

    public void setId(int id) {
        this.org_id = id;
    }

    public List<Superhero_Villain> getMembers() {
        return members;
    }

    public void setMembers(List<Superhero_Villain> members) {
        this.members = members;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getId() {
        return org_id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.org_id;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.description);
        hash = 97 * hash + Objects.hashCode(this.address);
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
        final Organisation other = (Organisation) obj;
        if (this.org_id != other.org_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        return true;
    }

}
