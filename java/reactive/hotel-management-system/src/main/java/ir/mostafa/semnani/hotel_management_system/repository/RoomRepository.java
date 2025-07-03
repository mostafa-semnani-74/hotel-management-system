package ir.mostafa.semnani.hotel_management_system.repository;

import ir.mostafa.semnani.hotel_management_system.entity.Room;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
}
