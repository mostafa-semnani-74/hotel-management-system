package ir.mostafa.semnani.hotel_management_system.mapper;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.SaveRoomResponseDTO;
import ir.mostafa.semnani.hotel_management_system.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    Room toEntity(SaveRoomRequestDTO saveRoomRequestDTO);

    SaveRoomResponseDTO toSaveResponseDTO(Room room);

    GetAllRoomsResponseDTO toGetAllRoomsResponseDTO(Room rooms);

}
