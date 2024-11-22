package org.example.models;

public class CategorieEvent {
    int id;

    public CategorieEvent() {
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CategorieEvent{" +
                "id=" + id +
                ", description_categorie_event='" + description_categorie_event + '\'' +
                ", nom='" + nom + '\'' +
                '}';
    }

    public CategorieEvent(int id, String description_categorie_event, String nom) {
        this.id = id;
        this.description_categorie_event = description_categorie_event;
        this.nom = nom;
    }

    public CategorieEvent(String description_categorie_event, String nom) {
        this.description_categorie_event = description_categorie_event;
        this.nom = nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription_categorie_event() {
        return description_categorie_event;
    }

    public void setDescription_categorie_event(String description_categorie_event) {
        this.description_categorie_event = description_categorie_event;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    String description_categorie_event,nom;
}
