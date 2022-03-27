package fr.wykaz.petstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.JOINED)
public class Animal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate birth;

    private String couleur;

    @ManyToOne
    @JoinColumn(name="pet_store")
    private PetStore petStore;


    public Animal(LocalDate birth, String couleur, PetStore petStore) {
        this.birth = birth;
        this.couleur = couleur;
        this.petStore = petStore;
        petStore.getAnimals().add(this);
    }

    public Animal() {

    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public PetStore getPetStore() {
        return petStore;
    }

    public void setPetStore(PetStore petStore) {
        this.petStore = petStore;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "birth=" + birth +
                ", couleur='" + couleur + '\'' +
                '}';
    }
}