package fr.wykaz.petstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private String label;

    @Enumerated
    private ProdType type;

    private double price;

    @ManyToMany(mappedBy = "products")
    private Set<PetStore> petStores;

    {
        this.petStores = new HashSet<>();
    }

    public Product(String code, String label, ProdType type, double price) {
        this.code = code;
        this.label = label;
        this.type = type;
        this.price = price;
    }

    public Product() {
    }

    public void addProducts(PetStore petStore) {
        this.petStores.add(petStore);
        petStore.getProducts().add(this);
    }

    public void removeProducts(PetStore petStore) {
        this.petStores.remove(petStore);
        petStore.getProducts().remove(this);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ProdType getType() {
        return type;
    }

    public void setType(ProdType type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<PetStore> getPetStores() {
        return petStores;
    }

    public void setPetStores(Set<PetStore> petStores) {
        this.petStores = petStores;
    }

    @Override
    public String toString() {
        return "Product{" +
                "code='" + code + '\'' +
                ", label='" + label + '\'' +
                ", type=" + type +
                ", price=" + price +
                '}';
    }
}