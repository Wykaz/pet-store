package fr.wykaz.petstore;

import com.fasterxml.classmate.AnnotationInclusion;
import fr.wykaz.petstore.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.List;

public class Test {

    private static final String PU = "pu-petstore";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        System.out.println(em);

        //Product
        Product product1 = new Product("R1", "label", ProdType.ACCESSORY, 12.6);
        Product product2 = new Product("R2", "label", ProdType.CLEANING, 15.2);
        Product product3 = new Product("R3", "label", ProdType.FOOD, 18.3);

        //PetStore and Address
        PetStore petStore1 = new PetStore("Ace Pet", "Pole");
        Address address1 = new Address("6", "rue des bois", "35410", "Rennes");
        address1.setPetStore(petStore1);

        PetStore petStore2 = new PetStore("Furry Friends", "Jean");
        Address address2 = new Address("9", "rue de paris", "75000", "Paris");
        petStore2.setAddress(address2);

        PetStore petStore3 = new PetStore("Purr-fect Pets", "Cara");
        Address address3 = new Address("2", "rue de nice", "11111", "Nice");
        address3.setPetStore(petStore3);

        //ajoute de product au petStore
        petStore1.addProducts(product1);
        petStore1.addProducts(product2);
        petStore1.addProducts(product3);

        //ajoute de petStore au product
        product3.addProducts(petStore3);

        //Fish
        Fish anguille = new Fish(LocalDate.now(), "Bleu", petStore1, FishLivEnv.SEA_WATER);
        Fish fich2 = new Fish(LocalDate.now(), "Rouge", petStore1, FishLivEnv.FRESH_WATER);
        Fish fich3 = new Fish(LocalDate.now(), "Orange", petStore1, FishLivEnv.SEA_WATER);

        //Cat
        Cat chat1 = new Cat(LocalDate.now(), "Bleu", petStore1, "c1");
        Cat chat2 = new Cat(LocalDate.now(), "Rouge", petStore2, "c2");
        Cat chat3 = new Cat(LocalDate.now(), "Orange", petStore2, "c3");

        em.getTransaction().begin();

        em.persist(petStore1);
        em.persist(petStore2);
        em.persist(petStore3);

        em.getTransaction().commit();

        //extraire tous les animaux d’une animalerie donnée

        PetStore petStore = em.find(PetStore.class, 1L);
        if (petStore == null) {
            System.out.println("Imposible de trouver l'animalerie demender");
        } else {
            System.out.println("List des animaux :");
            for (Animal animal : petStore.getAnimals()) {
                System.out.println(animal);
            }
        }

        //requête qui permet d’extraire tous les animaux d’une animalerie donnée
        TypedQuery<Animal> query = em.createQuery("SELECT a FROM Animal a WHERE a.petStore.id= :id", Animal.class);
        query.setParameter("id", 2L);
        List<Animal> persons = query.getResultList();

        System.out.println("List des animaux via requête :");
        for (Animal animal : persons) {
            System.out.println(animal);
        }

        em.close();
        emf.close();
    }
}