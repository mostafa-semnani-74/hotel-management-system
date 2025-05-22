package ir.mostafa.semnani.hotel_management_system.controller;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.SaveRoomResponseDTO;
import ir.mostafa.semnani.hotel_management_system.service.RoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @PostMapping
    public Mono<SaveRoomResponseDTO> save(@Valid @RequestBody SaveRoomRequestDTO requestDTO) {
        return roomService.save(requestDTO);
    }

    @GetMapping
    public Flux<GetAllRoomsResponseDTO> getAll() {
        return roomService.getAll();
    }

}
