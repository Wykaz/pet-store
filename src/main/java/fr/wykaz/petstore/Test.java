package fr.wykaz.tpeval;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Test {

    private static final String PU = "pu-petstore";

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory(PU);
        EntityManager em = emf.createEntityManager();
        System.out.println(em);

        em.close();
        emf.close();
    }
}