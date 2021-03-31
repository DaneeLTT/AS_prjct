package ru.iu3.rpo.backend.models;

import javax.persistence.*;

@Entity
@Table(name="countries")
@Access(AccessType.FIELD)
public class Country {

    public Country() {}
    public Country(Long id) {this.id = id;}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcountry", updatable = false, nullable = false)
    public Long id;

    @Column(name = "name", nullable = false, unique = true)
    public String name;

}
