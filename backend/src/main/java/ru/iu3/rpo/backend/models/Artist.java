package ru.iu3.rpo.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@Access(AccessType.FIELD)
public class Artist {

    public Artist() { }
    public Artist(Long id) { this.id = id; }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idartist", updatable = false, nullable = false)
    public long id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

    @Column(name = "century", nullable = false, unique = true)
    public String century;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idcountry")
    public Country country;
}
