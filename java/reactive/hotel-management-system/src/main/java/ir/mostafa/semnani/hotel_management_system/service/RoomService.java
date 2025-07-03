package ir.mostafa.semnani.hotel_management_system.service;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.SaveRoomResponseDTO;
import jakarta.validation.Valid;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoomService {

    Mono<SaveRoomResponseDTO> save(@Valid SaveRoomRequestDTO requestDTO);

    Flux<GetAllRoomsResponseDTO> getAll();

}
