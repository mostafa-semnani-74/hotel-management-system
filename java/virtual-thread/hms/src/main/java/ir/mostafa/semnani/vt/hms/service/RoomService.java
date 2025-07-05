package ir.mostafa.semnani.vt.hms.service;

import ir.mostafa.semnani.vt.hms.dto.response.GetAllRoomsResponseDTO;

import java.util.List;

public interface RoomService {

    List<GetAllRoomsResponseDTO> getAll();

}
