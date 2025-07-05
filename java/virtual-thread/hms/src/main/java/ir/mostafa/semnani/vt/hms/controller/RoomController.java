package ir.mostafa.semnani.vt.hms.controller;

import ir.mostafa.semnani.vt.hms.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.vt.hms.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rooms")
@RequiredArgsConstructor
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<GetAllRoomsResponseDTO> getAll() {
        return roomService.getAll();
    }

}
