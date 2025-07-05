package ir.mostafa.semnani.vt.hms.mapper;

import ir.mostafa.semnani.vt.hms.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.vt.hms.entity.Room;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMapper {

    GetAllRoomsResponseDTO toGetAllRoomsResponseDTO(Room rooms);

}
