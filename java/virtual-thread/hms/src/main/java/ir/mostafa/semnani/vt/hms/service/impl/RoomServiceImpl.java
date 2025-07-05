package ir.mostafa.semnani.vt.hms.service.impl;

import ir.mostafa.semnani.vt.hms.config.AppVirtualThreadPool;
import ir.mostafa.semnani.vt.hms.dto.response.GetAllRoomsResponseDTO;
import ir.mostafa.semnani.vt.hms.mapper.RoomMapper;
import ir.mostafa.semnani.vt.hms.repository.RoomRepository;
import ir.mostafa.semnani.vt.hms.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Semaphore;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    private final RoomMapper roomMapper;

    @Override
    public List<GetAllRoomsResponseDTO> getAll() {
        try {

            var responseFuture = AppVirtualThreadPool.getExecutorService().submit(() -> {

                Semaphore semaphore = new Semaphore(8);

                try {

                    log.info("Getting all rooms in RoomServiceImpl");

                    semaphore.acquire();

                    var rooms = roomRepository.findAll()
                            .stream()
                            .map(roomMapper::toGetAllRoomsResponseDTO)
                            .toList();

                    log.info(" {} Rooms found in getAll in RoomServiceImpl", rooms.size());

                    return rooms;

                } catch (Exception e) {
                    log.error("error in getAll in RoomServiceImpl in vt : ", e);
                    throw new RuntimeException("internal server error in vt in getAll in RoomServiceImpl");

                } finally {
                    semaphore.release();
                }

            });

            return responseFuture.get();

        } catch (Exception e) {
            log.error("error in getAll in RoomServiceImpl : ", e);
            throw new RuntimeException("internal server error");
        }
    }

}
