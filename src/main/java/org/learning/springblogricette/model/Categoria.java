package org.learning.springblogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

@Entity
@Table(name = "categories")
public class Categoria {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@NotEmpty(message = "Inserisci il nome della categoria")
private String name;
@OneToMany(mappedBy = "categoria")
private List<Ricetta> ricettalist;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ricetta> getRicettalist() {
        return ricettalist;
    }

    public void setRicettalist(List<Ricetta> ricettalist) {
        this.ricettalist = ricettalist;
    }
}
