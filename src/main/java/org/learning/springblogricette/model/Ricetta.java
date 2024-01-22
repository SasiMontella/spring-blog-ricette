package org.learning.springblogricette.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ricette")
public class Ricetta {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
@NotEmpty(message = "Inserisci il titolo della ricetta")
private String title;
@NotEmpty(message = "Inserisci gli ingredienti")
private String ingridients;
private String photo;
private String time;
@NotNull
@Min(1)
private int portions;
@NotEmpty(message = "Inserisci la descrizione")
@Column(length = 400)
@Lob
private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIngridients() {
        return ingridients;
    }

    public void setIngridients(String ingridients) {
        this.ingridients = ingridients;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
