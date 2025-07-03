package ir.mostafa.semnani.hotel_management_system.service.impl;

import ir.mostafa.semnani.hotel_management_system.dto.request.SaveRoomRequestDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.hotel_management_system.dto.response.SaveRoomResponseDTO;
import ir.mostafa.semnani.hotel_management_system.mapper.RoomMapper;
import ir.mostafa.semnani.hotel_management_system.repository.RoomRepository;
import ir.mostafa.semnani.hotel_management_system.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final Scheduler scheduler = Schedulers.fromExecutorService(Executors.newVirtualThreadPerTaskExecutor());

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    @Override
    public Mono<SaveRoomResponseDTO> save(final SaveRoomRequestDTO requestDTO) {
        try {
            log.info("Saving room: {}", requestDTO);
            return roomRepository.save(roomMapper.toEntity(requestDTO))
                    .publishOn(scheduler)
                    .map(roomMapper::toSaveResponseDTO)
                    .log();
        } catch (Exception e) {
            log.error("error in save in RoomServiceImpl : ", e);
            throw new RuntimeException("internal server error");
        }
    }

    @Override
    public Flux<GetAllRoomsResponseDTO> getAll() {
        try {
            return roomRepository.findAll()
                    .map(roomMapper::toGetAllRoomsResponseDTO)
                    .publishOn(scheduler)
                    .limitRate(8)
                    .log();
        } catch (Exception e) {
            log.error("error in getAll in RoomServiceImpl : ", e);
            throw new RuntimeException("internal server error");
        }
    }

}
