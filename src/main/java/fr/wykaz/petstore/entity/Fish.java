package fr.wykaz.petstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Entity
public class Fish extends Animal {

    @Enumerated
    @Column(name = "living_env")
    private FishLivEnv livingEnv;

    public Fish(LocalDate birth, String couleur, PetStore petStore, FishLivEnv livingEnv) {
        super(birth, couleur, petStore);
        this.livingEnv = livingEnv;
    }

    public Fish() {
        super();
    }

    public FishLivEnv getLivingEnv() {
        return livingEnv;
    }

    public void setLivingEnv(FishLivEnv livingEnv) {
        this.livingEnv = livingEnv;
    }

    @Override
    public String toString() {
        return "Fish{" +
                super.toString() +
                ", livingEnv=" + livingEnv +
                '}';
    }
}