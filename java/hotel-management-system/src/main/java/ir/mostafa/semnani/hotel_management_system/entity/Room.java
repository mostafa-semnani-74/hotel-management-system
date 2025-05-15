package ir.mostafa.semnani.hotel_management_system.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("room")
@Getter
@Setter
public class Room {

    @Id
    private Long id;

    @Column("number_of_beds")
    private int numberOfBeds;

}
