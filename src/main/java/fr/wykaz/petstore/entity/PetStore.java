package fr.wykaz.tpeval.entity;

import javax.persistence.*;

@Entity
@Table(name = "pet_store")
public class PetStore {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "manager_name")
    private String managerName;
}
