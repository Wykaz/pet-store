package fr.wykaz.petstore.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pet_store")
public class PetStore implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "manager_name")
    private String managerName;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="pet_store_product",
            joinColumns= @JoinColumn(name="id_pet_store", referencedColumnName="id"),
            inverseJoinColumns= @JoinColumn(name="id_product", referencedColumnName="id")
    )
    private final Set<Product> products;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(unique = true)
    private Address address;

    @OneToMany(mappedBy = "petStore", cascade = CascadeType.PERSIST)
    private Set<Animal> animals;

    {
        this.products = new HashSet<>();
        this.animals = new HashSet<>();
    }

    public PetStore(String name, String managerName) {
        this.name = name;
        this.managerName = managerName;
    }

    public PetStore() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void addProducts(Product products) {
        this.products.add(products);
        products.getPetStores().add(this);
    }

    public void removeProducts(Product products) {
        this.products.remove(products);
        products.getPetStores().remove(this);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
        address.defPetStore(this);
    }

    public void defAddress(Address address) {
        this.address = address;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "name='" + name + '\'' +
                ", managerName='" + managerName + '\'' +
                '}';
    }
}