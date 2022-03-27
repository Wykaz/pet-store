package fr.wykaz.petstore.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "cat")
public class Cat extends Animal {

    private String chipid;

    public Cat(LocalDate birth, String couleur, PetStore petStore, String chipid) {
        super(birth, couleur, petStore);
        this.chipid = chipid;
    }

    public Cat() {
        super();
    }

    public String getChipid() {
        return chipid;
    }

    public void setChipid(String chipid) {
        this.chipid = chipid;
    }

    @Override
    public String toString() {
        return "Cat{" +
                super.toString() +
                ", chipid='" + chipid + '\'' +
                '}';
    }
}