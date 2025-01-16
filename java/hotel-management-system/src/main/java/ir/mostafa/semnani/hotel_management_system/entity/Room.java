package ir.mostafa.semnani.hotel_management_system.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "number_of_beds")
    private int numberOfBeds;

}
