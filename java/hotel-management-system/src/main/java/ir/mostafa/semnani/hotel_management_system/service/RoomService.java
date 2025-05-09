package ir.mostafa.semnani.hotel_management_system.service;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.SaveRoomResponseDTO;
import ir.mostafa.semnani.hotel_management_system.entity.Room;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoomService {

    Mono<SaveRoomResponseDTO> save(@Valid SaveRoomRequestDTO requestDTO);

}
